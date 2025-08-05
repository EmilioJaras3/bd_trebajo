package com.arbol.api.routes;

import com.arbol.api.di.DI;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiRoutes {
    public static void configure(Javalin app) {
        app.routes(() -> {
            path("/arboles", () -> {
                get(DI.getArbolController()::getAll);
                post(DI.getArbolController()::create);
                path("/{id}", () -> {
                    get(DI.getArbolController()::getOne);
                    put(DI.getArbolController()::update);
                    delete(DI.getArbolController()::delete);
                });
            });
        });
    }
}
