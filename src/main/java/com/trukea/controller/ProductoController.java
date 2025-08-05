package com.trukea.controller;

import com.trukea.dao.ProductoDAO;
import com.trukea.model.Producto;
import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para el recurso Producto.
 *
 * --- ¿CÓMO FUNCIONA EN JAVALIN? ---
 * A diferencia del MVC tradicional con Servlets, en Javalin un controlador es simplemente
 * una clase que agrupa la lógica de manejo de peticiones.
 * Cada método público recibe un objeto `Context` (ctx). Este objeto es mágico:
 * - `ctx.pathParam("id")`: Obtiene parámetros de la URL (ej: /productos/5).
 * - `ctx.bodyAsClass(Producto.class)`: Convierte el JSON de la petición en un objeto Java.
 * - `ctx.json(listaDeProductos)`: Convierte un objeto o lista Java a JSON y lo envía como respuesta.
 * - `ctx.status(200)`: Establece el código de estado HTTP de la respuesta.
 */
public class ProductoController {

    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    // --- MANEJADOR PARA GET /productos ---
    public void getAll(Context ctx) {
        try {
            List<Producto> productos = productoDAO.getAll();
            ctx.json(productos); // Envía la lista de productos como JSON.
            ctx.status(200); // OK
        } catch (SQLException e) {
            ctx.status(500).result("Error de base de datos al obtener productos: " + e.getMessage());
        }
    }

    // --- MANEJADOR PARA GET /productos/{id} ---
    public void getOne(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id")); // Obtiene el ID de la URL.
            Producto producto = productoDAO.findById(id);
            if (producto != null) {
                ctx.json(producto);
                ctx.status(200); // OK
            } else {
                ctx.status(404).result("Producto no encontrado."); // Not Found
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID del producto debe ser un número entero."); // Bad Request
        } catch (SQLException e) {
            ctx.status(500).result("Error de base de datos al buscar el producto: " + e.getMessage());
        }
    }

    // --- MANEJADOR PARA POST /productos ---
    public void create(Context ctx) {
        try {
            // Convierte el JSON del cuerpo de la petición en un objeto Producto.
            Producto nuevoProducto = ctx.bodyAsClass(Producto.class);
            productoDAO.add(nuevoProducto);
            ctx.status(201); // Created
            ctx.result("Producto creado exitosamente.");
        } catch (Exception e) {
            // Esto puede fallar si el JSON es inválido o por un error de BD.
            ctx.status(400).result("Error al crear el producto: " + e.getMessage());
        }
    }

    // --- MANEJADOR PARA PUT /productos/{id} ---
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Producto productoActualizado = ctx.bodyAsClass(Producto.class);

            // Asegurarse de que el ID en el cuerpo coincida con el ID en la URL.
            productoActualizado.setId(id);

            productoDAO.update(productoActualizado);
            ctx.status(200); // OK
            ctx.result("Producto actualizado exitosamente.");
        } catch (Exception e) {
            ctx.status(400).result("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // --- MANEJADOR PARA DELETE /productos/{id} ---
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            productoDAO.delete(id);
            ctx.status(204); // No Content (éxito, sin cuerpo de respuesta)
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID del producto debe ser un número entero.");
        } catch (SQLException e) {
            ctx.status(500).result("Error de base de datos al borrar el producto: " + e.getMessage());
        }
    }
}
