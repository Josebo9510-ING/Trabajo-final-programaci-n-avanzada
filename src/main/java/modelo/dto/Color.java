package modelo.dto;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Usuario
 */
public class Color implements Serializable{
    private int id_color;
    private String nombre_color;

    private List<Automovil> ListaAutomovil =new ArrayList();

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public String getNombre_color() {
        return nombre_color;
    }

    public void setNombre_color(String nombre_color) {
        this.nombre_color = nombre_color;
    }

    public List<Automovil> getListaAutomovil() {
        return ListaAutomovil;
    }

    public void setListaAutomovil(List<Automovil> ListaAutomovil) {
        this.ListaAutomovil = ListaAutomovil;
    }

   
    
    
    
    
}