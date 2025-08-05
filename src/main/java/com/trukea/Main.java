package com.trukea;

import com.trukea.routes.ApiRoutes;
import io.javalin.Javalin;

/**
 * Clase principal de la aplicación.
 * Adaptada para usar la sintaxis de configuración de Javalin 4.x.
 */
public class Main {

    public static void main(String[] args) {

        // 1. Creamos la instancia de Javalin usando la sintaxis de la v4.
        Javalin app = Javalin.create(config -> {
            // Se ha eliminado la configuración de CORS para evitar errores de compilación.

            // Añadimos las otras configuraciones usando la sintaxis de la v4.
            config.defaultContentType = "application/json";
            config.ignoreTrailingSlashes = true;

            // Le decimos a Javalin que sirva archivos estáticos desde la carpeta 'public' en los recursos.
            config.addStaticFiles("/public");
        });

        // 2. Le pasamos la instancia de la app a nuestro configurador de rutas.
        ApiRoutes.configure(app);

        // 3. Iniciamos el servidor en el puerto 3000, como se solicitó.
        app.start(3000);

        System.out.println("===================================================================");
        System.out.println("  API INICIADA - http://localhost:3000");
        System.out.println("  Frontend de prueba: http://localhost:3000/index.html");
        System.out.println("  Endpoints de productos: http://localhost:3000/productos");
        System.out.println("  Endpoints de usuarios:  http://localhost:3000/usuarios");
        System.out.println("===================================================================");
    }
}
