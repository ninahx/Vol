package com.model;

public class Role {
    private int id;
    private String nom;
    private int valeur;

    // Constructeurs
    public Role() {}

    public Role(int id, String nom, int valeur) {
        this.id = id;
        this.nom = nom;
        this.valeur = valeur;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
