package com.example.coronaapp.modelo;

import java.util.ArrayList;
import java.util.List;

public class TablaService {

    public static List<Tabla> tablaList = new ArrayList<>();

    public static void addElemento(Tabla tabla) { tablaList.add(tabla); }

    public static void removeElemento(Tabla tabla) { tablaList.remove(tabla); }

    public static void updateElemento(Tabla tabla) {
        tablaList.set(tablaList.indexOf(tabla), tabla); }
}
