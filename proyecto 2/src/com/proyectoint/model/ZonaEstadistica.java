package com.proyectoint.model;

public class ZonaEstadistica implements Comparable<ZonaEstadistica> {

    private String zona;
    private double totalKg;

    public ZonaEstadistica(String zona, double totalKg) {
        this.zona = zona;
        this.totalKg = totalKg;
    }

    public String getZona() {
        return zona;
    }

    public double getTotalKg() {
        return totalKg;
    }

    @Override
    public int compareTo(ZonaEstadistica other) {
        // Para PriorityQueue como max-heap (mayor primero)
        return Double.compare(other.totalKg, this.totalKg);
    }
}
