/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IURegistrarVenta;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

// DAOS
import modelo.dao.VentaDao;
import modelo.dao.VendedorDao;
import modelo.dao.ClienteDao;
import modelo.dao.AutomovilDao;
import modelo.dao.MetodoPagoDao;

// DTOS

import modelo.dto.Venta;
import modelo.dto.Vendedor;
import modelo.dto.Cliente;
import modelo.dto.Automovil;
import modelo.dto.MetodoPago;




/**
 *
 * @author Usuario
 */

 /**
 * Controlador que gestiona la interfaz de registro de ventas.
 */

public class ControladorRegistrarVenta implements ActionListener{
    private IURegistrarVenta vista;
    
// DAOs necesarios para buscar la info
    private VentaDao ventaDao;
    private VendedorDao vendedorDao;
    private ClienteDao clienteDao;
    private AutomovilDao automovilDao;
    private MetodoPagoDao metodoPagoDao; 

    // Objetos temporales para guardar lo que se encuentra en las búsquedas
    private Vendedor vendedorActual;
    private Cliente clienteActual;
    private Automovil automovilActual;

    public ControladorRegistrarVenta(IURegistrarVenta vista) {
        this.vista = vista;
        
        // Inicializamos todos los DAOs
        this.ventaDao = new VentaDao();
        this.vendedorDao = new VendedorDao();
        this.clienteDao = new ClienteDao();
        this.automovilDao = new AutomovilDao();
        this.metodoPagoDao = new MetodoPagoDao();

        // Escuchar los botones
        this.vista.BuscarVendedor_IURegistrarVenta.addActionListener(this);
        this.vista.BuscarCliente_IURegistrarVenta.addActionListener(this);
        this.vista.BuscarAuto_IURegistrarVenta.addActionListener(this);
        this.vista.RegistrarVenta_Button.addActionListener(this);
        this.vista.Limpiar_IURegistrarVenta.addActionListener(this);
        this.vista.Salir_IURegistrarVenta.addActionListener(this);

        // Inicialización de la ventana
        iniciarVista();
        this.vista.setVisible(true);
    }

    private void iniciarVista() {
        //  Poner Fecha Actual
        this.vista.Fecha_venta.setText(LocalDate.now().toString());
        
        //  Llenar el ComboBox de Metodos de Pago
        llenarComboMetodoPago();
    }
    
    private void llenarComboMetodoPago() {
        
        
        List<MetodoPago> lista = metodoPagoDao.readALL();
        DefaultComboBoxModel model = new DefaultComboBoxModel(lista.toArray());
        this.vista.MetodoPagoEnVenta_IURegistrarVenta.setModel(model);
                    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // --- BUSCAR VENDEDOR ---
        if (e.getSource() == this.vista.BuscarVendedor_IURegistrarVenta) {
            BuscarVendedor_IURegistrarVenta();
        }
        
        // --- BUSCAR CLIENTE ---
        else if (e.getSource() == this.vista.BuscarCliente_IURegistrarVenta) {
            BuscarCliente_IURegistrarVenta();
        }
        
        // --- BUSCAR AUTO (Y CALCULAR) ---
        else if (e.getSource() == this.vista.BuscarAuto_IURegistrarVenta) {
            BuscarAuto_IURegistrarVenta();
        }
        
        // --- REGISTRAR VENTA ---
        else if (e.getSource() == this.vista.RegistrarVenta_Button) {
            RegistrarVenta_Button();
        }
        
        // --- LIMPIAR ---
        else if (e.getSource() == this.vista.Limpiar_IURegistrarVenta) {
            Limpiar_IURegistrarVenta();
        }
        
        // --- SALIR ---
        else if (e.getSource() == this.vista.Salir_IURegistrarVenta) {
            this.vista.dispose();
        }
    }

    
    // LÓGICA DE BÚSQUEDA
    

