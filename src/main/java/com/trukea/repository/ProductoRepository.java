package com.trukea.repository;

import com.trukea.config.DatabaseConfig;
import com.trukea.model.Producto;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {
    public List<Producto> getAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio, stock FROM tu_tabla_productos";
        try (Connection conn = DatabaseConfig.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(mapRowToProducto(rs));
            }
        }
        return productos;
    }

    public Producto findById(int id) throws SQLException {
        String sql = "SELECT * FROM tu_tabla_productos WHERE id_producto = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToProducto(rs);
                }
            }
        }
        return null;
    }

    public void save(Producto producto) throws SQLException {
        String sql = "INSERT INTO tu_tabla_productos (nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setBigDecimal(2, producto.getPrecio());
            pstmt.setInt(3, producto.getStock());
            pstmt.executeUpdate();
        }
    }

    // update y delete...

    private Producto mapRowToProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getBigDecimal("precio"));
        p.setStock(rs.getInt("stock"));
        return p;
    }
}
