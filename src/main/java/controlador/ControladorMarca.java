/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUMarca;

/**
 *
 * @author Usuario
 */

/**
 * Controlador encargado de manejar la vista relacionada con las marcas
 * de automóviles.
 */

public class ControladorMarca implements ActionListener{
    private IUMarca vista;
    

public  ControladorMarca (IUMarca vista){
    this.vista = vista;
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
       
    }
  
 }
