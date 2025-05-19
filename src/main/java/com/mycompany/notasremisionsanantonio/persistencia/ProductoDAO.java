
package com.mycompany.notasremisionsanantonio.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProductoDAO {
    
    public boolean agregarProducto(String nombre, String caracteristicas, 
            int precio, int cantidad) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) {
            return false;
        }
        String sql = "INSERT INTO producto (nombre, caracteristicas, precio, "
                + "cantidad) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, caracteristicas);
            ps.setInt(3, precio);
            ps.setInt(4, cantidad);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            return false;
        }
    }
    
}
