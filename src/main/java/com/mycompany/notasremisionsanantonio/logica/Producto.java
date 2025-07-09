
package com.mycompany.notasremisionsanantonio.logica;

public class Producto {
    private int id_producto;
    private String nombre;
    private Double precio;
    private String caracteristicas;
    private int cantidad; // <-- cambiado de int a double
    private boolean estatus;

    public Producto() {
    }

    public Producto(int id_producto, String nombre, Double precio, 
            String caracteristicas, int cantidad, boolean estatus) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEstatus(boolean estatus) {
        
        this.estatus = estatus; // Asigna el valor del parámetro al atributo de la clase
    }

    public boolean isEstatus() {
        return estatus;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
