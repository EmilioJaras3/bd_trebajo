package com.custom.api.model;

public class Cuidado {
    private int idCuidado;
    private String fertilizante;
    private String insecticidas;

    // Getters y Setters
    public int getIdCuidado() { return idCuidado; }
    public void setIdCuidado(int idCuidado) { this.idCuidado = idCuidado; }
    public String getFertilizante() { return fertilizante; }
    public void setFertilizante(String fertilizante) { this.fertilizante = fertilizante; }
    public String getInsecticidas() { return insecticidas; }
    public void setInsecticidas(String insecticidas) { this.insecticidas = insecticidas; }
}
