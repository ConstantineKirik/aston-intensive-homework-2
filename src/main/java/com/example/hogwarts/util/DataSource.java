package com.example.hogwarts.util;

import java.sql.*;

public class DataSource {
    private static DataSource dataSource;

    private DataSource() {
    }

    static {
        loadDriver();
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hogwarts", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void loadDriver()  {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
