package com.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private String codeReservation;
    private Vol vol;
    private AvionSiege avionSiege;
    private LocalDateTime dateReservation;
    private BigDecimal prixTotal;
    private StatutVol statutReservation;
    private byte[] fichierPasseport;
    private String numeroPasseport;

    
    public Reservation(Long id, String codeReservation, Vol vol, AvionSiege avionSiege, LocalDateTime dateReservation,
            BigDecimal prixTotal, StatutVol statutReservation, byte[] fichierPasseport, String numeroPasseport) {
        this.id = id;
        this.codeReservation = codeReservation;
        this.vol = vol;
        this.avionSiege = avionSiege;
        this.dateReservation = dateReservation;
        this.prixTotal = prixTotal;
        this.statutReservation = statutReservation;
        this.fichierPasseport = fichierPasseport;
        this.numeroPasseport = numeroPasseport;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCodeReservation() {
        return codeReservation;
    }
    public void setCodeReservation(String codeReservation) {
        this.codeReservation = codeReservation;
    }
    public Vol getVol() {
        return vol;
    }
    public void setVol(Vol vol) {
        this.vol = vol;
    }
    public AvionSiege getAvionSiege() {
        return avionSiege;
    }
    public void setAvionSiege(AvionSiege avionSiege) {
        this.avionSiege = avionSiege;
    }
    public LocalDateTime getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
    public BigDecimal getPrixTotal() {
        return prixTotal;
    }
    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }
    public StatutVol getStatutReservation() {
        return statutReservation;
    }
    public void setStatutReservation(StatutVol statutReservation) {
        this.statutReservation = statutReservation;
    }
    public byte[] getFichierPasseport() {
        return fichierPasseport;
    }
    public void setFichierPasseport(byte[] fichierPasseport) {
        this.fichierPasseport = fichierPasseport;
    }
    public String getNumeroPasseport() {
        return numeroPasseport;
    }
    public void setNumeroPasseport(String numeroPasseport) {
        this.numeroPasseport = numeroPasseport;
    }
}