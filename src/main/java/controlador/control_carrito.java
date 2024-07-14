
package controlador;

import javax.faces.bean.ManagedBean;
import beans.*;
import dao.Negocio;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped //Para que se mantenga la listaCarrito durante

public class control_carrito implements Serializable{
    
    private String codigoCurso;
    private int cantidad;
    private List<Compra> listaCarrito;  

     // Constructor
    public control_carrito() {
          listaCarrito = new ArrayList<>();
    }

    public void agregarAlCarrito() {
        
        Curso c = new Negocio().buscarCurso(codigoCurso);
        
        Compra cp= new Compra();
        cp.setCantidad(cantidad);
        cp.setCodcur(codigoCurso);
        cp.setNomCur(c.getNomCur());
        cp.setPrecio(c.getPrecio());

        listaCarrito.add(cp);
        
        // Limpiar valores después de agregar al carrito 
        codigoCurso = null;
        cantidad = 1;
        
        // Imprimir información para depuración
        System.out.println("Curso agregado al carrito: " + cp.getNomCur());
        
        
       // Redirigir a la página del carrito después de agregar
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/carrito.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void init() {
        
        System.out.println("Inicializando control_carrito...");
        if (listaCarrito == null) {
            listaCarrito = new ArrayList<>();
        }
        System.out.println("Lista de cursos en el carrito:");
        for (Compra compra : listaCarrito) {
            System.out.println("- " + compra.getCodcur() + " | " + compra.getNomCur() + " | " + compra.getPrecio() + " | " + compra.getCantidad());
        }
        
    }

    //Getters y setters
    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Compra> getListaCarrito() {
        
        return listaCarrito;
    }

    public void setListaCarrito(List<Compra> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }
    
}
