/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.data.dao;

import com.myapp.data.Connection.DatabaseConnection;
import com.myapp.models.Producto;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author juanc
 */
public class ProductosDAO {
    public ArrayList<Producto> getAllProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT producto.id AS id, producto.nombre AS nombre, producto.descripcion AS descripcion, producto.precio AS precio, producto.imagen AS img ,tipo_producto.tipo AS tipo FROM producto JOIN tipo_producto ON producto.tipo = tipo_producto.id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id"),rs.getString("tipo"), rs.getString("nombre"), rs.getString("descripcion"),rs.getInt("precio"),rs.getString("img")));
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        }
        return productos;
    }
    
    public void addProducto(Producto producto) {
        int tipo=0;
        String sql = "INSERT INTO Producto (tipo, nombre, descripcion, precio, imagen) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tipo);
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setInt(4, producto.getPrecio());
            stmt.setString(5, producto.getImagen());
            stmt.executeUpdate();
        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteProducto(int id) {
        String sql = "DELETE FROM Producto WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProducto(Producto producto) {
        int tipo=0;
        String sql = "UPDATE Producto SET tipo = ?, nombre = ?, descripcion = ?, precio = ?, imagen = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tipo);
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setInt(4, producto.getPrecio());
            stmt.setString(5, producto.getImagen());
            stmt.setInt(6, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
