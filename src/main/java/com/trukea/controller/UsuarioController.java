package com.trukea.controller;

import com.trukea.model.Usuario;
import com.trukea.service.UsuarioService;
import io.javalin.http.Context;

/**
 * Controlador para el recurso Usuario.
 *
 * Traduce las peticiones HTTP a llamadas a la capa de servicio.
 */
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void getAll(Context ctx) {
        try {
            ctx.json(usuarioService.getAll());
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500).result("Error al obtener los usuarios: " + e.getMessage());
        }
    }

    public void getOne(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuario = usuarioService.findById(id);
            if (usuario != null) {
                ctx.json(usuario);
                ctx.status(200);
            } else {
                ctx.status(404).result("Usuario no encontrado.");
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número.");
        } catch (Exception e) {
            ctx.status(500).result("Error al buscar el usuario: " + e.getMessage());
        }
    }

    public void create(Context ctx) {
        try {
            Usuario nuevoUsuario = ctx.bodyAsClass(Usuario.class);
            usuarioService.save(nuevoUsuario);
            ctx.status(201).result("Usuario creado.");
        } catch (Exception e) {
            ctx.status(400).result("Datos inválidos para crear el usuario: " + e.getMessage());
        }
    }

    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuarioActualizado = ctx.bodyAsClass(Usuario.class);
            usuarioActualizado.setId(id);
            usuarioService.update(usuarioActualizado);
            ctx.status(200).result("Usuario actualizado.");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número.");
        } catch (Exception e) {
            ctx.status(400).result("Datos inválidos para actualizar el usuario: " + e.getMessage());
        }
    }

    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            usuarioService.delete(id);
            ctx.status(204);
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número.");
        } catch (Exception e) {
            ctx.status(500).result("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}
