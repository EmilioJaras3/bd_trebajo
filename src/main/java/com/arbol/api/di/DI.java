package com.arbol.api.di;

import com.arbol.api.controller.ArbolController;
import com.arbol.api.repository.ArbolRepository;
import com.arbol.api.service.ArbolService;

public class DI {
    private static ArbolController arbolControllerSingleton;

    public static ArbolController getArbolController() {
        if (arbolControllerSingleton == null) {
            ArbolRepository repo = new ArbolRepository();
            ArbolService service = new ArbolService(repo);
            arbolControllerSingleton = new ArbolController(service);
        }
        return arbolControllerSingleton;
    }
}
