package com.custom.api.model;

public class Especie {
    private int idEspecie;
    private int idAmbiente;
    private String tipoDeSuelo;
    private String nombre;

    // Getters y Setters
    public int getIdEspecie() { return idEspecie; }
    public void setIdEspecie(int idEspecie) { this.idEspecie = idEspecie; }
    public int getIdAmbiente() { return idAmbiente; }
    public void setIdAmbiente(int idAmbiente) { this.idAmbiente = idAmbiente; }
    public String getTipoDeSuelo() { return tipoDeSuelo; }
    public void setTipoDeSuelo(String tipoDeSuelo) { this.tipoDeSuelo = tipoDeSuelo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
