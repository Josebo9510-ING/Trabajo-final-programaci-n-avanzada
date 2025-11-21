/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.Modelo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ModeloDao {
    private Connection coneccion;
    private Modelo modelo;
    
    // Método registrar
    public int Registrar (Modelo modelo){
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "INSERT INTO modelo ( nombre_modelo) VALUES (?)"
            );
            
            ps.setString(1, modelo.getNombre_modelo());
            
            return ps.executeUpdate();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
    // Método consultar
    public Modelo Consultar (int id_modelo){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "SELECT * FROM modelo WHERE id_modelo = ?"
            );
            
            ps.setInt(1, id_modelo);
            ResultSet resultado = ps.executeQuery();
            modelo = new Modelo();
           
            if (resultado.next()){
                modelo.setId_modelo(resultado.getInt("id_modelo"));
                modelo.setNombre_modelo(resultado.getString("nombre_modelo"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe un modelo con ese ID");
                return null;
            }
            
            
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return modelo;
    }
     
    // Método actualizar
    public int Actualizar (Modelo modelo){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "UPDATE modelo SET nombre_modelo=? WHERE id_modelo=?"
            );
          
            ps.setString(1, modelo.getNombre_modelo());
            ps.setInt(2, modelo.getId_modelo());
          
            return ps.executeUpdate();
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
     
    // Método eliminar
    public int Eliminar (Modelo modelo){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "DELETE FROM modelo WHERE id_modelo=?"
            );
          
            ps.setInt(1, modelo.getId_modelo());
            return ps.executeUpdate();
          
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
     
    // Método listar
    public List<Modelo> readALL(){
        try{
            List<Modelo> lista = new ArrayList<>();
            coneccion = Conexion.conectar();
                        
            PreparedStatement ps = coneccion.prepareStatement(
                "SELECT * FROM modelo"
            );
           
            ResultSet resultado = ps.executeQuery();
            
            while(resultado.next()) {
                Modelo modelo = new Modelo();
                
                modelo.setId_modelo(resultado.getInt("id_modelo"));
                modelo.setNombre_modelo(resultado.getString("nombre_modelo"));
                
                lista.add(modelo);
            }
            
            return lista;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }       
}
