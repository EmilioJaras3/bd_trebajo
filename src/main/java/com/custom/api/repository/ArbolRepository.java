package com.custom.api.repository;

import com.custom.api.config.DatabaseConfig;
import com.custom.api.model.Arbol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository para el recurso Arbol, adaptado al schema del usuario.
 */
public class ArbolRepository {

    public List<Arbol> findAll() throws SQLException {
        List<Arbol> arboles = new ArrayList<>();
        String sql = "SELECT * FROM arbol";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                arboles.add(mapRowToArbol(rs));
            }
        }
        return arboles;
    }

    public Arbol findById(int id) throws SQLException {
        String sql = "SELECT * FROM arbol WHERE id_arbol = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToArbol(rs);
                }
            }
        }
        return null;
    }

    public Arbol save(Arbol arbol) throws SQLException {
        String sql = "INSERT INTO arbol (id_especie, nombre, id_ambiente, ubicacion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, arbol.getIdEspecie());
            pstmt.setString(2, arbol.getNombre());
            pstmt.setInt(3, arbol.getIdAmbiente());
            pstmt.setString(4, arbol.getUbicacion());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    arbol.setIdArbol(generatedKeys.getInt(1));
                    return arbol;
                } else {
                    throw new SQLException("La creación del árbol falló.");
                }
            }
        }
    }

    // El resto de los métodos CRUD (update, delete) se implementarían de forma similar...

    private Arbol mapRowToArbol(ResultSet rs) throws SQLException {
        Arbol arbol = new Arbol();
        arbol.setIdArbol(rs.getInt("id_arbol"));
        arbol.setIdEspecie(rs.getInt("id_especie"));
        arbol.setNombre(rs.getString("nombre"));
        arbol.setIdAmbiente(rs.getInt("id_ambiente"));
        arbol.setUbicacion(rs.getString("ubicacion"));
        return arbol;
    }
}
