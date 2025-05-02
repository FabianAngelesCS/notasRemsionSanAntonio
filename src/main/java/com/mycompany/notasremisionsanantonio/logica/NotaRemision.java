
package com.mycompany.notasremisionsanantonio.logica;
import java.util.ArrayList;
import java.util.List;

public class NotaRemision {
    private int id_notaRemision;
    private int totalNota;
    private List<DetalleNota> detalles;

    public NotaRemision() {
        this.detalles = new ArrayList<>();
    }

    public NotaRemision(int id_notaRemision) {
        this();
        this.id_notaRemision = id_notaRemision;
    }

    public int getId_notaRemision() {
        return id_notaRemision;
    }

    public void setId_notaRemision(int id_notaRemision) {
        this.id_notaRemision = id_notaRemision;
    }

    public int getTotalNota() {
        return totalNota;
    }

    public List<DetalleNota> getDetalles() {
        return detalles;
    }

    public void agregarDetalle(DetalleNota detalle) {
        detalles.add(detalle);
        totalNota += detalle.calcularSubtotal();
    }
}
