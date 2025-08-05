package com.trukea.di;

import com.trukea.controller.ProductoController;
import com.trukea.controller.UsuarioController;
import com.trukea.repository.ProductoRepository;
import com.trukea.repository.UsuarioRepository;
import com.trukea.service.ProductoService;
import com.trukea.service.UsuarioService;

public class DI {
    private static ProductoController pController;
    private static UsuarioController uController;

    public static ProductoController getProductoController() {
        if (pController == null) {
            pController = new ProductoController(new ProductoService(new ProductoRepository()));
        }
        return pController;
    }
    public static UsuarioController getUsuarioController() {
        if (uController == null) {
            uController = new UsuarioController(new UsuarioService(new UsuarioRepository()));
        }
        return uController;
    }
}
