/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.IUColor;


/**
 *
 * @author Usuario
 */

/**
 * Controlador que gestiona las acciones sobre la vista de colores
 * de los automóviles del concesionario.
 */

public class ControladorColor implements ActionListener{
    private IUColor vista;
    
public  ControladorColor (IUColor vista){
    this.vista = vista;
    vista.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
       
    }
  
 }
