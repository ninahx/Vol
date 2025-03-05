package com.model;

public class StatutVol {
    private Long id;
    private String statut;
    public StatutVol(Long id, String statut) {
        this.id = id;
        this.statut = statut;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
}
