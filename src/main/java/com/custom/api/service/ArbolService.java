package com.custom.api.service;

import com.custom.api.model.Arbol;
import com.custom.api.repository.ArbolRepository;
import java.sql.SQLException;
import java.util.List;

public class ArbolService {

    private final ArbolRepository arbolRepository;

    public ArbolService(ArbolRepository arbolRepository) {
        this.arbolRepository = arbolRepository;
    }

    public List<Arbol> getAllArboles() throws SQLException {
        return arbolRepository.findAll();
    }

    public Arbol getArbolById(int id) throws SQLException {
        return arbolRepository.findById(id);
    }

    public Arbol createArbol(Arbol arbol) throws SQLException {
        if (arbol.getNombre() == null || arbol.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del árbol es obligatorio.");
        }
        return arbolRepository.save(arbol);
    }

    // update y delete se implementarían aquí...
}
