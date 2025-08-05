package com.trukea;

import com.trukea.controller.ProductoController;
import com.trukea.controller.UsuarioController;
import com.trukea.dao.ProductoDAO;
import com.trukea.dao.UsuarioDAO;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) {

        // --- INICIALIZACIÓN ---
        // 1. Creamos las instancias de los DAOs y Controladores.
        //    Esto es una forma simple de "Inyección de Dependencias".
        ProductoDAO productoDAO = new ProductoDAO();
        ProductoController productoController = new ProductoController(productoDAO);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioController usuarioController = new UsuarioController(usuarioDAO);

        // 2. Creamos y configuramos la aplicación Javalin.
        Javalin app = Javalin.create(config -> {
            // Aquí se pueden añadir configuraciones globales, como el manejo de CORS, etc.
            config.http.defaultContentType = "application/json";
            config.router.ignoreTrailingSlashes = true;
        }).start(7070); // Iniciamos el servidor en el puerto 7070.

        System.out.println("Servidor Javalin iniciado en http://localhost:7070");

        // --- DEFINICIÓN DE RUTAS (ENDPOINTS) ---
        // Aquí es donde la magia de Javalin ocurre.
        // Mapeamos las URLs a los métodos de nuestros controladores.
        app.routes(() -> {

            // --- Rutas para el recurso "Productos" ---
            // El método path() agrupa todas las rutas que empiezan con "/productos"
            path("/productos", () -> {
                get(productoController::getAll);          // GET /productos -> Llama al método getAll del controlador
                post(productoController::create);         // POST /productos -> Llama al método create

                // Grupo para rutas que incluyen un ID
                path("/{id}", () -> {
                    get(productoController::getOne);      // GET /productos/123 -> Llama al método getOne
                    put(productoController::update);      // PUT /productos/123 -> Llama al método update
                    delete(productoController::delete);   // DELETE /productos/123 -> Llama al método delete
                });
            });

            // --- Rutas para el recurso "Usuarios" ---
            // Repetimos el mismo patrón para el segundo recurso.
            path("/usuarios", () -> {
                get(usuarioController::getAll);
                post(usuarioController::create);

                path("/{id}", () -> {
                    get(usuarioController::getOne);
                    put(usuarioController::update);
                    delete(usuarioController::delete);
                });
            });

            // --- ¡INSTRUCCIONES! ---
            // Si quieres añadir un tercer recurso, por ejemplo "Pedidos", solo tienes que:
            // 1. Crear el modelo Pedido.java
            // 2. Crear el DAO PedidoDAO.java
            // 3. Crear el controlador PedidoController.java
            // 4. Añadir aquí el bloque de rutas:
            //    path("/pedidos", () -> { ... });
        });
    }
}
