package com.trukea.service;

import com.trukea.model.Usuario;
import com.trukea.repository.UsuarioRepository;
import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> getAll() throws SQLException {
        return repository.getAll();
    }

    // etc...
}
