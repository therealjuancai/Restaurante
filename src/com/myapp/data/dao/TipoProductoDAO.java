/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.data.dao;

import com.myapp.data.Connection.DatabaseConnection;
import com.myapp.models.TipoProducto;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author juanc
 */
public class TipoProductoDAO {
    
    public ArrayList<TipoProducto> getAllTiposProducto() {
        ArrayList<TipoProducto> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipos_producto";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tipos.add(new TipoProducto(rs.getInt("id"), rs.getString("tipo")));
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        }
        return tipos;
    }
}
