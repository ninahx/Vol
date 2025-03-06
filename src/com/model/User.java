package com.model;

import dao.Column;
import dao.GenericDAO;
import dao.Table;
import java.util.ArrayList;
import java.util.List;
import com.connection.Connexion;

@Table(name = "Users")
public class User {
    @Column(name = "id")
    private String id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "role")
    private String role;

    // Constructeurs
    public User() {}

    public User(String id, String nom, String prenom, String email, String motDePasse, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public User(String nom, String prenom, String email, String motDePasse, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Méthode pour récupérer tous les utilisateurs
    public static List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAll(new User());
            for (Object obj : listObj) {
                if (obj instanceof User) {
                    listUsers.add((User) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    // Méthode pour insérer un utilisateur
    public static boolean insert(User user) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.saveWithSequence(user, "", "generate_user_id()");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour mettre à jour un utilisateur
    public static boolean update(User user) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.update(user, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour supprimer un utilisateur
    public static boolean delete(User user) {
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            dao.delete(user, "id");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour trouver un utilisateur par son ID
    public static User findById(String id) {
        String userCriteria = "id = '" + id + "'";
        User user = null;
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new User(), userCriteria);
            if (!listObj.isEmpty() && listObj.get(0) instanceof User) {
                user = (User) listObj.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // Méthode pour authentifier un utilisateur
    public static User authenticate(String email, String motDePasse) {
        String criteria = "email = '" + email + "' AND mot_de_passe = '" + motDePasse + "'";
        User user = null;
        try {
            GenericDAO dao = Connexion.getGenericDAO();
            List<Object> listObj = dao.findAllWithCriteria(new User(), criteria);
            if (!listObj.isEmpty() && listObj.get(0) instanceof User) {
                user = (User) listObj.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
