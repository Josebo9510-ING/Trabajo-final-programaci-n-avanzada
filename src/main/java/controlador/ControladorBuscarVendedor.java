/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUBusquedaDeVendedor;

/**
 *
 * @author Usuario
 */

/**
 * Controlador que gestiona la búsqueda de vendedores dentro del sistema.
 */

public class ControladorBuscarVendedor implements ActionListener{
    private IUBusquedaDeVendedor vista;
    
    /**
     * Constructor que inicializa la vista de búsqueda de vendedores y la muestra.
     * @param vista Interfaz de usuario para buscar vendedores.
     */
    
public  ControladorBuscarVendedor (IUBusquedaDeVendedor vista){
    this.vista = vista;
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
       
    }
  
 }
