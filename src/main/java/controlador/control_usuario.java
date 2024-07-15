package controlador;

import beans.ProcesoAPI;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import beans.UsuarioBean;
import dao.Negocio;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class control_usuario {

    private UsuarioBean usuario;
    private Negocio negocio;

    public control_usuario() {
        usuario = new UsuarioBean();
        negocio = new Negocio();
    }

    // Método para autocompletar datos usando la API de RENIEC
    public void autocompletarDatos() {
        ProcesoAPI.autocompletarDatos(usuario.getDni(), usuario);
    }

    public void registrar() {
    String resultado = negocio.registrarUsuario(usuario);
    if (resultado != null) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


    public void login() {
        String resultado = negocio.loginUsuario(usuario);
        if (resultado != null) {
            try {
                // Redirige a index.xhtml después de un login exitoso
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Redirección fallida", "No se pudo redirigir a la página principal"));
                e.printStackTrace();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fallido", "Credenciales incorrectas"));
        }
    }
       

    // Getters y Setters
    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }
}
