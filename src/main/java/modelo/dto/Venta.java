/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;
import java.io.Serializable;
import java.time.LocalDate;
import java.math.BigDecimal;


/**
 *
 * @author Usuario
 */
public class Venta implements Serializable {
    private int num_factura_venta;
    private BigDecimal precio_base;
    private BigDecimal impuesto_venta;
    private BigDecimal IVA;
    private BigDecimal total_venta;
    private LocalDate fecha_venta;
    
// llaves foraneas
    private Cliente cliente;
    private Vendedor vendedor;
    private MetodoPago metodoPago;
    private Automovil automovil;

    public int getNum_factura_venta() {
        return num_factura_venta;
    }

    public void setNum_factura_venta(int num_factura_venta) {
        this.num_factura_venta = num_factura_venta;
    }

    public BigDecimal getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(BigDecimal precio_base) {
        this.precio_base = precio_base;
    }

    public BigDecimal getImpuesto_venta() {
        return impuesto_venta;
    }

    public void setImpuesto_venta(BigDecimal impuesto_venta) {
        this.impuesto_venta = impuesto_venta;
    }

    public BigDecimal getIVA() {
        return IVA;
    }

    public void setIVA(BigDecimal IVA) {
        this.IVA = IVA;
    }

    public BigDecimal getTotal_venta() {
        return total_venta;
    }

    public void setTotal_venta(BigDecimal total_venta) {
        this.total_venta = total_venta;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }
}
    
   