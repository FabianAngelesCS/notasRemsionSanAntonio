package com.mycompany.notasremisionsanantonio.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class RemisionDAO {
    
    public int insertarRemision(int idCliente, Date fecha, String folio) {
        int idGenerado = -1;
        String sql = "INSERT INTO remision (id_cliente, fecha, folio) VALUES (?, ?, ?)";
        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, idCliente);
            stmt.setDate(2, new java.sql.Date(fecha.getTime()));
            stmt.setString(3, folio);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idGenerado;
    }

    public void insertarDetalleRemision(int idRemision, int idProducto, double cantidad, double precio) {
        String sql = "INSERT INTO detalle_remision (id_remision, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRemision);
            stmt.setInt(2, idProducto);
            stmt.setDouble(3, cantidad);
            stmt.setDouble(4, precio);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
    
    public void marcarComoImpresa(int idRemision) {
            String sql = "UPDATE remision SET impresa = 1 WHERE id_remision = ?";

            try (Connection conn = new Conexion().conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idRemision);
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas == 0) {
                    throw new SQLException("No se encontró la remisión con ID: " + idRemision);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Error al marcar como impresa: " + ex.getMessage());
            }
    }
}
