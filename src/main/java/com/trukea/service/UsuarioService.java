package com.trukea.service;

import com.trukea.model.Usuario;
import com.trukea.repository.UsuarioRepository;
import java.sql.SQLException;
import java.util.List;

/**
 * Capa de Servicio para Usuarios.
 *
 * Aquí iría la lógica de negocio relacionada con los usuarios.
 * Por ejemplo:
 * - Validar que un email no esté ya registrado antes de crear un usuario.
 * - Encriptar la contraseña antes de guardarla en la base de datos.
 * - Enviar un email de bienvenida al nuevo usuario.
 */
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAll() throws SQLException {
        return usuarioRepository.getAll();
    }

    public Usuario findById(int id) throws SQLException {
        return usuarioRepository.findById(id);
    }

    public void save(Usuario usuario) throws SQLException {
        // Ejemplo de lógica de negocio:
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("El formato del email no es válido.");
        }
        // Aquí llamarías a un método para encriptar la contraseña si la tuvieras.
        // usuario.setPassword(PasswordHasher.hash(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public void update(Usuario usuario) throws SQLException {
        usuarioRepository.update(usuario);
    }

    public void delete(int id) throws SQLException {
        usuarioRepository.delete(id);
    }
}
