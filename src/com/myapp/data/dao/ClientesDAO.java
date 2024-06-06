/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.data.dao;

/**
 *
 * @author juanc
 */
import com.myapp.models.Cliente;
import com.myapp.data.Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ClientesDAO {
    
    public ArrayList<Cliente> getAllClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("cedula"),rs.getString("genero"),rs.getInt("edad")));
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        }
        return clientes;
    }
    
    public void addCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, cedula, genero, edad) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getCedula());
            stmt.setString(3, cliente.getGenero());
            stmt.setInt(4, cliente.getEdad());
            stmt.executeUpdate();
        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCliente(String cedula) {
        String sql = "DELETE FROM cliente WHERE cedula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = ?, genero = ?, edad = ? WHERE cedula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getGenero());
            stmt.setInt(3, cliente.getEdad());
            stmt.setString(4, cliente.getCedula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
