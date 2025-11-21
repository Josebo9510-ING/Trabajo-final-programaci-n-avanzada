/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionarioMain;

import controlador.ControladorPrincipal;
import vista.IUVistaPrincipal;

/**
 *
 * @author Usuario
 */

 /**
 * Clase principal del sistema de concesionario de automóviles.
 * Su función es inicializar el programa y crear el controlador principal
 * que conecta la interfaz gráfica con la lógica del sistema.
 */

public class ConcesionarioMain {
    
        
    public static void main(String args[]) {
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(new IUVistaPrincipal());
        
}
    
}
