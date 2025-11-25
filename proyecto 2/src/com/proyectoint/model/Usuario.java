package com.proyectoint.model;

public class Usuario {

    private String cedula;
    private String nombre;
    private String correo;
    private Rol rol;

    public Usuario(String cedula, String nombre, String correo, Rol rol) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
