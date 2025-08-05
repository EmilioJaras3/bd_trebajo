package com.trukea.repository;

import com.trukea.config.DatabaseConfig;
import com.trukea.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository para el recurso Producto.
 * Esta capa se comunica directamente con la base de datos.
 * Es el equivalente al patrón DAO (Data Access Object).
 *
 * --- ¡INSTRUCCIONES! ---
 * Adapta todas las sentencias SQL para que coincidan con tu tabla de productos.
 */
public class ProductoRepository {

    public List<Producto> getAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio, stock FROM tu_tabla_productos"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public Producto findById(int id) throws SQLException {
        Producto producto = null;
        String sql = "SELECT id_producto, nombre, precio, stock FROM tu_tabla_productos WHERE id_producto = ?"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setId(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setStock(rs.getInt("stock"));
                }
            }
        }
        return producto;
    }

    public void save(Producto producto) throws SQLException {
        String sql = "INSERT INTO tu_tabla_productos (nombre, precio, stock) VALUES (?, ?, ?)"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getStock());
            pstmt.executeUpdate();
        }
    }

    public void update(Producto producto) throws SQLException {
        String sql = "UPDATE tu_tabla_productos SET nombre = ?, precio = ?, stock = ? WHERE id_producto = ?"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getStock());
            pstmt.setInt(4, producto.getId());
            pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tu_tabla_productos WHERE id_producto = ?"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
