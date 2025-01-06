package com.lengueCode.service;

import com.lengueCode.dao.Comptedao;
import com.lengueCode.dao.Transactiondao;
import com.lengueCode.model.Compte;
import com.lengueCode.model.Transactions;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TransactionService {
    private Comptedao comptedao ; //pour acceder aux donnees des comptes
    private Transactiondao transactiondao; //pour enregistrer les transactions

    public TransactionService(Comptedao comptedao, Transactiondao transactiondao) {
        this.comptedao = comptedao;
        this.transactiondao = transactiondao;
    }

    public void transfer(int compteSourceId, int compteDestinationId, double montant) throws SQLException{
        //recuperer les comptes sources et destination
        Compte compteSource = comptedao.getCompteById(compteSourceId);
        Compte compteDestination = comptedao.getCompteById(compteDestinationId);


        //verifier si le solde est suffisant
        if (compteSource.getSolde() < montant){
            System.out.println("Le solde du compte source est insuffisant  "+ compteSource.getSolde()+ " Montant demander : " + montant);
            return;
        }

        //mettre a jour les comptes
        compteSource.setSolde(compteSource.getSolde() - montant);
        compteDestination.setSolde(compteDestination.getSolde() + montant);

        //sauvegarder les comptes mis a jour
        comptedao.sauvegarderCompte(compteSource);
        comptedao.sauvegarderCompte(compteDestination);

        //enregistrer la transaction
        Transactions transactions = new Transactions();
        transactions.setTransactionId("1");
        transactions.setCompteSourceId(compteSourceId);
        transactions.setCompteDestinationId(compteDestinationId);
        transactions.setMontant(montant);
        transactions.setDateTransaction(LocalDate.now());

        transactiondao.sauvegarderTransaction(transactions);

    }


    public List<Transactions> getTransactionHistory(int num_compte) throws SQLException {
        return comptedao.getTransactionByCompteId(num_compte);
    }
}
