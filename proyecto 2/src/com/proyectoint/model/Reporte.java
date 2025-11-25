package com.proyectoint.model;

import java.time.LocalDate;

public class Reporte {

    private int id;
    private LocalDate fecha;
    private String zona;
    private TipoResiduo tipoResiduo;
    private double pesoKg;

    public Reporte(int id, LocalDate fecha, String zona, TipoResiduo tipoResiduo, double pesoKg) {
        this.id = id;
        this.fecha = fecha;
        this.zona = zona;
        this.tipoResiduo = tipoResiduo;
        this.pesoKg = pesoKg;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getZona() {
        return zona;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public double getPesoKg() {
        return pesoKg;
    }
}
