/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.MetodoPago;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class MetodoPagoDao {
    private Connection coneccion;
    private MetodoPago metodoPago;
    
    // Método registrar
    public int Registrar (MetodoPago metodoPago){
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "INSERT INTO metodoPago ( descripcion_metodo_pago) VALUES (?)"
            );
            ps.setString(1, metodoPago.getDescripcion_metodo_pago());
            
            
            return ps.executeUpdate();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
    // Método consultar
    public MetodoPago Consultar (int codigo_pago){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps;
            ps = coneccion.prepareStatement(
                    "SELECT * FROM metodoPago WHERE codigo_pago = ?"
            );
            
            ps.setInt(1, codigo_pago);
            ResultSet resultado = ps.executeQuery();
            metodoPago = new MetodoPago();
           
            if (resultado.next()){
                metodoPago.setCodigo_pago(resultado.getInt("codigo_pago"));
                metodoPago.setDescripcion_metodo_pago(resultado.getString("descripcion_metodo_pago"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe un método de pago con ese código");
                return null;
            }
            
            
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return metodoPago;
    }
     
    // Método actualizar
    public int Actualizar (MetodoPago metodoPago){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "UPDATE metodoPago SET descripcion_metodo_pago=? WHERE codigo_pago=?"
            );
          
            ps.setString(1, metodoPago.getDescripcion_metodo_pago());
            ps.setInt(2, metodoPago.getCodigo_pago());
          
            return ps.executeUpdate();
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
     
    // Método eliminar
    public int Eliminar (MetodoPago metodoPago){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "DELETE FROM metodoPago WHERE codigo_pago=?"
            );
          
            ps.setInt(1, metodoPago.getCodigo_pago());
            return ps.executeUpdate();
          
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
     
    // Método listar
    public List<MetodoPago> readALL(){
        try{
            List<MetodoPago> lista = new ArrayList<>();
            coneccion = Conexion.conectar();
                        
            PreparedStatement ps = coneccion.prepareStatement(
                "SELECT * FROM metodoPago"
            );
           
            ResultSet resultado = ps.executeQuery();
            
            while(resultado.next()) {
                MetodoPago metodoPago = new MetodoPago();
                
                metodoPago.setCodigo_pago(resultado.getInt("codigo_pago"));
                metodoPago.setDescripcion_metodo_pago(resultado.getString("descripcion_metodo_pago"));
                
                lista.add(metodoPago);
            }
            
            return lista;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }       
}
