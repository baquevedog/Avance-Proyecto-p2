package com.proyectoint.datastructures;

import com.proyectoint.model.Rol;
import com.proyectoint.model.Usuario;

import java.util.*;

public class UsuariosIndex {

    private final TreeMap<String, Usuario> byCedula = new TreeMap<>();
    private final HashMap<String, Usuario> byCorreo = new HashMap<>();
    private final HashMap<Rol, HashSet<String>> byRol = new HashMap<>();
    private final Deque<Usuario> undoStack = new ArrayDeque<>();

    public void addUsuario(Usuario u) {
        byCedula.put(u.getCedula(), u);
        byCorreo.put(u.getCorreo(), u);
        byRol.computeIfAbsent(u.getRol(), r -> new HashSet<>()).add(u.getCedula());
    }

    public void updateUsuario(Usuario u) {
        Usuario anterior = byCedula.get(u.getCedula());
        if (anterior != null) {
            undoStack.push(cloneUsuario(anterior));
        }
        addUsuario(u);
    }

    public void removeUsuario(String cedula) {
        Usuario u = byCedula.remove(cedula);
        if (u != null) {
            byCorreo.remove(u.getCorreo());
            HashSet<String> set = byRol.get(u.getRol());
            if (set != null) {
                set.remove(cedula);
            }
        }
    }

    public Usuario buscarPorCedula(String cedula) {
        return byCedula.get(cedula);
    }

    public Usuario buscarPorCorreo(String correo) {
        return byCorreo.get(correo);
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(byCedula.values());
    }

    public List<Usuario> listarPorRol(Rol rol) {
        HashSet<String> ids = byRol.getOrDefault(rol, new HashSet<>());
        List<Usuario> result = new ArrayList<>();
        for (String id : ids) {
            Usuario u = byCedula.get(id);
            if (u != null) {
                result.add(u);
            }
        }
        return result;
    }

    public Optional<Usuario> undo() {
        if (undoStack.isEmpty()) {
            return Optional.empty();
        }
        Usuario anterior = undoStack.pop();
        addUsuario(anterior);
        return Optional.of(anterior);
    }

    private Usuario cloneUsuario(Usuario u) {
        return new Usuario(u.getCedula(), u.getNombre(), u.getCorreo(), u.getRol());
    }
}
