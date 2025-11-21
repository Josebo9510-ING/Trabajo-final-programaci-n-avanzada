package modelo.dao;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.Cliente;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDao {
    
    private Connection coneccion;
    private Cliente cliente;
    
    // MÉTODO REGISTRAR 
 
    public int Registrar(Cliente cliente) {
        try {
            coneccion = Conexion.conectar();
            
            
            String sql = "INSERT INTO cliente ( nombre_cliente, edad, correo ) VALUES (?,?,?)";
            
            PreparedStatement ps = coneccion.prepareStatement(sql);
            
            ps.setString(1, cliente.getNombre_cliente());
            ps.setInt(2, cliente.getEdad());
            ps.setString(3, cliente.getCorreo());
            

            
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
    
    // MÉTODO CONSULTAR
    
    public Cliente Consultar(int id_cliente) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM cliente WHERE id_cliente =?");
            ps.setInt(1, id_cliente);
            ResultSet resultado = ps.executeQuery();
            cliente = new Cliente();

            if (resultado.next()) {
                cliente.setId_cliente(resultado.getInt("id_cliente"));
                cliente.setNombre_cliente(resultado.getString("nombre_cliente"));
                cliente.setEdad(resultado.getInt("edad"));
                cliente.setCorreo(resultado.getString("correo"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe un cliente con ese ID");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return cliente;
    }

    
    // MÉTODO ACTUALIZAR 
    
    public int Actualizar(Cliente cliente) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("UPDATE cliente SET nombre_cliente=?, edad=?, coreo=? WHERE id_cliente=? ");

            ps.setString(1, cliente.getNombre_cliente());
            ps.setInt(2, cliente.getEdad());
            ps.setString(3, cliente.getCorreo());
            ps.setInt(4, cliente.getId_cliente());

            return ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
    // MÉTODO ELIMINAR 
    
    public int Eliminar(Cliente cliente) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("DELETE from cliente WHERE id_cliente=?");

            ps.setInt(1, cliente.getId_cliente());
            return ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
    // MÉTODO LISTAR
    
    public List<Cliente> readALL() {
        try {
            List<Cliente> lista = new ArrayList<>();
            coneccion = Conexion.conectar();

            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM cliente");
            ResultSet resultado = ps.executeQuery();

            
            while (resultado.next()) {
                Cliente cliente = new Cliente(); // 
                
                cliente.setId_cliente(resultado.getInt("id_cliente"));
                cliente.setNombre_cliente(resultado.getString("nombre_cliente"));
                cliente.setEdad(resultado.getInt("edad"));
                cliente.setCorreo(resultado.getString("correo"));

                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}