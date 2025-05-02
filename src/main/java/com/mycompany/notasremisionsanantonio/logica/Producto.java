
package com.mycompany.notasremisionsanantonio.logica;

public class Producto {
    private int id_producto;
    private String nombre;
    private int precio;

    public Producto() {
    }

    public Producto(int id_producto, String nombre, int precio) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
