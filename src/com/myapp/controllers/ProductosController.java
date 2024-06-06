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
    
    
}
