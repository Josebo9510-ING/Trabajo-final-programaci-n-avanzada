/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUCliente;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDao;
import modelo.dto.Cliente;


/**
 *
 * @author Usuario
 */

 
/**
 * Controlador que maneja las acciones relacionadas con los clientes
 * del concesionario.
 */

public class ControladorCliente implements ActionListener{
    private IUCliente vista;
    private ClienteDao modelo;
    private Cliente cliente;
    
    
    /**
     * Constructor que inicializa la vista de cliente y la muestra.
     * @param vista Interfaz de usuario para la gestión de clientes.
     */
    
public  ControladorCliente (IUCliente vista){
    this.vista = vista;
    this.modelo = new ClienteDao();
    
    // Escuchamos los botones
        this.vista.ConsultarCliente.addActionListener(this);
        this.vista.RegistrarCliente.addActionListener(this);
        this.vista.ActualizarCliente.addActionListener(this);
        this.vista.EliminarCliente.addActionListener(this);
        this.vista.ConsultarTodosClientes.addActionListener(this); 
    
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
       
    if (ae.getSource().equals(this.vista.ConsultarCliente)) {
            ConsultarCliente();
        }
        if (ae.getSource().equals(this.vista.RegistrarCliente)) {
            RegistrarCliente();
        }
        if (ae.getSource().equals(this.vista.ActualizarCliente)) {
            ActualizarCliente();
        }
        if (ae.getSource().equals(this.vista.EliminarCliente)) {
            EliminarCliente();
        }
        if (ae.getSource().equals(this.vista.ConsultarTodosClientes)) {
            ConsultarTodosClientes();
        }
    }
// MÉTODOS CRUD
    

    public void ConsultarCliente() {
        // ID
        int id_cliente = Integer.valueOf(this.vista.id_cliente.getText());

        // Llamar al DAO
        cliente = modelo.Consultar(id_cliente);

        //  Mostrar resultado
        if (cliente != null) {
            String mensaje = "--- DATOS DEL CLIENTE ---\n\n" +
                             "ID: " + cliente.getId_cliente() + "\n" +
                             "Nombre: " + cliente.getNombre_cliente() + "\n" +
                             "Edad: " + cliente.getEdad() + "\n" +
                             "Correo: " + cliente.getCorreo();
            
            JOptionPane.showMessageDialog(vista, mensaje);
        }
       
    }

    public void RegistrarCliente() {
        cliente = new Cliente();

        // Capturar datos 
        cliente.setNombre_cliente(this.vista.nombre_cliente.getText());
        cliente.setEdad(Integer.valueOf(this.vista.Edad.getText()));
        cliente.setCorreo(this.vista.CorreoCliente.getText());
        // Llamar al DAO
        if (modelo.Registrar(cliente) > 0) {
            JOptionPane.showMessageDialog(vista, "Cliente registrado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrar cliente");
        }
    }
        public void ActualizarCliente() {
        cliente = new Cliente();

        // Capturar datos 
        cliente.setId_cliente(Integer.valueOf(this.vista.id_cliente.getText()));
        cliente.setNombre_cliente(this.vista.nombre_cliente.getText());
        cliente.setEdad(Integer.valueOf(this.vista.Edad.getText()));
        cliente.setCorreo(this.vista.CorreoCliente.getText());

        //  Llamar al DAO
        if (modelo.Actualizar(cliente) > 0) {
            JOptionPane.showMessageDialog(vista, "cliente actualizado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar. Verifique el ID.");
        }
    }

    public void EliminarCliente() {
        cliente = new Cliente();
        
        // Solo necesitamos el ID
        cliente.setId_cliente(Integer.valueOf(this.vista.id_cliente.getText()));

        if (modelo.Eliminar(cliente) > 0) {
            JOptionPane.showMessageDialog(vista, "Cliente eliminado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar");
        }
    }

    public void ConsultarTodosClientes() {
        // Obtener lista
        List<Cliente> lista = modelo.readALL();

        if (lista != null) {
            // Recuperar modelo de la tabla de la vista
            DefaultTableModel modeloTabla = (DefaultTableModel) this.vista.jTableCliente.getModel();
            modeloTabla.setRowCount(0); 
            //  Llenar filas
            for (Cliente v : lista) {
                
               
                Object[] fila = new Object[4]; 
                
                fila[0] = v.getId_cliente();
                fila[1] = v.getNombre_cliente();
                fila[2] = v.getEdad();
                fila[3] = v.getCorreo();
                

                modeloTabla.addRow(fila);
            }
        }
    }
}
