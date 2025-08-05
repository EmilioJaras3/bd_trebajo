package com.trukea;

import com.trukea.routes.ApiRoutes;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;


public class Main {
    public static void main(String[] args) {

            config.defaultContentType = "application/json";
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(3000);

    }
}