package com.custom.api.routes;

import com.custom.api.controller.ArbolController;
import com.custom.api.di.DI;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiRoutes {

    public static void configure(Javalin app) {
        ArbolController arbolController = DI.getArbolController();

        app.routes(() -> {
            path("/arboles", () -> {
                get(arbolController::getAll);
                post(arbolController::create);
                // get, put, delete by id would be added here...
            });
        });
    }
}
