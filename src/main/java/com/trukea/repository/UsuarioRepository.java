package com.trukea.repository;

import com.trukea.config.DatabaseConfig;
import com.trukea.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    public List<Usuario> getAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre_de_usuario, email, fecha_de_registro FROM tu_tabla_usuarios";
        try (Connection conn = DatabaseConfig.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNombreDeUsuario(rs.getString("nombre_de_usuario"));
                u.setEmail(rs.getString("email"));
                u.setFechaDeRegistro(rs.getDate("fecha_de_registro"));
                usuarios.add(u);
            }
        }
        return usuarios;
    }

    // etc...
}
