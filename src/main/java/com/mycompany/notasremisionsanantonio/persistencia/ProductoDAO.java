
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
    
    /*public boolean actualizarProducto(Producto producto){
        Conexion conexion = new Conexion();
        Connection conn = null;
        PreparedStatement ps = null;
    
        try {
            conn = conexion.conectar();
            if (conn == null) {
                System.out.println("Error: Conexión a la base de datos fallida.");
                return false;
            }
            conn.setAutoCommit(false); // Desactivar autocommit para manejo de transacciones
            
            String sql = "UPDATE producto SET nombre = ?, caracteristicas = ?, precio = ?, " +
                         ", estatus = ?,cantidad = ? WHERE id_producto = ?";
    
            ps = conn.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCaracteristicas());
            ps.setInt(3, producto.getPrecio());
            ps.setString(1, producto.getNombre());
    }*/
    
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
                c.setPrecio(rs.getInt("precio"));
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
