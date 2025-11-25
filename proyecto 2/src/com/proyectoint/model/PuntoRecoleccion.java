package com.proyectoint.model;

public class PuntoRecoleccion {

    private int id;
    private String direccion;
    private String barrio;
    private double latitud;
    private double longitud;
    private TipoResiduo tipoResiduo;

    public PuntoRecoleccion(int id, String direccion, String barrio, double latitud, double longitud,
                            TipoResiduo tipoResiduo) {
        this.id = id;
        this.direccion = direccion;
        this.barrio = barrio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipoResiduo = tipoResiduo;
    }

    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }
}
