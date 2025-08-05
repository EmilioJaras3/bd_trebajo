package com.custom.api.controller;

import com.custom.api.model.Arbol;
import com.custom.api.service.ArbolService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

public class ArbolController {

    private final ArbolService arbolService;

    public ArbolController(ArbolService arbolService) {
        this.arbolService = arbolService;
    }

    public void getAll(Context ctx) throws Exception {
        ctx.json(arbolService.getAllArboles());
        ctx.status(HttpStatus.OK);
    }

    public void getOne(Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Arbol arbol = arbolService.getArbolById(id);
        if (arbol != null) {
            ctx.json(arbol);
            ctx.status(HttpStatus.OK);
        } else {
            ctx.status(HttpStatus.NOT_FOUND);
        }
    }

    public void create(Context ctx) throws Exception {
        Arbol nuevoArbol = ctx.bodyAsClass(Arbol.class);
        Arbol arbolCreado = arbolService.createArbol(nuevoArbol);
        ctx.json(arbolCreado);
        ctx.status(HttpStatus.CREATED);
    }

    // update y delete se implementarían aquí...
}
