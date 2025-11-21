package modelo.dto;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Usuario
 */
public class Marca implements Serializable{
    private int id_marca;
    private String nombre_marca;

    private List<Automovil> ListaAutomovil =new ArrayList();

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public List<Automovil> getListaAutomovil() {
        return ListaAutomovil;
    }

    public void setListaAutomovil(List<Automovil> ListaAutomovil) {
        this.ListaAutomovil = ListaAutomovil;
    }

 
    
    
}