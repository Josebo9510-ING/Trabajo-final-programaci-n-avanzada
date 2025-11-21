/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUModelo;

/**
 *
 * @author Usuario
 */

/**
 * Controlador encargado de la gestión de los modelos de automóviles.
 */

public class ControladorModelo implements ActionListener{
    private IUModelo vista;
    

public  ControladorModelo (IUModelo vista){
    this.vista = vista;
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
       
    }
  
 }
