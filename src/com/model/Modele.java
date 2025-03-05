package com.model;


public class Modele {
    private Long id;
    private String nomModele;
    private String type;
    public Modele(Long id, String nomModele, String type) {
        this.id = id;
        this.nomModele = nomModele;
        this.type = type;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomModele() {
        return nomModele;
    }
    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}















