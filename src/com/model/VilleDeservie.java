package com.model;

import dao.Column;
import dao.GenericDAO;
import dao.Table;
import java.util.ArrayList;
import java.util.List;
import com.connection.Connexion;

@Table(name = "VilleDeservie")
public class VilleDeservie {
    @Column(name = "id")
    private String id;

    @Column(name = "nom_ville")
    private String nomVille;

    @Column(name = "code_ville")
    private String codeVille;

    // Constructeurs
    public VilleDeservie() {}

    public VilleDeservie(String id, String nomVille, String codeVille) {
        this.id = id;
        this.nomVille = nomVille;
        this.codeVille = codeVille;
    }

    public VilleDeservie(String nomVille, String codeVille) {
        this.nomVille = nomVille;
        this.codeVille = codeVille;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(String codeVille) {
        this.codeVille = codeVille;
    }

    // Méthode pour récupérer toutes les villes desservies
    public static List<VilleDeservie> getAllVilles() {
        List<VilleDeservie> listVilles = new ArrayList<>();
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAll(new VilleDeservie());
            for (Object obj : listObj) {
                if (obj instanceof VilleDeservie) {
                    listVilles.add((VilleDeservie) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVilles;
    }

    // Méthode pour insérer une ville desservie
    public static boolean insert(VilleDeservie ville) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.saveWithSequence(ville, "", "generate_ville_deservie_id()");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour mettre à jour une ville desservie
    public static boolean update(VilleDeservie ville) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.update(ville, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour supprimer une ville desservie
    public static boolean delete(VilleDeservie ville) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.delete(ville, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour trouver une ville par son ID
    public static VilleDeservie findById(String id) {
        String villeCriteria = "id = '" + id + "'";
        VilleDeservie ville = null;
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new VilleDeservie(), villeCriteria);
            if (!listObj.isEmpty() && listObj.get(0) instanceof VilleDeservie) {
                ville = (VilleDeservie) listObj.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ville;
    }
}
