package com.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/banking_db";

    // MySQL Username
    private static final String USERNAME = "root";

    // MySQL Password
    private static final String PASSWORD = "password";

    // Method to get database connection
    public static Connection getConnection() {

        Connection con = null;

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return con;
    }
}