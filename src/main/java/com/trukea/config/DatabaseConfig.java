package com.trukea.config;

import java.sql.*;

public class DatabaseConfig {
    private static final String URL = System.getenv("DATABASE_URL") != null
            ? System.getenv("DATABASE_URL")
            : "jdbc:mysql://52.71.195.110:3306/trukea_db";
    private static final String USERNAME = System.getenv("DB_USER") != null
            ? System.getenv("DB_USER")
            : "admin";
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null
            ? System.getenv("DB_PASSWORD")
            : "Angelito7@2024!";

    public static Connection getConnection() throws SQLException {
        // --- NOTA PARA TI ---
        // Para que esta línea funcione, necesitas tener el "driver" de MySQL en tu proyecto.
        // Si usas Maven, esto se añade en el archivo pom.xml.
        // Si no, tienes que añadir el archivo .jar del conector de MySQL manualmente a tu classpath.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: Driver de MySQL no encontrado.", e);
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void initialize() {
        try (Connection conn = getConnection()) {
            System.out.println("Conectado a la base de datos MySQL");
        } catch (Exception e) {
            System.err.println("Error conectando a la base de datos: " + e.getMessage());
        }
    }
}