    private void BuscarVendedor_IURegistrarVenta() {
        try {
            int id_vendedor = Integer.valueOf(this.vista.idVendedor_IURegistrarVenta.getText());
            vendedorActual = vendedorDao.Consultar(id_vendedor); 

            if (vendedorActual != null) {
                this.vista.nombreVendedor_IURegistrarVenta.setText(vendedorActual.getNombre_vendedor());
            } else {
                JOptionPane.showMessageDialog(vista, "Vendedor no encontrado");
                vendedorActual = null;
                this.vista.nombreVendedor_IURegistrarVenta.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID Vendedor debe ser numérico");
        }
    }

    private void BuscarCliente_IURegistrarVenta() {
        try {
            int id_cliente = Integer.valueOf(this.vista.BuscarCliente_IURegistrarVenta.getText());
            clienteActual = clienteDao.Consultar(id_cliente); 

            if (clienteActual != null) {
                this.vista.nombreCliente_IURegistrarVenta.setText(clienteActual.getNombre_cliente());
                this.vista.CorreoCliente_IURegistrarVenta.setText(clienteActual.getCorreo());
            } else {
                JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
                clienteActual = null;
                this.vista.nombreCliente_IURegistrarVenta.setText("");
                this.vista.CorreoCliente_IURegistrarVenta.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID Cliente debe ser numérico");
        }
    }

    private void BuscarAuto_IURegistrarVenta() {
        try {
            int id_auto = Integer.valueOf(this.vista.idAuto_IURegistrarVenta.getText());
            automovilActual = automovilDao.Consultar(id_auto);

            if (automovilActual != null) {
                // Llenar datos visuales
                this.vista.marca_IURegistrarVenta2.setText(automovilActual.getNombre_marca());
                this.vista.modelo_IURegistrarVenta.setText(automovilActual.getNombre_modelo());
                this.vista.color_IURegistrarVenta.setText(automovilActual.getNombre_color());
                this.vista.TipoMotor_IURegistrarVenta.setText(automovilActual.getDescripcion()); 
                this.vista.PrecioBase_IURegistrarVenta.setText(automovilActual.getPrecio_base().toString());
                
                // calcular tarifas
                calcularTarifas();
                
            } else {
                JOptionPane.showMessageDialog(vista, "Automóvil no encontrado");
                automovilActual = null;
                limpiarCamposAuto();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID Automóvil debe ser numérico");
        }
    }

        // LÓGICA TARIFAS 
    
    private void calcularTarifas() {
        if (automovilActual == null) return;

        BigDecimal precioBase = automovilActual.getPrecio_base();
        
        // Determinar porcentaje según Tipo de Motor
        // Buscamos si la descripción contiene la palabra clave
        String motor = automovilActual.getDescripcion().toLowerCase(); 
        double porcentaje = 0;

        if (motor.contains("gasolina")) {
            porcentaje = 0.10; // 10%
        } else if (motor.contains("eléctrico") || motor.contains("electrico")) {
            porcentaje = 0.20; // 20%
        } else if (motor.contains("híbrido") || motor.contains("hibrido")) {
            porcentaje = 0.30; // 30%
        }

        // Cálculo de Impuesto Venta
        BigDecimal impuestoVenta = precioBase.multiply(new BigDecimal(porcentaje));
        
        // Subtotal (Base + Impuesto) para calcular IVA
        BigDecimal subtotal = precioBase.add(impuestoVenta);
        
        // Cálculo de IVA 
        BigDecimal iva = subtotal.multiply(new BigDecimal(0.19));
        
        
        BigDecimal total = precioBase.add(impuestoVenta).add(iva);

        // Mostrar en la vista
       this.vista.Impuesto_venta_IURegistrarVenta.setText(impuestoVenta.setScale(2, RoundingMode.HALF_UP).toString());
       this.vista.IVA_IURegistrarVenta.setText(iva.setScale(2, RoundingMode.HALF_UP).toString());
       this.vista.Total_venta_IURegistrarVenta.setText(total.setScale(2, RoundingMode.HALF_UP).toString());
    }

    
    // REGISTRAR VENTA
    
    private void RegistrarVenta_Button() {
        // Validamos que se haya buscado todo primero
        if (vendedorActual == null || clienteActual == null || automovilActual == null) {
            JOptionPane.showMessageDialog(vista, "Debe buscar Vendedor, Cliente y Automóvil antes de registrar.");
            return;
        }

        try {
            Venta venta = new Venta();
            
            //  Fecha Actual
            venta.setFecha_venta(LocalDate.now());
            
            // Relaciones 
            venta.setVendedor(vendedorActual);
            venta.setCliente(clienteActual);
            venta.setAutomovil(automovilActual);
            
            //  Metodo de Pago (Del ComboBox)
            MetodoPago mp = (MetodoPago) this.vista.MetodoPagoEnVenta_IURegistrarVenta.getSelectedItem();
            venta.setMetodoPago(mp);
            
            //  Valores tomados de las cajas de texto
            venta.setPrecio_base(new BigDecimal(this.vista.PrecioBase_IURegistrarVenta.getText()));
            venta.setImpuesto_venta(new BigDecimal(this.vista.Impuesto_venta_IURegistrarVenta.getText()));
            venta.setIVA(new BigDecimal(this.vista.IVA_IURegistrarVenta.getText()));
            venta.setTotal_venta(new BigDecimal(this.vista.Total_venta_IURegistrarVenta.getText()));

            //  Guardar en BD
            if (ventaDao.Registrar(venta) > 0) {
                mostrarFacturaPantalla(venta); 
                Limpiar_IURegistrarVenta();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar la venta en BD.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error en el registro: " + ex.getMessage());
        }
    }

    
    // PANTALLAZO DE FACTURA
    
    private void mostrarFacturaPantalla(Venta v) {
        String factura = "============= FACTURA DE VENTA =============\n" +
                         "Fecha: " + v.getFecha_venta() + "\n" +
                         "Método de Pago: " + v.getMetodoPago().getDescripcion_metodo_pago() + "\n\n" +
                         "--- CLIENTE ---\n" +
                         "Nombre: " + v.getCliente().getNombre_cliente() + "\n" +
                         "Correo: " + v.getCliente().getCorreo() + "\n\n" +
                         "--- VENDEDOR ---\n" +
                         "Atendido por: " + v.getVendedor().getNombre_vendedor() + "\n\n" +
                         "--- AUTOMÓVIL ---\n" +
                         "Vehículo: " + v.getAutomovil().getNombre_marca() + " " + v.getAutomovil().getNombre_modelo() + "\n" +
                         "Motor: " + v.getAutomovil().getDescripcion() + "\n\n" +
                         "--- TOTALES ---\n" +
                         "Precio Base:   $ " + v.getPrecio_base() + "\n" +
                         "Impuesto Tipo: $ " + v.getImpuesto_venta() + "\n" +
                         "IVA (19%):     $ " + v.getIVA() + "\n" +
                         "--------------------------------------------\n" +
                         "TOTAL A PAGAR: $ " + v.getTotal_venta() + "\n" +
                         "============================================";
        
        JOptionPane.showMessageDialog(vista, factura, "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void Limpiar_IURegistrarVenta() {
        this.vista.idVendedor_IURegistrarVenta.setText("");
        this.vista.nombreVendedor_IURegistrarVenta.setText("");
        
        this.vista.idCliente_IURegistrarVenta.setText("");
        this.vista.nombreCliente_IURegistrarVenta.setText("");
        this.vista.CorreoCliente_IURegistrarVenta.setText("");
        
        this.vista.idAuto_IURegistrarVenta.setText("");
        limpiarCamposAuto();
        
        vendedorActual = null;
        clienteActual = null;
        automovilActual = null;
    }
    
    private void limpiarCamposAuto() {
        this.vista.marca_IURegistrarVenta2.setText("");
        this.vista.modelo_IURegistrarVenta.setText("");
        this.vista.color_IURegistrarVenta.setText("");
        this.vista.TipoMotor_IURegistrarVenta.setText("");
        this.vista.PrecioBase_IURegistrarVenta.setText("");
        this.vista.Impuesto_venta_IURegistrarVenta.setText("");
        this.vista.IVA_IURegistrarVenta.setText("");
        this.vista.Total_venta_IURegistrarVenta.setText("");
    }
}





