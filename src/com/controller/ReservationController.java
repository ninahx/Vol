package com.controller;

import com.model.Reservation;
import com.model.Vol;
import com.model.TypeSiege;
import com.model.VilleDeservie;
import main.modelView.ModelView;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;
import main.auth.Auth;
import java.util.Calendar;
import com.model.Avion;
import com.model.VolAvion;

import main.controller.*;


import main.session.MySession;

@Controller
public class ReservationController {
    
    @URLS("/reservations")
    public ModelView getAllReservations() {
        ModelView mv = new ModelView();
        List<Reservation> reservations = Reservation.getAllReservations();
        mv.add("reservations", reservations);
        mv.setUrl("/reservation/list.jsp");
        return mv;
    }
    
    @URLS("/reservation/form")
    public ModelView reservationForm() {
        ModelView mv = new ModelView();
        List<Vol> vols = Vol.getAllVols();
        List<TypeSiege> typesSiege = TypeSiege.getAllTypesSiege();
        
        mv.add("vols", vols);
        mv.add("typesSiege", typesSiege);
        mv.setUrl("/reservation/form.jsp");
        return mv;
    }
    
    @URLS("/reservation/add")
    @POST
    public ModelView addReservation(@Param("volId") String volId,
                                  @Param("clientNom") String clientNom,
                                  @Param("clientPrenom") String clientPrenom,
                                  @Param("clientEmail") String clientEmail,
                                  @Param("typeSiegeId") String typeSiegeId,
                                  @Param("nombreSiege") int nombreSiege,
                                  @Param("dateReservation") String dateReservationStr,
                                  @Param("photo") MultipartFile file) {
        ModelView mv = new ModelView();

        try {
            // Vérifier si le vol existe
            Vol vol = Vol.findById(volId);
            if (vol == null) {
                mv.add("error", "Ce vol n'existe pas.");
                return prepareFormView(mv);
            }

            // Vérifier la disponibilité des sièges
            TypeSiege typeSiege = TypeSiege.findById(typeSiegeId);
            if (!verifierDisponibiliteSieges(vol, typeSiege.getType(), nombreSiege)) {
                mv.add("error", "Il n'y a plus assez de sièges disponibles de ce type pour ce vol.");
                return prepareFormView(mv);
            }

            // Convertir la date de réservation en objet Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateReservation = new Date(dateFormat.parse(dateReservationStr).getTime());

            // Créer l'objet Reservation
            Reservation reservation = new Reservation(
                volId, clientNom, clientPrenom, clientEmail, typeSiegeId, nombreSiege, dateReservation
            );
            
            // Ajouter le fichier photo à la réservation s'il est fourni
            if (file != null && !file.isEmpty()) {
                byte[] fileBytes = file.getBytes(); // Convertir le fichier en tableau de bytes
                reservation.setFichier(fileBytes); // Ajouter le fichier à la réservation
            }

            // Enregistrer la réservation
            boolean success = Reservation.insert(reservation);

            if (success) {
                mv.add("success", "Réservation enregistrée avec succès!");
                mv.setUrl("/reservation/confirmation.jsp");
                mv.add("reservation", reservation);
                return mv;
            } else {
                mv.add("error", "Erreur lors de l'enregistrement de la réservation.");
                return prepareFormView(mv);
            }

        } catch (Exception e) {
            mv.add("error", "Erreur lors de l'enregistrement de la réservation: " + e.getMessage());
            return prepareFormView(mv);
        }
    }

    // Mettre à jour la méthode de vérification de disponibilité
    private boolean verifierDisponibiliteSieges(Vol vol, String typeSiege, int nombreSiegeDemande) {
        // Récupérer tous les avions associés au vol
        List<Avion> avions = VolAvion.getAvionsByVolId(vol.getId());

        if (avions == null || avions.isEmpty()) {
            // Si aucun avion n'est associé au vol, on refuse par défaut
            return false;
        }

        // Calculer la capacité totale des sièges pour le type demandé
        int capaciteTotale = 0;
        for (Avion avion : avions) {
            if (typeSiege.equalsIgnoreCase("Business")) {
                capaciteTotale += avion.getNombreSiegeBusiness();
            } else if (typeSiege.equalsIgnoreCase("Economique")) {
                capaciteTotale += avion.getNombreSiegeEco();
            }
        }

        // Compter le nombre de réservations déjà effectuées pour ce type de siège
        long nombreReservations = compterReservationsParType(vol.getId(), typeSiege.toUpperCase());

        // Vérifier la disponibilité en tenant compte du nombre de sièges demandés
        return (capaciteTotale - nombreReservations) >= nombreSiegeDemande;
    }
    
    private ModelView prepareFormView(ModelView mv) {
        List<Vol> vols = Vol.getAllVols();
        List<TypeSiege> typesSiege = TypeSiege.getAllTypesSiege();
        
        mv.add("vols", vols);
        mv.add("typesSiege", typesSiege);
        mv.setUrl("/reservation/form.jsp");
        return mv;
    }
    
    private long compterReservationsParType(String volId, String typeSiege) {
        // Cette méthode devrait compter le nombre de réservations pour un type de siège donné
        // Pour l'instant, implémentons une version de base
        List<Reservation> reservations = Reservation.findByTypeSiegeId(typeSiege);
        long count = 0;
        
        for (Reservation reservation : reservations) {
            if (reservation.getVolId().equals(volId)) {
                TypeSiege type = TypeSiege.findById(reservation.getTypeSiegeId());
                if (type != null && type.getType().equalsIgnoreCase(typeSiege)) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    @URLS("/reservation/delete")
    public ModelView deleteReservation(@Param("id") String id) {
        ModelView mv = new ModelView();
        
        try {
            Reservation reservation = Reservation.findById(id);
            if (reservation != null) {
                // Vérifier le délai d'annulation
                Vol vol = Vol.findById(reservation.getVolId());
                if (vol != null && !verifierDelaiAnnulation(vol)) {
                    mv.add("error", "Le délai d'annulation pour ce vol est dépassé.");
                } else {
                    boolean success = Reservation.delete(reservation);
                    if (success) {
                        mv.add("success", "Réservation annulée avec succès.");
                    } else {
                        mv.add("error", "Erreur lors de l'annulation de la réservation.");
                    }
                }
            } else {
                mv.add("error", "Réservation introuvable.");
            }
        } catch (Exception e) {
            mv.add("error", "Erreur lors de l'annulation de la réservation: " + e.getMessage());
        }
        
        List<Reservation> reservations = Reservation.getAllReservations();
        mv.add("reservations", reservations);
        mv.setUrl("/reservation/list.jsp");
        return mv;
    }
    
    private boolean verifierDelaiAnnulation(Vol vol) {
        // Vérifier si le délai d'annulation est respecté
        if (vol.getHeureAnnulationAvantVol() != null) {
            Calendar dateDepart = Calendar.getInstance();
            dateDepart.setTime(vol.getDateDepart());
            
            Calendar dateActuelle = Calendar.getInstance();
            
            // Calculer la différence en heures
            long diffMillis = dateDepart.getTimeInMillis() - dateActuelle.getTimeInMillis();
            long diffHeures = diffMillis / (60 * 60 * 1000);
            
            return diffHeures >= vol.getHeureAnnulationAvantVol();
        }
        // Si aucun délai n'est configuré, on accepte l'annulation
        return true;
    }
}