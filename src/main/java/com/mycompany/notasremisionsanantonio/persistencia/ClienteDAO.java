
package com.mycompany.notasremisionsanantonio.persistencia;

import com.mycompany.notasremisionsanantonio.logica.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean agregarCliente(String nombre, String telefono, 
            String direccion, String observaciones) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            return false;
        }

        String sql = "INSERT INTO cliente (nombre, telefono, direccion, "
                + "observaciones) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, direccion);
            ps.setString(4, observaciones);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            return false;
        }
    }
    public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) return lista;

        String sql = "SELECT id_cliente, nombre, telefono, direccion, observaciones, estatus FROM cliente";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente")); 
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setObservaciones(rs.getString("observaciones"));
                c.setEstatus(rs.getBoolean("estatus")); 
                lista.add(c);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }

        return lista;
    }

    
    public boolean actualizarEstatus(int idCliente, boolean nuevoEstatus) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            System.out.println("Conexión fallida.");
            return false;
        }

        String sql = "UPDATE cliente SET estatus = ? WHERE id_cliente = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, nuevoEstatus);
            ps.setInt(2, idCliente);
            int rowsAffected = ps.executeUpdate();
            conn.close();

            if (rowsAffected > 0) {
                System.out.println("Estatus actualizado exitosamente.");
                return true;
            } else {
                System.out.println("No se encontró el cliente o no se actualizó.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar estatus: " + e.getMessage());
            return false;
        }
    }

    private void cargarClientes() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
