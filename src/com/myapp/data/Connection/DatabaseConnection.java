/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.data.Connection;
import java.sql.*;
/**
 *
 * @author juanc
 */
public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/programacion2";
    private static final String USER = "root"; // Cambia esto según tu configuración
    private static final String PASSWORD = "root3"; // Cambia esto según tu configuración

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
