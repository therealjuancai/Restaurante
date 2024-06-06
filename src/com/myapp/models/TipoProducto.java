/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.models;

/**
 *
 * @author juanc
 */
public class TipoProducto {
    private int id;
    private String Tipo;

    public TipoProducto(int id, String Tipo) {
        this.id = id;
        this.Tipo = Tipo;
    }

    public TipoProducto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
}
