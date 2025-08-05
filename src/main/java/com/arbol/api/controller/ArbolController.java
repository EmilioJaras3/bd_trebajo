package com.arbol.api.controller;

import com.arbol.api.model.Arbol;
import com.arbol.api.service.ArbolService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

public class ArbolController {
    private final ArbolService service;

    public ArbolController(ArbolService service) {
        this.service = service;
    }

    public void getAll(Context ctx) throws Exception {
        ctx.json(service.getAllArboles());
    }

    public void getOne(Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Arbol arbol = service.getArbolById(id);
        if (arbol != null) {
            ctx.json(arbol);
        } else {
            ctx.status(HttpStatus.NOT_FOUND);
        }
    }

    public void create(Context ctx) throws Exception {
        Arbol arbol = ctx.bodyAsClass(Arbol.class);
        Arbol arbolCreado = service.createArbol(arbol);
        ctx.status(HttpStatus.CREATED).json(arbolCreado);
    }

    public void update(Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Arbol arbol = ctx.bodyAsClass(Arbol.class);
        service.updateArbol(id, arbol);
        ctx.status(HttpStatus.NO_CONTENT);
    }

    public void delete(Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.deleteArbol(id);
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
