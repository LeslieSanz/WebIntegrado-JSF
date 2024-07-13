
//package controlador;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import beans.*;
//import dao.Negocio;
//import java.util.*;
//import javax.annotation.PostConstruct;
//import javax.faces.model.SelectItem;
//@ManagedBean
//@RequestScoped
//public class control_alu {
//   private List<Alumno> lisalu;
//   private List<Pagos> lispago;
//   
//   private double total;
//   private String nombre;
//   
//   private String texto;
//   
//   private List<SelectItem> lisesp; //Para el comboBox de Especialidad
//
//   private Especialidad ep;
//   
//   private List<SelectItem> liscur; //Para el comboBox de Curso
//   private Notas n;
//   
//   private Alumno alu;
//      
//   @PostConstruct  // se ejecuta cuando la pagina jsf se levanta
//public void init() {
//    try {
//        lisalu = new Negocio().LisAlu();
//
//        // Para el comboBox de especialidades
//        lisesp = new ArrayList<>();
//        for (Especialidad x : new Negocio().LisESp()) {
//            SelectItem sel = new SelectItem(x.getCode(), x.getNomes());
//            lisesp.add(sel);
//        }
//
//        // Para el comboBox de cursos
//        liscur = new ArrayList<>();
//        for (Notas x : new Negocio().LisCur()) {
//            SelectItem cur = new SelectItem(x.getCodc(), x.getNomc());
//            liscur.add(cur);
//        }
//    } catch (Exception e) {
//        e.printStackTrace(); // Imprime la traza del error para más detalles
//    }
//}
//
//   
//   public void filtraEsp(){
//       lisalu = new Negocio().LisAluEsp(ep.getCode());
//   }
//   
//   public void filtraCur(){
//       lisalu = new Negocio().LisAluCur(n.getCodc());
//   }
//   
//   //Para contar la cantidad de aprobados
//   public int cuentaAprobados(){
//       //lisalu=new Negocio().LisAluCur(n.getCodc());
//       int cont=0;
//       for(Alumno x:lisalu){
//           if(x.Observ().equals("Aprobado")){
//               cont++;
//           }
//       }
//       return cont;
//   }
//   
//   //Para contar la cantidad de aprobados
//   public int cuentaDesAprobados(){
//       //lisalu=new Negocio().LisAluCur(n.getCodc());
//       int cont=0;
//       for(Alumno x:lisalu){
//           if(x.Observ().equals("Desaprobado")){
//               cont++;
//           }
//       }
//       return cont;
//   }
//   
//   public void verpago(Alumno a){
//       nombre=a.getApe()+","+a.getNoma();
//       lispago=new Negocio().LisPago(a.getCoda());
//       total=0;
//       for(Pagos x:lispago) total +=x.getMonto();
//   }
//   
//    public control_alu() {
//     lisalu=new ArrayList();
//     texto="";
//     ep = new Especialidad();
//     n = new Notas();
//     alu = new Alumno();
//    }
//    
//    //Grabar alumno
//    public void graba(){
//        alu.setCodes(ep.getCode());
//        new Negocio().graba(alu);
//        alu=new Alumno();
//    }
//    
//    public void filtra(){
//        lisalu = new Negocio().LisAlu(texto);
//    }
//
//    public String getTexto() {
//        return texto;
//    }
//
//    public void setTexto(String texto) {
//        this.texto = texto;
//    }
//
//    public List<Alumno> getLisalu() {
//        return lisalu;
//    }
//
//    public void setLisalu(List<Alumno> lisalu) {
//        this.lisalu = lisalu;
//    }
//
//    public List<Pagos> getLispago() {
//        return lispago;
//    }
//
//    public void setLispago(List<Pagos> lispago) {
//        this.lispago = lispago;
//    }
//
//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public List<SelectItem> getLisesp() {
//        return lisesp;
//    }
//
//    public void setLisesp(List<SelectItem> lisesp) {
//        this.lisesp = lisesp;
//    }
//
//    public Especialidad getEp() {
//        return ep;
//    }
//
//    public void setEp(Especialidad ep) {
//        this.ep = ep;
//    }
//    
//    public List<SelectItem> getLiscur() {
//        return liscur;
//    }
//
//    public void setLiscur(List<SelectItem> liscur) {
//        this.liscur = liscur;
//    }
//
//    public Notas getN() {
//        return n;
//    }
//
//    public void setN(Notas n) {
//        this.n = n;
//    }
//
//    public Alumno getAlu() {
//        return alu;
//    }
//
//    public void setAlu(Alumno alu) {
//        this.alu = alu;
//    }
//    
//}
