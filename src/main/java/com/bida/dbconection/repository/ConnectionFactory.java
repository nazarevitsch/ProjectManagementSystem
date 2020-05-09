package com.bida.dbconection.repository;

import org.postgresql.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionFactory {

    private Connection connection;
    private Statement statement;

    private ConnectionFactory(String URL, String USERNAME, String PASSWORD){
        create(URL, USERNAME, PASSWORD);
    }

    static ConnectionFactory createConnection(String URL, String USERNAME, String PASSWORD){
        return new ConnectionFactory(URL, USERNAME, PASSWORD);
    }

    private void create(String URL, String USERNAME, String PASSWORD){
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (Exception e){
            Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
            logger.error("Wrong PASSWORD, USERNAME, or URL");
            System.exit(1);
        }
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
