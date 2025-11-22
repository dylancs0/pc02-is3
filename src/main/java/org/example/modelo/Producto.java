package org.example.modelo;

import java.util.Objects;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private String sku;
    private String categoria;
    private boolean esActivo;
    private boolean descuentoAplicable;

    public Producto(String sku, String nombre, double precio, int cantidad, String categoria, boolean esActivo, boolean descuentoAplicable) {
        if (sku == null || sku.trim().isEmpty()) throw new IllegalArgumentException("Sku no puede ser vacio");
        if (precio < 0) throw new IllegalArgumentException("Precio no puede ser negativo");
        if (cantidad < 0) throw new IllegalArgumentException("Cantidad no puede ser negativa");
        this.sku = sku;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.esActivo = esActivo;
        this.descuentoAplicable = descuentoAplicable;
    }

    public Producto(String sku, String nombre, double precio, int cantidad) {
        this(sku, nombre, precio, cantidad, null, true, false);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("Precio no puede ser negativo");
        this.precio = precio;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("Cantidad no puede ser negativa");
        this.cantidad = cantidad;
    }

    public String getSku() { return sku; }
    public void setSku(String sku) {
        if (sku == null || sku.trim().isEmpty()) throw new IllegalArgumentException("sku no puede ser vacio");
        this.sku = sku;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public boolean isEsActivo() { return esActivo; }
    public void setEsActivo(boolean esActivo) { this.esActivo = esActivo; }

    public boolean isDescuentoAplicable() { return descuentoAplicable; }
    public void setDescuentoAplicable(boolean descuentoAplicable) { this.descuentoAplicable = descuentoAplicable; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(getSku(), producto.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSku());
    }

    @Override
    public String toString() {
        return "Producto{" +
                "sku='" + sku + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", categoria='" + categoria + '\'' +
                ", esActivo=" + esActivo +
                ", descuentoAplicable=" + descuentoAplicable +
                '}';
    }
}
