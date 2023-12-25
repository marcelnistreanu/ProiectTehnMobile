package com.example.proiect.models;

public class Recenzie {
    private String comentariu;
    private int rating;

    public Recenzie(String comentariu, int rating) {
        this.comentariu = comentariu;
        this.rating = rating;
    }

    public String getComentariu() {
        return comentariu;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
