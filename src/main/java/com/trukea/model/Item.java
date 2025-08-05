package com.trukea.model;

// ---------------------------------------------------------------- //
// ------------------- MODELO DE DATOS (POJO / JavaBean) ------------------- //
// ---------------------------------------------------------------- //

/**
 * Esta clase representa un "Modelo" en la arquitectura MVC.
 * Es un objeto simple (conocido como POJO o JavaBean) que sirve para almacenar
 * los datos de un registro de tu tabla.
 *
 * Por ejemplo, si tienes una tabla "Productos", esta clase podría llamarse "Producto.java"
 * y tendría campos para id, nombre, precio, stock, etc.
 *
 * --- ¡INSTRUCCIONES! ---
 * 1. Cambia el nombre de la clase "Item" por uno que represente tu tabla (ej: "Usuario", "Producto").
 * 2. Cambia los campos (id, nombre, descripcion) por los de tu tabla.
 * 3. Asegúrate de que cada campo tenga sus métodos getter y setter.
 */
public class Item {

    // --- Atributos / Campos ---
    // Representan las columnas de tu tabla en la base de datos.
    private int id;
    private String nombre;
    private String descripcion;
    // <-- AÑADE AQUÍ MÁS CAMPOS SI LOS NECESITAS (ej: private double precio;)

    // --- Constructores ---
    // Un constructor vacío es útil para crear objetos y luego llenarles los datos.
    public Item() {
    }

    // Un constructor con todos los campos es útil para crear objetos ya inicializados.
    public Item(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // --- Métodos Getter y Setter ---
    // Son métodos públicos que permiten acceder y modificar los valores de los atributos privados.
    // Esto es un principio clave de la encapsulación en la Programación Orientada a Objetos.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // <-- AÑADE AQUÍ LOS GETTERS Y SETTERS PARA TUS CAMPOS ADICIONALES -->

    // --- Método toString (Opcional) ---
    // Es útil para depurar. Permite imprimir el estado de un objeto fácilmente.
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
