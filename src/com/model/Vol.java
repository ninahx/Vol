package com.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Vol {
    private Long id;
    private Avion avion;
    private Ville villeDepart;
    private Ville villeArrivee;
    private LocalDateTime dateDepart;
    private Duration dureeVol;
    private StatutVol statutVol;
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Avion getAvion() {
        return avion;
    }
    public void setAvion(Avion avion) {
        this.avion = avion;
    }
    public Ville getVilleDepart() {
        return villeDepart;
    }
    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }
    public Ville getVilleArrivee() {
        return villeArrivee;
    }
    public void setVilleArrivee(Ville villeArrivee) {
        this.villeArrivee = villeArrivee;
    }
    public LocalDateTime getDateDepart() {
        return dateDepart;
    }
    public void setDateDepart(LocalDateTime dateDepart) {
        this.dateDepart = dateDepart;
    }
    public Duration getDureeVol() {
        return dureeVol;
    }
    public void setDureeVol(Duration dureeVol) {
        this.dureeVol = dureeVol;
    }
    public StatutVol getStatutVol() {
        return statutVol;
    }
    public void setStatutVol(StatutVol statutVol) {
        this.statutVol = statutVol;
    }
}