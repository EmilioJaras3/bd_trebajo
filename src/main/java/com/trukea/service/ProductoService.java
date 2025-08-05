package com.trukea.service;

import com.trukea.model.Producto;
import com.trukea.repository.ProductoRepository;
import java.sql.SQLException;
import java.util.List;

/**
 * Capa de Servicio para Productos.
 *
 * --- ¿CUÁL ES SU PROPÓSITO? ---
 * Esta capa se sitúa entre el Controlador y el Repositorio.
 * Su responsabilidad es orquestar la lógica de negocio. Por ejemplo:
 * - Validar que un producto tenga stock antes de venderlo.
 * - Calcular precios con impuestos.
 * - Comprobar si un usuario tiene permisos para modificar un producto.
 *
 * En un CRUD simple como este, puede parecer que solo llama al repositorio,
 * pero es una pieza clave para que la aplicación pueda crecer de forma ordenada.
 * El Controlador no debe contener lógica de negocio, solo manejar la petición HTTP.
 */
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Recibe el repositorio a través de inyección de dependencias.
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAll() throws SQLException {
        // Aquí podrías añadir lógica, como filtrar productos no disponibles.
        return productoRepository.getAll();
    }

    public Producto findById(int id) throws SQLException {
        // Aquí podrías añadir lógica, como registrar quién consultó el producto.
        return productoRepository.findById(id);
    }

    public void save(Producto producto) throws SQLException {
        // Aquí podrías añadir lógica de validación antes de guardar.
        // Por ejemplo: if (producto.getPrecio() <= 0) { throw new IllegalArgumentException("El precio debe ser positivo"); }
        productoRepository.save(producto);
    }

    public void update(Producto producto) throws SQLException {
        // Lógica de validación...
        productoRepository.update(producto);
    }

    public void delete(int id) throws SQLException {
        // Lógica de validación, como comprobar si el producto está en un pedido activo antes de borrar.
        productoRepository.delete(id);
    }
}
