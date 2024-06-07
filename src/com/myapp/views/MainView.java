/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.views;

/**
 *
 * @author juanc
 */

import com.myapp.controllers.ClientesController;
import com.myapp.controllers.MainController;
import com.myapp.controllers.ProductosController;
import com.myapp.controllers.VentasController;
import com.myapp.core.CustomTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;


public class MainView extends JFrame implements ActionListener{
    
    private JMenuBar barra;
    private JMenu abrir;
    private JMenuItem ventanaProductos, ventanaClientes, ventanaVentas;
    private MainController mainController;
    
    //productos
    private ProductosController productosController;
    private JInternalFrame frameProductos;
    private JButton bEditar, bAnadir, bShowStatistics,bBorrar, bMostrar,productoImagen;
    private JLabel tvproductoTipo,tvproductoNombre,tvproductoDescripcion,tvproductoPrecio, tvproductoImagen, productoTitulo,txtImagen;
    private JTextField productoNombre,productoDescripcion,productoPrecio;
    private JComboBox productoTipo;
    private JTable tableProductos;
    private CustomTableModel productosModel;
    private JScrollPane scrollProductos;
    private String[][]tiposDeProductos;
    
    //clientes
    private ClientesController clientesController;
    private JInternalFrame frameClientes;
    private JButton bEditarCliente, bAnadirCliente, bShowStatisticsCliente,bBorrarCliente, bMostrarCliente;
    private JLabel tvClienteNombre,tvClienteCedula,tvClienteGenero, tvClienteEdad, clienteTitulo;
    private JTextField ClienteNombre,ClienteCedula, ClienteEdad;
    private JComboBox ClienteGenero;
    private JTable tableClientes;
    private CustomTableModel ClientesModel;
    private JScrollPane scrollClientes;
    
    //Ventas
    private VentasController ventasController;
    private JInternalFrame frameVentas;
    private JButton bEditarVenta, bAnadirVenta, bShowStatisticsVenta,bBorrarVenta, bMostrarVenta;
    private JLabel tvVentaProducto,tvVentaCantidad,tvVentaCliente, tvVentaFecha,tvVentaId, VentaId, VentaTitulo;
    private JTextField VentaCantidad, VentaFecha;
    private JComboBox VentaProducto,VentaCliente;
    private JTable tableVentas;
    private CustomTableModel VentasModel;
    private JScrollPane scrollVentas;
    private String[] menuClientes={""};
    private String[] menuProductos={""};
    

    public MainView(MainController mainController, ClientesController clientesController, ProductosController productosController, VentasController ventasController) {
        this.mainController = mainController;
        this.clientesController = clientesController;
        this.productosController=productosController;
        this.ventasController=ventasController;
        initMain();
        visualizar();
        initListeners();
    }
    
    public void initMain(){
        this.barra = new JMenuBar();
        this.abrir = new JMenu("Open");
        this.ventanaProductos = new JMenuItem("Products");
        this.ventanaClientes = new JMenuItem("Customers");
        this.ventanaVentas = new JMenuItem("Sells");
        this.abrir.add(ventanaProductos);
        this.abrir.add(ventanaClientes);
        this.abrir.add(ventanaVentas);
        this.barra.add(this.abrir);
        this.setJMenuBar(barra);
        initProductos();
        initClientes();
        initVentas();
    }
    
