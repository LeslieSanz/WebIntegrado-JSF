package beans;

import java.io.Serializable;

public class Categoria implements Serializable{
    private String cod;
    private String nombre;

    public Categoria() {
    }

    public Categoria(String cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
    
    
}
