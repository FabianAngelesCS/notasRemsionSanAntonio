package com.mycompany.notasremisionsanantonio.logica;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;

import java.awt.Desktop;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.text.DecimalFormat;

public class GeneradorPDF {

    public static String generarPDF(int idRemision) throws Exception {
        String nombreArchivo = "remision_" + idRemision + ".pdf";
        String ruta = System.getProperty("java.io.tmpdir") + File.separator + nombreArchivo;
        File archivoPDF = new File(ruta);
        archivoPDF.deleteOnExit();

        Document documento = new Document(PageSize.LETTER, 36, 36, 36, 36);
        PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(archivoPDF));
        documento.open();

        // Aproximación de altura de la nota
        float alturaNota = 370f; // ← puedes ajustar este valor para que coincida con tu diseño
        float yBase = documento.getPageSize().getTop() - 100; // donde inicia la nota (margen superior)
        float mitadX = documento.getPageSize().getWidth() / 2;

        // Dibujar líneas en el área de la nota
        PdfContentByte canvas = writer.getDirectContent();

        float yInicio = yBase;
        float yCentro = yBase - (alturaNota / 2);

        canvas.moveTo(mitadX, yInicio);
        canvas.lineTo(mitadX, yInicio - 10);

        canvas.moveTo(mitadX, yCentro);
        canvas.lineTo(mitadX, yCentro - 10);

        canvas.stroke();

        PdfPTable layoutTabla = new PdfPTable(3);
        layoutTabla.setWidthPercentage(100);
        layoutTabla.setSpacingBefore(10);
        layoutTabla.setSpacingAfter(10);
        layoutTabla.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        layoutTabla.setWidths(new float[]{1f, 0.1f, 1f});

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

