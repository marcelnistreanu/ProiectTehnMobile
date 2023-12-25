package com.example.proiect.models;

import java.time.LocalDate;

public class Rezervare {
    private Long id;
    private LocalDate dataInceput;
    private LocalDate dataSfarsit;
    private int numarPersoane;
    private Destinatie destinatie;
    private Utilizator utilizator;

    public Rezervare(Long id, LocalDate dataInceput, LocalDate dataSfarsit, int numarPersoane, Destinatie destinatie, Utilizator utilizator) {
        this.id = id;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.numarPersoane = numarPersoane;
        this.destinatie = destinatie;
        this.utilizator = utilizator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(LocalDate dataInceput) {
        this.dataInceput = dataInceput;
    }

    public LocalDate getDataSfarsit() {
        return dataSfarsit;
    }

    public void setDataSfarsit(LocalDate dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public int getNumarPersoane() {
        return numarPersoane;
    }

    public void setNumarPersoane(int numarPersoane) {
        this.numarPersoane = numarPersoane;
    }

    public Destinatie getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(Destinatie destinatie) {
        this.destinatie = destinatie;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }
}