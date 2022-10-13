/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_datos;

import clases.Autor;
import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author andre
 */
public class AutorDao {
    public boolean guardar_autor(Autor autor){
        //Crear el ingreso a base de datos de Cliente.
        Connection conexionBaseDatos = null;
        boolean guardo_datos = true;
        
        //abrir conexion
        conexionBaseDatos = Conexion.getConexion();
        PreparedStatement prdStmt = null;
        java.sql.Date datFechaNacimiento = null; //la conversion para la fecha
        
        try{
            datFechaNacimiento = new java.sql.Date(autor.getFecha_nacimiento().getTime());
        }
        catch(Exception e){
            System.out.println("Error al convertir fecha " + e);            
        }
        
        
        try{
            String strSentencia =  "insert into biblioteca.Autor" +
                                   "(nombre, nacionalidad, fecha_nacimiento)" +
                                   "values(?,?,?)";
            
            prdStmt = conexionBaseDatos.prepareStatement(strSentencia);
            
            prdStmt.setString(1, autor.getNombre());
            prdStmt.setString(2, autor.getNacionalidad());
            prdStmt.setDate(3, datFechaNacimiento);
            guardo_datos = prdStmt.execute();
        }
        catch(Exception e){
            System.out.println("Se presento error al guardar autor " + e);            
        }
        finally{
            Conexion.cloConexion();
            try{
                prdStmt.close();
            }
            catch(Exception e){
                System.out.println("Se presento error al cerrar prepared statement " +  e);                
            }
        }
        
        return guardo_datos;
    }
    
    
    public ArrayList<Autor> consultar_autor(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Autor> arreglo_autores = new ArrayList<Autor>();
        
        try{
            String strSentencia ="      SELECT id_autor, nombre, nacionalidad, fecha_nacimiento "
                                            + "   FROM biblioteca.Autor         ";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int idTemp = resultado.getInt(1);
                String nombreTemp = resultado.getString(2);
                String NacionalidaTemp = resultado.getString(3);
                Date FechaTemp = resultado.getDate(4);
                arreglo_autores.add(new Autor(idTemp, nombreTemp,NacionalidaTemp,  FechaTemp));
            }
            
            
        } catch(Exception e){
                System.out.println("Se presento error al consultar autor "+e);
        }finally{
                Conexion.cloConexion();
                try{
                    prdStmt.close();
                }catch(Exception e){
                    System.out.println("Se presento error al cerrar prepared stament "+e);
                }
        }
        return arreglo_autores;
    }
    
    public ArrayList<Autor> consultar_autorCB(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Autor> arreglo_autores = new ArrayList<Autor>();
        
        try{
            String strSentencia ="   SELECT id_autor, rtrim(nombre)"
                                + "  FROM biblioteca.autor"
                                + "  ORDER BY nombre ASC";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int idTemp = resultado.getInt(1);
                String nombreTemp = resultado.getString(2);
                
                arreglo_autores.add(new Autor(idTemp, nombreTemp));
            }
        } 
        catch(Exception e){
            System.out.println("Se presento error al consultar autor "+e);
        }
        finally{
            Conexion.cloConexion();
            try{
                prdStmt.close();
            }
            catch(Exception e){
                System.out.println("Se presento error al cerrar prepared stament "+e);
            }
        }
        return arreglo_autores;        
    }    
}