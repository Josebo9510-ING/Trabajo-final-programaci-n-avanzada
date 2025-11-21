/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUAutomóvil;
import vista.IUBusquedaDeVendedor;
import vista.IUCliente;
import vista.IUColor;
import vista.IUMarca;
import vista.IUModelo;
import vista.IUMétodosDePago;
import vista.IURegistrarVenta;
import vista.IUTipoMotor;
import vista.IUVendedor;
import vista.IUVendedorConMayorVenta;
import vista.IUVentasCreditoConcesionario;
import vista.IUVentasPorVendedor;
import vista.IUVistaPrincipal;

/**
 *
 * @author Usuario
 */

/**
 * Controlador principal del sistema de concesionario.
 * Gestiona la navegación entre las diferentes vistas del programa,
 * escuchando los eventos de los menús de la interfaz principal.
 */

public class ControladorPrincipal implements ActionListener{
    private IUVistaPrincipal vista;
    
     /**
     * Constructor que asocia el controlador con la vista principal.
     * También agrega los ActionListener a los menús de la interfaz.
     * @param vista Ventana principal del sistema.
     */

      public ControladorPrincipal (IUVistaPrincipal vista){
          this.vista = vista;
           // Interfaz gestión
          this.vista.jMenuAutomovil.addActionListener(this);
          this.vista.jMenuVendedor.addActionListener(this);
          this.vista.jMenuCliente.addActionListener(this);
          this.vista.jMenuMetodoPago.addActionListener(this);
          
          // Interfaz Catalogo
          this.vista.jMenuMarca.addActionListener(this);
          this.vista.jMenuTipoMotor.addActionListener(this);
          this.vista.jMenuColor.addActionListener(this);
          this.vista.jMenuModelo.addActionListener(this);
          
          // Interfaz venta
          this.vista.jMenuRegistroVenta.addActionListener(this);    
          
          // Interfaz informes y consulta
          this.vista.jMenuVentasVendedor.addActionListener(this);
          this.vista.jMenuBuscarVendedor.addActionListener(this);
          this.vista.jMenuVendedorMayorVenta.addActionListener(this);
          this.vista.jMenuVentaCredito.addActionListener(this);
          
          this.vista.setVisible(true);
      }
      
     /**
     * Método que detecta el menú seleccionado por el usuario
     * y abre la vista correspondiente a través de su controlador.
     */

      @Override
      public void actionPerformed (ActionEvent ae){
          if (ae.getSource().equals(this.vista.jMenuAutomovil)){
              ControladorAutomovil controladorAutomovil =new ControladorAutomovil(new IUAutomóvil());
              
              }
          if (ae.getSource().equals(this.vista.jMenuVendedor)){
              ControladorVendedor controladorVendedor =new ControladorVendedor (new IUVendedor());
              }
         if (ae.getSource().equals(this.vista.jMenuCliente)){
              ControladorCliente controladorCliente =new ControladorCliente (new IUCliente());
              }
         if (ae.getSource().equals(this.vista.jMenuMetodoPago)){
              ControladorMetodoDePago controladorMetodoDePago =new ControladorMetodoDePago (new IUMétodosDePago());
              }
         
         
         if (ae.getSource().equals(this.vista.jMenuMarca)){
              ControladorMarca controladorMarca =new ControladorMarca (new IUMarca());
              }
         if (ae.getSource().equals(this.vista.jMenuTipoMotor)){
              ControladorTipoMotor controladorTipoMotor =new ControladorTipoMotor (new IUTipoMotor());
              }
         if (ae.getSource().equals(this.vista.jMenuColor)){
              ControladorColor controladorColor =new ControladorColor (new IUColor());
              }
         if (ae.getSource().equals(this.vista.jMenuModelo)){
              ControladorModelo controladorModelo =new ControladorModelo (new IUModelo());
              }
         
         if (ae.getSource().equals(this.vista.jMenuRegistroVenta)){
              ControladorRegistrarVenta controladorRegistrarVenta =new ControladorRegistrarVenta (new IURegistrarVenta());
              }
         
         if (ae.getSource().equals(this.vista.jMenuVentasVendedor)){
              ControladorVentasPorVendedor controladorVentasPorVendedor =new ControladorVentasPorVendedor (new IUVentasPorVendedor());
              }
         if (ae.getSource().equals(this.vista.jMenuBuscarVendedor)){
              ControladorBuscarVendedor controladorBuscarVendedor =new ControladorBuscarVendedor (new IUBusquedaDeVendedor());
              }
         if (ae.getSource().equals(this.vista.jMenuVendedorMayorVenta)){
              ControladorVendedorConMayorVenta controladorVendedorConMayorVenta =new ControladorVendedorConMayorVenta (new IUVendedorConMayorVenta());
              }
         if (ae.getSource().equals(this.vista.jMenuVentaCredito)){
              ControladorVentasCreditoConcesionario controladorVentasCreditoConcesionario =new ControladorVentasCreditoConcesionario (new IUVentasCreditoConcesionario());
              }
      }    
}
