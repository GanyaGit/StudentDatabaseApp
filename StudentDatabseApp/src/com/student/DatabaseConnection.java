package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "@358853";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }
}