package com.trukea.service;

import com.trukea.model.Producto;
import com.trukea.repository.ProductoRepository;
import java.sql.SQLException;
import java.util.List;

public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> getAll() throws SQLException {
        return repository.getAll();
    }

    // etc...
}
