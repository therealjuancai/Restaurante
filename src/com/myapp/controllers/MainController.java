/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.controllers;

/**
 *
 * @author juanc
 */
import com.myapp.views.*;

public class MainController {
    private MainView mainView;

    public MainController() {
        ClientesController clientesController=new ClientesController();
        ProductosController productosController=new ProductosController();
        mainView = new MainView(this,clientesController,productosController);
    }

    public void showMainView() {
        mainView.setVisible(true);
    }

}