package com.trukea.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // --- ¡INSTRUCCIONES! ---
    // Javalin no necesita un método `initialize`. Se puede eliminar.
    // El framework se encarga de iniciar y detener la aplicación.
    // Lo más importante es que el método `getConnection` funcione correctamente.

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
        // --- NOTA IMPORTANTE ---
        // Para que esta conexión funcione, Maven debe haber descargado el conector de MySQL
        // gracias a la dependencia que añadimos en el `pom.xml`.
        // No es necesario llamar a `Class.forName()` en las versiones modernas de JDBC.
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
