package com.arbol.api.model;

public class Arbol {
    private int idArbol;
    private int idEspecie;
    private String nombre;
    private int idAmbiente;
    private String ubicacion;

    public int getIdArbol() { return idArbol; }
    public void setIdArbol(int idArbol) { this.idArbol = idArbol; }
    public int getIdEspecie() { return idEspecie; }
    public void setIdEspecie(int idEspecie) { this.idEspecie = idEspecie; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getIdAmbiente() { return idAmbiente; }
    public void setIdAmbiente(int idAmbiente) { this.idAmbiente = idAmbiente; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
