package com.model;

import dao.Column;
import dao.GenericDAO;
import dao.Table;
import java.util.ArrayList;
import java.util.List;
import com.connection.Connexion;

@Table(name = "TypeSiege")
public class TypeSiege {
    @Column(name = "id")
    private String id;

    @Column(name = "type")
    private String type;

    // Constructeurs
    public TypeSiege() {}

    public TypeSiege(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public TypeSiege(String type) {
        this.type = type;
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    // Méthode pour récupérer tous les types de sièges
    public static List<TypeSiege> getAllTypesSiege() {
        List<TypeSiege> listTypes = new ArrayList<>();
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAll(new TypeSiege());
            for (Object obj : listObj) {
                if (obj instanceof TypeSiege) {
                    listTypes.add((TypeSiege) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTypes;
    }

    // Méthode pour insérer un type de siège
    public static boolean insert(TypeSiege typeSiege) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.saveWithSequence(typeSiege, "", "generate_type_siege_id()");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour mettre à jour un type de siège
    public static boolean update(TypeSiege typeSiege) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.update(typeSiege, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour supprimer un type de siège
    public static boolean delete(TypeSiege typeSiege) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.delete(typeSiege, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour trouver un type de siège par son ID
    public static TypeSiege findById(String id) {
        String criteria = "id = '" + id + "'";
        TypeSiege typeSiege = null;
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new TypeSiege(), criteria);
            if (!listObj.isEmpty() && listObj.get(0) instanceof TypeSiege) {
                typeSiege = (TypeSiege) listObj.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typeSiege;
    }
}
