
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import beans.*;
import dao.Negocio;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
@ManagedBean
@RequestScoped

public class control_curso {
    
    private List<Curso> liscur;
    private List<SelectItem> liscat; //Para el comboBox de Categorias
    private Categoria c;
    
    
    //Constructor
    public control_curso() {
     c = new Categoria();
     liscur=new ArrayList();
    }
    
    //Filtrar cursos por Categorias
    public void filtraCat(){
       liscur = new Negocio().ListarCursosPorCategoria(c.getCod());
   }
    
    @PostConstruct  // Se ejecuta cuando la pagina jsf se levanta
    public void init() {
        try {
            
           //Para listar todos los cursos
           liscur = new Negocio().ListarCursos();

           //Para el comboBox de categorias
            liscat = new ArrayList<>();
            for (Categoria x : new Negocio().ListarCategorias()) {
                SelectItem cat = new SelectItem(x.getCod(), x.getNombre());
                liscat.add(cat);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza del error para más detalles
        }
    }

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
    
}
