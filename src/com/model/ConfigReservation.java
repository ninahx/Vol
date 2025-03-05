package com.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class ConfigReservation {
    private Long id;
    private Duration heureValiditeAvantVol;
    private Duration heureMargeAnnulation;
    private LocalDateTime dateInsertion;
    
    public ConfigReservation(Long id, Duration heureValiditeAvantVol, Duration heureMargeAnnulation,
            LocalDateTime dateInsertion) {
        this.id = id;
        this.heureValiditeAvantVol = heureValiditeAvantVol;
        this.heureMargeAnnulation = heureMargeAnnulation;
        this.dateInsertion = dateInsertion;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Duration getHeureValiditeAvantVol() {
        return heureValiditeAvantVol;
    }
    public void setHeureValiditeAvantVol(Duration heureValiditeAvantVol) {
        this.heureValiditeAvantVol = heureValiditeAvantVol;
    }
    public Duration getHeureMargeAnnulation() {
        return heureMargeAnnulation;
    }
    public void setHeureMargeAnnulation(Duration heureMargeAnnulation) {
        this.heureMargeAnnulation = heureMargeAnnulation;
    }
    public LocalDateTime getDateInsertion() {
        return dateInsertion;
    }
    public void setDateInsertion(LocalDateTime dateInsertion) {
        this.dateInsertion = dateInsertion;
    }
}