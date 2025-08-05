package com.trukea.dao;

import com.trukea.config.DatabaseConfig;
import com.trukea.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para el recurso Usuario.
 * Contiene toda la lógica de base de datos (CRUD) para los usuarios.
 *
 * --- ¡INSTRUCCIONES! ---
 * Adapta todas las sentencias SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`)
 * para que coincidan con los nombres de tu tabla y columnas de usuarios.
 */
public class UsuarioDAO {

    public List<Usuario> getAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre_usuario, email, fecha_registro FROM tu_tabla_usuarios"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario")); // <-- ADAPTAR
                usuario.setNombreDeUsuario(rs.getString("nombre_usuario")); // <-- ADAPTAR
                usuario.setEmail(rs.getString("email")); // <-- ADAPTAR
                usuario.setFechaDeRegistro(rs.getDate("fecha_registro")); // <-- ADAPTAR
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
                    usuario.setId(rs.getInt("id_usuario")); // <-- ADAPTAR
                    usuario.setNombreDeUsuario(rs.getString("nombre_usuario")); // <-- ADAPTAR
                    usuario.setEmail(rs.getString("email")); // <-- ADAPTAR
                    usuario.setFechaDeRegistro(rs.getDate("fecha_registro")); // <-- ADAPTAR
                }
            }
        }
        return usuario;
    }

    public void add(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO tu_tabla_usuarios (nombre_usuario, email, fecha_registro) VALUES (?, ?, ?)"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombreDeUsuario()); // <-- ADAPTAR
            pstmt.setString(2, usuario.getEmail()); // <-- ADAPTAR
            pstmt.setDate(3, usuario.getFechaDeRegistro()); // <-- ADAPTAR
            pstmt.executeUpdate();
        }
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE tu_tabla_usuarios SET nombre_usuario = ?, email = ?, fecha_registro = ? WHERE id_usuario = ?"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombreDeUsuario()); // <-- ADAPTAR
            pstmt.setString(2, usuario.getEmail()); // <-- ADAPTAR
            pstmt.setDate(3, usuario.getFechaDeRegistro()); // <-- ADAPTAR
            pstmt.setInt(4, usuario.getId()); // <-- ADAPTAR (el ID para el WHERE)
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
