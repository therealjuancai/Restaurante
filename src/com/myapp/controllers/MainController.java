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
        mainView = new MainView();
        ClientesController clientesController=new ClientesController();
        mainView.setMainController(this);
        mainView.setClientesController(clientesController);
    }

    public void showMainView() {
        mainView.setVisible(true);
    }

    public void showClienteView() {

    }

    public void showProductoView() {

    }

    public void showVentaView() {

    }
}