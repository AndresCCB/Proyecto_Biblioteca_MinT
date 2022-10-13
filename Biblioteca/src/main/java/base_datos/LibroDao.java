/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_datos;

import clases.Conexion;
import clases.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class LibroDao {
    public boolean guardar_libro(Libro libro){
        //Crear el ingreso a base de datos de Cliente.
        Connection conexionBaseDatos = null;
        boolean guardo_datos = true;
        
        //abrir conexion
        conexionBaseDatos = Conexion.getConexion();
        PreparedStatement prdStmt = null;        
        
        try{
            String strSentencia =  "insert into biblioteca.Libro" +
                                   "(nombre, id_autor, editorial, año, estado)" +
                                   "values(?,?,?,?,?)";
            
            prdStmt = conexionBaseDatos.prepareStatement(strSentencia);
            
            prdStmt.setString(1, libro.getNombre());
            prdStmt.setInt(2, libro.getId_autor());
            prdStmt.setString(3, libro.getEditorial());
            prdStmt.setInt(4, libro.getAño());
            prdStmt.setString(5, libro.getEstado());
            
            guardo_datos = prdStmt.execute();
        }
        catch(Exception e){
            System.out.println("Se presento error al guardar libro " + e);            
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
    
    public ArrayList<Libro> consultar_libro(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Libro> arreglo_libros = new ArrayList<Libro>();
        
        try{
            String strSentencia ="      SELECT id_libro, nombre, id_autor, editorial, año, estado"
                                + "     FROM biblioteca.Libro         ";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int idTemp = resultado.getInt(1);
                String nombreTemp = resultado.getString(2);
                int id_autorTemp = resultado.getInt(3);
                String editorialTemp = resultado.getString(4);
                int añoTemp = resultado.getInt(5);
                String estadoTemp = resultado.getString(6);
                
                arreglo_libros.add(new Libro(idTemp, nombreTemp, id_autorTemp, editorialTemp, añoTemp, estadoTemp));
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
        return arreglo_libros;
    }
    
    public ArrayList<Libro> consultar_LibroCB(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Libro> arreglo_libros = new ArrayList<Libro>();
        
        try{
            String strSentencia ="   SELECT id_libro, rtrim(nombre)"
                                + "  FROM biblioteca.Libro"
                                + "  ORDER BY nombre ASC";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int id_LibroTemp = resultado.getInt(1);
                String nombreTemp = resultado.getString(2);
                
                arreglo_libros.add(new Libro(id_LibroTemp, nombreTemp));
            }
        } 
        catch(Exception e){
            System.out.println("Se presento error al consultar Socio "+e);
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
        return arreglo_libros;        
    }
}
