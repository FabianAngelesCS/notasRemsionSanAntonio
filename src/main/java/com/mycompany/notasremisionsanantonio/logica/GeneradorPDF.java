package com.mycompany.notasremisionsanantonio.logica;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;

import java.io.FileOutputStream;
import java.sql.*;

public class GeneradorPDF {

    public static void generarYMostrarPDF(int idRemision) {
        try {
            Document documento = new Document(PageSize.LETTER, 36, 36, 36, 36); 
            String ruta = "pdfs/remision_" + idRemision + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();
            Font fuenteFila = FontFactory.getFont(FontFactory.HELVETICA, 7);  


            // Tabla principal de 2 columnas
            PdfPTable layoutTabla = new PdfPTable(2);
            layoutTabla.setWidthPercentage(100);
            layoutTabla.setSpacingBefore(10);
            layoutTabla.setSpacingAfter(10);
            layoutTabla.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            layoutTabla.setWidths(new float[]{1f, 1f});

            // Generar las 3 notas
            PdfPCell nota1 = new PdfPCell(generarNota(idRemision));
            PdfPCell nota2 = new PdfPCell(generarNota(idRemision));
            PdfPCell nota3 = new PdfPCell(generarNota(idRemision));
            PdfPCell vacio = new PdfPCell();

            // Quitar bordes
            nota1.setBorder(PdfPCell.NO_BORDER);
            nota2.setBorder(PdfPCell.NO_BORDER);
            nota3.setBorder(PdfPCell.NO_BORDER);
            vacio.setBorder(PdfPCell.NO_BORDER);

            // Fila 1: copia 1 y 2
            layoutTabla.addCell(nota1);
            layoutTabla.addCell(nota2);

            // Fila 2: copia 3 y vacío
            layoutTabla.addCell(nota3);
            layoutTabla.addCell(vacio);

            documento.add(layoutTabla);

            documento.close();
            java.awt.Desktop.getDesktop().open(new java.io.File(ruta));

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        }
    }
    
    // Métodos auxiliares para formato de celdas
    private static PdfPCell getCell(String text, int alignment) {
        Font fuenteCabecera = FontFactory.getFont(FontFactory.HELVETICA, 8); // tamaño reducido
        PdfPCell cell = new PdfPCell(new Phrase(text, fuenteCabecera));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
}

    private static PdfPCell celdaEncabezado(String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8)));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        return cell;
    }
    private static PdfPTable generarNota(int idRemision) throws Exception {
        PdfPTable contenedor = new PdfPTable(1);
        contenedor.setWidthPercentage(100);
        Font fuenteFila = FontFactory.getFont(FontFactory.HELVETICA, 8);  

        // Título
        Paragraph encabezado = new Paragraph("PLÁSTICOS Y ALUMINIO SAN ANTONIO",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        contenedor.addCell(celdaSinBorde(encabezado));

        Paragraph subtitulo = new Paragraph("NOTA DE REMISIÓN",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9));
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        contenedor.addCell(celdaSinBorde(subtitulo));
        contenedor.addCell(celdaSinBorde(new Paragraph(" ")));

        // Datos de cabecera
        String folio = "", fecha = "", cliente = "";
        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement("""
                SELECT r.folio, r.fecha, c.nombre
                FROM remision r
                JOIN cliente c ON r.id_cliente = c.id_cliente
                WHERE r.id_remision = ?
            """)) {
            stmt.setInt(1, idRemision);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                folio = rs.getString("folio");
                fecha = rs.getString("fecha");
                cliente = rs.getString("nombre");
            }
        }

        PdfPTable cabecera = new PdfPTable(2);
        cabecera.setWidthPercentage(100);
        cabecera.addCell(getCell("Folio: " + folio, PdfPCell.ALIGN_LEFT));
        cabecera.addCell(getCell("Fecha: " + fecha, PdfPCell.ALIGN_RIGHT));
        cabecera.addCell(getCell("Cliente: " + cliente, PdfPCell.ALIGN_LEFT));
        cabecera.addCell(getCell("", PdfPCell.ALIGN_RIGHT));
        contenedor.addCell(celdaSinBorde(cabecera));

        // Tabla de productos
        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new int[]{4, 2, 2, 2});
        tabla.setSpacingBefore(5);

        tabla.addCell(celdaEncabezado("Producto"));
        tabla.addCell(celdaEncabezado("Cantidad"));
        tabla.addCell(celdaEncabezado("P. Unitario"));
        tabla.addCell(celdaEncabezado("Subtotal"));

        double total = 0.0;
        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement("""
                SELECT p.nombre, dr.cantidad, dr.precio_unitario,
                       (dr.cantidad * dr.precio_unitario) AS subtotal
                FROM detalle_remision dr
                JOIN producto p ON dr.id_producto = p.id_producto
                WHERE dr.id_remision = ?
            """)) {
            stmt.setInt(1, idRemision);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tabla.addCell(new PdfPCell(new Phrase(rs.getString("nombre"), fuenteFila)));
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(rs.getInt("cantidad")), fuenteFila)));
                tabla.addCell(new PdfPCell(new Phrase(String.format("$%.2f", rs.getDouble("precio_unitario")), fuenteFila)));
                double subtotal = rs.getDouble("subtotal");
                tabla.addCell(new PdfPCell(new Phrase(String.format("$%.2f", subtotal), fuenteFila)));
                total += subtotal;
            }
        }

        contenedor.addCell(celdaSinBorde(tabla));

        Paragraph totalParrafo = new Paragraph("Total: $" + String.format("%.2f", total),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD,9));
        totalParrafo.setAlignment(Element.ALIGN_RIGHT);
        contenedor.addCell(celdaSinBorde(totalParrafo));

        Paragraph leyenda = new Paragraph(
                "Me comprometo a pagar el monto de esta nota en su totalidad. Firma del cliente: _________________________",
                FontFactory.getFont(FontFactory.HELVETICA, 9));
        contenedor.addCell(celdaSinBorde(leyenda));
        
        contenedor.addCell(celdaSinBorde(new Paragraph(" ")));
        contenedor.addCell(celdaSinBorde(new Paragraph(" ")));

        return contenedor;
    }
    private static PdfPCell celdaSinBorde(Element e) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(e);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

}




