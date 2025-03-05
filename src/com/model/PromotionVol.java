package com.model;

import java.time.LocalDateTime;

public class PromotionVol {
    private Long id;
    private Vol vol;
    private int pourcentageReduction;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private AvionSiege avionSiege;
    public PromotionVol(Long id, Vol vol, int pourcentageReduction, LocalDateTime dateDebut, LocalDateTime dateFin,
            AvionSiege avionSiege) {
        this.id = id;
        this.vol = vol;
        this.pourcentageReduction = pourcentageReduction;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.avionSiege = avionSiege;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Vol getVol() {
        return vol;
    }
    public void setVol(Vol vol) {
        this.vol = vol;
    }
    public int getPourcentageReduction() {
        return pourcentageReduction;
    }
    public void setPourcentageReduction(int pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }
    public LocalDateTime getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }
    public AvionSiege getAvionSiege() {
        return avionSiege;
    }
    public void setAvionSiege(AvionSiege avionSiege) {
        this.avionSiege = avionSiege;
    }
}

