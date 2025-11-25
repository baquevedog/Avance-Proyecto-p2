package com.proyectoint.service;

import com.proyectoint.datastructures.ReportesIndex;
import com.proyectoint.model.Reporte;
import com.proyectoint.model.TipoResiduo;
import com.proyectoint.model.ZonaEstadistica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ReporteService {

    private final ReportesIndex index;

    public ReporteService(ReportesIndex index) {
        this.index = index;
    }

    public Reporte crearReporte(LocalDate fecha, String zona,
                                TipoResiduo tipo, double pesoKg) {
        return index.crearReporte(fecha, zona, tipo, pesoKg);
    }

    public List<Reporte> listarTodos() {
        return index.listarTodos();
    }

    public List<Reporte> buscarPorRangoFechas(LocalDate desde, LocalDate hasta) {
        return index.buscarPorRangoFechas(desde, hasta);
    }

    public List<Reporte> buscarPorZona(String zona) {
        return index.buscarPorZona(zona);
    }

    public List<ZonaEstadistica> topZonas(int k) {
        PriorityQueue<ZonaEstadistica> pq = index.calcularTopZonas();
        List<ZonaEstadistica> result = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            result.add(pq.poll());
        }
        return result;
    }
}
