package beans;

public class Curso {
    private String codcur;
    private String nomCur;
    private double precio;
    private String codCat;
    private String descripCur;
    
    //Obtener detalle de compra
    private String nomCat;
    public String getDescripCur() {
        return descripCur;
    }

    public void setDescripCur(String descripCur) {
        this.descripCur = descripCur;
    }

    public Curso(String codcur, String nomCur, double precio, String codCat, String descripCur) {
        this.codcur = codcur;
        this.nomCur = nomCur;
        this.precio = precio;
        this.codCat = codCat;
        this.descripCur = descripCur;
    }


    public Curso() {
    }

    public String getCodcur() {
        return codcur;
    }

    public void setCodcur(String codcur) {
        this.codcur = codcur;
    }

    public String getNomCur() {
        return nomCur;
    }

    public void setNomCur(String nomCur) {
        this.nomCur = nomCur;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCodCat() {
        return codCat;
    }

    public void setCodCat(String codCat) {
        this.codCat = codCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }
}
