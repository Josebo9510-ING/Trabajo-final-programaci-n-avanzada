/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

/**
 *
 * @author Usuario
 */
import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.Automovil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.math.BigDecimal;


public class AutomovilDao {
    private Connection coneccion;
    private Automovil automovil;
    
      // Método registrar
    public int Registrar (Automovil automovil){
      try {
          coneccion = Conexion.conectar();
            PreparedStatement ps= coneccion.prepareStatement("INSERT INTO automovil ( precio_base, id_tipo_motor, id_marca, id_modelo, id_color )VALUES (?,?,?,?,?)");
            ps.setBigDecimal(1, automovil.getPrecio_base());
            ps.setInt(2, automovil.getId_tipo_motor());
            ps.setInt(3, automovil.getId_marca());
            ps.setInt(4, automovil.getId_modelo());
            ps.setInt(5, automovil.getId_color());
            
            return ps.executeUpdate();
      } catch (SQLException e){
          JOptionPane.showMessageDialog(null, e);
          return 0;
      }
    
    }
    
     // Método consultar
    
     public Automovil Consultar (int id_auto){
      try{
          coneccion = Conexion.conectar();
          PreparedStatement ps = coneccion.prepareStatement(
                "SELECT a.id_auto,a.precio_base,a.id_tipo_motor,a.id_marca, a.id_modelo, a.id_color," +
                " tm.descripcion, m.nombre_marca, mo.nombre_modelo, c.nombre_color " +
                "FROM automovil a " +
                "INNER JOIN tipoMotor tm ON a.id_tipo_motor = tm.id_tipo_motor " +        
                "INNER JOIN marca m ON a.id_marca = m.id_marca " +
                "INNER JOIN modelo mo ON a.id_modelo = mo.id_modelo " +
                "INNER JOIN color c ON a.id_color = c.id_color " +
                
                "WHERE a.id_auto = ?");
          
           ps.setInt(1, id_auto);
           ResultSet resultado = ps.executeQuery();
           automovil= new Automovil();
           
            if (resultado.next()) {
                //  IDs
                automovil.setId_auto(resultado.getInt("id_auto"));
                automovil.setPrecio_base(resultado.getBigDecimal("precio_base"));
                automovil.setId_tipo_motor(resultado.getInt("id_tipo_motor"));
                automovil.setId_marca(resultado.getInt("id_marca"));
                automovil.setId_modelo(resultado.getInt("id_modelo"));
                automovil.setId_color(resultado.getInt("id_color"));
                
                
                
                //  Nombres (obtenidos del JOIN)
                automovil.setDescripcion(resultado.getString("descripcion"));
                automovil.setNombre_marca(resultado.getString("nombre_marca"));
                automovil.setNombre_modelo(resultado.getString("nombre_modelo"));
                automovil.setNombre_color(resultado.getString("nombre_color"));
                
                
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe un automóvil con ese código");
                return null;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return automovil;
    }
     
     // Método actualizar
     
     public int Actualizar (Automovil automovil){
      try{
           coneccion = Conexion.conectar();
          PreparedStatement ps= coneccion.prepareStatement("UPDATE automovil SET precio_base=?,id_tipo_motor=?,id_marca=?,id_modelo=?,id_color=? WHERE id_auto=? ");
          
          ps.setBigDecimal(1, automovil.getPrecio_base());
          ps.setInt(2,automovil.getId_tipo_motor());
          ps.setInt(3,automovil.getId_marca());
          ps.setInt(4,automovil.getId_modelo());
          ps.setInt(5,automovil.getId_color());
          ps.setInt(6,automovil.getId_auto());
          
           return ps.executeUpdate();
      } catch (SQLException e){
        JOptionPane.showMessageDialog(null, e);
        return 0;
      }
     }
     
      // Método eliminar
     
     public int Eliminar (Automovil automovil){
      try{
           coneccion = Conexion.conectar();
          PreparedStatement ps= coneccion.prepareStatement("DELETE from automovil WHERE id_auto=?");
          
          ps.setInt(1,automovil.getId_auto());
           return ps.executeUpdate();
          
      } catch (SQLException e){
          JOptionPane.showMessageDialog(null, e);
          return 0;
      }
     }
     
      // Método de listar
     
     public List<Automovil> readALL(){
        
        try{
            List<Automovil> lista = new ArrayList<>();
            coneccion =Conexion.conectar();
                        
            PreparedStatement ps = coneccion.prepareStatement(
                "SELECT a.id_auto,a.precio_base, a.id_tipo_motor, a.id_marca, a.id_modelo, a.id_color," +
                "tm.descripcion, m.nombre_marca, mo.nombre_modelo, c.nombre_color " +
                "FROM automovil a " +
                "INNER JOIN tipo_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +        
                "INNER JOIN marca m ON a.id_marca = m.id_marca " +
                "INNER JOIN modelo mo ON a.id_modelo = mo.id_modelo " +
                "INNER JOIN color c ON a.id_color = c.id_color " +
                
                "ORDER BY a.id_auto"
            );
           
            ResultSet resultado = ps.executeQuery();
            
                        
            while (resultado.next()) {
                Automovil automovil = new Automovil();
                
                //  IDs
                automovil.setId_auto(resultado.getInt("id_auto"));
                automovil.setPrecio_base(resultado.getBigDecimal("precio_base"));
                automovil.setId_tipo_motor(resultado.getInt("id_tipo_motor"));
                automovil.setId_marca(resultado.getInt("id_marca"));
                automovil.setId_modelo(resultado.getInt("id_modelo"));
                automovil.setId_color(resultado.getInt("id_color"));
                
                
                
                //  Nombres (obtenidos del JOIN)
                
                automovil.setDescripcion(resultado.getString("descripcion"));
                automovil.setNombre_marca(resultado.getString("nombre_marca"));
                automovil.setNombre_modelo(resultado.getString("nombre_modelo"));
                automovil.setNombre_color(resultado.getString("nombre_color"));
                
                
                
                lista.add(automovil);
            }
            
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
     }
}
