package dao;
//Se realiza todas las operaciones que se van a ejecutar en las
// paginas jsp
import java.sql.*;
import java.util.*;
import beans.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import util.MySQLConexion;

public class Negocio {
   
 
    public List<Categoria> ListarCategorias(){
        List<Categoria> lista=new ArrayList();
        Connection cn=MySQLConexion.getConexion();
        
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
     
    //Registrar compras
    public String registrarCompra(String codusu, List<Compra> lista) {
        
        String fac="";
        
        //Calcular el total de la compra
        double sum=0;
        for(Compra x:lista)sum=sum+x.total();
        
        //Grabar la compra
        String sql="{call generar_factura(?,?)}";
        Connection cn=MySQLConexion.getConexion();
        try{
        CallableStatement st=cn.prepareCall(sql);
        st.setString(1, codusu);
        st.setDouble(2, sum);
        ResultSet rs=st.executeQuery();
        rs.next();
        fac=rs.getString(1);
        //Grabar los detalles
        sql="{call agregar_detalle(?,?,?)}";
        CallableStatement st2=cn.prepareCall(sql);
        for(Compra x:lista){
            st2.setString(1,fac);
            st2.setString(2, x.getCodcur());
            st2.setInt(3, x.getCantidad());
            st2.executeUpdate();
        }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return fac;
    }

     public String registrarUsuario(UsuarioBean usuario) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = MySQLConexion.getConexion();
            String sql = "INSERT INTO usuario (dni, password, nombre, apellidos, correo, Rol_codRol) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellidos());
            ps.setString(5, usuario.getCorreo());
            ps.setInt(6, 2);
            ps.executeUpdate();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Usuario registrado correctamente"));
            return "login?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro fallido", "Ocurrió un error al registrar el usuario"));
            return null;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método de login
    public String loginUsuario(UsuarioBean usuario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = MySQLConexion.getConexion();
            String sql = "SELECT cod FROM usuario WHERE dni = ? AND password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getPassword());

            rs = ps.executeQuery();

            if (rs.next()) {
                // Credenciales válidas
                usuario.setCod(rs.getString(1));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login exitoso", "Credenciales válidas"));
                return "index?faces-redirect=true";
            } else {
                // Credenciales inválidas
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fallido", "DNI o contraseña incorrectos"));
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fallido", "Ocurrió un error al intentar iniciar sesión"));
            System.out.println("Error de SQL: " + e.getMessage());
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
     // Lista de Compras por Usuario
    public List<Compra> LisComprasPorUsu() {
        String codusu = "A0001";

        List<Compra> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        System.out.println("Iniciando la conexión a la base de datos");

        try {
            String sql = "{call ObtenerComprasPorUsuario(?)}"; // Agregar el parámetro del procedimiento
            CallableStatement st = cn.prepareCall(sql);
            System.out.println("Preparando la llamada al procedimiento almacenado");

            st.setString(1, codusu);
            System.out.println("Estableciendo el parámetro codusu: " + codusu);

            ResultSet rs = st.executeQuery();
            System.out.println("Ejecutando la consulta");

            while (rs.next()) {
                Compra c = new Compra();
                c.setCodCompra(rs.getString(1));
                c.setFecha(rs.getString(2));
                c.setCantCursos(rs.getInt(3)); // Agregar la cantidad de cursos
                c.setMonto(rs.getDouble(4)); // Agregar el monto total

                System.out.println("Compra obtenida: " + c.getCodCompra() + ", " + c.getFecha() + ", " + c.getCantCursos() + ", " + c.getMonto());
                lista.add(c);
            }

            System.out.println("Consulta completada. Número de compras obtenidas: " + lista.size());
        } catch (Exception ex) {
            System.out.println("Error durante la ejecución del método LisComprasPorUsu");
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null && !cn.isClosed()) {
                    cn.close();
                    System.out.println("Conexión a la base de datos cerrada");
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión a la base de datos");
                e.printStackTrace();
            }
        }

        return lista;
    }

    // Listar tooooodos los cursos de una compra
    public List<Compra> LisDetalleCompra(String codcom) {    
        List<Compra> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();   
        try {
            String sql = "{call ObtenerDetalleCompra(?)}"; 
            CallableStatement st = cn.prepareCall(sql);
            st.setString(1, codcom);
            ResultSet rs = st.executeQuery();   
            while (rs.next()) {
                Compra c = new Compra();
                c.setCodcur(rs.getString(1));
                c.setNomCat(rs.getString(2));
                c.setCantidad(rs.getInt(3)); 
                c.setPrecio(rs.getDouble(4));
                lista.add(c);
            } 
            System.out.println("Consulta completada. Número de detalles obtenidos: " + lista.size());
        } catch (Exception ex) {
            System.out.println("Error durante la ejecución del método LisComprasPorUsu");
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null && !cn.isClosed()) {
                    cn.close();
                    System.out.println("Conexión a la base de datos cerrada");
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión a la base de datos");
                e.printStackTrace();
            }
        }

        return lista;
    }
}
