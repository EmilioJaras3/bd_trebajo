package com.trukea.dao;

import com.trukea.config.DatabaseConfig;
import com.trukea.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para el recurso Producto.
 * Contiene toda la lógica de base de datos (CRUD) para los productos.
 *
 * --- ¡INSTRUCCIONES! ---
 * Adapta todas las sentencias SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`)
 * para que coincidan con los nombres de tu tabla y columnas de productos.
 */
public class ProductoDAO {

    public List<Producto> getAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio, stock FROM tu_tabla_productos"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id_producto")); // <-- ADAPTAR
                producto.setNombre(rs.getString("nombre")); // <-- ADAPTAR
                producto.setPrecio(rs.getDouble("precio")); // <-- ADAPTAR
                producto.setStock(rs.getInt("stock")); // <-- ADAPTAR
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
                    producto.setId(rs.getInt("id_producto")); // <-- ADAPTAR
                    producto.setNombre(rs.getString("nombre")); // <-- ADAPTAR
                    producto.setPrecio(rs.getDouble("precio")); // <-- ADAPTAR
                    producto.setStock(rs.getInt("stock")); // <-- ADAPTAR
                }
            }
        }
        return producto;
    }

    public void add(Producto producto) throws SQLException {
        String sql = "INSERT INTO tu_tabla_productos (nombre, precio, stock) VALUES (?, ?, ?)"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre()); // <-- ADAPTAR
            pstmt.setDouble(2, producto.getPrecio()); // <-- ADAPTAR
            pstmt.setInt(3, producto.getStock()); // <-- ADAPTAR
            pstmt.executeUpdate();
        }
    }

    public void update(Producto producto) throws SQLException {
        String sql = "UPDATE tu_tabla_productos SET nombre = ?, precio = ?, stock = ? WHERE id_producto = ?"; // <-- ADAPTAR

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre()); // <-- ADAPTAR
            pstmt.setDouble(2, producto.getPrecio()); // <-- ADAPTAR
            pstmt.setInt(3, producto.getStock()); // <-- ADAPTAR
            pstmt.setInt(4, producto.getId()); // <-- ADAPTAR (el ID para el WHERE)
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
