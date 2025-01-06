package com.lengueCode.mainApp;

import com.lengueCode.model.TypeCompte;
import com.lengueCode.service.TransactionService;
import com.lengueCode.dao.Clientdao;
import com.lengueCode.dao.Comptedao;
import com.lengueCode.dao.Transactiondao;
import com.lengueCode.model.Client;
import com.lengueCode.model.Compte;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        Clientdao clientdao = new Clientdao();
        Comptedao comptedao = new Comptedao();
        Transactiondao transactiondao = new Transactiondao();
        TransactionService transactionService = new TransactionService(comptedao, transactiondao);

        Client client1 = new Client("C001", "Hassan", "salif", "Etoudi", 657882477);
        Client client2 = new Client("C002", "Mabah", "Elise", "Nkoabang", 698008010);
        Compte compte1 = new Compte(1, TypeCompte.COURANT, "actif", 1230000, LocalDate.now(), "C001");
        Compte compte2 = new Compte(2, TypeCompte.EPARGNE, "actif", 10000000, LocalDate.now(), "C002");
        Compte compte3 = new Compte(3, TypeCompte.COURANT, "actif", 45000, LocalDate.now(), "C002");
        Compte compte4 = new Compte(4, TypeCompte.EPARGNE, "actif", 320500000, LocalDate.now(), "C001");

        //clientdao.sauvegarderClient(client2);
        //comptedao.sauvegarderCompte(compte3);
        //comptedao.sauvegarderCompte(compte4);
        //comptedao.getCompteById(1);

        Client client = clientdao.getClientById("C002");
        List<Compte> comptes = clientdao.getComptesByClientId("C002");
        System.out.println(client+" Comptes associes : "+comptes);


/*
        System.out.println("Tentative de transfer....");
        transactionService.transfer(1,6, 10000);*/

    }
}
