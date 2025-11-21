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
public class Vendedor implements Serializable{
    private int id_vendedor;
    private String nombre_vendedor;
    private String profesion;
    private List<Venta> ListaVenta =new ArrayList();
    
    // getters and setters

    public Vendedor() {
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public List getListaVenta() {
        return ListaVenta;
    }

    public void setListaVenta(List ListaVenta) {
        this.ListaVenta = ListaVenta;
    }
    
    
    
    
}
