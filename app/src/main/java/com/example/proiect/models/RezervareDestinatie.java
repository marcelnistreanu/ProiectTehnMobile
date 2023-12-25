package com.example.proiect.models;

public class RezervareDestinatie {
    private Rezervare rezervare;
    private Destinatie destinatie;

    public RezervareDestinatie(Rezervare rezervare, Destinatie destinatie) {
        this.rezervare = rezervare;
        this.destinatie = destinatie;
    }

    public Rezervare getRezervare() {
        return rezervare;
    }

    public void setRezervare(Rezervare rezervare) {
        this.rezervare = rezervare;
    }

    public Destinatie getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(Destinatie destinatie) {
        this.destinatie = destinatie;
    }
}
