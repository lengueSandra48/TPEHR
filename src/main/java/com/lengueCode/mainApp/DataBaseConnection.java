package com.lengueCode.mainApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    static String URL = "jdbc:postgresql://localhost:5432/bankingApp" ;
    static String user = "postgres";
    static String password = "sandra" ;
    static Connection connection;

    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(URL,user,password);
            Class.forName("org.postgresql.Driver");

//            if (connection != null) {
//                System.out.println("connection etablie");
//            }else {
//                System.out.println("echec de connection");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
