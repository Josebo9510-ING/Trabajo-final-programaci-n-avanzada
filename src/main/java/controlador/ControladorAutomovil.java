/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import modelo.dao.AutomovilDao;
import vista.IUAutomóvil;
import modelo.dto.Automovil;
import modelo.dto.Marca;
import modelo.dto.Modelo;
import modelo.dto.Color;
import modelo.dto.TipoMotor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Usuario
 */

 /**
 * Controlador encargado de gestionar las acciones relacionadas con
 * la interfaz de automóviles del concesionario.
 */

public class ControladorAutomovil implements ActionListener{
    private IUAutomóvil vista;
    private AutomovilDao modelo;
    private Automovil automovil;
    
/**
 * Constructor que recibe la vista de automóviles y la hace visible.
 * @param vista Interfaz de usuario para gestionar automóviles.
 */

public  ControladorAutomovil (IUAutomóvil vista){
    this.vista = vista;
    this.modelo = new AutomovilDao();
    
    // Eventos
    this.vista.ConsultarAutomovil.addActionListener(this);
    this.vista.RegistrarAutomovil.addActionListener(this);
    this.vista.ActualizarAutomovil.addActionListener(this);
    this.vista.EliminarAutomovil.addActionListener(this);
    this.vista.ConsultarTodosAutos.addActionListener(this);
    
    vista.setVisible(true);
        
}

    /**
     * Método que captura y maneja los eventos generados por la vista.
     * @param ae     
     */

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista.ConsultarAutomovil)){
         ConsultarAutomovil(); }
        
        if(ae.getSource().equals(this.vista.RegistrarAutomovil)){
         RegistrarAutomovil (); }
        
        if (ae.getSource().equals(this.vista.ActualizarAutomovil)){
         ActualizarAutomovil(); }
    
        if (ae.getSource().equals(this.vista.EliminarAutomovil)){
         EliminarAutomovil(); }
     
        if (ae.getSource().equals(this.vista.ConsultarTodosAutos)){
         ConsultarTodosAutos(); 
    }
}
 
   // llamado de métodos CRUD
   //----- Botón consultar----
   public void ConsultarAutomovil() {
        //  Captura el ID 
        int id_automovil = Integer.valueOf(this.vista.id_automovil.getText());

        //  Llamar al DAO
        automovil = modelo.Consultar(id_automovil);

        // Si existe, llenamos la vista
        if (automovil != null) {
            // Llenar precio 
            this.vista.Btn_precio_base.setText(automovil.getPrecio_base().toString());
            
            // Mostrar mensaje 
           String mensaje = "--- DATOS DEL AUTOMÓVIL ---\n\n" +
                             "ID: " + automovil.getId_auto() + "\n" +
                             "Marca: " + automovil.getNombre_marca() + "\n" +
                             "Modelo: " + automovil.getNombre_modelo() + "\n" +
                             "Color: " + automovil.getNombre_color() + "\n" +
                             "Tipo Motor: " + automovil.getDescripcion() + "\n" + 
                             "Precio Base: $ " + automovil.getPrecio_base();

            JOptionPane.showMessageDialog(vista, mensaje);
        }
   }

     //-----------Botón registrar--------
   
     public void RegistrarAutomovil() {
        automovil = new Automovil();

        //Capturar datos de Cajas de Texto
        
        automovil.setPrecio_base(new BigDecimal(this.vista.Btn_precio_base.getText()));

        // Capturar datos de ComboBoxes 
        // Marca
        Marca marcaBox = (Marca) this.vista.MarcasDeslegable.getSelectedItem();
        automovil.setId_marca(marcaBox.getId_marca());

        // Modelo
        Modelo modeloBox = (Modelo) this.vista.ModelosDesplegable.getSelectedItem();
        automovil.setId_modelo(modeloBox.getId_modelo());

        // Color
        Color colorBox = (Color) this.vista.ColoresDeslegable.getSelectedItem();
        automovil.setId_color(colorBox.getId_color());

        // Tipo Motor
        TipoMotor motorBox = (TipoMotor) this.vista.TpoMotorDesplegable.getSelectedItem();
        automovil.setId_tipo_motor(motorBox.getId_tipo_motor());

        //  Llamar al DAO
        if (modelo.Registrar(automovil) > 0) {
            JOptionPane.showMessageDialog(vista, "Automóvil registrado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrar");
        }
    }
        
    //----- Botón Actualizar-----
     public void ActualizarAutomovil() {
        automovil = new Automovil();

        // ID Para saber cuál actualizar
        automovil.setId_auto(Integer.valueOf(this.vista.id_automovil.getText()));

        // 2. Nuevos Datos
        automovil.setPrecio_base(new BigDecimal(this.vista.Btn_precio_base.getText()));

        Marca marcaBox = (Marca) this.vista.MarcasDeslegable.getSelectedItem();
        automovil.setId_marca(marcaBox.getId_marca());

        Modelo modeloBox = (Modelo) this.vista.ModelosDesplegable.getSelectedItem();
        automovil.setId_modelo(modeloBox.getId_modelo());

        Color colorBox = (Color) this.vista.ColoresDeslegable.getSelectedItem();
        automovil.setId_color(colorBox.getId_color());

        TipoMotor motorBox = (TipoMotor) this.vista.TpoMotorDesplegable.getSelectedItem();
        automovil.setId_tipo_motor(motorBox.getId_tipo_motor());
        
//  Llamar al DAO
        if (modelo.Actualizar(automovil) > 0) {
            JOptionPane.showMessageDialog(vista, "Automóvil actualizado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar");
        }
     }
    
  //------Botón Eliminar------
     public void EliminarAutomovil() {
        automovil = new Automovil();
        
        //necesitamos el ID
        automovil.setId_auto(Integer.valueOf(this.vista.id_automovil.getText()));

        // Llamar al DAO
        if (modelo.Eliminar(automovil) > 0) {
            JOptionPane.showMessageDialog(vista, "Automóvil eliminado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar");
        }
    }
      //------ Botón consultar todos-----
     
      public void ConsultarTodosAutos() {
        // Llamamos al DAO para obtener la lista de todos los autos
        List<Automovil> listaAutos = modelo.readALL();

        // Validamos que la lista no sea nula
        if (listaAutos != null) {
            
                 
           DefaultTableModel modeloTabla = (DefaultTableModel) this.vista.jTableAutos.getModel();
           
           modeloTabla.setRowCount(0);

            // Recorrer la lista y llenar el modelo
            for (Automovil auto : listaAutos) {
                Object[] fila = new Object[6]; // Un arreglo para las 6 columnas
                
                fila[0] = auto.getId_auto();
                fila[1] = auto.getNombre_marca();
                
                // Usamos los getters de los nombres (ya que tu DAO hace JOINs)
                fila[2] = auto.getNombre_modelo();
                fila[3] = auto.getNombre_color();
                fila[4] = auto.getDescripcion();
                fila[5] = auto.getPrecio_base(); 

                // Agregamos la fila al modelo
                modeloTabla.addRow(fila);
            }

            this.vista.jTableAutos.setModel(modeloTabla);
        
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudieron cargar los datos.");
        }
    }
}
    
    
