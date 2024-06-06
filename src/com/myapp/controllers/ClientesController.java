/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.controllers;

import com.myapp.data.dao.ClientesDAO;
import com.myapp.models.Cliente;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author juanc
 */
public class ClientesController {
    
    private ClientesDAO clientesDAO;
    private ArrayList<Cliente> clientes;
    private Cliente clienteSelected;

    public ClientesController() {
        clientesDAO=new ClientesDAO();
    }
    
    public String[][] getArrayClientes(){
        clientes=clientesDAO.getAllClientes();
        String[][] data = new String[clientes.size()][4];
        for(int i=0;i<clientes.size();i++){
            Cliente cliente=clientes.get(i);
            data[i][0]=cliente.getNombre();
            data[i][1]=cliente.getCedula();
            data[i][2]=cliente.getGenero();
            data[i][3]=String.valueOf(cliente.getEdad());
        }
        return data;
    }
    
    public void addCliente(String Nombre,String Cedula,String Genero, String Edad){
        
         if (Nombre.isEmpty() || Cedula.isEmpty() || Genero.isEmpty() || Edad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Por favor, complete todos los campos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (Nombre.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "El nombre no puede contener números.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar la cédula
        if (!Cedula.matches("\\d{1,10}")) {
            JOptionPane.showMessageDialog(null, "La cédula debe contener solo números y tener hasta 10 caracteres.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar la edad
        int edadInt;
        try {
            edadInt = Integer.parseInt(Edad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[][] aux=getArrayClientes();
        for(String[] cliente : aux){
            if(Cedula.equals(cliente[1])){
                JOptionPane.showMessageDialog(null, "ya existe un cliente con esa cedula", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // Si todas las validaciones pasan, crear el cliente y agregarlo
        Cliente cliente = new Cliente(0, Nombre, Cedula, Genero, edadInt);
        clientesDAO.addCliente(cliente);
    }

    public void deleteCliente(String cedula){
        String[][] aux=getArrayClientes();
        for(String[] cliente : aux){
            if(cedula.equals(cliente[1])){
                clientesDAO.deleteCliente(cedula);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No existe cliente con esa cedula", "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }
    
    public void updateCliente(String Nombre,String Cedula,String Genero, String Edad){
        if (Nombre.isEmpty() || Cedula.isEmpty() || Genero.isEmpty() || Edad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Por favor, complete todos los campos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (Nombre.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "El nombre no puede contener números.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar la cédula
        if (!Cedula.matches("\\d{1,10}")) {
            JOptionPane.showMessageDialog(null, "La cédula debe contener solo números y tener hasta 10 caracteres.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar la edad
        int edadInt;
        try {
            edadInt = Integer.parseInt(Edad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Cliente clientes = new Cliente(0, Nombre, Cedula, Genero, Integer.parseInt(Edad));
        String[][] aux=getArrayClientes();
        for(String[] cliente : aux){
            if(Cedula.equals(cliente[1])){
                clientesDAO.updateCliente(clientes);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No existe cliente con esa cedula", "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }
    
}
