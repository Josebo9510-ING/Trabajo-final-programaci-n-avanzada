/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.Vendedor;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendedorDao {
    
    private Connection coneccion;
    private Vendedor vendedor;
    
    // MÉTODO REGISTRAR 
 
    public int Registrar(Vendedor vendedor) {
        try {
            coneccion = Conexion.conectar();
            
            
            String sql = "INSERT INTO vendedor (nombre_vendedor, profesion) VALUES (?,?)";
            
            PreparedStatement ps = coneccion.prepareStatement(sql);
            
            // Nombre
            ps.setString(1, vendedor.getNombre_vendedor());
            //  Profesión
            ps.setString(2, vendedor.getProfesion());
            
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
    
    // MÉTODO CONSULTAR
    
    public Vendedor Consultar(int id_vendedor) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM vendedor WHERE id_vendedor =?");
            ps.setInt(1, id_vendedor);
            ResultSet resultado = ps.executeQuery();
            vendedor = new Vendedor();

            if (resultado.next()) {
                vendedor.setId_vendedor(resultado.getInt("id_vendedor"));
                vendedor.setNombre_vendedor(resultado.getString("nombre_vendedor"));
                vendedor.setProfesion(resultado.getString("profesion"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe un vendedor con ese ID");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return vendedor;
    }

    
    // MÉTODO ACTUALIZAR 
    
    public int Actualizar(Vendedor vendedor) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("UPDATE vendedor SET nombre_vendedor=?, profesion=? WHERE id_vendedor=? ");

            ps.setString(1, vendedor.getNombre_vendedor());
            ps.setString(2, vendedor.getProfesion());
            ps.setInt(3, vendedor.getId_vendedor());

            return ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
    // MÉTODO ELIMINAR 
    
    public int Eliminar(Vendedor vendedor) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("DELETE from vendedor WHERE id_vendedor=?");

            ps.setInt(1, vendedor.getId_vendedor());
            return ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
    // MÉTODO LISTAR
    
    public List<Vendedor> readALL() {
        try {
            List<Vendedor> lista = new ArrayList<>();
            coneccion = Conexion.conectar();

            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM vendedor");
            ResultSet resultado = ps.executeQuery();

            
            while (resultado.next()) {
                Vendedor vendedor = new Vendedor(); // <--- Aquí adentro
                
                vendedor.setId_vendedor(resultado.getInt("id_vendedor"));
                vendedor.setNombre_vendedor(resultado.getString("nombre_vendedor"));
                vendedor.setProfesion(resultado.getString("profesion"));

                lista.add(vendedor);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}