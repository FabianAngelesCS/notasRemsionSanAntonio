
package com.mycompany.notasremisionsanantonio.logica;

public class Producto {
    private int id_producto;
    private String producto;
    private int precio;
    private String caracteristicas;
    private int cantidad;
    

    public Producto() {
        
    }

    public Producto(int id_producto, String producto, int precio, 
            String caracteristicas, int cantidad) {
        this.id_producto = id_producto;
        this.producto = producto;
        this.precio = precio;
        this.caracteristicas= caracteristicas;
        this.cantidad = cantidad;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return producto;
    }

    public void setNombre(String producto) {
        this.producto = producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public String getCaracteristicas(){
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas){
        this.caracteristicas= caracteristicas;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
  
}
