/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TipoMotor implements Serializable{
    private int id_tipo_motor;
    private String descripcion;
    private BigDecimal porcentaje_impuesto;
    private List<Automovil> ListaVenta =new ArrayList();

    public int getId_tipo_motor() {
        return id_tipo_motor;
    }

    public void setId_tipo_motor(int id_tipo_motor) {
        this.id_tipo_motor = id_tipo_motor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPorcentaje_impuesto() {
        return porcentaje_impuesto;
    }

    public void setPorcentaje_impuesto(BigDecimal porcentaje_impuesto) {
        this.porcentaje_impuesto = porcentaje_impuesto;
    }

    public List<Automovil> getListaVenta() {
        return ListaVenta;
    }

    public void setListaVenta(List<Automovil> ListaVenta) {
        this.ListaVenta = ListaVenta;
    }

   


   
  
}