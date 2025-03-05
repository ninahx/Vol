package com.model;

import java.time.LocalDateTime;

public class HistoriqueEtatReservation {
    private Long id;
    private Reservation reservation;
    private LocalDateTime dateModification;
    private String description;
    public HistoriqueEtatReservation(Long id, Reservation reservation, LocalDateTime dateModification,
            String description) {
        this.id = id;
        this.reservation = reservation;
        this.dateModification = dateModification;
        this.description = description;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public LocalDateTime getDateModification() {
        return dateModification;
    }
    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

