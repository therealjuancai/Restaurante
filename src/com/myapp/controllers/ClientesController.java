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
            JOptionPane.showMessageDialog(null, "All fields are mandatory. Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Nombre.matches(".*[\\d\\W].*")) {
            JOptionPane.showMessageDialog(null, "The name cannot contain numbers or special characters.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the ID
        if (!Cedula.matches("\\d{1,10}")) {
            JOptionPane.showMessageDialog(null, "The ID must contain only numbers and be up to 10 characters long.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the age
        int edadInt;
        try {
            edadInt = Integer.parseInt(Edad);
            if (edadInt < 0) {
                JOptionPane.showMessageDialog(null, "Age cannot be negative.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (edadInt > 999) {
                JOptionPane.showMessageDialog(null, "Quantity must be less than 4 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Age must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[][] aux = getArrayClientes();
        for (String[] cliente : aux) {
            if (Cedula.equals(cliente[1])) {
                JOptionPane.showMessageDialog(null, "A client with this ID already exists.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // If all validations pass, create the client and add it
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
        JOptionPane.showMessageDialog(null, "No client exists with this DNI.", "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void updateCliente(String Nombre,String Cedula,String Genero, String Edad){
        if (Nombre.isEmpty() || Cedula.isEmpty() || Genero.isEmpty() || Edad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are mandatory. Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Nombre.matches(".*[\\d\\W].*")) {
            JOptionPane.showMessageDialog(null, "The name cannot contain numbers or special characters.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the ID
        if (!Cedula.matches("\\d{1,10}")) {
            JOptionPane.showMessageDialog(null, "The ID must contain only numbers and be up to 10 characters long.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the age
        int edadInt;
        try {
            edadInt = Integer.parseInt(Edad);
            if (edadInt < 0) {
                JOptionPane.showMessageDialog(null, "Age cannot be negative.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Age must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
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
        JOptionPane.showMessageDialog(null, "No client exists with this DNI.", "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
