
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
    
    
    //Constructor
    public control_compras() {
     liscompras = new ArrayList();
    }

    @PostConstruct  // Se ejecuta cuando la pagina jsf se levanta
    public void init() {
        System.out.println("Inicializando control compras");
        try {
           //Para listar todas las compras del usuario
           liscompras = new Negocio().LisComprasPorUsu();
            
//            String cod = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod");
//            if (cod != null) {
//                buscarCurso(cod);
//            }

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
