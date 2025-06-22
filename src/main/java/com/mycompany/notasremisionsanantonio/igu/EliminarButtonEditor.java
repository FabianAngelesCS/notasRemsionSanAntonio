package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class EliminarButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private boolean isPushed;
    private int editingRow;

    public EliminarButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Eliminar");
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        isPushed = true;
        editingRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            int confirm = JOptionPane.showConfirmDialog(table, "¿Seguro que deseas eliminar esta remisión?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int idRemision = (int) table.getValueAt(editingRow, 0); 
                eliminarRemisionDeBase(idRemision);
                ((DefaultTableModel) table.getModel()).removeRow(editingRow);
                // Actulizar vista de tabla. 
                SwingUtilities.invokeLater(() -> {
                    if (table.isEditing()) {
                        table.getCellEditor().cancelCellEditing();
                    }
                });
            }
        }
        isPushed = false;
        return "Eliminar";
    }

    private void eliminarRemisionDeBase(int idRemision) {
        String sql = "DELETE FROM remision WHERE id_remision = ?";
        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRemision);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(table, "Error al eliminar la remisión.");
        }
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}

