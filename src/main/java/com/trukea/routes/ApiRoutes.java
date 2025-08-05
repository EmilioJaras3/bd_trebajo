package com.trukea.routes;

import com.trukea.controller.ProductoController;
import com.trukea.controller.UsuarioController;
import com.trukea.di.DI;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

/**
 * Clase de configuración para las rutas de la API.
 *
 * --- ¿CUÁL ES SU PROPÓSITO? ---
 * Centraliza la definición de todos los endpoints de la aplicación.
 * Mantiene el archivo Main.java limpio, cuya única responsabilidad
 * debería ser iniciar la aplicación.
 *
 * Esta clase obtiene los controladores desde el contenedor de DI y los
 * asocia a las rutas correspondientes.
 */
public class ApiRoutes {

    public static void configure(Javalin app) {
        // 1. Obtenemos las instancias de los controladores desde nuestro contenedor de DI.
        ProductoController productoController = DI.getProductoController();
        UsuarioController usuarioController = DI.getUsuarioController();

        // 2. Definimos las rutas de la API.
        app.routes(() -> {

            // --- Rutas para el recurso "Productos" ---
            path("/productos", () -> {
                get(productoController::getAll);
                post(productoController::create);

                path("/{id}", () -> {
                    get(productoController::getOne);
                    put(productoController::update);
                    delete(productoController::delete);
                });
            });

            // --- Rutas para el recurso "Usuarios" ---
            path("/usuarios", () -> {
                get(usuarioController::getAll);
                post(usuarioController::create);

                path("/{id}", () -> {
                    get(usuarioController::getOne);
                    put(usuarioController::update);
                    delete(usuarioController::delete);
                });
            });
        });
    }
}
