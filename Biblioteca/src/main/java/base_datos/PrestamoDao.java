/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_datos;

import clases.Conexion;
import clases.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author andre
 */
public class PrestamoDao {
    public boolean guardar_prestamo(Prestamo prestamo){
        //Crear el ingreso a base de datos de Cliente.
        Connection conexionBaseDatos = null;
        boolean guardo_datos = true;
        
        //abrir conexion
        conexionBaseDatos = Conexion.getConexion();
        PreparedStatement prdStmt = null; 
        java.sql.Date datFechaEntrega = null; //la conversion para la fecha
        
        try{
            datFechaEntrega = new java.sql.Date(prestamo.getFecha_entrega().getTime());
        }
        catch(Exception e){
            System.out.println("Error al convertir fecha " + e);            
        }
        
        try{
            String strSentencia =  "insert into biblioteca.Prestamo" +
                                   "(fecha_entrega, id_socio, cantidad_libros)" +
                                   "values(?,?,?)";
            
            prdStmt = conexionBaseDatos.prepareStatement(strSentencia);
            
            prdStmt.setDate(1, datFechaEntrega);
            prdStmt.setInt(2, prestamo.getId_socio());
            prdStmt.setInt(3, prestamo.getCantidad_libros());
            
            
            guardo_datos = prdStmt.execute();
        }
        catch(Exception e){
            System.out.println("Se presento error al guardar prestamo " + e);            
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
    
    public ArrayList<Prestamo> consultar_prestamo(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Prestamo> arreglo_prestamos = new ArrayList<Prestamo>();
        
        try{
            String strSentencia ="      SELECT id_prestamo, fecha_entrega, id_socio, cantidad_libros"
                                + "     FROM biblioteca.Prestamo         ";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int id_prestamoTemp = resultado.getInt(1);
                Date Fecha_entregaTemp = resultado.getDate(2);
                int id_socioTemp = resultado.getInt(3);
                int Cant_librosTemp = resultado.getInt(4);
                
                arreglo_prestamos.add(new Prestamo(id_prestamoTemp, Fecha_entregaTemp, id_socioTemp, Cant_librosTemp));
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
        return arreglo_prestamos;
    }
    
    public ArrayList<Prestamo> consultar_PrestamoCB(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Prestamo> arreglo_prestamos = new ArrayList<Prestamo>();
        
        try{
            String strSentencia ="   SELECT id_prestamo, id_socio"
                                + "  FROM biblioteca.Prestamo"
                                + "  ORDER BY id_socio ASC";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int id_PrestamoTemp = resultado.getInt(1);
                int id_socioTemp = resultado.getInt(2);
                
                arreglo_prestamos.add(new Prestamo(id_PrestamoTemp, id_socioTemp));
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
        return arreglo_prestamos;        
    }
}
