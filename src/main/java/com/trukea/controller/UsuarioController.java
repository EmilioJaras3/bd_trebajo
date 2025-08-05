package com.trukea.controller;

import com.trukea.service.UsuarioService;
import io.javalin.http.Context;

public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    public void getAll(Context ctx) throws Exception {
        ctx.json(service.getAll());
    }

    // etc...
}
