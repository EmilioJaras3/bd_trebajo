package com.trukea.controller;

import com.trukea.model.Producto;
import com.trukea.service.ProductoService;
import io.javalin.http.Context;

/**
 * Controlador para el recurso Producto.
 *
 * --- ¿CUÁL ES SU PROPÓSITO? ---
 * Esta capa es el punto de entrada para las peticiones HTTP.
 * NO contiene lógica de negocio. Su única responsabilidad es:
 * 1. Recibir la petición (`Context ctx`).
 * 2. Extraer datos de la petición (parámetros, cuerpo JSON).
 * 3. Llamar al método correspondiente en la capa de Servicio.
 * 4. Devolver la respuesta (en formato JSON, con el código de estado correcto).
 */
public class ProductoController {

    private final ProductoService productoService;

    // Recibe el servicio a través de inyección de dependencias.
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void getAll(Context ctx) {
        try {
            ctx.json(productoService.getAll());
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500).result("Error al obtener los productos: " + e.getMessage());
        }
    }

    public void getOne(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Producto producto = productoService.findById(id);
            if (producto != null) {
                ctx.json(producto);
                ctx.status(200);
            } else {
                ctx.status(404).result("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número.");
        } catch (Exception e) {
            ctx.status(500).result("Error al buscar el producto: " + e.getMessage());
        }
    }

    public void create(Context ctx) {
        try {
            Producto nuevoProducto = ctx.bodyAsClass(Producto.class);
            productoService.save(nuevoProducto);
            ctx.status(201).result("Producto creado.");
        } catch (Exception e) {
            ctx.status(400).result("Datos inválidos para crear el producto: " + e.getMessage());
        }
    }

    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Producto productoActualizado = ctx.bodyAsClass(Producto.class);
            productoActualizado.setId(id); // Aseguramos que el ID sea el de la URL
            productoService.update(productoActualizado);
            ctx.status(200).result("Producto actualizado.");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número.");
        } catch (Exception e) {
            ctx.status(400).result("Datos inválidos para actualizar el producto: " + e.getMessage());
        }
    }

    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            productoService.delete(id);
            ctx.status(204); // No Content
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número.");
        } catch (Exception e) {
            ctx.status(500).result("Error al eliminar el producto: " + e.getMessage());
        }
    }
}
