/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUMétodosDePago;

/**
 *
 * @author Usuario
 */

 /**
 * Controlador que gestiona las acciones sobre la interfaz de métodos de pago.
 */

public class ControladorMetodoDePago implements ActionListener{
    private IUMétodosDePago vista;
    

public  ControladorMetodoDePago (IUMétodosDePago vista){
    this.vista = vista;
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
       
    }
  
 }