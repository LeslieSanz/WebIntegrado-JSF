package dao;
//se realiza todas las operaciones que se van a ejecutar en las
// paginas jsp
//import java.sql.*;
//import java.util.*;
//import beans.*;
//import util.MySQLConexion;
//public class Negocio {
// public List<Alumno> LisAlu(){
// List<Alumno> lista=new ArrayList();
// String sql="select idalumno, apealumno, nomalumno from alumno";
// try{
//  PreparedStatement st=MySQLConexion.getConexion().prepareStatement(sql);
//  ResultSet  rs=st.executeQuery();
//  while(rs.next()){
//      Alumno a=new Alumno(rs.getString(1),rs.getString(2),rs.getString(3));
//      lista.add(a);
//  }
// }catch(SQLException  ex){
//   ex.printStackTrace();
// }
// return lista;
// }  
// 
// public List<Especialidad> LisESp(){
// List<Especialidad> lista=new ArrayList();
// String sql="select idesp, nomesp from especialidad";
// try{
//  PreparedStatement st=MySQLConexion.getConexion().prepareStatement(sql);
//  ResultSet  rs=st.executeQuery();
//  while(rs.next()){
//      Especialidad a=new Especialidad(rs.getString(1),rs.getString(2));
//      lista.add(a);
//  }
// }catch(SQLException  ex){
//   ex.printStackTrace();
// }
// return lista;
// }  
// 
// //busqueda de un alumno por codigo
// public Alumno BusAlu(String cod){
// Alumno a=null;
// String sql="select idalumno, apealumno, nomalumno from alumno where idalumno=?";
// try{
//  PreparedStatement st=MySQLConexion.getConexion().prepareStatement(sql);
//  st.setString(1, cod);
//  ResultSet  rs=st.executeQuery();
//  if(rs.next()){
//       a=new Alumno(rs.getString(1),rs.getString(2),rs.getString(3));
//  }
// }catch(SQLException  ex){
//   ex.printStackTrace();
// }
// return a;
// }  
// 
// public void graba(Alumno a){
//    String sql="{call spAdiAlu(?,?,?,?)}";
// try{
//  CallableStatement st=MySQLConexion.getConexion().prepareCall(sql);
//  st.setString(1, a.getApe());
//  st.setString(2, a.getNoma());
//  st.setString(3, a.getCodes());
//  st.setString(4, a.getColegio());
//  st.executeUpdate();
// }catch(SQLException  ex){
//   ex.printStackTrace();
// } 
//     
//     
//     
// }
// /////////////////////////
// 
// //listar los pagos de un alumno 
// public List<Pagos> LisPago(String cod){
// List<Pagos> lista=new ArrayList();
// String sql="select ciclo, fecha,monto from Pagos where idalumno=?";
// try{
// PreparedStatement st=MySQLConexion.getConexion().prepareStatement(sql);
//   st.setString(1, cod);
//  ResultSet  rs=st.executeQuery();
//  while(rs.next()){
//      Pagos a=new Pagos(rs.getString(1),rs.getString(2),rs.getDouble(3));
//      lista.add(a);
//  }
// }catch(SQLException  ex){
//   ex.printStackTrace();
// }
// return lista;
// }
// 
// //Filtrar por apellido o nombre
// public List<Alumno> LisAlu(String cad){
// List<Alumno> lista=new ArrayList();
// String sql="select idalumno, apealumno, nomalumno from alumno where apealumno like ? or nomalumno like ?";
// try{
//  PreparedStatement st=MySQLConexion.getConexion().prepareStatement(sql);
//  st.setString(1, "%"+cad+"%");
//  st.setString(2, "%"+cad+"%");
//  ResultSet  rs=st.executeQuery();
//  while(rs.next()){
//      Alumno a=new Alumno(rs.getString(1),rs.getString(2),rs.getString(3));
//      lista.add(a);
//  }
// }catch(SQLException  ex){
//   ex.printStackTrace();
// }
// return lista;
// }
// 
//     //Lista de alumnos por especialidad
//     public List<Alumno> LisAluEsp(String cad){
//     List<Alumno> lista=new ArrayList();
//     String sql="select idalumno, apealumno, nomalumno from alumno where idesp=?";
//     try{
//      PreparedStatement st=MySQLConexion.getConexion().prepareStatement(sql);
//      st.setString(1, cad);
//      ResultSet  rs=st.executeQuery();
//      while(rs.next()){
//          Alumno a=new Alumno(rs.getString(1),rs.getString(2),rs.getString(3));
//          lista.add(a);
//      }
//     }catch(SQLException  ex){
//       ex.printStackTrace();
//     }
//     return lista;
//     }
//     
//     public List<Notas> LisCur(){
//     List<Notas> lista=new ArrayList();
//     String sql="select IdCurso, NomCurso from curso";
//     try{
//      PreparedStatement st=MySQLConexion.getConexion().prepareStatement(sql);
//      ResultSet  rs=st.executeQuery();
//      while(rs.next()){
//          Notas n=new Notas(rs.getString(1),rs.getString(2));
//          lista.add(n);
//      }
//     }catch(SQLException  ex){
//       ex.printStackTrace();
//     }
//     return lista;
//     }
//
//     //Lista de alumnos por curso
//     public List<Alumno> LisAluCur(String cad){
//     List<Alumno> lista=new ArrayList();
//     String sql="select a.IdAlumno, apealumno, nomalumno, n.ExaParcial, n.ExaFinal \n" +
//    "from alumno as a join notas as n on a.IdAlumno=n.IdAlumno\n" +
//    "join curso as c on n.IdCurso=c.IdCurso\n" +
//    "where c.IdCurso=?";
//     try{
//      PreparedStatement st=MySQLConexion.getConexion().prepareStatement(sql);
//      st.setString(1, cad);
//      ResultSet  rs=st.executeQuery();
//      while(rs.next()){
//          Alumno a=new Alumno(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
//          lista.add(a);
//      }
//     }catch(SQLException  ex){
//       ex.printStackTrace();
//     }
//     return lista;
//     }  
// 
// 
//}
