package com.arbol.api.repository;

import com.arbol.api.config.DatabaseConfig;
import com.arbol.api.model.Arbol;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            setArbolParameters(pstmt, arbol);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Fallo al crear el árbol.");

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    arbol.setIdArbol(generatedKeys.getInt(1));
                    return arbol;
                } else {
                    throw new SQLException("Fallo al crear el árbol, no se obtuvo ID.");
                }
            }
        }
    }

    public void update(Arbol arbol) throws SQLException {
        String sql = "UPDATE arbol SET id_especie = ?, nombre = ?, id_ambiente = ?, ubicacion = ? WHERE id_arbol = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setArbolParameters(pstmt, arbol);
            pstmt.setInt(5, arbol.getIdArbol());
            pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM arbol WHERE id_arbol = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Arbol mapRowToArbol(ResultSet rs) throws SQLException {
        Arbol arbol = new Arbol();
        arbol.setIdArbol(rs.getInt("id_arbol"));
        arbol.setIdEspecie(rs.getInt("id_especie"));
        arbol.setNombre(rs.getString("nombre"));
        arbol.setIdAmbiente(rs.getInt("id_ambiente"));
        arbol.setUbicacion(rs.getString("ubicacion"));
        return arbol;
    }

    private void setArbolParameters(PreparedStatement pstmt, Arbol arbol) throws SQLException {
        pstmt.setInt(1, arbol.getIdEspecie());
        pstmt.setString(2, arbol.getNombre());
        pstmt.setInt(3, arbol.getIdAmbiente());
        pstmt.setString(4, arbol.getUbicacion());
    }
}