    public void initProductos(){
        this.frameProductos= new JInternalFrame();
       
        this.tvproductoTipo=new JLabel("Type:");
        this.tvproductoNombre=new JLabel("Name:");
        this.tvproductoDescripcion=new JLabel("Description:");
        this.tvproductoPrecio=new JLabel("cost:");
        this.tvproductoImagen=new JLabel("Upload Image");
        this.productoTitulo=new JLabel("PRODUCTS");
        this.txtImagen=new JLabel("");
        String[] columnNames={"id","Type", "Name", "Description", "cost", "Image"};
        productosModel = new CustomTableModel(columnNames,0);
        tableProductos = new JTable(productosModel);
        
        this.scrollProductos=new JScrollPane(tableProductos);
        
        Font font = new Font("Arial", Font.BOLD, 24);
        productoTitulo.setFont(font);
        productoTitulo.setBounds(30, 30, 500, 20);
        
        tvproductoTipo.setBounds(30, 90, 100, 20);
        tvproductoNombre.setBounds(30, 120, 100, 20);
        tvproductoDescripcion.setBounds(30, 150, 100, 20);
        tvproductoPrecio.setBounds(30, 180, 100, 20);
        tvproductoImagen.setBounds(30, 210, 100, 20);
        
        scrollProductos.setBounds(30, 260, 820, 400);
        
        tiposDeProductos=productosController.getArrayTipos();
       
        String[][] data=productosController.getArrayProductos();
            productosModel.setRowCount(0);
            for(String[] producto : data){
                productosModel.addRow(producto);
            }
        
        String[] opciones =new String[tiposDeProductos.length];
        
        for(int i=0;i<tiposDeProductos.length;i++){
            opciones[i]=tiposDeProductos[i][1];
        }
        
        this.productoTipo=new JComboBox(opciones);
        this.productoNombre=new JTextField("");
        this.productoDescripcion=new JTextField("");
        this.productoPrecio=new JTextField("");
        this.productoImagen=new JButton("Upload file");
        
        productoTipo.setBounds(130, 90, 400, 20);
        productoNombre.setBounds(130, 120, 400, 20);
        productoDescripcion.setBounds(130, 150, 400, 20);
        productoPrecio.setBounds(130, 180, 400, 20);
        productoImagen.setBounds(130, 210, 200, 40);
        txtImagen.setBounds(340, 210, 200, 40);
        
        this.bEditar=new JButton("Update");
        this.bAnadir=new JButton("Create");
        this.bShowStatistics=new JButton("Ver Estadisticas");
        this.bBorrar=new JButton("Delete");
        this.bMostrar=new JButton("Read");
        
         
        bEditar.setBounds(570, 90, 130, 40);
        bAnadir.setBounds(720, 90, 130, 40);  
        bBorrar.setBounds(570, 150, 280, 40);
        bMostrar.setBounds(750, 150, 100, 40);
        bShowStatistics.setBounds(630, 210, 220, 40);

        
        this.add(frameProductos);
        
        this.frameProductos.setLayout(null);
        
        this.frameProductos.add(productoTitulo);
        this.frameProductos.add(tvproductoTipo);
        this.frameProductos.add( tvproductoNombre);
        this.frameProductos.add(tvproductoDescripcion);
        this.frameProductos.add(tvproductoPrecio);
        this.frameProductos.add(tvproductoImagen);
        
        this.frameProductos.add(scrollProductos);
        
        this.frameProductos.add(productoTitulo);
        this.frameProductos.add(productoTipo);
        this.frameProductos.add(productoNombre);
        this.frameProductos.add(productoDescripcion);
        this.frameProductos.add(productoPrecio);
        this.frameProductos.add(productoImagen);
        this.frameProductos.add(txtImagen);
        
        this.frameProductos.add(bEditar);
        this.frameProductos.add(bAnadir);
        this.frameProductos.add(bBorrar);       
    }
    public void initClientes(){
        this.frameClientes= new JInternalFrame();
       
        this.tvClienteNombre=new JLabel("Name:");
        this.tvClienteCedula=new JLabel("DNI:");
        this.tvClienteEdad=new JLabel("Age:");
        this.tvClienteGenero=new JLabel("Gender:");
        this.clienteTitulo=new JLabel("Customers");
        
        String[] columnNames={"Name", "DNI", "Gender", "Age"};
        ClientesModel = new CustomTableModel(columnNames,0);
        tableClientes = new JTable(ClientesModel);
        
        this.scrollClientes=new JScrollPane(tableClientes);
        
        Font font = new Font("Arial", Font.BOLD, 24);
        clienteTitulo.setFont(font);
        clienteTitulo.setBounds(30, 30, 500, 20);
        
        tvClienteNombre.setBounds(30, 90, 100, 20);
        tvClienteCedula.setBounds(30, 120, 100, 20);
        tvClienteEdad.setBounds(30, 150, 100, 20);
        tvClienteGenero.setBounds(30, 180, 100, 20);
        
        scrollClientes.setBounds(30, 230, 820, 450);
       
        String[] opciones = {"Male", "Female", "Other"};
        this.ClienteGenero=new JComboBox(opciones);
        this.ClienteNombre=new JTextField("");
        this.ClienteCedula=new JTextField("");
        this.ClienteEdad=new JTextField("");
        
        ClienteNombre.setBounds(130, 90, 400, 20);
        ClienteCedula.setBounds(130, 120, 400, 20);
        ClienteEdad.setBounds(130, 150, 400, 20);
        ClienteGenero.setBounds(130, 180, 400, 20);
        
        this.bEditarCliente=new JButton("Upload");
        this.bAnadirCliente=new JButton("Create");
        this.bShowStatisticsCliente=new JButton("Ver Estadisticas");
        this.bBorrarCliente=new JButton("Delete");
        this.bMostrarCliente=new JButton("Read");
        
        bEditarCliente.setBounds(570, 90, 130, 40);
        bAnadirCliente.setBounds(720, 90, 130, 40);  
        bBorrarCliente.setBounds(570, 150, 280, 40);
        bShowStatisticsCliente.setBounds(720, 150, 130, 40);

        
        this.add(frameClientes);
        
        this.frameClientes.setLayout(null);
        
        this.frameClientes.add(clienteTitulo);
        this.frameClientes.add(tvClienteNombre);
        this.frameClientes.add( tvClienteCedula);
        this.frameClientes.add(tvClienteEdad);
        this.frameClientes.add(tvClienteGenero);
        
        this.frameClientes.add(scrollClientes);
        
        this.frameClientes.add(ClienteNombre);
        this.frameClientes.add(ClienteEdad);
        this.frameClientes.add(ClienteCedula);
        this.frameClientes.add(ClienteGenero);
        
        this.frameClientes.add(bEditarCliente);
        this.frameClientes.add(bAnadirCliente);
        this.frameClientes.add(bBorrarCliente);
       
    }
    public void initVentas(){
        this.frameVentas = new JInternalFrame();
        
        this.tvVentaId=new JLabel("");
        this.tvVentaProducto=new JLabel("Product:");
        this.tvVentaCantidad=new JLabel("Quantity:");
        this.tvVentaCliente=new JLabel("Customer:");
        this.tvVentaFecha=new JLabel("Date:");
        this.VentaTitulo=new JLabel("Sells");
        
        
        String[] columnNames={"id","Product", "Quantity", "Customer", "Date","Total Value"};
        VentasModel = new CustomTableModel(columnNames,0);
        tableVentas = new JTable(VentasModel);
        
        this.scrollVentas=new JScrollPane(tableVentas);
        
        
        Font font = new Font("Arial", Font.BOLD, 24);
        VentaTitulo.setFont(font);
        VentaTitulo.setBounds(30, 30, 500, 20);
        
        tvVentaId.setBounds(570, 70, 50, 20);
        tvVentaProducto.setBounds(30, 90, 100, 20);
        tvVentaCantidad.setBounds(30, 120, 100, 20);
        tvVentaCliente.setBounds(30, 150, 100, 20);
        tvVentaFecha.setBounds(30, 180, 100, 20);
        
        scrollVentas.setBounds(30, 230, 820, 450);
       
        
        this.VentaId=new JLabel("");
        this.menuProductos=ventasController.getNamesProductos();
        this.VentaProducto=new JComboBox(menuProductos);
        this.VentaCantidad=new JTextField("");
        this.VentaFecha=new JTextField("");
        this.menuClientes=ventasController.getNamesClientes();
        this.VentaCliente=new JComboBox(menuClientes);
        
        VentaId.setBounds(630, 70, 50, 20);
        VentaProducto.setBounds(130, 90, 400, 20);
        VentaCantidad.setBounds(130, 120, 400, 20);
        VentaCliente.setBounds(130, 150, 400, 20);
        VentaFecha.setBounds(130, 180, 400, 20);
        
        this.bEditarVenta=new JButton("Upload");
        this.bAnadirVenta=new JButton("Create");
        this.bShowStatisticsVenta=new JButton("Ver Estadisticas");
        this.bBorrarVenta=new JButton("Delete");
        this.bMostrarVenta=new JButton("Read");
        
         
        bEditarVenta.setBounds(570, 90, 130, 40);
        bAnadirVenta.setBounds(720, 90, 130, 40);  
        bBorrarVenta.setBounds(570, 150, 280, 40);
        bMostrarVenta.setBounds(750, 150, 100, 40);
        bShowStatisticsVenta.setBounds(630, 210, 220, 40);

        
        this.add(frameVentas);
        
        this.frameVentas.setLayout(null);
        
        this.frameVentas.add(VentaTitulo);
        this.frameVentas.add(tvVentaId);
        this.frameVentas.add(tvVentaProducto);
        this.frameVentas.add( tvVentaCantidad);
        this.frameVentas.add(tvVentaCliente);
        this.frameVentas.add(tvVentaFecha);
        
        this.frameVentas.add(scrollVentas);
        
        this.frameVentas.add(VentaId);
        this.frameVentas.add(VentaProducto);
        this.frameVentas.add(VentaCantidad);
        this.frameVentas.add(VentaCliente);
        this.frameVentas.add(VentaFecha);
        
        this.frameVentas.add(bEditarVenta);
        this.frameVentas.add(bAnadirVenta);
        this.frameVentas.add(bBorrarVenta);
    }
    
