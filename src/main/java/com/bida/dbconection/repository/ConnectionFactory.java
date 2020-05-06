package com.bida.dbconection.repository;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionFactory {

    private Connection connection;
    private Statement statement;

    private ConnectionFactory(String URL, String USERNAME, String PASSWORD){
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Maybe Wrong URL, Password or Username");
        }
    }

    static ConnectionFactory createConnection(String URL, String USERNAME, String PASSWORD){
        return new ConnectionFactory(URL, USERNAME, PASSWORD);
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error with Connection Close");
        }
    }

    public Statement getStatement() {
        return statement;
    }
}
