package com.trukea;

import com.trukea.routes.ApiRoutes;
import io.javalin.Javalin;

/**
 * Clase principal de la aplicación.
 *
 * --- ¿CUÁL ES SU PROPÓSITO? ---
 * Gracias a la nueva arquitectura, esta clase es ahora muy simple.
 * Sus únicas responsabilidades son:
 * 1. Crear la instancia del servidor Javalin.
 * 2. Llamar al configurador de rutas.
 * 3. Iniciar el servidor.
 */
public class Main {

    public static void main(String[] args) {

        // 1. Creamos la instancia de Javalin con configuración básica.
        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.router.ignoreTrailingSlashes = true;
            // Le decimos a Javalin que sirva archivos estáticos desde la carpeta 'public' en los recursos.
            // Así es como podrá encontrar nuestro index.html.
            config.staticFiles.add("/public");
        });

        // 2. Le pasamos la instancia de la app a nuestro configurador de rutas.
        ApiRoutes.configure(app);

        // 3. Iniciamos el servidor en el puerto 7070.
        app.start(7070);

        System.out.println("===================================================================");
        System.out.println("  API INICIADA - http://localhost:7070");
        System.out.println("  Endpoints de productos: http://localhost:7070/productos");
        System.out.println("  Endpoints de usuarios:  http://localhost:7070/usuarios");
        System.out.println("===================================================================");
    }
}
