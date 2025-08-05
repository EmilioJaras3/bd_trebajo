package com.trukea.repository;

import com.trukea.config.DatabaseConfig;
import com.trukea.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository para el recurso Usuario.
 * Esta capa se comunica directamente con la base de datos.
 *
 * --- ¡INSTRUCCIONES! ---
 * Adapta todas las sentencias SQL para que coincidan con tu tabla de usuarios.
 */
public class UsuarioRepository {

    public List<Usuario> getAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre_usuario, email, fecha_registro FROM tu_tabla_usuarios"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombreDeUsuario(rs.getString("nombre_usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setFechaDeRegistro(rs.getDate("fecha_registro"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario findById(int id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT id_usuario, nombre_usuario, email, fecha_registro FROM tu_tabla_usuarios WHERE id_usuario = ?"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNombreDeUsuario(rs.getString("nombre_usuario"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setFechaDeRegistro(rs.getDate("fecha_registro"));
                }
            }
        }
        return usuario;
    }

    public void save(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO tu_tabla_usuarios (nombre_usuario, email, fecha_registro) VALUES (?, ?, ?)"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombreDeUsuario());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setDate(3, usuario.getFechaDeRegistro());
            pstmt.executeUpdate();
        }
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE tu_tabla_usuarios SET nombre_usuario = ?, email = ?, fecha_registro = ? WHERE id_usuario = ?"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombreDeUsuario());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setDate(3, usuario.getFechaDeRegistro());
            pstmt.setInt(4, usuario.getId());
            pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tu_tabla_usuarios WHERE id_usuario = ?"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
