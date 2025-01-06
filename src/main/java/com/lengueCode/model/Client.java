package com.lengueCode.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String client_id;
    private String nom;
    private String prenom;
    private String adresse;
    private double num_tel;
    private List<Compte> comptes;  // association one-to-many

    public Client() {
    }

    public Client(String client_id, String nom, String prenom, String adresse, double num_tel) {
        this.client_id = client_id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.comptes = new ArrayList<>();
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(double num_tel) {
        this.num_tel = num_tel;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
