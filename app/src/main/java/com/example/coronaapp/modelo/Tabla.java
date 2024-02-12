package com.example.coronaapp.modelo;

public class Tabla {
    private String id;
    private double incidencia14;
    private double incidencia7;

    public Tabla() {
    }

    public Tabla(String id, double incidencia14, double incidencia7) {
        this.id = id;
        this.incidencia14 = incidencia14;
        this.incidencia7 = incidencia7;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getIncidencia14() { return incidencia14; }

    public void setIncidencia14(double incidencia14) { this.incidencia14 = incidencia14; }

    public double getIncidencia7() { return incidencia7;    }

    public void setIncidencia7(double incidencia7) { this.incidencia7 = incidencia7; }

    public boolean equals(Object obj) {
        return id.equals(((Tabla)obj).id);
    }

}
