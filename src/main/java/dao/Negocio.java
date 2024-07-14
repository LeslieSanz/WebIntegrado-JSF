package dao;
//Se realiza todas las operaciones que se van a ejecutar en las
// paginas jsp
import java.sql.*;
import java.util.*;
import beans.*;
import util.MySQLConexion;

public class Negocio {
   
 
    public List<Categoria> ListarCategorias(){
        List<Categoria> lista=new ArrayList();
        Connection cn=MySQLConexion.getConexion();
        System.out.println("Holi este es listar cat");
        try{
            String sql="SELECT codCat,nomCategor FROM categoria";
            PreparedStatement st=cn.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Categoria ct=new Categoria();
                ct.setCod(rs.getString(1));
                ct.setNombre(rs.getString(2));
                lista.add(ct);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }
    
    public List<Curso> ListarCursos(){
        List<Curso> lista=new ArrayList();
        Connection cn=MySQLConexion.getConexion();
        System.out.println("Holi este es listar cursos");
        try{
            String sql="SELECT codCurso, nombreCurso, precio, Categoria_codCat, descripCur FROM curso";
            PreparedStatement st=cn.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Curso c = new Curso();
                c.setCodcur(rs.getString("codCurso"));
                c.setNomCur(rs.getString("nombreCurso"));            
                c.setPrecio(rs.getDouble("precio"));
                c.setCodCat(rs.getString("Categoria_codCat"));
                c.setDescripCur(rs.getString("descripCur"));
                lista.add(c);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }
    
    public List<Curso> ListarCursosPorCategoria(String codCat) {
    List<Curso> lista = new ArrayList<>();
    Connection cn = MySQLConexion.getConexion();
    System.out.println("Holi este es listar cursos por cat");
    try {
        String sql = "SELECT codCurso, nombreCurso, precio, Categoria_codCat,  descripCur FROM curso WHERE Categoria_codCat = ?";
        PreparedStatement st = cn.prepareStatement(sql);
        st.setString(1, codCat);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Curso c = new Curso();
            c.setCodcur(rs.getString("codCurso"));
            c.setNomCur(rs.getString("nombreCurso"));            
            c.setPrecio(rs.getDouble("precio"));
            c.setCodCat(rs.getString("Categoria_codCat"));
            c.setDescripCur(rs.getString("descripCur"));
            lista.add(c);
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    //Buscar por Codigo
     public Curso buscarCurso(String cod){
        Curso c = null;
        Connection cn=MySQLConexion.getConexion();
         System.out.println("Holi este es buscar curso");
        try{
            String sql="SELECT codCurso, nombreCurso, precio, Categoria_codCat,  descripCur FROM curso where codCurso=?";
            PreparedStatement st=cn.prepareStatement(sql);
            st.setString(1, cod);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                c=new  Curso();
                c.setCodcur(rs.getString(1));
                c.setNomCur(rs.getString(2));
                c.setPrecio(rs.getDouble(3));
                c.setCodCat(rs.getString(4));
                c.setDescripCur(rs.getString(5));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return c;
    }
     
}
