package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static final String URL = "jdbc:postgresql://localhost:5432/Курсовая ООП";
    private static final String USER = "postgres";
    private static final String PASSWORD = "23121204";

    private Connection connection;

    public DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
