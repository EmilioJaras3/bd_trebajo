package com.trukea.routes;

import com.trukea.di.DI;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiRoutes {
    public static void configure(Javalin app) {
        app.routes(() -> {
            path("/productos", () -> {
                get(DI.getProductoController()::getAll);
                // post, get by id, etc.
            });
            path("/usuarios", () -> {
                get(DI.getUsuarioController()::getAll);
                // post, get by id, etc.
            });
        });
    }
}
