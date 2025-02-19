package com.model;

public class Utilisateur {
    private int idUtilisateur;
    private String nomUtilisateur;
    private String adresse;
    private int age;
    private String choix;
    private String motDePasse;

    public Utilisateur() {}

    public Utilisateur(int idUtilisateur, String nomUtilisateur, String adresse, int age, String choix, String motDePasse) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.adresse = adresse;
        this.age = age;
        this.choix = choix;
        this.motDePasse = motDePasse;
    }

    // Getters et Setters
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
