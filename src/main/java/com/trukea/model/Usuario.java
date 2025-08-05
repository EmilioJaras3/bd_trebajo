package com.trukea.model;

import java.sql.Date;

public class Usuario {
    private int id;
    private String nombreDeUsuario;
    private String email;
    private Date fechaDeRegistro;

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
