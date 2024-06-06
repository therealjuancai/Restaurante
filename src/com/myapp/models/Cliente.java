/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.models;

/**
 *
 * @author juanc
 */
public class Cliente {
    private int id;
    private String Nombre;
    private String Cedula;
    private String Genero;
    private int Edad;

    public Cliente(int id, String Nombre, String Cedula, String Genero, int Edad) {
        this.id = id;
        this.Nombre = Nombre;
        this.Cedula = Cedula;
        this.Genero = Genero;
        this.Edad = Edad;
    }
    
    public Cliente(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
    
    
}
