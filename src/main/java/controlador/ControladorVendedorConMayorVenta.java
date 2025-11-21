/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUVendedorConMayorVenta;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.VentaDao;
import modelo.dao.VendedorDao;
import modelo.dto.Venta;
import modelo.dto.Vendedor;
/**
 *
 * @author Usuario
 */

/**
 * Controlador que muestra la información del vendedor con mayor número de ventas.
 */

public class ControladorVendedorConMayorVenta implements ActionListener{
    private IUVendedorConMayorVenta vista;
    private VentaDao ventaDao;
    private VendedorDao vendedorDao;
    

public  ControladorVendedorConMayorVenta (IUVendedorConMayorVenta vista){
    this.vista = vista;
    this.vendedorDao = new VendedorDao();
    this.ventaDao = new VentaDao();
    
     // escuchar botón mostrar
     this.vista.MostrarVendedorConMayorVenta_IUVendedoMayVenta.addActionListener(this);
     // escuchar botón limpiar
     this.vista.BtnLimpiarInforme.addActionListener(this);
    
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista.MostrarVendedorConMayorVenta_IUVendedoMayVenta)){
        mostrarVendedorMayorVenta();
        }
        
       if (ae.getSource().equals(this.vista.BtnLimpiarInforme)){
        limpiarTabla();
        }
    }
      private void mostrarVendedorMayorVenta(){
      Venta ventaMayor = ventaDao.obtenerVentaMayorMonto();
        
        if (ventaMayor != null) {
            //  Obtener el ID del vendedor que hizo esa venta
            int id_vendedor = ventaMayor.getVendedor().getId_vendedor();
            
            //  Consultar los datos del vendedor
            Vendedor vendedor = vendedorDao.Consultar(id_vendedor);
            
            // Preparar la tabla
            DefaultTableModel modeloTabla = (DefaultTableModel) this.vista.jTableDatosVendedorMayorVenta.getModel();
            modeloTabla.setRowCount(0); 
            
            // Llenar la fila 
            Object[] fila = new Object[5];
            
            
            fila[0] = id_vendedor;
            
            
            if (vendedor != null) {
                fila[1] = vendedor.getNombre_vendedor();
                fila[2] = vendedor.getProfesion();
            } else {
                fila[1] = "Desconocido (ID: " + id_vendedor + ")";
                fila[2] = "-";
            }
            
            // Columna 4: N° Factura
            fila[3] = ventaMayor.getNum_factura_venta();
            
            // Columna 5: Valor de la venta
            fila[4] = ventaMayor.getTotal_venta();
            
            // Agregamos la fila a la tabla
            modeloTabla.addRow(fila);
            
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontraron ventas registradas en el sistema.");
            // Limpiamos la tabla por si acaso
            ((DefaultTableModel) this.vista.jTableDatosVendedorMayorVenta.getModel()).setRowCount(0);
        }
    }
      
      public void limpiarTabla(){
      
        DefaultTableModel modeloTabla = (DefaultTableModel) this.vista.jTableDatosVendedorMayorVenta.getModel();
        modeloTabla.setRowCount(0);
       }
}
      
  
 
    