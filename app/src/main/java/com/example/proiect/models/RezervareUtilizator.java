package com.example.proiect.models;

public class RezervareUtilizator {
    private Utilizator utilizator;
    private Rezervare rezervare;

    public RezervareUtilizator(Utilizator utilizator, Rezervare rezervare) {
        this.utilizator = utilizator;
        this.rezervare = rezervare;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public Rezervare getRezervare() {
        return rezervare;
    }

    public void setRezervare(Rezervare rezervare) {
        this.rezervare = rezervare;
    }
}
