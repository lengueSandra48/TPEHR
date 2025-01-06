package com.lengueCode.dao;

import com.lengueCode.mainApp.DataBaseConnection;
import com.lengueCode.model.Client;
import com.lengueCode.model.Compte;
import com.lengueCode.model.TypeCompte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Clientdao {

    public void sauvegarderClient(Client client) {
        try {
            Connection connection = DataBaseConnection.getConnection();

            // verifions si le client existe dans la base de donnees.
            PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM client WHERE client_id = ?");
            checkStatement.setString(1, client.getClient_id());
            ResultSet rs = checkStatement.executeQuery();

            rs.next();
            boolean clientExists = rs.getInt(1) > 0;

            PreparedStatement statement;
            if (clientExists) {
                statement = connection.prepareStatement(
                        "UPDATE client SET nom = ? WHERE client_id = ?");
                statement.setString(1, client.getNom());
                statement.setString(2, client.getClient_id());
            } else {
                statement = connection.prepareStatement(
                        "INSERT INTO client(client_id, nom, prenom, adresse, num_tel) VALUES (?, ?, ?, ?, ?)");
                statement.setString(1, client.getClient_id());
                statement.setString(2, client.getNom());
                statement.setString(3, client.getPrenom());
                statement.setString(4, client.getAdresse());
                statement.setDouble(5, client.getNum_tel());
            }

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(client.getNom() + " est enregistré dans la base de données.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible d'enregistrer le client");
        }
    }

    public Client getClientById(String client_id){
        Client client = null;
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM client WHERE client_id = ?");
            statement.setString(1, client_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                client = new Client();

                client.setClient_id(resultSet.getString("client_id"));
                client.setNom((resultSet.getString("nom")));
                client.setPrenom(resultSet.getString("prenom"));
                client.setAdresse(resultSet.getString("adresse"));
                client.setNum_tel(resultSet.getDouble("num_tel"));

                //recuperer les comptes associes au client

                client.setComptes(getComptesByClientId(client_id));

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la recuperation du client");

        }
        return client;
    }

    public List<Compte> getComptesByClientId(String client_id){
        List<Compte> comptes = new ArrayList<>();

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT num_compte, type_compte, status, solde, date_creation_compte FROM compte WHERE client_id = ?");
            statement.setString(1, client_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                int numCompte = resultSet.getInt("num_compte");
                TypeCompte typeCompte = TypeCompte.valueOf(resultSet.getString("type_compte"));
                String status = resultSet.getString("status");
                double solde = resultSet.getDouble("solde");
                java.sql.Date dateCreation = resultSet.getDate("date_creation_compte");

                // Création de l'objet Compte
                Compte compte = new Compte(numCompte, typeCompte, status, solde, dateCreation.toLocalDate(), client_id);
                comptes.add(compte);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return comptes;
    }

    public void supprimerClientById (String client_id){
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM client WHERE client_id = ?");
            statement.setString(1, client_id);
            statement.execute();
            System.out.println("Le client dont l'identifiant est : " + client_id + " a bien ete supprimer de la BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
