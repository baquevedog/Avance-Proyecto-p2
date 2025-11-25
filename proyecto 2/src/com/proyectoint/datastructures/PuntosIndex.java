package com.proyectoint.datastructures;

import com.proyectoint.model.PuntoRecoleccion;
import com.proyectoint.model.TipoResiduo;

import java.util.*;

public class PuntosIndex {

    private final ArrayList<PuntoRecoleccion> puntos = new ArrayList<>();
    private final HashMap<TipoResiduo, HashSet<Integer>> byTipo = new HashMap<>();
    private final TreeMap<String, PuntoRecoleccion> byDireccion = new TreeMap<>();

    private int nextId = 1;

    public PuntoRecoleccion crearPunto(String direccion, String barrio,
                                       double lat, double lon, TipoResiduo tipo) {
        PuntoRecoleccion p = new PuntoRecoleccion(nextId++, direccion, barrio, lat, lon, tipo);
        puntos.add(p);
        byTipo.computeIfAbsent(tipo, t -> new HashSet<>()).add(p.getId());
        byDireccion.put(direccion.toLowerCase(), p);
        return p;
    }

    public List<PuntoRecoleccion> listarTodos() {
        return new ArrayList<>(puntos);
    }

    public List<PuntoRecoleccion> listarPorTipo(TipoResiduo tipo) {
        HashSet<Integer> ids = byTipo.getOrDefault(tipo, new HashSet<>());
        List<PuntoRecoleccion> result = new ArrayList<>();
        for (PuntoRecoleccion p : puntos) {
            if (ids.contains(p.getId())) {
                result.add(p);
            }
        }
        return result;
    }

    public PuntoRecoleccion buscarPorDireccionExacta(String direccion) {
        return byDireccion.get(direccion.toLowerCase());
    }

    public List<PuntoRecoleccion> buscarPorPrefijoDireccion(String prefijo) {
        String fromKey = prefijo.toLowerCase();
        String toKey = fromKey + Character.MAX_VALUE;

        SortedMap<String, PuntoRecoleccion> sub = byDireccion.subMap(fromKey, toKey);
        return new ArrayList<>(sub.values());
    }
}
