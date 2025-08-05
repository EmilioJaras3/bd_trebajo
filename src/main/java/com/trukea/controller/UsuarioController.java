package com.trukea.controller;

import com.trukea.dao.UsuarioDAO;
import com.trukea.model.Usuario;
import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para el recurso Usuario.
 *
 * Agrupa toda la lógica de manejo de peticiones para los usuarios.
 * Cada método público es un "handler" que se puede asociar a una ruta en Main.java.
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // --- MANEJADOR PARA GET /usuarios ---
    public void getAll(Context ctx) {
        try {
            List<Usuario> usuarios = usuarioDAO.getAll();
            ctx.json(usuarios);
            ctx.status(200);
        } catch (SQLException e) {
            ctx.status(500).result("Error de base de datos al obtener usuarios: " + e.getMessage());
        }
    }

    // --- MANEJADOR PARA GET /usuarios/{id} ---
    public void getOne(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuario = usuarioDAO.findById(id);
            if (usuario != null) {
                ctx.json(usuario);
                ctx.status(200);
            } else {
                ctx.status(404).result("Usuario no encontrado.");
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID del usuario debe ser un número entero.");
        } catch (SQLException e) {
            ctx.status(500).result("Error de base de datos al buscar el usuario: " + e.getMessage());
        }
    }

    // --- MANEJADOR PARA POST /usuarios ---
    public void create(Context ctx) {
        try {
            Usuario nuevoUsuario = ctx.bodyAsClass(Usuario.class);
            // Podrías añadir validaciones aquí antes de llamar al DAO.
            usuarioDAO.add(nuevoUsuario);
            ctx.status(201).result("Usuario creado exitosamente.");
        } catch (Exception e) {
            ctx.status(400).result("Error al crear el usuario: " + e.getMessage());
        }
    }

    // --- MANEJADOR PARA PUT /usuarios/{id} ---
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuarioActualizado = ctx.bodyAsClass(Usuario.class);
            usuarioActualizado.setId(id);
            usuarioDAO.update(usuarioActualizado);
            ctx.status(200).result("Usuario actualizado exitosamente.");
        } catch (Exception e) {
            ctx.status(400).result("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // --- MANEJADOR PARA DELETE /usuarios/{id} ---
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            usuarioDAO.delete(id);
            ctx.status(204); // No Content
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID del usuario debe ser un número entero.");
        } catch (SQLException e) {
            ctx.status(500).result("Error de base de datos al borrar el usuario: " + e.getMessage());
        }
    }
}
