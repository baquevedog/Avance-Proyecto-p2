package com.proyectoint.service;

import com.proyectoint.datastructures.UsuariosIndex;
import com.proyectoint.model.Rol;
import com.proyectoint.model.Usuario;

import java.util.List;

public class UsuarioService {

    private final UsuariosIndex index;

    public UsuarioService(UsuariosIndex index) {
        this.index = index;
    }

    public void crearUsuario(String cedula, String nombre, String correo, Rol rol) {
        Usuario u = new Usuario(cedula, nombre, correo, rol);
        index.addUsuario(u);
    }

    public void actualizarUsuario(String cedula, String nombre, String correo, Rol rol) {
        Usuario u = new Usuario(cedula, nombre, correo, rol);
        index.updateUsuario(u);
    }

    public void eliminarUsuario(String cedula) {
        index.removeUsuario(cedula);
    }

    public Usuario buscarPorCedula(String cedula) {
        return index.buscarPorCedula(cedula);
    }

    public Usuario buscarPorCorreo(String correo) {
        return index.buscarPorCorreo(correo);
    }

    public List<Usuario> listarTodos() {
        return index.listarTodos();
    }

    public List<Usuario> listarPorRol(Rol rol) {
        return index.listarPorRol(rol);
    }

    public void deshacer() {
        index.undo();
    }
}
