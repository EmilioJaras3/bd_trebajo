package com.trukea.controller;

import com.trukea.dao.ItemDAO;
import com.trukea.model.Item;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// --- NOTA SOBRE LOS IMPORTS ---
// Si usas un servidor de aplicaciones más antiguo (como Tomcat 9 o inferior),
// puede que necesites usar `javax.servlet.*` en lugar de `jakarta.servlet.*`.
// import javax.servlet.RequestDispatcher;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// ---------------------------------------------------------------- //
// ------------------- CONTROLADOR (Servlet) ------------------- //
// ---------------------------------------------------------------- //

/**
 * El Controlador es el "cerebro" de la aplicación en el patrón MVC.
 * Su trabajo es:
 * 1. Recibir las peticiones del usuario (desde el navegador).
 * 2. Decidir qué hacer con esa petición (ej: "¿quiere ver la lista?" o "¿quiere borrar un item?").
 * 3. Hablar con el Modelo (el DAO) para obtener o guardar datos.
 * 4. Enviar los datos a la Vista (el JSP) para que se muestren al usuario.
 *
 * La anotación `@WebServlet` mapea este servlet a una URL. Todas las peticiones a
 * `http://tu-servidor/tu-app/items` serán manejadas por esta clase.
 */
@WebServlet("/items")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemDAO itemDAO;

    // El método init() se llama una sola vez cuando el servlet se carga por primera vez.
    public void init() {
        itemDAO = new ItemDAO();
    }

    // --- MANEJA LAS PETICIONES POST (enviar datos desde un formulario) ---
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // En lugar de tener un servlet para cada acción, usamos un parámetro "action".
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Acción por defecto.
        }

        try {
            switch (action) {
                case "insert":
                    insertItem(request, response);
                    break;
                case "update":
                    updateItem(request, response);
                    break;
                default:
                    listItems(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // --- MANEJA LAS PETICIONES GET (acceder a una URL) ---
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Acción por defecto.
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteItem(request, response);
                    break;
                default: // "list" o cualquier otro caso
                    listItems(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // --- MÉTODOS DE ACCIÓN (lógica del controlador) ---

    private void listItems(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // 1. Llama al DAO para obtener la lista de items.
        List<Item> listItems = itemDAO.getAllItems();
        // 2. Guarda la lista en el objeto 'request' para que el JSP pueda acceder a ella.
        request.setAttribute("listItems", listItems);
        // 3. Reenvía la petición al JSP para que renderice la vista.
        RequestDispatcher dispatcher = request.getRequestDispatcher("items.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simplemente reenvía al JSP que contiene el formulario.
        RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        // 1. Obtiene el ID del item a editar desde la URL.
        int id = Integer.parseInt(request.getParameter("id"));
        // 2. Llama al DAO para obtener los datos de ese item.
        Item existingItem = itemDAO.getItemById(id);
        // 3. Guarda el item en el 'request' y reenvía al mismo formulario.
        request.setAttribute("item", existingItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // 1. Obtiene los datos enviados desde el formulario.
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        // 2. Crea un nuevo objeto Item con esos datos.
        Item newItem = new Item(0, nombre, descripcion); // El ID no importa para la inserción.
        // 3. Llama al DAO para guardarlo en la base de datos.
        itemDAO.addItem(newItem);
        // 4. Redirige al usuario de vuelta a la lista de items.
        response.sendRedirect("items");
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // 1. Obtiene los datos del formulario, incluyendo el ID.
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        // 2. Crea un objeto Item con los datos actualizados.
        Item item = new Item(id, nombre, descripcion);
        // 3. Llama al DAO para actualizarlo.
        itemDAO.updateItem(item);
        // 4. Redirige a la lista.
        response.sendRedirect("items");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // 1. Obtiene el ID del item a borrar desde la URL.
        int id = Integer.parseInt(request.getParameter("id"));
        // 2. Llama al DAO para borrarlo.
        itemDAO.deleteItem(id);
        // 3. Redirige a la lista.
        response.sendRedirect("items");
    }
}
