
package com.mycompany.notasremisionsanantonio.logica;

public class Producto {
    private int id_producto;
    private String nombre;
    private double precio; // <-- cambiado de int a double
    private String caracteristicas;
    private double cantidad; // <-- cambiado de int a double
    private boolean estatus;

    public Producto() {
    }

    public Producto(int id_producto, String nombre, double precio, 
            String caracteristicas, double cantidad, boolean estatus) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.caracteristicas = caracteristicas;
        this.cantidad = cantidad;
        this.estatus = estatus;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
