/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.controllers;

import com.myapp.data.dao.ClientesDAO;
import com.myapp.data.dao.ProductosDAO;
import com.myapp.data.dao.VentasDAO;
import com.myapp.models.Cliente;
import com.myapp.models.Producto;
import com.myapp.models.Venta;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class VentasController {
    
    private ClientesDAO clientesDAO;
    private ProductosDAO productosDAO;
    private VentasDAO ventasDAO;
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Venta> ventas;
    
    public VentasController(){
        clientesDAO=new ClientesDAO();
        productosDAO=new ProductosDAO();
        ventasDAO=new VentasDAO();
    }
    
    public String[][] getArrayProductos(){
        productos=productosDAO.getAllProductos();
        String[][] data = new String[productos.size()][6];
        for(int i=0;i<productos.size();i++){
            Producto tipo=productos.get(i);
            data[i][0]=Integer.toString(tipo.getId());
            data[i][1]=tipo.getTipo();
            data[i][2]=tipo.getNombre();
            data[i][3]=tipo.getDescripcion();
            data[i][4]=Integer.toString(tipo.getPrecio());
            data[i][5]=tipo.getImagen();
        }
        return data;
    }
    
    public String[] getNamesProductos(){
        productos=productosDAO.getAllProductos();
        String[] data = new String[productos.size()];
        for(int i=0;i<productos.size();i++){
            Producto tipo=productos.get(i);
            data[i]=tipo.getNombre();
        }
        return data;
    }
    
    public String[][] getArrayClientes(){
        clientes=clientesDAO.getAllClientes();
        String[][] data = new String[clientes.size()][5];
        for(int i=0;i<clientes.size();i++){
            Cliente cliente=clientes.get(i);
            data[i][0]=Integer.toString(cliente.getId());
            data[i][1]=cliente.getNombre();
            data[i][2]=cliente.getCedula();
            data[i][3]=cliente.getGenero();
            data[i][4]=String.valueOf(cliente.getEdad());
        }
        return data;
    }
    
    public String[] getNamesClientes(){
        clientes=clientesDAO.getAllClientes();
        String[] data = new String[clientes.size()];
        for(int i=0;i<clientes.size();i++){
            Cliente cliente=clientes.get(i);
            data[i]=cliente.getNombre();
        }
        return data;
    }
    
    
    
    public String[][] getArrayVentas(){
        ventas=ventasDAO.getAllVentas();
        String[][] data = new String[ventas.size()][6];
        for(int i=0;i<ventas.size();i++){
            Venta tipo=ventas.get(i);
            data[i][0]=Integer.toString(tipo.getId());
            data[i][1]=tipo.getProducto();
            data[i][2]=Integer.toString(tipo.getCantidad());
            data[i][3]=tipo.getCliente();
            data[i][4]=tipo.getFecha();
            data[i][5]=Integer.toString(tipo.getTotal());
        }
        return data;
    }
    
    public void addVenta(String producto,String cantidad,String cliente,String fecha){
        int productoAux=0;
        int precioAux=0;
        String [][] ps=getArrayProductos();
        for(String[] p:ps){
            if(p[2].equals(producto))
                productoAux=Integer.parseInt(p[0]);
                precioAux=Integer.parseInt(p[4]);
        }
        
        int clienteAux=0;
        String [][] cs=getArrayClientes();
        for(String[] c:cs){
            if(c[1].equals(cliente))
                clienteAux=Integer.parseInt(c[0]);
        }
        
        
        if ( cantidad.isEmpty() || fecha.isEmpty() || producto.isEmpty()|| cliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are mandatory. Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int cantidadInt;
        try {
            cantidadInt = Integer.parseInt(cantidad);
            if (cantidadInt < 1) {
                JOptionPane.showMessageDialog(null, "Quantity must be greater than 1.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(fecha);
            Date maxDate = sdf.parse("07/06/2024");
            Date minDate = sdf.parse("01/01/2000");
            if (parsedDate.after(maxDate)) {
                JOptionPane.showMessageDialog(null, "Date must not be after 07/06/2024.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }if (parsedDate.before(minDate)) {
                JOptionPane.showMessageDialog(null, "Date must not be before 01/01/2000.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Date must be in the format dd/MM/yyyy and must be a valid date.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int total = precioAux*cantidadInt;
        Venta venta = new Venta(0, producto, cantidadInt, cliente, fecha,total);
        ventasDAO.addVenta(venta, clienteAux, productoAux);
        
    }
    
    
    public void deleteVenta(String id){
        String[][] aux=getArrayVentas();
        for(String[] venta : aux){
            if(id.equals(venta[0])){
                ventasDAO.deleteVenta(Integer.parseInt(id));
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No Sell exists with this ID.", "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void updateVenta(String id,String producto,String cantidad,String cliente,String fecha){
        int productoAux=0;
        int precioAux=0;
        String [][] ps=getArrayProductos();
        for(String[] p:ps){
            if(p[2].equals(producto))
                productoAux=Integer.parseInt(p[0]);
                precioAux=Integer.parseInt(p[4]);
        }
        
        if (id.isEmpty() || cantidad.isEmpty() || fecha.isEmpty() || producto.isEmpty()|| cliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are mandatory. Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int clienteAux=0;
        String [][] cs=getArrayClientes();
        for(String[] c:cs){
            if(c[1].equals(cliente))
                clienteAux=Integer.parseInt(c[0]);
        }
        
        int cantidadInt;
        try {
            cantidadInt = Integer.parseInt(cantidad);
            if (cantidadInt < 1) {
                JOptionPane.showMessageDialog(null, "Quantity must be greater than 1.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(fecha);
            Date maxDate = sdf.parse("07/06/2024");
            Date minDate = sdf.parse("01/01/2000");
            if (parsedDate.after(maxDate)) {
                JOptionPane.showMessageDialog(null, "Date must not be after 07/06/2024.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }if (parsedDate.before(minDate)) {
                JOptionPane.showMessageDialog(null, "Date must not be before 01/01/2000.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Date must be in the format dd/MM/yyyy and must be a valid date.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int total = precioAux*cantidadInt;
        Venta venta = new Venta(Integer.parseInt(id), producto, cantidadInt, cliente, fecha,total);
        ventasDAO.updateVenta(venta, clienteAux, productoAux);
        
    }
    
}