    public void initListeners(){
        listenersProductos();
        listenersClientes();
        listenersVentas();
    }
    
    public void listenersProductos(){
        this.ventanaProductos.addActionListener(this);
        this.productoImagen.addActionListener(this);
        this.bBorrar.addActionListener(this);
        this.bEditar.addActionListener(this);
        this.bAnadir.addActionListener(this);
        
        tableProductos.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            int row = tableProductos.getSelectedRow(); 
            if (row != -1) {
                productoNombre.setText((String)tableProductos.getValueAt(row, 2));
                productoDescripcion.setText((String)tableProductos.getValueAt(row, 3));
                productoPrecio.setText((String)tableProductos.getValueAt(row, 4));
                txtImagen.setText((String)tableProductos.getValueAt(row, 5));
            
        }
    }
        });
        
    }
    public void listenersClientes(){
        this.ventanaClientes.addActionListener(this);
        this.bMostrarCliente.addActionListener(this);
        this.bAnadirCliente.addActionListener(this);
        this.bBorrarCliente.addActionListener(this);
        this.bEditarCliente.addActionListener(this);
        
        
        tableClientes.addMouseListener(new MouseAdapter(){
            @Override
             public void mouseClicked(MouseEvent e) {
            int row = tableClientes.getSelectedRow(); 
            if (row != -1) {
                ClienteNombre.setText((String)tableClientes.getValueAt(row, 0));
                ClienteCedula.setText((String)tableClientes.getValueAt(row, 1));
                ClienteEdad.setText((String)tableClientes.getValueAt(row, 3));
        }
    }
        });
    }
    
    public void listenersVentas(){
        this.ventanaVentas.addActionListener(this);
        this.bMostrarVenta.addActionListener(this);
        this.bAnadirVenta.addActionListener(this);
        this.bBorrarVenta.addActionListener(this);
        this.bEditarVenta.addActionListener(this);
        
        tableVentas.addMouseListener(new MouseAdapter(){
            @Override
             public void mouseClicked(MouseEvent e) {
            int row = tableVentas.getSelectedRow(); 
            if (row != -1) {
                VentaCantidad.setText((String)tableVentas.getValueAt(row, 2));
                VentaFecha.setText((String)tableVentas.getValueAt(row, 4));
                tvVentaId.setText("id:");
                VentaId.setText((String)tableVentas.getValueAt(row, 0));
        }
    }
        });
        
    }
    
    public void visualizar(){
        setTitle("soft restaurant");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         accionesCliente(e);
         accionesProducto(e);
         accionesVenta(e);
    }
    
    private void accionesProducto(ActionEvent e){
        if(e.getSource()==this.ventanaProductos){
            frameProductos.show(true);
            frameClientes.show(false);
            frameVentas.show(false);
            try {
                frameProductos.setMaximum(true);
            } catch (PropertyVetoException ex) {
                System.out.println("Fallo");
            }
        }else if(e.getSource()== this.productoImagen){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(frameProductos);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                txtImagen.setText(selectedFile.getAbsolutePath());
            }
        }else if (e.getSource()== bAnadir) {
            productosController.addProducto(productoTipo.getSelectedItem().toString(),productoNombre.getText(), productoDescripcion.getText(), productoPrecio.getText(), txtImagen.getText());
            String[][] data=productosController.getArrayProductos();
            productosModel.setRowCount(0);
            for(String[] producto : data){
                productosModel.addRow(producto);
            }
            
             menuProductos=ventasController.getNamesProductos();
            VentaProducto.removeAllItems();
            for (String item : menuProductos) {
                VentaProducto.addItem(item);
            }
            

        }else if(e.getSource()==bBorrar){
            productosController.deleteProducto(productoNombre.getText());
            String[][] data=productosController.getArrayProductos();
            productosModel.setRowCount(0);
            for(String[] producto : data){
                productosModel.addRow(producto);
            }
            
            menuProductos=ventasController.getNamesProductos();
            VentaProducto.removeAllItems();
            for (String item : menuProductos) {
                VentaProducto.addItem(item);
            }
            
            limpiarCamposProducto();
        }else if(e.getSource()==bEditar){
            productosController.updateProducto(productoTipo.getSelectedItem().toString(),productoNombre.getText(), productoDescripcion.getText(), productoPrecio.getText(), txtImagen.getText());
            String[][] data=productosController.getArrayProductos();
            productosModel.setRowCount(0);
            for(String[] producto : data){
                productosModel.addRow(producto);
            }
            
            menuProductos=ventasController.getNamesProductos();
            VentaProducto.removeAllItems();
            for (String item : menuProductos) {
                VentaProducto.addItem(item);
            }
            
            limpiarCamposProducto();
        }
    }
    private void accionesCliente(ActionEvent e){
        if(e.getSource()==ventanaClientes){
            frameClientes.show(true);
            frameProductos.show(false);
            frameVentas.show(false);
            String[][] data = clientesController.getArrayClientes();
            ClientesModel.setRowCount(0);
            for(String[] cliente : data){
                ClientesModel.addRow(cliente);
            }
            try {
                frameClientes.setMaximum(true);
            } catch (PropertyVetoException ex) {
                System.out.println("Fallo");
            }
        }else if(e.getSource()==bAnadirCliente){
            clientesController.addCliente(ClienteNombre.getText(), ClienteCedula.getText(), ClienteGenero.getSelectedItem().toString(), ClienteEdad.getText());
            String[][] data = clientesController.getArrayClientes();
            ClientesModel.setRowCount(0);
            for(String[] cliente : data){
                ClientesModel.addRow(cliente);
            }
            
            menuClientes=ventasController.getNamesClientes();
            VentaCliente.removeAllItems();
            for (String item : menuClientes) {
                VentaCliente.addItem(item);
            }
            

        }else if(e.getSource()==bBorrarCliente){
            clientesController.deleteCliente(ClienteCedula.getText());
            String[][] data = clientesController.getArrayClientes();
            ClientesModel.setRowCount(0);
            for(String[] cliente : data){
                ClientesModel.addRow(cliente);
            }
            
             menuClientes=ventasController.getNamesClientes();
            VentaCliente.removeAllItems();
            for (String item : menuClientes) {
                VentaCliente.addItem(item);
            }
            
            limpiarCamposCliente();
        }else if(e.getSource()==bEditarCliente){
            clientesController.updateCliente(ClienteNombre.getText(),ClienteCedula.getText(), ClienteGenero.getSelectedItem().toString(), ClienteEdad.getText());
            String[][] data = clientesController.getArrayClientes();
            ClientesModel.setRowCount(0);
            for(String[] cliente : data){
                ClientesModel.addRow(cliente);
            }
            
            menuClientes=ventasController.getNamesClientes();
            VentaCliente.removeAllItems();
            for (String item : menuClientes) {
                VentaCliente.addItem(item);
            }
            
            limpiarCamposCliente();
        }
    
    }
    private void accionesVenta(ActionEvent e){
        if(e.getSource()==ventanaVentas){
            frameClientes.show(false);
            frameProductos.show(false);
            frameVentas.show(true);
            String[][] data=ventasController.getArrayVentas();
            VentasModel.setRowCount(0);
            for(String[] venta : data){
                VentasModel.addRow(venta);
            }
            try {
                frameVentas.setMaximum(true);
            } catch (PropertyVetoException ex) {
                System.out.println("Fallo");
            }
        }else if(e.getSource()==bAnadirVenta){
            ventasController.addVenta(VentaProducto.getSelectedItem().toString(), VentaCantidad.getText(), VentaCliente.getSelectedItem().toString(), VentaFecha.getText());
            String[][] data=ventasController.getArrayVentas();
            VentasModel.setRowCount(0);
            for(String[] venta : data){
                VentasModel.addRow(venta);
            }

        }else if(e.getSource()==bBorrarVenta){
            ventasController.deleteVenta(VentaId.getText());
            String[][] data=ventasController.getArrayVentas();
            VentasModel.setRowCount(0);
            for(String[] venta : data){
                VentasModel.addRow(venta);
            }
            limpiarCamposVenta();
        }else if(e.getSource()==bEditarVenta){
            ventasController.updateVenta(VentaId.getText(),VentaProducto.getSelectedItem().toString(), VentaCantidad.getText(), VentaCliente.getSelectedItem().toString(), VentaFecha.getText());
            String[][] data=ventasController.getArrayVentas();
            VentasModel.setRowCount(0);
            for(String[] venta : data){
                VentasModel.addRow(venta);
            }
            limpiarCamposVenta();
        }
    }
    
    public void limpiarCamposProducto(){
        productoNombre.setText("");
        productoDescripcion.setText("");
        productoPrecio.setText("");
        txtImagen.setText("");
    }
    
    public void limpiarCamposCliente(){
        ClienteNombre.setText("");
        ClienteCedula.setText("");
        ClienteEdad.setText("");
    }
    
    public void limpiarCamposVenta(){
        VentaCantidad.setText("");
        VentaFecha.setText("");
        tvVentaId.setText("");
        VentaId.setText("");
    }
    
}
