
package com.mycompany.notasremisionsanantonio.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
