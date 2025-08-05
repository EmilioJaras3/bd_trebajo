package com.trukea.controller;

import com.trukea.service.ProductoService;
import io.javalin.http.Context;

public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    public void getAll(Context ctx) throws Exception {
        ctx.json(service.getAll());
    }

    // etc...
}
