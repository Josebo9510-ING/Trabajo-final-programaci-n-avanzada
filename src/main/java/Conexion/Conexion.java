/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Conexion {
    private static final String BASEDATOS = "bd_concesionario";
    private static final String URL = "jdbc:mysql://localhost:3306/bd_concesionario";
    private static final String USER = "root";
    private static final String PASSWORD = "INGinformatica2025";
    private static Connection conectar = null;
    
    public static Connection conectar (){
      try{
      Class.forName ("com.mysql.cj.jdbc.Driver");
      conectar = DriverManager.getConnection(URL,USER,PASSWORD);
          JOptionPane.showMessageDialog(null, "Base de datos conectada");
          } catch (ClassNotFoundException | SQLException e){
           JOptionPane.showMessageDialog(null, e);
          }
          return conectar;
          }
    
    public static void cerrarConexion(){
       try{
          if (conectar != null && ! conectar.isClosed() ){
              conectar.close();
              System.out.println("Conexión cerrada");
          }
       } catch (SQLException e){
           System.out.println("Error al cerrar conexión:" + e.getMessage ());    
       }
    }
    public static void main (String [] args){
     conectar ();
    }
}
    

