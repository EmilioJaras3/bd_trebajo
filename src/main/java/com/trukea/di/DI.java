package com.trukea.di;

import com.trukea.controller.ProductoController;
import com.trukea.controller.UsuarioController;
import com.trukea.repository.ProductoRepository;
import com.trukea.repository.UsuarioRepository;
import com.trukea.service.ProductoService;
import com.trukea.service.UsuarioService;

/**
 * Contenedor de Inyección de Dependencias (DI) manual.
 *
 * --- ¿CUÁL ES SU PROPÓSITO? ---
 * Esta clase es responsable de CREAR e INTERCONECTAR todos los objetos de la aplicación
 * (Repositorios, Servicios, Controladores).
 *
 * En lugar de que cada clase cree sus propias dependencias (ej: `new MiDependencia()`),
 * las creamos aquí y se las "inyectamos" a través de sus constructores.
 *
 * Esto sigue el Principio de Inversión de Dependencias y nos da varias ventajas:
 * - El código es más fácil de probar (podemos "inyectar" objetos falsos o 'mocks').
 * - Las dependencias son explícitas.
 * - Gestionamos el ciclo de vida de los objetos en un solo lugar (aquí los creamos como 'singletons').
 *
 * Frameworks como Spring o Guice automatizan esto, pero hacerlo manualmente es genial para aprender.
 */
public class DI {

    // --- Singletons ---
    // Guardamos las instancias aquí para no tener que crearlas cada vez que se piden.
    private static ProductoController productoControllerSingleton;
    private static UsuarioController usuarioControllerSingleton;

    // --- Getters Públicos ---
    // Estos son los únicos métodos que el resto de la aplicación (Main, Routes) usará.

    public static ProductoController getProductoController() {
        // Usamos un 'if' para asegurarnos de que solo se crea la instancia una vez (patrón Singleton).
        if (productoControllerSingleton == null) {
            // Creamos las dependencias de abajo hacia arriba: Repository -> Service -> Controller
            ProductoRepository productoRepository = new ProductoRepository();
            ProductoService productoService = new ProductoService(productoRepository);
            productoControllerSingleton = new ProductoController(productoService);
        }
        return productoControllerSingleton;
    }

    public static UsuarioController getUsuarioController() {
        if (usuarioControllerSingleton == null) {
            UsuarioRepository usuarioRepository = new UsuarioRepository();
            UsuarioService usuarioService = new UsuarioService(usuarioRepository);
            usuarioControllerSingleton = new UsuarioController(usuarioService);
        }
        return usuarioControllerSingleton;
    }
}
