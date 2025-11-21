/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;


import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.dto.Venta;
import modelo.dto.Vendedor;
import modelo.dto.Cliente;
import modelo.dto.Automovil;
import modelo.dto.MetodoPago;
import java.sql.Connection;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VentaDao {
    private Connection coneccion;
    private Venta venta;
    
    // Método registrar
    public int Registrar(Venta venta) {
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement(
                "INSERT INTO venta ( fecha_venta, precio_base, impuesto_venta, IVA,  total_venta, id_auto, id_cliente, " +
                " id_vendedor, codigo_pago ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            // Convertimos la fecha de Java (util) a fecha de SQL
            ps.setDate(1, java.sql.Date.valueOf(venta.getFecha_venta()));            
            ps.setBigDecimal(2, venta.getPrecio_base());
            ps.setBigDecimal(3, venta.getImpuesto_venta());
            ps.setBigDecimal(4, venta.getIVA());
            ps.setBigDecimal(5, venta.getTotal_venta());
            ps.setInt(6, venta.getAutomovil().getId_auto());
            ps.setInt(7, venta.getCliente().getId_cliente());
            ps.setInt(8, venta.getVendedor().getId_vendedor());
            ps.setInt(9, venta.getMetodoPago().getCodigo_pago());
            
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
           // MÉTODO LISTAR (TODAS)
    
         public List<Venta> readALL() {
        List<Venta> lista = new ArrayList<>();
        try {
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM venta");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venta v = new Venta();

                v.setNum_factura_venta(rs.getInt("num_factura_venta"));
                
                java.sql.Date fechaSql = rs.getDate("fecha_venta");
                if (fechaSql != null) {
                    v.setFecha_venta(fechaSql.toLocalDate());
                }

                v.setPrecio_base(rs.getBigDecimal("precio_base"));
                v.setImpuesto_venta(rs.getBigDecimal("impuesto_venta"));
                v.setIVA(rs.getBigDecimal("iva"));
                v.setTotal_venta(rs.getBigDecimal("total_pagar"));

                Vendedor ven = new Vendedor();
                ven.setId_vendedor(rs.getInt("id_vendedor"));
                v.setVendedor(ven);

                Cliente cli = new Cliente();
                cli.setId_cliente(rs.getInt("id_cliente"));
                v.setCliente(cli);

                Automovil auto = new Automovil();
                auto.setId_auto(rs.getInt("id_auto"));
                v.setAutomovil(auto);

                MetodoPago mp = new MetodoPago();
                mp.setCodigo_pago(rs.getInt("id_forma_pago"));
                v.setMetodoPago(mp);

                lista.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }

    
    // LISTAR VENTAS POR VENDEDOR
    
    public List<Venta> listarVentasPorVendedor(int idVendedor) {
        List<Venta> lista = new ArrayList<>();
        try {
            coneccion = Conexion.conectar();
            String sql = "SELECT * FROM venta WHERE id_vendedor = ?";
            PreparedStatement ps = coneccion.prepareStatement(sql);
            ps.setInt(1, idVendedor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venta v = new Venta();

                v.setNum_factura_venta(rs.getInt("num_factura_venta"));
                
                java.sql.Date fechaSql = rs.getDate("fecha_venta");
                if (fechaSql != null) {
                    v.setFecha_venta(fechaSql.toLocalDate());
                }

                v.setPrecio_base(rs.getBigDecimal("precio_base"));
                v.setImpuesto_venta(rs.getBigDecimal("impuesto_venta"));
                v.setIVA(rs.getBigDecimal("iva"));
                v.setTotal_venta(rs.getBigDecimal("total_pagar"));

                Vendedor ven = new Vendedor();
                ven.setId_vendedor(rs.getInt("id_vendedor"));
                v.setVendedor(ven);

                Cliente cli = new Cliente();
                cli.setId_cliente(rs.getInt("id_cliente"));
                v.setCliente(cli);

                Automovil auto = new Automovil();
                auto.setId_auto(rs.getInt("id_auto"));
                v.setAutomovil(auto);

                MetodoPago mp = new MetodoPago();
                mp.setCodigo_pago(rs.getInt("id_forma_pago"));
                v.setMetodoPago(mp);

                lista.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }

    
    // OBTENER TOTAL VENTAS POR VENDEDOR
    
    public BigDecimal obtenerTotalVentasPorVendedor(int idVendedor) {
        BigDecimal total = BigDecimal.ZERO;
        try {
            coneccion = Conexion.conectar();
            String sql = "SELECT SUM(total_pagar) as total_acumulado FROM venta WHERE id_vendedor = ?";
            PreparedStatement ps = coneccion.prepareStatement(sql);
            ps.setInt(1, idVendedor);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BigDecimal resultado = rs.getBigDecimal("total_acumulado");
                if (resultado != null) {
                    total = resultado;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return total;
    }

    
    // LISTAR VENTAS CON CRÉDITO CONCESIONARIO (ID = 1)
    
    public List<Venta> listarVentasConCreditoConcesionario() {
        List<Venta> lista = new ArrayList<>();
        try {
            coneccion = Conexion.conectar();
            String sql = "SELECT * FROM venta WHERE id_forma_pago = 1"; 
            PreparedStatement ps = coneccion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venta v = new Venta();

                v.setNum_factura_venta(rs.getInt("num_factura_venta"));
                
                java.sql.Date fechaSql = rs.getDate("fecha_venta");
                if (fechaSql != null) {
                    v.setFecha_venta(fechaSql.toLocalDate());
                }

                v.setPrecio_base(rs.getBigDecimal("precio_base"));
                v.setImpuesto_venta(rs.getBigDecimal("impuesto_venta"));
                v.setIVA(rs.getBigDecimal("iva"));
                v.setTotal_venta(rs.getBigDecimal("total_pagar"));

                Vendedor ven = new Vendedor();
                ven.setId_vendedor(rs.getInt("id_vendedor"));
                v.setVendedor(ven);

                Cliente cli = new Cliente();
                cli.setId_cliente(rs.getInt("id_cliente"));
                v.setCliente(cli);

                Automovil auto = new Automovil();
                auto.setId_auto(rs.getInt("id_auto"));
                v.setAutomovil(auto);

                MetodoPago mp = new MetodoPago();
                mp.setCodigo_pago(rs.getInt("id_forma_pago"));
                v.setMetodoPago(mp);

                lista.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }

    
    // OBTENER VENTA MAYOR MONTO
    
    public Venta obtenerVentaMayorMonto() {
        Venta v = null;
        try {
            coneccion = Conexion.conectar();
            String sql = "SELECT * FROM venta ORDER BY total_pagar DESC LIMIT 1";
            PreparedStatement ps = coneccion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                v = new Venta();

                v.setNum_factura_venta(rs.getInt("num_factura_venta"));
                
                java.sql.Date fechaSql = rs.getDate("fecha_venta");
                if (fechaSql != null) {
                    v.setFecha_venta(fechaSql.toLocalDate());
                }

                v.setPrecio_base(rs.getBigDecimal("precio_base"));
                v.setImpuesto_venta(rs.getBigDecimal("impuesto_venta"));
                v.setIVA(rs.getBigDecimal("iva"));
                v.setTotal_venta(rs.getBigDecimal("total_pagar"));

                Vendedor ven = new Vendedor();
                ven.setId_vendedor(rs.getInt("id_vendedor"));
                v.setVendedor(ven);

                Cliente cli = new Cliente();
                cli.setId_cliente(rs.getInt("id_cliente"));
                v.setCliente(cli);

                Automovil auto = new Automovil();
                auto.setId_auto(rs.getInt("id_auto"));
                v.setAutomovil(auto);

                MetodoPago mp = new MetodoPago();
                mp.setCodigo_pago(rs.getInt("id_forma_pago"));
                v.setMetodoPago(mp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return v;
    }
}