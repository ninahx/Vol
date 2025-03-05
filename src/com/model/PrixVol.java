package com.model;

import java.math.BigDecimal;

public class PrixVol {
    private Long id;
    private BigDecimal valeur;
    private Vol vol;
    private TypeSiege typeSiege;
    public PrixVol(Long id, BigDecimal valeur, Vol vol, TypeSiege typeSiege) {
        this.id = id;
        this.valeur = valeur;
        this.vol = vol;
        this.typeSiege = typeSiege;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getValeur() {
        return valeur;
    }
    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }
    public Vol getVol() {
        return vol;
    }
    public void setVol(Vol vol) {
        this.vol = vol;
    }
    public TypeSiege getTypeSiege() {
        return typeSiege;
    }
    public void setTypeSiege(TypeSiege typeSiege) {
        this.typeSiege = typeSiege;
    }
}