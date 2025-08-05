package com.arbol.api.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static String buildUrl() {
        String host = System.getenv("MYSQL_HOST");
        String port = System.getenv("MYSQL_PORT");
        String database = System.getenv("MYSQL_DATABASE");

        if (host == null) host = "localhost";
        if (port == null) port = "3306";
        if (database == null) database = "arbol_db_"; // Corresponds to user's final schema

        return String.format("jdbc:mysql://%s:%s/%s", host, port, database);
    }

    private static String getUsername() {
        String user = System.getenv("MYSQL_USER");
        return user != null ? user : "root";
    }

    private static String getPassword() {
        String pass = System.getenv("MYSQL_PASSWORD");
        return pass != null ? pass : "password";
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(buildUrl(), getUsername(), getPassword());
    }
}
