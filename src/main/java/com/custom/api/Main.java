package com.custom.api;

import com.custom.api.routes.ApiRoutes;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
        }).start(7070);

        ApiRoutes.configure(app);
    }
}
