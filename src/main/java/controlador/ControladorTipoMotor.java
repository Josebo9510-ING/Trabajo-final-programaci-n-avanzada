/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUTipoMotor;


/**
 *
 * @author Usuario
 */

/**
 * Controlador para la gestión de los tipos de motor disponibles en el concesionario.
 */

public class ControladorTipoMotor implements ActionListener{
    private IUTipoMotor vista;
    

public  ControladorTipoMotor (IUTipoMotor vista){
    this.vista = vista;
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
       
    }
  
 }