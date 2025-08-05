package com.trukea.model;

import java.sql.Date;

/**
 * Modelo de datos (POJO) para un Usuario.
 * Representa una fila en la tabla de usuarios.
 */
public class Usuario {

    private int id;
    private String nombreDeUsuario;
    private String email;
    private Date fechaDeRegistro;

    public Usuario() {
    }

    public Usuario(int id, String nombreDeUsuario, String email, Date fechaDeRegistro) {
        this.id = id;
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.fechaDeRegistro = fechaDeRegistro;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombreDeUsuario() { return nombreDeUsuario; }
    public void setNombreDeUsuario(String nombreDeUsuario) { this.nombreDeUsuario = nombreDeUsuario; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getFechaDeRegistro() { return fechaDeRegistro; }
    public void setFechaDeRegistro(Date fechaDeRegistro) { this.fechaDeRegistro = fechaDeRegistro; }
}
