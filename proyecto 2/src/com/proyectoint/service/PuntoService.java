package com.proyectoint.service;

import com.proyectoint.datastructures.PuntosIndex;
import com.proyectoint.model.PuntoRecoleccion;
import com.proyectoint.model.TipoResiduo;

import java.util.List;

public class PuntoService {

    private final PuntosIndex index;

    public PuntoService(PuntosIndex index) {
        this.index = index;
    }

    public PuntoRecoleccion crearPunto(String direccion, String barrio,
                                       double lat, double lon, TipoResiduo tipo) {
        return index.crearPunto(direccion, barrio, lat, lon, tipo);
    }

    public List<PuntoRecoleccion> listarTodos() {
        return index.listarTodos();
    }

    public List<PuntoRecoleccion> listarPorTipo(TipoResiduo tipo) {
        return index.listarPorTipo(tipo);
    }

    public PuntoRecoleccion buscarPorDireccionExacta(String direccion) {
        return index.buscarPorDireccionExacta(direccion);
    }

    public List<PuntoRecoleccion> buscarPorPrefijoDireccion(String prefijo) {
        return index.buscarPorPrefijoDireccion(prefijo);
    }
}
