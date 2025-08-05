package com.arbol.api;

import com.arbol.api.routes.ApiRoutes;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.router.ignoreTrailingSlashes = true;
        }).start(3000);

        ApiRoutes.configure(app);

        System.out.println("API de Árboles iniciada en http://localhost:3000");
    }
}
