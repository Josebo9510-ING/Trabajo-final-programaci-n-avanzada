package modelo.dao;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.Marca;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcaDao {
    
    private Connection coneccion;
    private Marca marca;
    
    // MÉTODO REGISTRAR 
 
    public int Registrar(Marca marca) {
        try {
            coneccion = Conexion.conectar();
            
            
            String sql = "INSERT INTO marca (nombre_marca ) VALUES (?)";
            
            PreparedStatement ps = coneccion.prepareStatement(sql);
            
            // Nombre
            ps.setString(1, marca.getNombre_marca());

            
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
    
    // MÉTODO CONSULTAR
    
    public Marca Consultar(int id_marca) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM marca WHERE id_marca =?");
            ps.setInt(1, id_marca);
            ResultSet resultado = ps.executeQuery();
            marca = new Marca();

            if (resultado.next()) {
                marca.setId_marca(resultado.getInt("id_marca"));
                marca.setNombre_marca(resultado.getString("nombre_marca"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe un marca con ese ID");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return marca;
    }

    
    // MÉTODO ACTUALIZAR 
    
    public int Actualizar(Marca marca) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("UPDATE marca SET nombre_marca=? WHERE id_marca=? ");

            ps.setString(1, marca.getNombre_marca());
            ps.setInt(3, marca.getId_marca());

            return ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
    // MÉTODO ELIMINAR 
    
    public int Eliminar(Marca marca) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("DELETE from marca WHERE id_marca=?");

            ps.setInt(1, marca.getId_marca());
            return ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
    // MÉTODO LISTAR
    
    public List<Marca> readALL() {
        try {
            List<Marca> lista = new ArrayList<>();
            coneccion = Conexion.conectar();

            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM marca");
            ResultSet resultado = ps.executeQuery();

            
            while (resultado.next()) {
                Marca marca = new Marca(); 
                
                marca.setId_marca(resultado.getInt("id_marca"));
                marca.setNombre_marca(resultado.getString("nombre_marca"));

                lista.add(marca);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}