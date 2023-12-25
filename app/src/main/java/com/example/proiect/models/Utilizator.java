package com.example.proiect.models;

public class Utilizator {
    private Long id;
    private String nume;
    private String username;
    private String parola;
    private boolean esteAdmin;

    public Utilizator(Long id, String nume, String username, String parola, boolean esteAdmin) {
        this.id = id;
        this.nume = nume;
        this.username = username;
        this.parola = parola;
        this.esteAdmin = esteAdmin;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public boolean isEsteAdmin() {
        return esteAdmin;
    }

    public void setEsteAdmin(boolean esteAdmin) {
        this.esteAdmin = esteAdmin;
    }

    public boolean verificaParola(String parolaIntrodusa) {
        return this.parola.equals(parolaIntrodusa);
    }
}