package com.model;

import dao.Column;
import dao.GenericDAO;
import dao.Table;
import java.util.ArrayList;
import java.util.List;
import com.connection.Connexion;

@Table(name = "Vol_Avion")
public class VolAvion {
    @Column(name = "id")
    private int id;

    @Column(name = "vol_id")
    private String volId;

    @Column(name = "avion_id")
    private String avionId;

    // Constructeurs
    public VolAvion() {}

    public VolAvion(String volId, String avionId) {
        this.volId = volId;
        this.avionId = avionId;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getVolId() { return volId; }
    public void setVolId(String volId) { this.volId = volId; }

    public String getAvionId() { return avionId; }
    public void setAvionId(String avionId) { this.avionId = avionId; }

    // Méthodes CRUD
    public static List<VolAvion> getAllVolAvions() {
        List<VolAvion> list = new ArrayList<>();
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAll(new VolAvion());
            for (Object obj : listObj) {
                if (obj instanceof VolAvion) {
                    list.add((VolAvion) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insert(VolAvion volAvion) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.save(volAvion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(VolAvion volAvion) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.delete(volAvion, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Méthode pour récupérer tous les avions associés à un vol
    public static List<Avion> getAvionsByVolId(String volId) {
        List<Avion> avions = new ArrayList<>();
        try {
            // Récupérer toutes les liaisons VolAvion correspondant au vol
            String volAvionCriteria = "vol_id = '" + volId +"'";
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> ObjectVolAvions = dao.findAllWithCriteria(new VolAvion(), volAvionCriteria);
            List<VolAvion> volAvions = new ArrayList<>();
            for (Object ObjectVolAvion : ObjectVolAvions) {
                if (ObjectVolAvion instanceof VolAvion) {
                    volAvions.add((VolAvion) ObjectVolAvion);
                }
            }

            if (volAvions != null && !volAvions.isEmpty()) {
                // Pour chaque liaison, récupérer l'avion associé
                for (VolAvion volAvion : volAvions) {
                    Avion avion = Avion.findById(volAvion.getAvionId());
                    if (avion != null) {
                        avions.add(avion);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avions;
    }
}
