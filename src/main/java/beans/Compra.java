package beans;

public class Compra extends Curso{
    private int cantidad;
    
    public double total(){
        return cantidad*super.getPrecio();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
    
