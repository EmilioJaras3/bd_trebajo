package com.trukea.dao;

import com.trukea.config.DatabaseConfig;
import com.trukea.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// ---------------------------------------------------------------- //
// ------------------- DAO (Data Access Object) ------------------- //
// ---------------------------------------------------------------- //

/**
 * Esta clase es el corazón de la persistencia de datos.
 * Su única responsabilidad es comunicarse con la base de datos para realizar
 * las operaciones CRUD (Crear, Leer, Actualizar, Borrar).
 *
 * El resto de la aplicación (como los Servlets) no sabrá CÓMO se guardan los datos,
 * solo le pedirá a esta clase que los guarde o los recupere.
 *
 * --- ¡INSTRUCCIONES! ---
 * Esta es la clase más importante que debes adaptar.
 * 1. Revisa cada método.
 * 2. Reemplaza los nombres de tabla y columnas genéricos (ej: `TU_TABLA`, `TU_COLUMNA_NOMBRE`)
 *    por los nombres REALES de tu base de datos.
 */
public class ItemDAO {

    // --- CREATE (Añadir un nuevo item) ---
    public void addItem(Item item) throws SQLException {
        // --- ¡ADAPTA ESTA CONSULTA! ---
        // Reemplaza 'TU_TABLA' por el nombre de tu tabla.
        // Reemplaza 'col_nombre', 'col_desc' por los nombres de tus columnas.
        // Asegúrate de que el número de '?' coincida con el número de columnas.
        String sql = "INSERT INTO TU_TABLA (col_nombre, col_desc) VALUES (?, ?)";

        // Usamos try-with-resources para asegurar que la conexión se cierre automáticamente.
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // --- ¡ADAPTA ESTOS PARÁMETROS! ---
            // Asigna los valores del objeto 'item' a los '?' de la consulta, en orden.
            pstmt.setString(1, item.getNombre());
            pstmt.setString(2, item.getDescripcion());
            // Si tienes más columnas, añade más líneas como: pstmt.setDouble(3, item.getPrecio());

            pstmt.executeUpdate(); // Ejecuta la inserción.
        }
    }

    // --- READ (Obtener todos los items) ---
    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        // --- ¡ADAPTA ESTA CONSULTA! ---
        String sql = "SELECT id_col, col_nombre, col_desc FROM TU_TABLA"; // Reemplaza por tus columnas y tabla.

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Iteramos sobre cada fila que devuelve la consulta.
            while (rs.next()) {
                // Creamos un objeto Item por cada fila.
                Item item = new Item();

                // --- ¡ADAPTA ESTE BLOQUE! ---
                // Obtenemos los datos de cada columna por su nombre y los asignamos al objeto.
                item.setId(rs.getInt("id_col"));
                item.setNombre(rs.getString("col_nombre"));
                item.setDescripcion(rs.getString("col_desc"));
                // Si tienes más columnas: item.setPrecio(rs.getDouble("col_precio"));

                items.add(item); // Añadimos el objeto a la lista.
            }
        }
        return items;
    }

    // --- READ (Obtener un item por su ID) ---
    public Item getItemById(int id) throws SQLException {
        Item item = null;
        // --- ¡ADAPTA ESTA CONSULTA! ---
        String sql = "SELECT id_col, col_nombre, col_desc FROM TU_TABLA WHERE id_col = ?"; // Reemplaza por tus columnas y tabla.

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Asignamos el ID al '?' de la consulta.

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    item = new Item();
                    // --- ¡ADAPTA ESTE BLOQUE! ---
                    item.setId(rs.getInt("id_col"));
                    item.setNombre(rs.getString("col_nombre"));
                    item.setDescripcion(rs.getString("col_desc"));
                }
            }
        }
        return item;
    }

    // --- UPDATE (Actualizar un item existente) ---
    public void updateItem(Item item) throws SQLException {
        // --- ¡ADAPTA ESTA CONSULTA! ---
        String sql = "UPDATE TU_TABLA SET col_nombre = ?, col_desc = ? WHERE id_col = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // --- ¡ADAPTA ESTOS PARÁMETROS! ---
            // Asigna los nuevos valores a los '?'
            pstmt.setString(1, item.getNombre());
            pstmt.setString(2, item.getDescripcion());
            pstmt.setInt(3, item.getId()); // El ID va al final para el 'WHERE'.

            pstmt.executeUpdate();
        }
    }

    // --- DELETE (Borrar un item por su ID) ---
    public void deleteItem(int id) throws SQLException {
        // --- ¡ADAPTA ESTA CONSULTA! ---
        String sql = "DELETE FROM TU_TABLA WHERE id_col = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Asignamos el ID al '?' del WHERE.
            pstmt.executeUpdate();
        }
    }
}
