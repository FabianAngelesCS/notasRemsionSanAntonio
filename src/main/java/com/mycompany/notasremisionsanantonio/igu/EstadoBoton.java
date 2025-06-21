package com.mycompany.notasremisionsanantonio.igu;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;

import java.awt.Component;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
//import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;



public class EstadoBoton {
    // Clase para el renderizador del botón
       public static class Renderer extends JButton implements javax.swing.table.TableCellRenderer {
        public Renderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
            if (value instanceof Boolean) {
                boolean estado = (Boolean) value;
                setText(estado ? "Desactivar" : "Activar");
                setBackground(estado ? new Color(144, 238, 144) : new Color(255, 182, 193));
                setForeground(Color.BLACK);
            }
            return this;
        }
    }

    public static class Editor extends DefaultCellEditor {
        private JButton button;
        private boolean estadoActual;
        private int idRegistro;
        private JTable tabla;
        private String nombreTabla; // "producto", "cliente", etc.
        private String nombreCampoID; // "nombre", "id_cliente", etc.

        public Editor(JCheckBox checkBox, String nombreTabla, String nombreCampoID) {
            super(checkBox);
            this.nombreTabla = nombreTabla;
            this.nombreCampoID = nombreCampoID;
            
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(this::accionBoton);
        }

        private void accionBoton(ActionEvent e) {
            fireEditingStopped();
            cambiarEstadoRegistro();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
            estadoActual = (Boolean) value;
            idRegistro = (Integer) table.getValueAt(row, 0);
            tabla = table;

            button.setText(estadoActual ? "Desactivar" : "Activar");
            button.setBackground(estadoActual ? new Color(144, 238, 144) : new Color(255, 182, 193));
            
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return !estadoActual;
        }
        
        private static final String NOMBRE_ENCABEZADO_ESTATUS= "Estatus";

        private void cambiarEstadoRegistro() {
        boolean nuevoEstado = !estadoActual;
        System.out.println("UPDATE " + nombreTabla + " SET estatus = " + nuevoEstado +
                " WHERE " + nombreCampoID + " = " + idRegistro);

        // Usando try-with-resources para asegurar el cierre de recursos
        try (Connection conn = new Conexion().conectar();
             java.sql.PreparedStatement ps = conn.prepareStatement(
                 "UPDATE " + nombreTabla + " SET estatus = ? WHERE " + nombreCampoID + " = ?")) {

            ps.setBoolean(1, nuevoEstado);
            ps.setInt(2, idRegistro);
            ps.executeUpdate();

            // --- INICIO DE LA PARTE PARA ACTUALIZAR LA TABLA VISUAL ---
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) { // Asegúrate de que haya una fila seleccionada
                int indiceColumnaEstatus = -1;

                // Buscar el índice de la columna "Estatus" por su nombre
                for (int i = 0; i < tabla.getColumnModel().getColumnCount(); i++) {
                    // Compara el texto del encabezado. ¡Debe ser EXACTO!
                    if (tabla.getColumnModel().getColumn(i).getHeaderValue().equals(NOMBRE_ENCABEZADO_ESTATUS)) {
                        indiceColumnaEstatus = i;
                        break; // Una vez encontrado, salimos del bucle
                    }
                }

                if (indiceColumnaEstatus != -1) {
                    // Si se encontró la columna, actualiza el valor en el modelo de la tabla
                    ((javax.swing.table.DefaultTableModel)tabla.getModel()).setValueAt(nuevoEstado, filaSeleccionada, indiceColumnaEstatus);
                } else {
                    // Manejar el caso donde la columna "Estatus" no se encuentra
                    System.err.println("Error: La columna con encabezado '" + NOMBRE_ENCABEZADO_ESTATUS + "' no se encontró en la tabla '" + nombreTabla + "'.");
                    JOptionPane.showMessageDialog(button, "Advertencia: No se pudo actualizar el estado visual en la tabla. La columna '"+ NOMBRE_ENCABEZADO_ESTATUS +"' no se encontró.");
                }
            } else {
                System.out.println("No se seleccionó ninguna fila para actualizar visualmente en la tabla '" + nombreTabla + "'.");
            }
            // --- FIN DE LA PARTE PARA ACTUALIZAR LA TABLA VISUAL ---

        } catch (Exception e) {
            JOptionPane.showMessageDialog(button, "Error al actualizar estado: " + e.getMessage());
            System.err.println("Error en cambiarEstadoRegistro: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace para depuración
        }
    }

    }
}
