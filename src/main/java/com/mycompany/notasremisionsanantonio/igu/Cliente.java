/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.notasremisionsanantonio.igu;

/**
 *
 * @author Sebaz
 */
public class Cliente {
  private int id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String observaciones;
    private boolean estatus; // Podría ser un String si usas "Activo"/"Inactivo"

    // Constructor vacío (útil para frameworks o si necesitas instanciar sin valores iniciales)
    public Cliente() {
    }

    // Constructor con todos los campos (muy útil para crear objetos Cliente rápidamente)
    public Cliente(int id, String nombre, String telefono, String direccion, String observaciones, boolean estatus) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.observaciones = observaciones;
        this.estatus = estatus;
    }

    // --- Métodos Getters ---
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public boolean isEstatus() { // Para booleanos, el getter suele ser 'is'
        return estatus;
    }

    // --- Métodos Setters ---
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", telefono='" + telefono + '\'' +
               ", direccion='" + direccion + '\'' +
               ", observaciones='" + observaciones + '\'' +
               ", estatus=" + estatus +
               '}';
    }
}