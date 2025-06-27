
package com.mycompany.notasremisionsanantonio.persistencia;

import com.mycompany.notasremisionsanantonio.logica.Cliente;
import com.mycompany.notasremisionsanantonio.logica.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
            ps.setDouble(3, precio);
            ps.setInt(4, cantidad);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarProducto(Producto producto) {
        Conexion conexion = new Conexion();
        // Usamos try-with-resources para asegurar el cierre de Connection y PreparedStatement
        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE producto SET nombre = ?, caracteristicas = ?, precio = ?, " +
                 "cantidad = ?, estatus = ? WHERE id_producto = ?")) { // <-- Coma extra removida
            
            if (conn == null) {
                System.out.println("Error: Conexión a la base de datos fallida.");
                return false;
            }
            
            // conn.setAutoCommit(false); // Opcional: si manejas transacciones complejas.
                                        // Para un solo UPDATE, no es estrictamente necesario aquí.
            
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCaracteristicas());
            ps.setDouble(3, producto.getPrecio()); // <-- Usar setDouble()
            ps.setDouble(4, producto.getCantidad());
            ps.setBoolean(5, producto.isEstatus());
            ps.setInt(6, producto.getId_producto());
            
            int affectedRows = ps.executeUpdate();
            // conn.commit(); // Opcional: si manejas transacciones
            
            if (affectedRows > 0) {
                System.out.println("Producto " + producto.getId_producto() + " actualizado exitosamente.");
                return true;
            } else {
                System.out.println("No se encontró el producto con ID " + producto.getId_producto() + " para actualizar.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            e.printStackTrace(); // Útil para depuración
            /*
            // Bloque rollback si se usa conn.setAutoCommit(false);
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Error al revertir transacción: " + ex.getMessage());
            }
            */
            return false;
        }
        // Con try-with-resources, el bloque finally para cerrar recursos ya no es necesario
    }
    
    public List<Producto> obtenerProductos() {
        List<Producto> lista = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        if (conn == null) return lista;

        String sql = "SELECT id_producto, nombre, caracteristicas, precio, cantidad, estatus FROM producto";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto c = new Producto();
                c.setId_producto(rs.getInt("id_producto")); 
                c.setNombre(rs.getString("nombre"));
                c.setCaracteristicas(rs.getString("caracteristicas"));
                c.setPrecio(rs.getDouble("precio"));
                c.setCantidad(rs.getInt("cantidad"));
                c.setEstatus(rs.getBoolean("estatus")); 
                lista.add(c);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        return lista;
    }
}
