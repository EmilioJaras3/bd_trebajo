package com.trukea.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static String buildUrl() {
        // CORRECCIÓN: Construye la URL a partir de las variables de entorno de TU script
        String host = System.getenv("MYSQL_HOST");
        String port = System.getenv("MYSQL_PORT");
        String database = System.getenv("MYSQL_DATABASE");

        // Si las variables no están, usa los valores por defecto
        if (host == null) host = "52.71.195.110";
        if (port == null) port = "3306";
        if (database == null) database = "trukea_db";

        return String.format("jdbc:mysql://%s:%s/%s", host, port, database);
    }

    private static String getUsername() {
        // CORRECCIÓN: Lee la variable de usuario de TU script
        String user = System.getenv("MYSQL_USER");
        return user != null ? user : "admin";
    }

    private static String getPassword() {
        // CORRECCIÓN: Lee la variable de contraseña de TU script
        String pass = System.getenv("MYSQL_PASSWORD");
        return pass != null ? pass : "Angelito7@2024!";
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(buildUrl(), getUsername(), getPassword());
    }
}