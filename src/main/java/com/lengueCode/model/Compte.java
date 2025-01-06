package com.lengueCode.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compte {
    private int num_compte;
    private TypeCompte type_compte;
    private String status;
    private double solde;
    private LocalDate date_creation_compte;
    private String client_id;  //liaison avec un client
    private List<Transactions> transactions;


    public Compte() {
    }

    public Compte(int num_compte, TypeCompte type_compte, String status, double solde, LocalDate date_creation_compte, String client_id) {
        this.num_compte = num_compte;
        this.type_compte = type_compte;
        this.status = status;
        this.solde = solde;
        this.date_creation_compte = date_creation_compte;
        this.client_id = client_id;
        this.transactions = new ArrayList<>();
    }

    public int getNum_compte() {
        return num_compte;
    }

    public void setNum_compte(int num_compte) {
        this.num_compte = num_compte;
    }

    public TypeCompte getType_compte() {
        return type_compte;
    }

    public void setType_compte(TypeCompte type_compte) {
        this.type_compte = type_compte;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public LocalDate getDate_creation_compte() {
        return date_creation_compte;
    }

    public void setDate_creation_compte(LocalDate date_creation_compte) {
        this.date_creation_compte = date_creation_compte;
    }

    public String getClientId() {
        return client_id;
    }

    public void setClientId(String client_id) {
        this.client_id = client_id;
    }

    public List<Transactions> getTransactions() {return transactions;}

    public void setTransactions(List<Transactions> transactions) {this.transactions = transactions;}

    @Override
    public String toString() {
        return "Compte{" +
                "num_compte=" + num_compte +
                ", type_compte=" + type_compte +
                ", status='" + status + '\'' +
                ", solde=" + solde +
                ", date_creation_compte=" + date_creation_compte +
                ", client_id='" + client_id + '\'' +
                '}';
    }
}
