package com.clientcrud.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static final ConnectionPool instance = new ConnectionPool();
    private final String url = "jdbc:postgresql://localhost/clientcrud";
    private final String user = "postgres";
    private final String password = "renan123";
    private Connection connection;

    private ConnectionPool() {
        if (instance != null) {
            throw new IllegalStateException("Já existe uma instância da classe ConnectionPool.");
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
