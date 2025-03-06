package com.controller;

import com.model.Vol;
import main.modelView.ModelView;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;
import main.auth.Auth;
import com.model.VilleDeservie;
import main.controller.Controller;
import main.controller.POST;
import main.controller.Param;
import main.controller.*;


@Controller
public class VolController {
    @Auth(role="ADMIN")
    @URLS("/addVol")
    public ModelView vol() {
        ModelView modelView = new ModelView();
        List<VilleDeservie> listVilleDeservies = VilleDeservie.getAllVilles();
        modelView.add("listVille", listVilleDeservies);
        modelView.setUrl("/vol/ajouVol.jsp");
        return modelView;
    }
    
    @URLS("/vols")
    public ModelView getAllVols() {
        ModelView mv = new ModelView();
        List<Vol> vols = Vol.getAllVols();
        mv.add("vols", vols);
        mv.setUrl("/vol/list.jsp");
        return mv;
    }
    
    @Auth(role="ADMIN")
    @URLS("/vol/add")
    @POST
    @OnValidationError("/addVol")
    public ModelView addVol(
//            @Param("villeDepartId") String villeDepartId,
//                            @Param("villeArriveeId") String villeArriveeId,
//                            @Param("dateDepart") String dateDepartStr,
//                            @Param("dateArrivee") String dateArriveeStr,
                            @ParamObject Vol vol) {
        ModelView mv = new ModelView();

        // Conversion des chaînes de caractères en java.sql.Date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date dateDepart = new Date(sdf.parse(dateDepartStr).getTime());
//            Date dateArrivee = new Date(sdf.parse(dateArriveeStr).getTime());

            // Créer l'objet Vol avec les dates converties
//            Vol vol = new Vol(villeDepartId, villeArriveeId, dateDepart, dateArrivee);
            boolean success = Vol.insert(vol);

            if(success){
                List<VilleDeservie> listVilleDeservies = VilleDeservie.getAllVilles();
                mv.add("listVille", listVilleDeservies);
                mv.add("success", "Vol ajouté avec succès, vous pouvez maintenant le configurer.");
                mv.setUrl("/vols");
                return mv;
            }
            mv.add("error", "Erreur lors de l'ajout du vol!");
            mv.setUrl("/vol/add.jsp");
        } catch (Exception e) {
            mv.add("error", "Erreur lors de l'ajout du vol : " + e.getMessage());
            mv.setUrl("/vol/add.jsp");
        }
        return mv;
    }
    
    @Auth(role="ADMIN")
    @URLS("/vol/edit")
    public ModelView editVol(@Param("id") String id) {
        ModelView mv = new ModelView();

        // Récupérer le vol par son id
        Vol vol = Vol.findById(id);

        if (vol != null) {
            List<VilleDeservie> listVilleDeservies = VilleDeservie.getAllVilles();
            mv.add("vol", vol);
            mv.add("listVille", listVilleDeservies);
            mv.setUrl("/vol/editVol.jsp"); // Rediriger vers la page de modification
        } else {
            mv.add("error", "Vol non trouvé.");
            mv.setUrl("/vol/list.jsp"); // Rediriger vers la liste si le vol n'est pas trouvé
        }

        return mv;
    }
    
    @Auth(role="ADMIN")
    @URLS("/vol/update")
    @POST
    public ModelView updateVol(@Param("id") String id,
                               @Param("villeDepartId") String villeDepartId,
                               @Param("villeArriveeId") String villeArriveeId,
                               @Param("dateDepart") String dateDepartStr,
                               @Param("dateArrivee") String dateArriveeStr) {
        ModelView mv = new ModelView();

        try {
            // Conversion des dates
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDepart = new Date(sdf.parse(dateDepartStr).getTime());
            Date dateArrivee = new Date(sdf.parse(dateArriveeStr).getTime());

            // Création de l'objet Vol mis à jour
            Vol vol = new Vol(id, villeDepartId, villeArriveeId, dateDepart, dateArrivee);

            boolean success = Vol.update(vol);

            if (success) {
                mv.add("success", "Le vol a été mis à jour avec succès.");
                List<Vol> vols = Vol.getAllVols();
                mv.add("vols", vols);
                mv.setUrl("/vol/list.jsp");
            } else {
                mv.add("error", "Erreur lors de la mise à jour du vol.");
                mv.setUrl("/vol/editVol.jsp?id=" + id);
            }
        } catch (Exception e) {
            mv.add("error", "Erreur lors de la mise à jour du vol : " + e.getMessage());
            mv.setUrl("/vol/editVol.jsp?id=" + id);
        }

        return mv;
    }

    
    @Auth(role="ADMIN")
    @URLS("/vol/delete")
    @GET
    public ModelView deleteVol(@Param("id") String id) {
        ModelView mv = new ModelView();
        Vol vol = Vol.findById(id);
        if (vol != null) {
            boolean success = Vol.delete(vol);
            if (success) {
                mv.add("success", "Le vol a été supprimé avec succès.");
            } else {
                mv.add("error", "Erreur lors de la suppression du vol.");
            }
        } else {
            mv.add("error", "Vol introuvable.");
        }
        List<Vol> vols = Vol.getAllVols();
        mv.add("vols", vols);
        mv.setUrl("/vol/list.jsp");
        return mv;
    }
    
    @Auth(role="ADMIN")
    @URLS("/vol/promotions")
    public ModelView volPromotions() {
        ModelView mv = new ModelView();
        List<Vol> vols = Vol.getAllVols();
        mv.add("vols", vols);
        mv.setUrl("/vol/promotions.jsp");
        return mv;
    }

    @Auth(role="ADMIN")
    @URLS("/vol/updatePromotion")
    @POST
    public ModelView updatePromotion(@Param("id") String id,
                                    @Param("nombreSiegesBusinessPromo") Integer nombreSiegesBusinessPromo,
                                    @Param("nombreSiegesEcoPromo") Integer nombreSiegesEcoPromo) {
        ModelView mv = new ModelView();

        try {
            // Récupérer le vol existant
            Vol vol = Vol.findById(id);
            if (vol != null) {
                // Mettre à jour les informations de promotion
                vol.setNombreSiegesBusinessPromo(nombreSiegesBusinessPromo);
                vol.setNombreSiegesEcoPromo(nombreSiegesEcoPromo);

                boolean success = Vol.update(vol);

                if (success) {
                    mv.add("success", "Les sièges promotionnels ont été mis à jour avec succès.");
                } else {
                    mv.add("error", "Erreur lors de la mise à jour des sièges promotionnels.");
                }
            } else {
                mv.add("error", "Vol introuvable.");
            }

            // Récupérer la liste mise à jour des vols
            List<Vol> vols = Vol.getAllVols();
            mv.add("vols", vols);
            mv.setUrl("/vol/promotions.jsp");

        } catch (Exception e) {
            mv.add("error", "Erreur lors de la mise à jour des sièges promotionnels : " + e.getMessage());
            mv.setUrl("/vol/promotions.jsp");
        }

        return mv;
    }
    
    @Auth(role="ADMIN")
    @URLS("/vol/configReservation")
    public ModelView configurationReservation() {
        ModelView mv = new ModelView();
        List<Vol> vols = Vol.getAllVols();
        mv.add("vols", vols);
        mv.setUrl("/vol/configReservation.jsp");
        return mv;
    }

    @Auth(role="ADMIN")
    @URLS("/vol/updateHeureReservation")
    @POST
    public ModelView updateHeureReservation(@Param("id") String id,
                                          @Param("heureReservationAvantVol") Integer heureReservationAvantVol) {
        ModelView mv = new ModelView();

        try {
            Vol vol = Vol.findById(id);
            if (vol != null) {
                vol.setHeureReservationAvantVol(heureReservationAvantVol);
                boolean success = Vol.update(vol);

                if (success) {
                    mv.add("success", "Le délai de réservation a été mis à jour avec succès.");
                } else {
                    mv.add("error", "Erreur lors de la mise à jour du délai de réservation.");
                }
            } else {
                mv.add("error", "Vol introuvable.");
            }

            List<Vol> vols = Vol.getAllVols();
            mv.add("vols", vols);
            mv.setUrl("/vol/configReservation.jsp");

        } catch (Exception e) {
            mv.add("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mv.setUrl("/vol/configReservation.jsp");
        }

        return mv;
    }

    @Auth(role="ADMIN")
    @URLS("/vol/configAnnulation")
    public ModelView configurationAnnulation() {
        ModelView mv = new ModelView();
        List<Vol> vols = Vol.getAllVols();
        mv.add("vols", vols);
        mv.setUrl("/vol/configAnnulation.jsp");
        return mv;
    }

    @Auth(role="ADMIN")
    @URLS("/vol/updateHeureAnnulation")
    @POST
    public ModelView updateHeureAnnulation(@Param("id") String id,
                                         @Param("heureAnnulationAvantVol") Integer heureAnnulationAvantVol) {
        ModelView mv = new ModelView();

        try {
            Vol vol = Vol.findById(id);
            if (vol != null) {
                vol.setHeureAnnulationAvantVol(heureAnnulationAvantVol);
                boolean success = Vol.update(vol);

                if (success) {
                    mv.add("success", "Le délai d'annulation a été mis à jour avec succès.");
                } else {
                    mv.add("error", "Erreur lors de la mise à jour du délai d'annulation.");
                }
            } else {
                mv.add("error", "Vol introuvable.");
            }

            List<Vol> vols = Vol.getAllVols();
            mv.add("vols", vols);
            mv.setUrl("/vol/configAnnulation.jsp");

        } catch (Exception e) {
            mv.add("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mv.setUrl("/vol/configAnnulation.jsp");
        }

        return mv;
    }
    
    @URLS("/vol/search")
    public ModelView searchVols(@Param("villeDepartId") String villeDepartId,
                               @Param("villeArriveeId") String villeArriveeId,
                               @Param("dateDepart") String dateDepartStr,
                               @Param("dateArrivee") String dateArriveeStr,
                               @Param("nombreSiegesBusinessPromo") String nombreSiegesBusinessPromoStr,
                               @Param("nombreSiegesEcoPromo") String nombreSiegesEcoPromoStr) {
        ModelView mv = new ModelView();

        try {
            // Conversion des dates si elles sont fournies
            Date dateDepart = null;
            Date dateArrivee = null;
            if (dateDepartStr != null && !dateDepartStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateDepart = new Date(sdf.parse(dateDepartStr).getTime());
            }
            if (dateArriveeStr != null && !dateArriveeStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateArrivee = new Date(sdf.parse(dateArriveeStr).getTime());
            }

            // Conversion des nombres si elles sont fournies
            Integer nombreSiegesBusinessPromo = null;
            Integer nombreSiegesEcoPromo = null;
            if (nombreSiegesBusinessPromoStr != null && !nombreSiegesBusinessPromoStr.isEmpty()) {
                nombreSiegesBusinessPromo = Integer.parseInt(nombreSiegesBusinessPromoStr);
            }
            if (nombreSiegesEcoPromoStr != null && !nombreSiegesEcoPromoStr.isEmpty()) {
                nombreSiegesEcoPromo = Integer.parseInt(nombreSiegesEcoPromoStr);
            }

            // Effectuer la recherche multicritère
            List<Vol> vols = Vol.searchVols(villeDepartId, villeArriveeId, dateDepart, dateArrivee, nombreSiegesBusinessPromo, nombreSiegesEcoPromo);

            // Ajouter les résultats à la ModelView
            mv.add("vols", vols);
            mv.setUrl("/vol/list.jsp");

        } catch (NumberFormatException e) {
            mv.add("error", "Erreur de format numérique : " + e.getMessage());
            mv.setUrl("/vol/list.jsp");
        } catch (Exception e) {
            mv.add("error", "Erreur lors de la recherche : " + e.getMessage());
            mv.setUrl("/vol/list.jsp");
        }

        return mv;
    }
}
