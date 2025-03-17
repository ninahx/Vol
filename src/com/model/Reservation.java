package com.model;

import dao.Column;
import dao.GenericDAO;
import dao.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.connection.Connexion;

@Table(name = "Reservation")
public class Reservation {
    @Column(name = "id")
    private String id;

    @Column(name = "vol_id")
    private String volId;

    @Column(name = "client_nom")
    private String clientNom;

    @Column(name = "client_prenom")
    private String clientPrenom;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "type_siege_id")
    private String typeSiegeId;

    @Column(name = "nombre_siege")
    private int nombreSiege;

    @Column(name = "date_reservation")
    private Date dateReservation;

    @Column(name = "fichier")
    private byte[] fichier; // Nouvel attribut pour stocker le fichier

    // Constructeurs
    public Reservation() {}

    public Reservation(String id, String volId, String clientNom, String clientPrenom, String clientEmail, String typeSiegeId, int nombreSiege, Date dateReservation, byte[] fichier) {
        this.id = id;
        this.volId = volId;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;
        this.clientEmail = clientEmail;
        this.typeSiegeId = typeSiegeId;
        this.nombreSiege = nombreSiege;
        this.dateReservation = dateReservation;
        this.fichier = fichier;
    }

    public Reservation(String volId, String clientNom, String clientPrenom, String clientEmail, String typeSiegeId, int nombreSiege, Date dateReservation) {
        this.volId = volId;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;
        this.clientEmail = clientEmail;
        this.typeSiegeId = typeSiegeId;
        this.nombreSiege = nombreSiege;
        this.dateReservation = dateReservation;
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getVolId() { return volId; }
    public void setVolId(String volId) { this.volId = volId; }

    public String getClientNom() { return clientNom; }
    public void setClientNom(String clientNom) { this.clientNom = clientNom; }

    public String getClientPrenom() { return clientPrenom; }
    public void setClientPrenom(String clientPrenom) { this.clientPrenom = clientPrenom; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public String getTypeSiegeId() { return typeSiegeId; }
    public void setTypeSiegeId(String typeSiegeId) { this.typeSiegeId = typeSiegeId; }

    public int getNombreSiege() { return nombreSiege; }
    public void setNombreSiege(int nombreSiege) { this.nombreSiege = nombreSiege; }

    public Date getDateReservation() { return dateReservation; }
    public void setDateReservation(Date dateReservation) { this.dateReservation = dateReservation; }

    public byte[] getFichier() { return fichier; } // Getter pour le fichier
    public void setFichier(byte[] fichier) { this.fichier = fichier; } // Setter pour le fichier

    // Méthodes CRUD (inchangées)
    public static List<Reservation> getAllReservations() {
        List<Reservation> listReservations = new ArrayList<>();
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAll(new Reservation());
            for (Object obj : listObj) {
                if (obj instanceof Reservation) {
                    listReservations.add((Reservation) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listReservations;
    }

    public static boolean insert(Reservation reservation) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.saveWithSequence(reservation, "", "generate_reservation_id()");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Reservation reservation) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.update(reservation, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(Reservation reservation) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.delete(reservation, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Reservation findById(String id) {
        String criteria = "id = '" + id + "'";
        Reservation reservation = null;
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new Reservation(), criteria);
            if (!listObj.isEmpty() && listObj.get(0) instanceof Reservation) {
                reservation = (Reservation) listObj.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }

    public static List<Reservation> findByTypeSiegeId(String idTypeSiege) {
        String criteria = "type_siege_id = '" + idTypeSiege + "'";
        List<Reservation> listReservations = new ArrayList<>();
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new Reservation(), criteria);
            for (Object obj : listObj) {
                if (obj instanceof Reservation) {
                    listReservations.add((Reservation) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listReservations;
    }
}