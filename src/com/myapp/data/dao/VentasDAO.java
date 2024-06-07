/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.data.dao;

import com.myapp.data.Connection.DatabaseConnection;
import com.myapp.models.Producto;
import com.myapp.models.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author juanc
 */
public class VentasDAO {
    public ArrayList<Venta> getAllVentas() {
        ArrayList<Venta> ventas = new ArrayList<>();
        String sql = "SELECT venta.id AS id, venta.cantidad AS cantidad, venta.fecha AS fecha, cliente.nombre AS cliente, producto.nombre AS producto, venta.precio_total AS total FROM venta JOIN cliente ON venta.cliente = cliente.id JOIN producto ON venta.producto = producto.id;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ventas.add(new Venta(rs.getInt("id"),rs.getString("producto"), rs.getInt("cantidad"), rs.getString("cliente"),rs.getString("fecha"), rs.getInt("total")));
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ventas;
    }
    
     public void addVenta(Venta venta,int cliente, int producto) {      
        String sql = "INSERT INTO venta (cliente, producto, cantidad, fecha, precio_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente);
            stmt.setInt(2, producto);
            stmt.setInt(3, venta.getCantidad());
            stmt.setString(4, venta.getFecha());
            stmt.setInt(5, venta.getTotal());
            stmt.executeUpdate();
        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
     }
     
    public void deleteVenta(int id) {
        String sql = "DELETE FROM venta WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVenta(Venta venta,int cliente, int producto) {
        String sql = "UPDATE venta SET cliente = ?, producto = ?, cantidad = ?, fecha = ?, precio_total = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente);
            stmt.setInt(2, producto);
            stmt.setInt(3, venta.getCantidad());
            stmt.setString(4, venta.getFecha());
            stmt.setInt(5, venta.getTotal());
            stmt.setInt(6, venta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
