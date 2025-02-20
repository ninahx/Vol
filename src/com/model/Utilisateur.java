package com.model;

public class Utilisateur {
    private int id_utilisateur;
    private String email;
    private String motDePasse;
    private Role role;

    // Constructeurs
    public Utilisateur() {}

    public Utilisateur(int id_utilisateur, String email, String motDePasse, Role role) {
        this.id_utilisateur = id_utilisateur;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et Setters
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
