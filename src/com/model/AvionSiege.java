package com.model;


public class AvionSiege {
    private Long id;
    private Avion avion;
    private TypeSiege typeSiege;
    private int nbrSiegeDispo;

    
    public AvionSiege(Long id, Avion avion, TypeSiege typeSiege, int nbrSiegeDispo) {
        this.id = id;
        this.avion = avion;
        this.typeSiege = typeSiege;
        this.nbrSiegeDispo = nbrSiegeDispo;
    }
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
    public TypeSiege getTypeSiege() {
        return typeSiege;
    }
    public void setTypeSiege(TypeSiege typeSiege) {
        this.typeSiege = typeSiege;
    }
    public int getNbrSiegeDispo() {
        return nbrSiegeDispo;
    }
    public void setNbrSiegeDispo(int nbrSiegeDispo) {
        this.nbrSiegeDispo = nbrSiegeDispo;
    }
}
