package org.example.service;

import org.example.modelo.Producto;


public class ServicioX {


    public static boolean validarDescuentoAplicable(Producto p, double porcentaje) {
        if (p == null) return false;
        if (!p.isDescuentoAplicable()) return false;
        return porcentaje >= 0.0 && porcentaje <= 50.0;
    }
}