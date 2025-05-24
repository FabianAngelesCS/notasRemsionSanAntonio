package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.logica.Cliente;
import com.mycompany.notasremisionsanantonio.persistencia.ClienteDAO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/*public class EditorBoton extends AbstractCellEditor implements TableCellEditor {

    private final JButton boton = new JButton();
    private final JTable tabla;
    private final List<Cliente> clientes;
    private final Clientes ventanaClientes;

    public EditorBoton(JTable tabla, List<Cliente> clientes, Clientes ventanaClientes) {
        this.tabla = tabla;
        this.clientes = clientes;
        this.ventanaClientes = ventanaClientes;

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila < 0) return;

                int modelRow = tabla.convertRowIndexToModel(fila);
                int idCliente = Integer.parseInt(tabla.getModel().getValueAt(modelRow, 0).toString());

                Cliente cliente = buscarClientePorId(idCliente);
                if (cliente == null) {
                    System.out.println("Cliente no encontrado.");
                    return;
                }

                boolean nuevoEstatus = !cliente.isEstatus();

                ClienteDAO dao = new ClienteDAO();
                boolean actualizado = dao.actualizarEstatus(idCliente, nuevoEstatus);

                if (actualizado) {
                    cliente.setEstatus(nuevoEstatus);
                    // Aquí se delega la actualización visual a la ventana
                    ventanaClientes.actualizarEstatusEnTabla(idCliente, nuevoEstatus);
                    System.out.println("Estatus actualizado.");
                } else {
                    System.out.println("Error al actualizar el estatus.");
                }

                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        int modelRow = table.convertRowIndexToModel(row);
        int idCliente = Integer.parseInt(table.getModel().getValueAt(modelRow, 0).toString());

        boton.setText((value == null) ? "Acción" : value.toString());
        boton.setActionCommand(String.valueOf(idCliente));

        System.out.println("ID Cliente asignado al botón: " + boton.getActionCommand());
        return boton;
    }

    @Override
    public Object getCellEditorValue() {
        return boton.getText();
    }

    private Cliente buscarClientePorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId_cliente() == id) {
                return c;
            }
        }
        return null;
    }
}*/
