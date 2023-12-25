package com.example.proiect.models;

import java.io.Serializable;
import java.util.List;

public class Destinatie implements Serializable {
    private Long id;
    private String nume;
    private String locatie;
    private String descriere;
    private double pret;

    private List<Recenzie> listaRecenzii;

    public Destinatie(Long id, String nume, String locatie, String descriere, double pret, List<Recenzie> listaRecenzii) {
        this.id = id;
        this.nume = nume;
        this.locatie = locatie;
        this.descriere = descriere;
        this.pret = pret;
        this.listaRecenzii = listaRecenzii;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public List<Recenzie> getListaRecenzii() {
        return listaRecenzii;
    }

    public void setListaRecenzii(List<Recenzie> listaRecenzii) {
        this.listaRecenzii = listaRecenzii;
    }
}