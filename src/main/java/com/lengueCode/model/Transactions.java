package com.lengueCode.model;

import java.time.LocalDate;
import java.util.Date;

public class Transactions {
    private String transactionId;
    private int CompteSourceId; // numero du compte source
    private int CompteDestinationId; //numero du compte de destination
    private double montant;
    private LocalDate dateTransaction;

    public Transactions() {
    }

    public Transactions(String transactionId, int compteSourceId, int compteDestinationId, double montant, LocalDate dateTransaction) {
        this.transactionId = transactionId;
        CompteSourceId = compteSourceId;
        CompteDestinationId = compteDestinationId;
        this.montant = montant;
        this.dateTransaction = dateTransaction;
    }

    public String getTransactionId() {return transactionId;}

    public void setTransactionId(String transactionId) {this.transactionId = transactionId;}

    public int getCompteSourceId() {return CompteSourceId;}

    public void setCompteSourceId(int compteSourceId) {CompteSourceId = compteSourceId;}

    public int getCompteDestinationId() {return CompteDestinationId;}

    public void setCompteDestinationId(int compteDestinationId) {CompteDestinationId = compteDestinationId;}

    public double getMontant() {return montant;}

    public void setMontant(double montant) {this.montant = montant;}

    public LocalDate getDateTransaction() {return dateTransaction;}

    public void setDateTransaction(LocalDate dateTransaction) {this.dateTransaction = dateTransaction;}
}
