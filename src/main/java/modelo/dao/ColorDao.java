package modelo.dao;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ColorDao {
    
    private Connection coneccion;
    private Color color;
    
    // MÉTODO REGISTRAR 
 
    public int Registrar(Color color) {
        try {
            coneccion = Conexion.conectar();
            
            
            String sql = "INSERT INTO color ( nombre_color ) VALUES (?)";
            
            PreparedStatement ps = coneccion.prepareStatement(sql);
            
            // Nombre
            ps.setString(1, color.getNombre_color());

            
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
    
    // MÉTODO CONSULTAR
    
    public Color Consultar(int id_color) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM color WHERE id_color =?");
            ps.setInt(1, id_color);
            ResultSet resultado = ps.executeQuery();
            color = new Color();

            if (resultado.next()) {
                color.setId_color(resultado.getInt("id_color"));
                color.setNombre_color(resultado.getString("nombre_color"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe un color con ese ID");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return color;
    }

    
    // MÉTODO ACTUALIZAR 
    
    public int Actualizar(Color color) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("UPDATE color SET nombre_color=? WHERE id_color=? ");

            ps.setString(1, color.getNombre_color());
            ps.setInt(2, color.getId_color());

            return ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
    // MÉTODO ELIMINAR 
    
    public int Eliminar(Color color) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("DELETE from color WHERE id_color=?");

            ps.setInt(1, color.getId_color());
            return ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
    // MÉTODO LISTAR
    
    public List<Color> readALL() {
        try {
            List<Color> lista = new ArrayList<>();
            coneccion = Conexion.conectar();

            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM color");
            ResultSet resultado = ps.executeQuery();

            
            while (resultado.next()) {
                Color color = new Color(); // 
                
                color.setId_color(resultado.getInt("id_color"));
                color.setNombre_color(resultado.getString("nombre_color"));

                lista.add(color);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}