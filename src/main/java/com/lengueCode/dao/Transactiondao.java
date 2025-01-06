package com.lengueCode.dao;

import com.lengueCode.mainApp.DataBaseConnection;
import com.lengueCode.model.Transactions;

import java.sql.*;

public class Transactiondao {
    Connection connection = DataBaseConnection.getConnection();
    public void sauvegarderTransaction(Transactions transactions){
        try {
            PreparedStatement checkPreparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM transactions WHERE transactionId = ?");
            checkPreparedStatement.setString(1, transactions.getTransactionId());
            ResultSet rscheck = checkPreparedStatement.executeQuery();
            rscheck.next();

            boolean transactionExists = rscheck.getInt(1) > 0;
            if (transactionExists){
                System.out.println(transactions.getTransactionId() + " a deja ete effectuee");
            }else {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO transactions (transactionid, CompteSourceId, CompteDestinationId, montant, dateTransaction) VALUES (?, ?, ?, ?, ?)");

                statement.setString(1, transactions.getTransactionId());
                statement.setInt(2, transactions.getCompteSourceId());
                statement.setInt(3, transactions.getCompteDestinationId());
                statement.setDouble(4, transactions.getMontant());
                statement.setDate(5, java.sql.Date.valueOf(transactions.getDateTransaction()));

                boolean executed = statement.execute();
                if (executed) {
                    System.out.println("La transaction : " + transactions.getTransactionId()+ " a bien ete enregistree!");
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
