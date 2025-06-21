
package com.mycompany.notasremisionsanantonio.logica;

public class DetalleNota {
    private Producto producto;
    private int cantidad;

    public DetalleNota(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int calcularSubtotal() {
        return (int) (producto.getPrecio() * cantidad);
    }
}
