package com.model;

import dao.Column;
import dao.GenericDAO;
import dao.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.connection.Connexion;

@Table(name = "Avion")
public class Avion {
    @Column(name = "id")
    private String id;

    @Column(name = "model")
    private String model;

    @Column(name = "nombre_siege_business")
    private int nombreSiegeBusiness;

    @Column(name = "nombre_siege_eco")
    private int nombreSiegeEco;

    @Column(name = "date_fabrication")
    private Date dateFabrication;

    // Constructeurs
    public Avion() {}

    public Avion(String id, String model, int nombreSiegeBusiness, int nombreSiegeEco, Date dateFabrication) {
        this.id = id;
        this.model = model;
        this.nombreSiegeBusiness = nombreSiegeBusiness;
        this.nombreSiegeEco = nombreSiegeEco;
        this.dateFabrication = dateFabrication;
    }

    public Avion(String model, int nombreSiegeBusiness, int nombreSiegeEco, Date dateFabrication) {
        this.model = model;
        this.nombreSiegeBusiness = nombreSiegeBusiness;
        this.nombreSiegeEco = nombreSiegeEco;
        this.dateFabrication = dateFabrication;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNombreSiegeBusiness() {
        return nombreSiegeBusiness;
    }

    public void setNombreSiegeBusiness(int nombreSiegeBusiness) {
        this.nombreSiegeBusiness = nombreSiegeBusiness;
    }

    public int getNombreSiegeEco() {
        return nombreSiegeEco;
    }

    public void setNombreSiegeEco(int nombreSiegeEco) {
        this.nombreSiegeEco = nombreSiegeEco;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    // Méthode pour récupérer tous les avions
    public static List<Avion> getAllAvion() {
        List<Avion> listAvion = new ArrayList<>();
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAll(new Avion());
            for (Object obj : listObj) {
                if (obj instanceof Avion) {
                    listAvion.add((Avion) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAvion;
    }

    // Méthode pour insérer un avion
    public static boolean insert(Avion avion) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.saveWithSequence(avion, "", "generate_avion_id()");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour mettre à jour un avion
    public static boolean update(Avion avion) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.update(avion, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour supprimer un avion
    public static boolean delete(Avion avion) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.delete(avion, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour trouver un avion par son ID
    public static Avion findById(String id) {
        String avionCriteria = "id = '" + id + "'";
        Avion avion = null;
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new Avion(), avionCriteria);
            if (!listObj.isEmpty() && listObj.get(0) instanceof Avion) {
                avion = (Avion) listObj.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avion;
    }
}