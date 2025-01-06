package com.lengueCode.dao;

import com.lengueCode.mainApp.DataBaseConnection;
import com.lengueCode.model.Compte;
import com.lengueCode.model.Transactions;
import com.lengueCode.model.TypeCompte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Comptedao {
    Connection connection = DataBaseConnection.getConnection();

    public void sauvegarderCompte(Compte compte){

        try {
            PreparedStatement checkstatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM compte WHERE num_compte = ?");
            checkstatement.setInt(1, compte.getNum_compte());
            ResultSet rs = checkstatement.executeQuery();
            rs.next();
            boolean compteExiste = rs.getInt(1) > 0;

            if (compteExiste){
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE compte set solde = ? WHERE num_compte = ? ");
                statement.setDouble(1, compte.getSolde());
                statement.setInt(2, compte.getNum_compte());
                //System.out.println(compte.getNum_compte()+" est un compte deja existant dans la base de donnees");

            }else {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO compte(num_compte, type_compte, status, solde, date_creation_compte, client_id) VALUES (?, ?, ?, ?, ?, ?) ");

                statement.setInt(1, compte.getNum_compte());
                statement.setString(2, compte.getType_compte().name());
                statement.setString(3, compte.getStatus());
                statement.setDouble(4, compte.getSolde());
                statement.setDate(5, compte.getDate_creation_compte() != null
                        ? java.sql.Date.valueOf(compte.getDate_creation_compte())
                        : null );
                statement.setString(6, compte.getClientId());

                int rowAffected = statement.executeUpdate();
                if (rowAffected > 0){
                    System.out.println(" Le Compte : "+ compte.getNum_compte()+" a bien ete enregistrer dans la base de donnees.");
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("echec de l'enregistrement du compte" + compte.getNum_compte());

        }

    }

    public Compte getCompteById(int num_compte) {
        Compte compte = new Compte();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM compte WHERE num_compte = ?");
            statement.setInt(1, num_compte);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                int numCompte = resultSet.getInt("num_compte");
                TypeCompte typeCompte = TypeCompte.valueOf(resultSet.getString("type_compte"));
                String status = resultSet.getString("status");
                double solde = resultSet.getDouble("solde");
                Date dateCreation = resultSet.getDate("date_creation_compte");
                String clientId = resultSet.getString("client_id");

                compte = new Compte(numCompte, typeCompte, status, solde, dateCreation.toLocalDate(), clientId);
                compte.setTransactions(getTransactionByCompteId(num_compte));


            } else {
                System.out.println("Aucun compte trouvé avec le numero : " + num_compte);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  compte;
    }

    public List<Transactions> getTransactionByCompteId(int num_compte){

        List<Transactions> transactions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM transaction WHERE CompteSourceId = ? ");
            statement.setInt(1, num_compte);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String transactionId = resultSet.getString("transactionId");
                int compteSource = resultSet.getInt("CompteSourceId");
                int compteDestination = resultSet.getInt("CompteDestinationId");
                double montant = resultSet.getDouble("montant");
                java.sql.Date dateTransaction = resultSet.getDate("dateTransaction");

                Transactions transaction = new Transactions(transactionId,compteSource,compteDestination, montant, dateTransaction.toLocalDate());
                transactions.add(transaction);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }

}
