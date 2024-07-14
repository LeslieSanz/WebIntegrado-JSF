
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

public class control_curso {
    
    private List<Curso> liscur;
    private List<SelectItem> liscat; //Para el comboBox de Categorias
    private Categoria c;
    private Curso cur;
    
    
    //Constructor
    public control_curso() {
     c = new Categoria();
     cur = new Curso();
     liscur=new ArrayList();
    }
    
    public void filtraCat() {
        System.out.println("Entrando a filtraCat()");
        try {
            liscur = new Negocio().ListarCursosPorCategoria(c.getCod());
        } catch (Exception e) {
            System.out.println("Error al filtrar cursos por categoría: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
     // Método para buscar un curso por código
    public void buscarCurso(String cod) {
        System.out.println("Entrando a buscarCurso() con código: " + cod);
        try {
            cur = new Negocio().buscarCurso(cod);
        } catch (Exception e) {
            System.out.println("Error al buscar curso por código: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    
    @PostConstruct  // Se ejecuta cuando la pagina jsf se levanta
    public void init() {
        try {
            //System.out.println("Jaja");
           //Para listar todos los cursos
           liscur = new Negocio().ListarCursos();

           //Para el comboBox de categorias
            liscat = new ArrayList<>();
            for (Categoria x : new Negocio().ListarCategorias()) {
                SelectItem cat = new SelectItem(x.getCod(), x.getNombre());
                liscat.add(cat);
            }
            
            String cod = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod");
            if (cod != null) {
                buscarCurso(cod);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza del error para más detalles
        }
    }

    // Getters y Setters
    public List<SelectItem> getLiscat() {
        return liscat;
    }

    public void setLiscat(List<SelectItem> liscat) {
        this.liscat = liscat;
    }

    public Categoria getC() {
        return c;
    }

    public void setC(Categoria c) {
        this.c = c;
    }

    public List<Curso> getLiscur() {
        return liscur;
    }

    public void setLiscur(List<Curso> liscur) {
        this.liscur = liscur;
    }

    public Curso getCur() {
        return cur;
    }

    public void setCur(Curso cur) {
        this.cur = cur;
    }
    
}
