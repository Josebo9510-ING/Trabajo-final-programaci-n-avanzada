/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUVentasPorVendedor;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.VendedorDao;
import modelo.dao.VentaDao;
import modelo.dto.Vendedor;
import modelo.dto.Venta;
import modelo.dao.MetodoPagoDao;
import modelo.dao.ClienteDao;

/**
 *
 * @author Usuario
 */

/**
 * Controlador que muestra las ventas realizadas por cada vendedor.
 */

public class ControladorVentasPorVendedor implements ActionListener{
    private IUVentasPorVendedor vista;
    private VentaDao ventaDao;
    private VendedorDao vendedorDao;
    private MetodoPagoDao MetodoPagoDao;
    private ClienteDao clienteDao;
    

public  ControladorVentasPorVendedor (IUVentasPorVendedor vista){
    this.vista = vista;
    this.ventaDao = new VentaDao();
    this.vendedorDao = new VendedorDao();
    this.clienteDao = new ClienteDao();    
    this.MetodoPagoDao = new MetodoPagoDao();
    
    // Escuchar el botón buscar
     this.vista.BuscarIDVendedor_IUVentasPorVendedor.addActionListener(this);
    // Escuchar el botón limpiar
    this.vista.BtnLimpiarVentas.addActionListener(this);
    
       vista.setVisible(true);
        
}

    @Override
    // boton buscar
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista.BuscarIDVendedor_IUVentasPorVendedor)){
          generarReporte();
        }
        // boton limpiar
       if (ae.getSource().equals(this.vista.BtnLimpiarVentas)) {
        limpiarPantalla();
    }     
      }
  
    public void generarReporte (){
     try {
     if (this.vista.IDVendedor_IUVentasPorVendedor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un ID de vendedor");
                return;
            }

            int id_vendedor = Integer.valueOf(this.vista.IDVendedor_IUVentasPorVendedor.getText());

            // Buscar datos del Vendedor 
            Vendedor vendedor = vendedorDao.Consultar(id_vendedor);

            if (vendedor != null) {
                this.vista.NombreVendedor_IUVentasPorVendedor.setText(vendedor.getNombre_vendedor());
                
                // Llenar la Tabla con las ventas
                llenarTablaVentas(id_vendedor);
                
                // Calcular y mostrar el Total Acumulado
                // Usamos el método que ya tenías en VentaDao
                BigDecimal total = ventaDao.obtenerTotalVentasPorVendedor(id_vendedor);
                
                // Si no hay ventas
                if (total == null) total = BigDecimal.ZERO;
                
                this.vista.TotalVentas_IUVentasPorVendedor.setText("$ " + total.toString());
                
            } else {
                JOptionPane.showMessageDialog(vista, "No existe vendedor con ese ID");
                limpiarPantalla();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "El ID debe ser un número válido");
        }
    }
    
        // llenar la tabla
      private void llenarTablaVentas(int id_vendedor) {
        // Obtenemos la lista del DAO
        List<Venta> listaVentas = ventaDao.listarVentasPorVendedor(id_vendedor);
        
        //  Configuración de la tabla 
        // validar si hay datos en la tabla
        if (listaVentas != null && !listaVentas.isEmpty()) {
        
        // Recuperamos el modelo existente en la tabla 
            DefaultTableModel modeloTabla = (DefaultTableModel)
            this.vista.jTableIUVentasPorVendedor.getModel();
            
          
        //  Llenar las filas
        for (Venta v : listaVentas) {
                Object[] fila = new Object[5];
                
                
        
           fila[0] = v.getNum_factura_venta();
           fila[1] = v.getFecha_venta();
                
            //  Nombre Cliente 
           int id_cliente = v.getCliente().getId_cliente();
           modelo.dto.Cliente c = clienteDao.Consultar(id_cliente);
                
                if (c != null) {
                    fila[2] = c.getNombre_cliente(); // nombre
                } else {
                    fila[2] = "Desconocido";
                }

           // Método de Pago (Consultando a la BD) ---
            int idPago = v.getMetodoPago().getCodigo_pago();
            modelo.dto.MetodoPago mp = MetodoPagoDao.Consultar(idPago);
                
                if (mp != null) {
                    // traer la variable de nombre de pago
                    fila[3] = mp.getDescripcion_metodo_pago(); 
                } else {
                    fila[3] = "Desconocido";
                }
                
                // Total ---
                fila[4] = v.getTotal_venta();

                modeloTabla.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Este vendedor no ha realizado ventas aún.");
            // Limpiamos la tabla visualmente
            ((DefaultTableModel) this.vista.jTableIUVentasPorVendedor.getModel()).setRowCount(0);
        }
    }

    public void limpiarPantalla() {
    // Borrar Cajas de Texto
    this.vista.IDVendedor_IUVentasPorVendedor.setText("");
    this.vista.NombreVendedor_IUVentasPorVendedor.setText("");
    this.vista.TotalVentas_IUVentasPorVendedor.setText("");

    // Limpiar la Tabla visualmente
    
    DefaultTableModel modeloTabla = (DefaultTableModel) this.vista.jTableIUVentasPorVendedor.getModel();
    modeloTabla.setRowCount(0);
}
}
    
 
