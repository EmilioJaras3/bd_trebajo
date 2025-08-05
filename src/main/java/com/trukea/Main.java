package com.trukea;

import com.trukea.routes.ApiRoutes;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location; // <-- IMPORTANTE: Añadir esta línea

/**
 * Clase principal de la aplicación.
 * Adaptada para usar una sintaxis de Javalin más antigua para máxima compatibilidad.
 */
public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            // Se ha eliminado la configuración de CORS para evitar errores de compilación.

            // Configuraciones compatibles con versiones antiguas
            config.defaultContentType = "application/json";
            config.ignoreTrailingSlashes = true;

            // CORRECCIÓN: Sintaxis antigua para servir archivos estáticos.
            config.addStaticFiles("/public", Location.CLASSPATH);
        });

        ApiRoutes.configure(app);

        app.start(3000);

        System.out.println("===================================================================");
        System.out.println("  API INICIADA - http://localhost:3000");
        System.out.println("  Frontend de prueba: http://localhost:3000/index.html");
        System.out.println("===================================================================");
    }
}