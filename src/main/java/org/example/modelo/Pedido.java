package org.example.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private final List<Producto> detallesPedido;

    public Pedido() {
        this.detallesPedido = new ArrayList<>();
    }

    public List<Producto> getDetallesPedido() {
        return Collections.unmodifiableList(detallesPedido);
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            System.err.println("Error: Producto vacio");
            return false;
        }
        if (cantidad <= 0) {
            System.err.println("Error: La cantidad a agregar debe ser positiva");
            return false;
        }
        if (!producto.isEsActivo()) {
            System.err.println("Error: No se puede agregar un producto inactivo");
            return false;
        }
        if (producto.getSku() == null || producto.getSku().trim().isEmpty()) {
            System.err.println("Error: Producto sin sku");
            return false;
        }
        boolean productoYaExiste = detallesPedido.stream()
                .anyMatch(p -> p.getSku().equals(producto.getSku()));
        if (productoYaExiste) {
            return false;
        } else {
            Producto linea = new Producto(producto.getSku(), producto.getNombre(), producto.getPrecio(), cantidad, producto.getCategoria(), producto.isEsActivo(), producto.isDescuentoAplicable());
            detallesPedido.add(linea);
            return true;
        }
    }

    public boolean validarStock() {
        for (Producto p : detallesPedido) {
            if (p == null) return false;
            if (p.getCantidad() <= 0) return false;
        }
        return true;
    }
}
