/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.TipoMotor;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TipoMotorDao {
    private Connection coneccion;
    private TipoMotor tipoMotor;
    
    // Método registrar
    public int Registrar (TipoMotor tipoMotor){
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "INSERT INTO tipoMotor (descripcion, porcentaje_impuesto) VALUES (?,?)"
            );
            
            ps.setString(1, tipoMotor.getDescripcion());
            ps.setBigDecimal(2, tipoMotor.getPorcentaje_impuesto());
            
            return ps.executeUpdate();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
    // Método consultar
    public TipoMotor Consultar (int id_tipo_motor){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "SELECT * FROM tipoMotor WHERE id_tipo_motor = ?"
            );
            
            ps.setInt(1, id_tipo_motor);
            ResultSet resultado = ps.executeQuery();
            tipoMotor = new TipoMotor();
           
            if (resultado.next()){
                tipoMotor.setId_tipo_motor(resultado.getInt("id_tipo_motor"));
                tipoMotor.setDescripcion(resultado.getString("descripcion"));
                tipoMotor.setPorcentaje_impuesto(resultado.getBigDecimal("porcentaje_impuesto"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe un tipo de motor con ese ID");
                return null;
            }
            
            
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return tipoMotor;
    }
     
    // Método actualizar
    public int Actualizar (TipoMotor tipoMotor){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "UPDATE tipoMotor SET descripcion=?, porcentaje_impuesto=? WHERE id_tipo_motor=?"
            );
          
            ps.setString(1, tipoMotor.getDescripcion());
            ps.setBigDecimal(2, tipoMotor.getPorcentaje_impuesto());
            ps.setInt(3, tipoMotor.getId_tipo_motor());
          
            return ps.executeUpdate();
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
     
    // Método eliminar
    public int Eliminar (TipoMotor tipoMotor ){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "DELETE FROM tipoMotor WHERE id_tipo_motor=?"
            );
          
            ps.setInt(1, tipoMotor.getId_tipo_motor());
            return ps.executeUpdate();
          
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
     
    // Método listar
    public List<TipoMotor> readALL(){
        try{
            List<TipoMotor> lista = new ArrayList<>();
            coneccion = Conexion.conectar();
                        
            PreparedStatement ps = coneccion.prepareStatement(
                "SELECT * FROM tipoMotor"
            );
           
            ResultSet resultado = ps.executeQuery();
            
            while(resultado.next()) {
                TipoMotor tipoMotor = new TipoMotor();
                
                tipoMotor.setId_tipo_motor(resultado.getInt("id_tipo_motor"));
                tipoMotor.setDescripcion(resultado.getString("descripcion"));
                tipoMotor.setPorcentaje_impuesto(resultado.getBigDecimal("porcentaje_impuesto"));
                
                lista.add(tipoMotor);
            }
            
            return lista;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }       
}
