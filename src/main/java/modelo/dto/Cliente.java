package modelo.dto;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Usuario
 */
public class Cliente implements Serializable{
    private int id_cliente;
    private String nombre_cliente;
    private int edad;
    private String correo;

    private List<Venta> ListaVenta =new ArrayList();
    
    // getters and setters

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Venta> getListaVenta() {
        return ListaVenta;
    }

    public void setListaVenta(List<Venta> ListaVenta) {
        this.ListaVenta = ListaVenta;
    }

    
    
    
    
}