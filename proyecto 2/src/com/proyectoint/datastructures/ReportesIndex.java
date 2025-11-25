package com.proyectoint.datastructures;

import com.proyectoint.model.Reporte;
import com.proyectoint.model.ZonaEstadistica;

import java.time.LocalDate;
import java.util.*;

public class ReportesIndex {

    private final TreeMap<LocalDate, List<Reporte>> byFecha = new TreeMap<>();
    private final HashMap<String, List<Reporte>> byZona = new HashMap<>();

    private int nextId = 1;

    public Reporte crearReporte(LocalDate fecha, String zona,
                                com.proyectoint.model.TipoResiduo tipo, double pesoKg) {
        Reporte r = new Reporte(nextId++, fecha, zona, tipo, pesoKg);
        byFecha.computeIfAbsent(fecha, f -> new ArrayList<>()).add(r);
        byZona.computeIfAbsent(zona.toLowerCase(), z -> new ArrayList<>()).add(r);
        return r;
    }

    public List<Reporte> listarTodos() {
        List<Reporte> result = new ArrayList<>();
        for (List<Reporte> l : byFecha.values()) {
            result.addAll(l);
        }
        return result;
    }

    public List<Reporte> buscarPorRangoFechas(LocalDate desde, LocalDate hasta) {
        SortedMap<LocalDate, List<Reporte>> sub = byFecha.subMap(desde, true, hasta, true);
        List<Reporte> result = new ArrayList<>();
        for (List<Reporte> l : sub.values()) {
            result.addAll(l);
        }
        return result;
    }

    public List<Reporte> buscarPorZona(String zona) {
        return new ArrayList<>(byZona.getOrDefault(zona.toLowerCase(), new ArrayList<>()));
    }

    public PriorityQueue<ZonaEstadistica> calcularTopZonas() {
        HashMap<String, Double> acumulado = new HashMap<>();
        for (List<Reporte> lista : byFecha.values()) {
            for (Reporte r : lista) {
                acumulado.merge(r.getZona().toLowerCase(), r.getPesoKg(), Double::sum);
            }
        }
        PriorityQueue<ZonaEstadistica> pq = new PriorityQueue<>();
        for (Map.Entry<String, Double> e : acumulado.entrySet()) {
            pq.offer(new ZonaEstadistica(e.getKey(), e.getValue()));
        }
        return pq;
    }
}
