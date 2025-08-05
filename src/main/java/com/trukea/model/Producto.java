package com.trukea.model;

// ---------------------------------------------------------------- //
// ------------------- MODELO: Producto --------------------------- //
// ---------------------------------------------------------------- //

/**
 * Modelo de datos para un Producto. Es un "POJO" (Plain Old Java Object).
 * Sus instancias representan una fila en tu tabla de 'productos'.
 *
 * --- ¡INSTRUCCIONES! ---
 * - Adapta los campos (id, nombre, precio, stock) a las columnas de tu tabla de productos.
 * - No olvides añadir/quitar los getters y setters correspondientes.
 */
public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int stock;

    // Constructor vacío, requerido por algunas librerías como Jackson para deserializar JSON.
    public Producto() {
    }

    // Constructor para crear objetos con datos iniciales.
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // --- Getters y Setters ---
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
