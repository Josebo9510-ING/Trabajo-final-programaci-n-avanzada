/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class MetodoPago implements Serializable{
    private int codigo_pago;
    private String descripcion_metodo_pago;
    private List<Venta> ListaVenta =new ArrayList();

    public int getCodigo_pago() {
        return codigo_pago;
    }

    public void setCodigo_pago(int codigo_pago) {
        this.codigo_pago = codigo_pago;
    }

    public String getDescripcion_metodo_pago() {
        return descripcion_metodo_pago;
    }

    public void setDescripcion_metodo_pago(String descripcion_metodo_pago) {
        this.descripcion_metodo_pago = descripcion_metodo_pago;
    }

    public List<Venta> getListaVenta() {
        return ListaVenta;
    }

    public void setListaVenta(List<Venta> ListaVenta) {
        this.ListaVenta = ListaVenta;
    }
   
}