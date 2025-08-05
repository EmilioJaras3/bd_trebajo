package com.trukea.model;

import java.sql.Date;

// ---------------------------------------------------------------- //
// ------------------- MODELO: Usuario ---------------------------- //
// ---------------------------------------------------------------- //

/**
 * Modelo de datos para un Usuario. Es un "POJO" (Plain Old Java Object).
 * Sus instancias representan una fila en tu tabla de 'usuarios'.
 *
 * --- ¡INSTRUCCIONES! ---
 * - Adapta los campos a las columnas de tu tabla de usuarios.
 * - No olvides añadir/quitar los getters y setters correspondientes.
 */
public class Usuario {

    private int id;
    private String nombreDeUsuario;
    private String email;
    private Date fechaDeRegistro; // Usamos java.sql.Date para mapear directamente con el tipo DATE de SQL.

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con datos
    public Usuario(int id, String nombreDeUsuario, String email, Date fechaDeRegistro) {
        this.id = id;
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.fechaDeRegistro = fechaDeRegistro;
    }

    // --- Getters y Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(Date fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }
}