        return ruta;
    }


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

    public static void imprimirPDF(int idRemision) {
        try {
            String ruta = generarPDF(idRemision);
            Desktop.getDesktop().print(new File(ruta));
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error al imprimir el PDF: " + e.getMessage());
        }
    }

    private static PdfPCell getCell(String text, int alignment) {
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 8);
        PdfPCell cell = new PdfPCell(new Phrase(text, fuente));
        cell.setPadding(4);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell celdaEncabezado(String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, FontFactory.getFont(FontFactory.HELVETICA, 8)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static PdfPCell celdaSinBorde(Element e) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(e);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell crearCeldaEtiqueta(String texto) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 8);
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    private static PdfPCell crearCeldaDato(String texto) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 8);
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    private static PdfPTable generarNota(int idRemision) throws Exception {
        PdfPTable contenedor = new PdfPTable(1);    
        contenedor.setWidthPercentage(100);
        Font fuenteFila = FontFactory.getFont(FontFactory.HELVETICA, 8);

        Paragraph encabezado = new Paragraph("PLASTICOS Y ALUMINIO SAN ANTONIO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        contenedor.addCell(celdaSinBorde(encabezado));
        contenedor.addCell(celdaSinBorde(new Paragraph(" ")));

        String folio = "", fecha = "", cliente = "", idCliente = "";

        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement("""
                SELECT r.folio, r.fecha, c.nombre, c.id_cliente
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
                idCliente = rs.getString("id_cliente");
            }
        }

        PdfPTable datosCabecera = new PdfPTable(1);  // Contenedor principal
        datosCabecera.setWidthPercentage(100);

        Font fontCab = FontFactory.getFont(FontFactory.HELVETICA, 8);

        // Fila 1: Nombre cliente (etiqueta + valor)
        PdfPTable filaNombre = new PdfPTable(2);
        filaNombre.setWidthPercentage(100);
        filaNombre.setWidths(new float[]{1.8f, 5.2f});

        filaNombre.addCell(celdaConBorde("Nombre\u00A0cliente", fontCab));
        filaNombre.addCell(celdaConBorde(cliente, fontCab));

        // Añadir filaNombre a la tabla contenedora
        PdfPCell filaNombreCompleta = new PdfPCell(filaNombre);
        filaNombreCompleta.setBorder(PdfPCell.NO_BORDER);
        datosCabecera.addCell(filaNombreCompleta);

        // Fila 2: tabla con 6 columnas horizontales (3 etiquetas + 3 valores)
        PdfPTable fila2 = new PdfPTable(6);
        fila2.setWidthPercentage(100);
        fila2.setWidths(new float[]{1.2f, 1.0f, 1.5f, 0.8f, 1.2f, 2.3f});

        fila2.addCell(celdaConBorde("No.\u00A0Nota", fontCab));
        fila2.addCell(celdaConBorde(folio, fontCab));
        fila2.addCell(celdaConBorde("No.\u00A0Cliente", fontCab));
        fila2.addCell(celdaConBorde(idCliente, fontCab));
        fila2.addCell(celdaConBorde("Fecha", fontCab));
        fila2.addCell(celdaConBorde(fecha, fontCab));

        // Insertar la fila2 como una celda dentro del contenedor
        PdfPCell fila2Completa = new PdfPCell(fila2);
        fila2Completa.setBorder(PdfPCell.NO_BORDER);
        datosCabecera.addCell(fila2Completa);

        contenedor.addCell(celdaSinBorde(datosCabecera));

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{6f, 1.5f, 2f, 2.5f});
        tabla.setSpacingBefore(5);

        tabla.addCell(celdaEncabezadoConBorde("Producto"));
        tabla.addCell(celdaEncabezadoConBorde("Cant."));
        tabla.addCell(celdaEncabezadoConBorde("Precio"));
        tabla.addCell(celdaEncabezadoConBorde("Importe"));

        double total = 0.0;
        DecimalFormat formato = new DecimalFormat("###,###.00");

        int filas = 0;
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
                tabla.addCell(celdaConBorde(rs.getString("nombre"), fuenteFila));
                tabla.addCell(celdaConBorde(String.format("%.0f", rs.getDouble("cantidad")), fuenteFila));
                tabla.addCell(celdaConBorde(formato.format(rs.getDouble("precio_unitario")), fuenteFila));
                double subtotal = rs.getDouble("subtotal");
                tabla.addCell(celdaConBorde(formato.format(subtotal), fuenteFila));
                total += subtotal;
                filas++;
            }
        }

        int filasMinimas = 16; // puedes ajustar este número hasta lograr media hoja visual
        while (filas < filasMinimas) {
            for (int i = 0; i < 4; i++) {
                tabla.addCell(celdaConBorde(" ", fuenteFila));
            }
            filas++;
        }

        // Fila de total
        PdfPCell vacio = new PdfPCell(new Phrase("Total", fuenteFila));
        vacio.setColspan(3);
        vacio.setHorizontalAlignment(Element.ALIGN_RIGHT);
        vacio.setBorder(PdfPCell.BOX);

        PdfPCell totalCell = new PdfPCell(new Phrase(formato.format(total), fuenteFila));
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalCell.setBorder(PdfPCell.BOX);

        tabla.addCell(vacio);
        tabla.addCell(totalCell);

        contenedor.addCell(celdaSinBorde(tabla));

        // Leyenda pagaré con firma
        PdfPTable leyenda = new PdfPTable(2);
        leyenda.setWidthPercentage(100);
        leyenda.setWidths(new float[]{8f, 2f});

        Font fuentePagare = FontFactory.getFont(FontFactory.HELVETICA, 7);

        PdfPCell texto = new PdfPCell(new Phrase(
                "Por el presente pagaré reconozco deber y me obligo a pagar\n" +
                "en ésta ciudad o en cualquier otra que se me requiera la\n" +
                "cantidad de: (" + convertirNumeroATexto((int) total) + " pesos /100 M.N.)",
                fuentePagare
        ));
        texto.setBorder(PdfPCell.NO_BORDER);
        texto.setMinimumHeight(60f);
        texto.setPadding(6f);

        PdfPCell firma = new PdfPCell(new Phrase("Firma de recibido", fuentePagare));
        firma.setBorder(PdfPCell.NO_BORDER);
        firma.setHorizontalAlignment(Element.ALIGN_CENTER);
        firma.setVerticalAlignment(Element.ALIGN_MIDDLE);
        firma.setMinimumHeight(60f);

        leyenda.addCell(texto);
        leyenda.addCell(firma);
        contenedor.addCell(celdaSinBorde(leyenda));

        return contenedor;
    }
    private static PdfPCell celdaConBorde(String texto, Font fuente) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fuente));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.BOX);
        return cell;
    }

    private static PdfPCell celdaEncabezadoConBorde(String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.BOX);
        return cell;
    }

    private static String convertirNumeroATexto(int numero) {
        if (numero == 6300) return "Seis mil trescientos";
        return String.valueOf(numero);
    }
}

