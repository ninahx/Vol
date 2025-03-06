package com.model;

import dao.Column;
import dao.GenericDAO;
import dao.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.connection.Connexion;
import main.controller.NotNull;

@Table(name = "Vol")
public class Vol {
    @Column(name = "id")
    private String id;

    @Column(name = "ville_depart_id")
    private String villeDepartId;

    @Column(name = "ville_arrivee_id")
    private String villeArriveeId;

    @NotNull(message = "La date depart ne peut pas être nul.")
    @Column(name = "date_depart")
    private Date dateDepart;

    @NotNull(message = "Le date arrivée ne peut pas être nul.")
    @Column(name = "date_arrivee")
    private Date dateArrivee;

    @Column(name = "nombre_sieges_business_promo")
    private Integer nombreSiegesBusinessPromo;

    @Column(name = "nombre_sieges_eco_promo")
    private Integer nombreSiegesEcoPromo;

    @Column(name = "heure_reservation_avant_vol")
    private Integer heureReservationAvantVol;

    @Column(name = "heure_annulation_avant_vol")
    private Integer heureAnnulationAvantVol;

    // Constructeurs
    public Vol() {}

    public Vol(String id, String villeDepartId, String villeArriveeId, Date dateDepart, Date dateArrivee,
               Integer nombreSiegesBusinessPromo, Integer nombreSiegesEcoPromo,
               Integer heureReservationAvantVol, Integer heureAnnulationAvantVol) {
        this.id = id;
        this.villeDepartId = villeDepartId;
        this.villeArriveeId = villeArriveeId;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.nombreSiegesBusinessPromo = nombreSiegesBusinessPromo;
        this.nombreSiegesEcoPromo = nombreSiegesEcoPromo;
        this.heureReservationAvantVol = heureReservationAvantVol;
        this.heureAnnulationAvantVol = heureAnnulationAvantVol;
    }

    public Vol(String villeDepartId, String villeArriveeId, Date dateDepart, Date dateArrivee) {
        this.villeDepartId = villeDepartId;
        this.villeArriveeId = villeArriveeId;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }

    public Vol(String id, String villeDepartId, String villeArriveeId, Date dateDepart, Date dateArrivee) {
        this.id = id;
        this.villeDepartId = villeDepartId;
        this.villeArriveeId = villeArriveeId;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }
    

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getVilleDepartId() { return villeDepartId; }
    public void setVilleDepartId(String villeDepartId) { this.villeDepartId = villeDepartId; }

    public String getVilleArriveeId() { return villeArriveeId; }
    public void setVilleArriveeId(String villeArriveeId) { this.villeArriveeId = villeArriveeId; }

    public Date getDateDepart() { return dateDepart; }
    public void setDateDepart(Date dateDepart) { this.dateDepart = dateDepart; }

    public Date getDateArrivee() { return dateArrivee; }
    public void setDateArrivee(Date dateArrivee) { this.dateArrivee = dateArrivee; }

    public Integer getNombreSiegesBusinessPromo() { return nombreSiegesBusinessPromo; }
    public void setNombreSiegesBusinessPromo(Integer nombreSiegesBusinessPromo) { this.nombreSiegesBusinessPromo = nombreSiegesBusinessPromo; }

    public Integer getNombreSiegesEcoPromo() { return nombreSiegesEcoPromo; }
    public void setNombreSiegesEcoPromo(Integer nombreSiegesEcoPromo) { this.nombreSiegesEcoPromo = nombreSiegesEcoPromo; }

    public Integer getHeureReservationAvantVol() { return heureReservationAvantVol; }
    public void setHeureReservationAvantVol(Integer heureReservationAvantVol) { this.heureReservationAvantVol = heureReservationAvantVol; }

    public Integer getHeureAnnulationAvantVol() { return heureAnnulationAvantVol; }
    public void setHeureAnnulationAvantVol(Integer heureAnnulationAvantVol) { this.heureAnnulationAvantVol = heureAnnulationAvantVol; }

    // Méthodes CRUD
    public static List<Vol> getAllVols() {
        List<Vol> listVol = new ArrayList<>();
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAll(new Vol());
            for (Object obj : listObj) {
                if (obj instanceof Vol) {
                    listVol.add((Vol) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVol;
    }

    public static boolean insert(Vol vol) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.saveWithSequence(vol, "", "generate_vol_id()");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Vol vol) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.update(vol, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(Vol vol) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.delete(vol, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Vol findById(String id) {
        String criteria = "id = '" + id + "'";
        Vol vol = null;
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new Vol(), criteria);
            if (!listObj.isEmpty() && listObj.get(0) instanceof Vol) {
                vol = (Vol) listObj.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vol;
    }
    
    public static List<Vol> searchVols(String villeDepartId, String villeArriveeId, Date dateDepart, Date dateArrivee, Integer nombreSiegesBusinessPromo, Integer nombreSiegesEcoPromo) {
        List<Vol> vols = new ArrayList<>();
        StringBuilder criteria = new StringBuilder("1=1"); // Commencez avec une condition toujours vraie

        // Ajouter des critères dynamiques en fonction des paramètres fournis
        if (villeDepartId != null && !villeDepartId.isEmpty()) {
            criteria.append(" AND ville_depart_id = '").append(villeDepartId).append("'");
        }
        if (villeArriveeId != null && !villeArriveeId.isEmpty()) {
            criteria.append(" AND ville_arrivee_id = '").append(villeArriveeId).append("'");
        }
        if (dateDepart != null) {
            criteria.append(" AND date_depart = '").append(new java.sql.Date(dateDepart.getTime())).append("'");
        }
        if (dateArrivee != null) {
            criteria.append(" AND date_arrivee = '").append(new java.sql.Date(dateArrivee.getTime())).append("'");
        }
        if (nombreSiegesBusinessPromo != null) {
            criteria.append(" AND nombre_sieges_business_promo >= ").append(nombreSiegesBusinessPromo);
        }
        if (nombreSiegesEcoPromo != null) {
            criteria.append(" AND nombre_sieges_eco_promo >= ").append(nombreSiegesEcoPromo);
        }

        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new Vol(), criteria.toString());

            // Convertir la liste d'objets en liste de Vols
            for (Object obj : listObj) {
                if (obj instanceof Vol) {
                    vols.add((Vol) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vols;
    }
}
