package com.custom.api.model;

public class Cuidador {
    private int idCuidador;
    private String nombre;
    private int idArbol;
    private int idCuidado;

    // Getters y Setters
    public int getIdCuidador() { return idCuidador; }
    public void setIdCuidador(int idCuidador) { this.idCuidador = idCuidador; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getIdArbol() { return idArbol; }
    public void setIdArbol(int idArbol) { this.idArbol = idArbol; }
    public int getIdCuidado() { return idCuidado; }
    public void setIdCuidado(int idCuidado) { this.idCuidado = idCuidado; }
}
