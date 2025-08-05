<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Item</title>
    <%-- Un poco de estilo simple para que el formulario se vea mejor --%>
    <style>
        body { font-family: sans-serif; margin: 2em; }
        form { border: 1px solid #ccc; padding: 1em; border-radius: 5px; max-width: 500px; }
        div { margin-bottom: 1em; }
        label { display: block; margin-bottom: 0.5em; }
        input[type="text"] { width: 100%; padding: 8px; box-sizing: border-box; }
        button { padding: 10px 15px; background-color: #007BFF; color: white; border: none; border-radius: 3px; cursor: pointer; }
        h2 { color: #333; }
    </style>
</head>
<body>

    <!--
        VISTA (JSP) - Formulario para Crear y Editar

        Este archivo JSP es una VISTA en el patrón MVC. Su única responsabilidad es mostrar el HTML.
        No contiene lógica de negocio.

        --- ¿CÓMO FUNCIONA? ---
        Usa JSTL (JSP Standard Tag Library) con el prefijo 'c:' para la lógica de presentación.
        - `<c:if>`: Permite mostrar contenido condicionalmente.
        - `${expresion}`: Es Expression Language (EL), usado para acceder a los datos que el Servlet nos envió.
          Por ejemplo, `${item.nombre}` accede al método getNombre() del objeto 'item' que pusimos en el request.

        Este mismo formulario sirve para CREAR y para EDITAR:
        - Si el servlet nos envía un objeto 'item', significa que estamos EDITANDO. Rellenamos los campos con sus datos.
        - Si el servlet NO nos envía un objeto 'item', significa que estamos CREANDO. Mostramos los campos vacíos.
    -->

    <h2>
        <c:if test="${item != null}">
            Editar Item
        </c:if>
        <c:if test="${item == null}">
            Crear Nuevo Item
        </c:if>
    </h2>

    <%-- El formulario envía los datos al ItemServlet usando el método POST --%>
    <form action="items" method="post">

        <%--
            Este es un truco común en servlets. Usamos un campo oculto 'action'
            para decirle al servlet qué operación queremos realizar (insertar o actualizar).
            El servlet leerá este parámetro en el doPost.
        --%>
        <c:if test="${item != null}">
            <input type="hidden" name="action" value="update" />
            <input type="hidden" name="id" value="<c:out value='${item.id}' />" />
        </c:if>
        <c:if test="${item == null}">
            <input type="hidden" name="action" value="insert" />
        </c:if>

        <div>
            <label for="nombre">Nombre:</label>
            <%-- El tag <c:out> sirve para imprimir un valor de forma segura, evitando ataques XSS. --%>
            <input type="text" id="nombre" name="nombre" value="<c:out value='${item.nombre}' />" required>
        </div>

        <div>
            <label for="descripcion">Descripción:</label>
            <input type="text" id="descripcion" name="descripcion" value="<c:out value='${item.descripcion}' />" required>
        </div>

        <%-- ¡INSTRUCCIONES!
             Si añadiste más campos a tu modelo (Item.java),
             debes añadir los campos correspondientes aquí en el formulario.
             Ejemplo:
             <div>
                 <label for="precio">Precio:</label>
                 <input type="text" id="precio" name="precio" value="<c:out value='${item.precio}' />" required>
             </div>
        --%>

        <div>
            <button type="submit">Guardar</button>
        </div>
    </form>

    <p><a href="items">Volver a la lista</a></p>

</body>
</html>
