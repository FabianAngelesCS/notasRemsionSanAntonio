/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.notasremisionsanantonio.igu;

/**
 *
 * @author Sebaz
 */
public class Producto {
    private int id;
    private String nombre;
    private String caracteristicas;
    private Double precio;
    private boolean estatus;
    private int cantidad;
    
    public Producto(){
    }
    
    public Producto(int id, String nombre, String caracteristicas, Double precio, boolean estatus,int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.precio = precio;
        this.estatus = estatus;
        this.cantidad = cantidad;
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public Double getPrecio() {
        return precio;
    }

    public boolean isEstatus() { // Para booleanos, el getter suele ser 'is'
        return estatus;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    
    public int setId() {
        return id;
    }

    public String setNombre() {
        return nombre;
    }

    public String setCaracteristicas() {
        return caracteristicas;
    }

    public Double setPrecio() {
        return precio;
    }

    public boolean setEstatus() { // Para booleanos, el getter suele ser 'is'
        return estatus;
    }
    
    public int setCantidad() {
        return cantidad;
    }
    
    @Override
    public String toString() {
        return "Producto{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", caracteristicas='" + caracteristicas + '\'' +
               ", precio='" + precio + '\'' +
               ", estatus=" + estatus +'\''+
               ", cantidads='" + cantidad +  
               '}';
    }
    
}
