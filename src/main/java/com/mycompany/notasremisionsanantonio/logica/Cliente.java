
package com.mycompany.notasremisionsanantonio.logica;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private  int id_cliente;
    private String nombre;
    private String telefono;
    private String direccion;
    private String observaciones;
    private boolean estatus;
    private List<NotaRemision> notas;
    
    public Cliente() {
        this.notas = new ArrayList<>();
    }

    public Cliente(int id_cliente, String nombre, String telefono,
                   String direccion, String observaciones, boolean estatus) {
        this();
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.observaciones = observaciones;
        this.estatus = estatus;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
    
    public List<NotaRemision> getNotas() {
        return notas;
    }

    public void agregarNota(NotaRemision nota) {
        notas.add(nota);
    }
    public String toString() {
        return nombre;
    }
}

