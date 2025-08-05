package com.custom.api.di;

import com.custom.api.controller.ArbolController;
import com.custom.api.repository.ArbolRepository;
import com.custom.api.service.ArbolService;

public class DI {

    private static ArbolController arbolControllerSingleton;

    public static ArbolController getArbolController() {
        if (arbolControllerSingleton == null) {
            ArbolRepository arbolRepository = new ArbolRepository();
            ArbolService arbolService = new ArbolService(arbolRepository);
            arbolControllerSingleton = new ArbolController(arbolService);
        }
        return arbolControllerSingleton;
    }
}
