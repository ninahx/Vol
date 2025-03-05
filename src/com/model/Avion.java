package com.model;
import java.time.LocalDateTime;


public class Avion {
    private Long id;
    private String nomAvion;
    private Modele modele;
    private LocalDateTime dateFabrication;

    
    public Avion(Long id, String nomAvion, Modele modele, LocalDateTime dateFabrication) {
        this.id = id;
        this.nomAvion = nomAvion;
        this.modele = modele;
        this.dateFabrication = dateFabrication;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomAvion() {
        return nomAvion;
    }
    public void setNomAvion(String nomAvion) {
        this.nomAvion = nomAvion;
    }
    public Modele getModele() {
        return modele;
    }
    public void setModele(Modele modele) {
        this.modele = modele;
    }
    public LocalDateTime getDateFabrication() {
        return dateFabrication;
    }
    public void setDateFabrication(LocalDateTime dateFabrication) {
        this.dateFabrication = dateFabrication;
    }
}
