/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Usuario
 */
public class Modelo implements Serializable{
    private int id_modelo;
    private String nombre_modelo;
    private List<Automovil> ListaAutomovil =new ArrayList();

    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getNombre_modelo() {
        return nombre_modelo;
    }

    public void setNombre_modelo(String nombre_modelo) {
        this.nombre_modelo = nombre_modelo;
    }

    public List<Automovil> getListaAutomovil() {
        return ListaAutomovil;
    }

    public void setListaAutomovil(List<Automovil> ListaAutomovil) {
        this.ListaAutomovil = ListaAutomovil;
    }

   
  }
