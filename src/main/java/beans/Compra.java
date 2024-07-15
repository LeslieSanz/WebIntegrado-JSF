package beans;

public class Compra extends Curso{
    private int cantidad;
    
    //Para obtener lista de compras del usuario
    private String codCompra;
    private String fecha;
    private double monto;
    private int cantCursos;
    
    public double total(){
        return cantidad*super.getPrecio();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCantCursos() {
        return cantCursos;
    }

    public void setCantCursos(int cantCursos) {
        this.cantCursos = cantCursos;
    }

    public String getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(String codCompra) {
        this.codCompra = codCompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
    
