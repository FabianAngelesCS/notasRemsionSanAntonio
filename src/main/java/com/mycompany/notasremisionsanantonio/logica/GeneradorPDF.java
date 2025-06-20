package com.mycompany.notasremisionsanantonio.logica;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;

import java.awt.Desktop;
import java.io.*;
import java.nio.file.*;
import java.sql.*;

public class GeneradorPDF {

    public static String generarPDF(int idRemision) throws Exception {
        // Ruta del archivo en carpeta temporal del sistema
        String nombreArchivo = "remision_" + idRemision + ".pdf";
        String ruta = System.getProperty("java.io.tmpdir") + File.separator + nombreArchivo;
        File archivoPDF = new File(ruta);
        archivoPDF.deleteOnExit();

        Document documento = new Document(PageSize.LETTER, 36, 36, 36, 36);
        PdfWriter.getInstance(documento, new FileOutputStream(archivoPDF));
        documento.open();

        PdfPTable layoutTabla = new PdfPTable(3);
        layoutTabla.setWidthPercentage(100);
        layoutTabla.setSpacingBefore(10);
        layoutTabla.setSpacingAfter(10);
        layoutTabla.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        layoutTabla.setWidths(new float[]{1f, 0.05f, 1f});

        PdfPCell nota1 = new PdfPCell(generarNota(idRemision));
        PdfPCell espacio = new PdfPCell();
        PdfPCell nota2 = new PdfPCell(generarNota(idRemision));

        nota1.setBorder(PdfPCell.NO_BORDER);
        nota2.setBorder(PdfPCell.NO_BORDER);
        espacio.setBorder(PdfPCell.NO_BORDER);

        layoutTabla.addCell(nota1);
        layoutTabla.addCell(espacio);
        layoutTabla.addCell(nota2);

        documento.add(layoutTabla);
        documento.close();

        return ruta; // Devuelve la ruta temporal generada
    }


    // 2. Método para mostrar el PDF (abrirlo con visor predeterminado)
    public static void mostrarPDF(int idRemision) {
        try {
            String ruta = generarPDF(idRemision);
            Path archivoTemporal = Files.createTempFile("remision_temp_", ".pdf");
            Files.copy(Paths.get(ruta), archivoTemporal, StandardCopyOption.REPLACE_EXISTING);
            Desktop.getDesktop().open(archivoTemporal.toFile());
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error al mostrar el PDF: " + e.getMessage());
        }
    }

    // 3. Método para imprimir directamente el PDF
    public static void imprimirPDF(int idRemision) {
        try {
            String ruta = generarPDF(idRemision);
            Desktop.getDesktop().print(new File(ruta));
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error al imprimir el PDF: " + e.getMessage());
        }
    }

    // ---------------- MÉTODOS AUXILIARES ----------------

    private static PdfPCell getCell(String text, int alignment) {
        Font fuenteCabecera = FontFactory.getFont(FontFactory.HELVETICA, 11);
        PdfPCell cell = new PdfPCell(new Phrase(text, fuenteCabecera));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell celdaEncabezado(String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        return cell;
    }

    private static PdfPCell celdaSinBorde(Element e) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(e);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPTable generarNota(int idRemision) throws Exception {
        PdfPTable contenedor = new PdfPTable(1);
        contenedor.setWidthPercentage(100);
        Font fuenteFila = FontFactory.getFont(FontFactory.HELVETICA, 10);

        Paragraph encabezado = new Paragraph("PLÁSTICOS Y ALUMINIO SAN ANTONIO",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        contenedor.addCell(celdaSinBorde(encabezado));

        Paragraph subtitulo = new Paragraph("NOTA DE REMISIÓN",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        contenedor.addCell(celdaSinBorde(subtitulo));
        contenedor.addCell(celdaSinBorde(new Paragraph(" ")));

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
                tabla.addCell(new PdfPCell(new Phrase(String.format("%.2f", rs.getDouble("cantidad")), fuenteFila)));
                tabla.addCell(new PdfPCell(new Phrase(String.format("$%.2f", rs.getDouble("precio_unitario")), fuenteFila)));
                double subtotal = rs.getDouble("subtotal");
                tabla.addCell(new PdfPCell(new Phrase(String.format("$%.2f", subtotal), fuenteFila)));
                total += subtotal;
            }
        }

        contenedor.addCell(celdaSinBorde(tabla));

        Paragraph totalParrafo = new Paragraph("Total: $" + String.format("%.2f", total),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9));
        totalParrafo.setAlignment(Element.ALIGN_RIGHT);
        contenedor.addCell(celdaSinBorde(totalParrafo));

        Paragraph leyenda = new Paragraph(
                "Me comprometo a pagar el monto de esta nota en su totalidad. Firma del cliente: _________________________",
                FontFactory.getFont(FontFactory.HELVETICA, 9));
        contenedor.addCell(celdaSinBorde(leyenda));

        return contenedor;
    }
}



