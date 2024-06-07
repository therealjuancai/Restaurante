/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.controllers;

import com.myapp.data.dao.ProductosDAO;
import com.myapp.data.dao.TipoProductoDAO;
import com.myapp.models.Producto;
import com.myapp.models.TipoProducto;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author juanc
 */
public class ProductosController {
    
    private ProductosDAO productosDAO;
    private TipoProductoDAO tiposProductoDAO;
    private ArrayList<Producto> productos;
    private ArrayList<TipoProducto> tiposProducto;
    
    public ProductosController(){
        productosDAO=new ProductosDAO();
        tiposProductoDAO=new TipoProductoDAO();
    }
    
    public String[][] getArrayTipos(){
        tiposProducto=tiposProductoDAO.getAllTiposProducto();
        String[][] data = new String[tiposProducto.size()][2];
        for(int i=0;i<tiposProducto.size();i++){
            TipoProducto tipo=tiposProducto.get(i);
            data[i][0]=Integer.toString(tipo.getId());
            data[i][1]=tipo.getTipo();
        }
        return data;
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
    
    public void addProducto(String tipo,String nombre,String descripcion,String Precio,String Img){
        int tipoAux=0;
        String [][] tipos=getArrayTipos();
        for(String[] tipe:tipos){
            if(tipe[1].equals(tipo))
                tipoAux=Integer.parseInt(tipe[0]);
        }
        
         if (nombre.isEmpty() || descripcion.isEmpty() || Precio.isEmpty() || Img.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are mandatory. Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (nombre.matches(".*[\\d\\W].*")) {
            JOptionPane.showMessageDialog(null, "The name cannot contain numbers or special characters.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
       
        
        int priceInt;
        try {
            priceInt = Integer.parseInt(Precio);
            if (priceInt < 0) {
                JOptionPane.showMessageDialog(null, "Cost cannot be negative.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cost must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String[][] aux = getArrayProductos();
        for (String[] product : aux) {
            if (nombre.equals(product[2])) {
                JOptionPane.showMessageDialog(null, "A product with this name already exists.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        Producto producto=new Producto(0, tipo, nombre, descripcion, priceInt, Img);
        productosDAO.addProducto(producto, tipoAux);
   
    }
    
    public void deleteProducto(String nombre){
        String[][] aux=getArrayProductos();
        for(String[] product : aux){
            if(nombre.equals(product[2])){
                productosDAO.deleteProducto(nombre);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No product exists with this name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void updateProducto(String tipo,String nombre,String descripcion,String Precio,String Img){
        int tipoAux=0;
        String [][] tipos=getArrayTipos();
        for(String[] tipe:tipos){
            if(tipe[1].equals(tipo))
                tipoAux=Integer.parseInt(tipe[0]);
        }
        
         if (nombre.isEmpty() || descripcion.isEmpty() || Precio.isEmpty() || Img.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are mandatory. Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (nombre.matches(".*[\\d\\W].*")) {
            JOptionPane.showMessageDialog(null, "The name cannot contain numbers or special characters.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
       
        
        int priceInt;
        try {
            priceInt = Integer.parseInt(Precio);
            if (priceInt < 0) {
                JOptionPane.showMessageDialog(null, "Cost cannot be negative.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cost must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Producto producto=new Producto(0, tipo, nombre, descripcion, priceInt, Img);
        String[][] aux=getArrayProductos();
        for(String[] product : aux){
            if(nombre.equals(product[2])){
                productosDAO.updateProducto(producto,tipoAux);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No product exists with this name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        
   
    }
    
    
}
