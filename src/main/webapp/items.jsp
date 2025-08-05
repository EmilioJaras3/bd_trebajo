<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Items</title>
    <%-- Un poco de estilo simple para que la tabla se vea mejor --%>
    <style>
        body { font-family: sans-serif; margin: 2em; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        a { color: #007BFF; text-decoration: none; }
        .add-button { display: inline-block; margin-bottom: 1em; padding: 10px 15px; background-color: #28a745; color: white; border-radius: 3px; }
    </style>
</head>
<body>

    <!--
        VISTA (JSP) - Lista de todos los registros

        Esta es la vista principal. Su trabajo es mostrar los datos que el Controlador le envía.

        --- ¿CÓMO FUNCIONA? ---
        - El Servlet llama al DAO para obtener la lista de todos los items.
        - El Servlet guarda esa lista en el objeto 'request' con el nombre "listItems".
        - El Servlet reenvía la petición a este JSP.
        - Este JSP usa la etiqueta `<c:forEach>` de JSTL para recorrer la lista "listItems".
        - Por cada 'item' en la lista, crea una fila <tr> en la tabla con sus datos.
    -->

    <h2>Administración de Items</h2>

    <a href="items?action=new" class="add-button">Crear Nuevo Item</a>

    <table>
        <thead>
            <tr>
                <!-- ¡INSTRUCCIONES! Adapta los encabezados a tus columnas -->
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%--
                El bucle forEach itera sobre la colección 'listItems' que nos pasó el servlet.
                'var="item"' crea una variable temporal para cada elemento de la lista en cada iteración.
            --%>
            <c:forEach var="item" items="${listItems}">
                <tr>
                    <%-- Usamos ${item.propiedad} para acceder a los getters del objeto Item --%>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.nombre}" /></td>
                    <td><c:out value="${item.descripcion}" /></td>
                    <td>
                        <%--
                            Estos enlaces le envían una "acción" al servlet a través de la URL (petición GET).
                            - action=edit: Le dice al servlet que muestre el formulario de edición.
                            - action=delete: Le dice al servlet que borre este item.
                            Es importante pasar el ID para que el servlet sepa a qué item nos referimos.
                        --%>
                        <a href="items?action=edit&id=<c:out value='${item.id}'/>">Editar</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="items?action=delete&id=<c:out value='${item.id}'/>" onclick="return confirm('¿Estás seguro de que quieres borrar este item?')">Borrar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
