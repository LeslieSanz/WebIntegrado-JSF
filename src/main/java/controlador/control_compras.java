
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import beans.*;
import dao.Negocio;
import java.io.IOException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
@ManagedBean
@RequestScoped

public class control_compras {
    
    private List<Compra> liscompras;
    private List<Compra> lisdetalles;

    public List<Compra> getLisdetalles() {
        return lisdetalles;
    }

    public void setLisdetalles(List<Compra> lisdetalles) {
        this.lisdetalles = lisdetalles;
    }
    
    
    //Constructor
    public control_compras() {
     liscompras = new ArrayList();
    }
    
    // Método para buscar todos los cursos de una compra por código
    public void buscarDetalles(String cod) {
        System.out.println("Entrando a buscarDetalles con código: " + cod);
        try {
            //lisdetalles = new Negocio().LisDetalleCompra(cod);
        } catch (Exception e) {
            System.out.println("Error al buscar detalles código: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @PostConstruct  // Se ejecuta cuando la pagina jsf se levanta
    public void init() {
        System.out.println("Inicializando control compras");
        try {
           //Para listar todas las compras del usuario
           //liscompras = new Negocio().LisComprasPorUsu();
            
            String cod = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codCompra");
            if (cod != null) {
                buscarDetalles(cod);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza del error para más detalles
        }
    }

    //Getters y setters
    public List<Compra> getLiscompras() {
        return liscompras;
    }

    public void setLiscompras(List<Compra> liscompras) {
        this.liscompras = liscompras;
    }

    


    
}
