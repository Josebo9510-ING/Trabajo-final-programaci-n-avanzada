/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUVendedor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.VendedorDao;
import modelo.dto.Vendedor;

/**
 *
 * @author Usuario
 */

/**
 * Controlador encargado de la gestión de vendedores en el sistema.
 */

public class ControladorVendedor implements ActionListener{
    private IUVendedor vista;
    private VendedorDao modelo;
    private Vendedor vendedor;
    

public  ControladorVendedor (IUVendedor vista){
    this.vista = vista;
    this.modelo = new VendedorDao();

        // Escuchamos los botones
        this.vista.ConsultarVendedor.addActionListener(this);
        this.vista.RegistrarVendedor.addActionListener(this);
        this.vista.ActualizarVendedor.addActionListener(this);
        this.vista.EliminarVendedor.addActionListener(this);
        this.vista.ConsultarTodosVendedores.addActionListener(this); 
    
    
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista.ConsultarVendedor)) {
            ConsultarVendedor();
        }
        if (ae.getSource().equals(this.vista.RegistrarVendedor)) {
            RegistrarVendedor();
        }
        if (ae.getSource().equals(this.vista.ActualizarVendedor)) {
            ActualizarVendedor();
        }
        if (ae.getSource().equals(this.vista.EliminarVendedor)) {
            EliminarVendedor();
        }
        if (ae.getSource().equals(this.vista.ConsultarTodosVendedores)) {
            ConsultarTodosVendedores();
        }
    }
  // MÉTODOS CRUD
    

    public void ConsultarVendedor() {
        // ID
        int id_vendedor = Integer.valueOf(this.vista.id_vendedor.getText());

        // Llamar al DAO
        vendedor = modelo.Consultar(id_vendedor);

        //  Mostrar resultado
        if (vendedor != null) {
            String mensaje = "--- DATOS DEL VENDEDOR ---\n\n" +
                             "ID: " + vendedor.getId_vendedor() + "\n" +
                             "Nombre: " + vendedor.getNombre_vendedor() + "\n" +
                             "Profesión: " + vendedor.getProfesion();
            
            JOptionPane.showMessageDialog(vista, mensaje);
        }
       
    }

    public void RegistrarVendedor() {
        vendedor = new Vendedor();

        // Capturar datos 
        vendedor.setNombre_vendedor(this.vista.nombreVendedor.getText());
        vendedor.setProfesion(this.vista.Profesion.getText());

        // Llamar al DAO
        if (modelo.Registrar(vendedor) > 0) {
            JOptionPane.showMessageDialog(vista, "Vendedor registrado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrar vendedor");
        }
    }

    public void ActualizarVendedor() {
        vendedor = new Vendedor();

        // Capturar datos 
        vendedor.setId_vendedor(Integer.valueOf(this.vista.id_vendedor.getText()));
        vendedor.setNombre_vendedor(this.vista.nombreVendedor.getText());
        vendedor.setProfesion(this.vista.Profesion.getText());

        //  Llamar al DAO
        if (modelo.Actualizar(vendedor) > 0) {
            JOptionPane.showMessageDialog(vista, "Vendedor actualizado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar. Verifique el ID.");
        }
    }

    public void EliminarVendedor() {
        vendedor = new Vendedor();
        
        // Solo necesitamos el ID
        vendedor.setId_vendedor(Integer.valueOf(this.vista.id_vendedor.getText()));

        if (modelo.Eliminar(vendedor) > 0) {
            JOptionPane.showMessageDialog(vista, "Vendedor eliminado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar");
        }
    }

    public void ConsultarTodosVendedores() {
        // Obtener lista
        List<Vendedor> lista = modelo.readALL();

        if (lista != null) {
            // Recuperar modelo de la tabla de la vista
            DefaultTableModel modeloTabla = (DefaultTableModel) this.vista.jTableVendedor.getModel();
            modeloTabla.setRowCount(0); 
            //  Llenar filas
            for (Vendedor v : lista) {
                
               
                Object[] fila = new Object[2]; 
                
                fila[0] = v.getId_vendedor();
                fila[1] = v.getNombre_vendedor();
                

                modeloTabla.addRow(fila);
            }
        }
    }
}
 
