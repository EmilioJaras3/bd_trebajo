package com.arbol.api.service;

import com.arbol.api.model.Arbol;
import com.arbol.api.repository.ArbolRepository;
import java.sql.SQLException;
import java.util.List;

public class ArbolService {
    private final ArbolRepository repository;

    public ArbolService(ArbolRepository repository) {
        this.repository = repository;
    }

    public List<Arbol> getAllArboles() throws SQLException {
        return repository.findAll();
    }

    public Arbol getArbolById(int id) throws SQLException {
        return repository.findById(id);
    }

    public Arbol createArbol(Arbol arbol) throws SQLException {
        if (arbol.getNombre() == null || arbol.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del árbol es obligatorio.");
        }
        return repository.save(arbol);
    }

    public void updateArbol(int id, Arbol arbol) throws SQLException {
        Arbol existingArbol = repository.findById(id);
        if (existingArbol == null) {
            throw new IllegalArgumentException("Árbol no encontrado con id: " + id);
        }
        arbol.setIdArbol(id);
        repository.update(arbol);
    }

    public void deleteArbol(int id) throws SQLException {
        repository.delete(id);
    }
}
