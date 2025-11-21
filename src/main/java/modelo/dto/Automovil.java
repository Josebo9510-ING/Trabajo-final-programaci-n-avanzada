/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Usuario
 */
public class Automovil implements Serializable{
    // Atributos con ID
    private int id_auto;
    private BigDecimal precio_base;
    private int id_marca;
    private int id_modelo;
    private int id_color;
    private int id_tipo_motor;
    
    // Atributos con nombre
    private String nombre_marca;
    private String descripcion;
    private String nombre_modelo;
    private String nombre_color;

    
    // Getters and setters
    public int getId_auto() {
        return id_auto;
    }

    public void setId_auto(int id_auto) {
        this.id_auto = id_auto;
    }

    public BigDecimal getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(BigDecimal precio_base) {
        this.precio_base = precio_base;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public int getId_tipo_motor() {
        return id_tipo_motor;
    }

    public void setId_tipo_motor(int id_tipo_motor) {
        this.id_tipo_motor = id_tipo_motor;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre_modelo() {
        return nombre_modelo;
    }

    public void setNombre_modelo(String nombre_modelo) {
        this.nombre_modelo = nombre_modelo;
    }

    public String getNombre_color() {
        return nombre_color;
    }

    public void setNombre_color(String nombre_color) {
        this.nombre_color = nombre_color;
    }
    
}

    