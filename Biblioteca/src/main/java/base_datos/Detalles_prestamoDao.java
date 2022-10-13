/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_datos;

import clases.Conexion;
import clases.Detalles_prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author andre
 */
public class Detalles_prestamoDao {
    public boolean guardar_detallesPR(Detalles_prestamo detalles_prestamo){
        //Crear el ingreso a base de datos de Cliente.
        Connection conexionBaseDatos = null;
        boolean guardo_datos = true;
        
        //abrir conexion
        conexionBaseDatos = Conexion.getConexion();
        PreparedStatement prdStmt = null; 
        java.sql.Date datFechaDevolucion = null; //la conversion para la fecha
        
        try{
            datFechaDevolucion = new java.sql.Date(detalles_prestamo.getFecha_devolucion().getTime());
        }
        catch(Exception e){
            System.out.println("Error al convertir fecha " + e);            
        }
        
        try{
            String strSentencia =  "insert into biblioteca.detalles_prestamo" +
                                   "(id_libro, id_prestamo, fecha_devolucion)" +
                                   "values(?,?,?)";
            
            prdStmt = conexionBaseDatos.prepareStatement(strSentencia);
            
            prdStmt.setInt(1, detalles_prestamo.getId_libro());
            prdStmt.setInt(2, detalles_prestamo.getId_prestamo());
            prdStmt.setDate(3, datFechaDevolucion);            
            
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
    
    public ArrayList<Detalles_prestamo> consultar_prestamo(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Detalles_prestamo> arreglo_Detalles_prestamos = new ArrayList<Detalles_prestamo>();
        
        try{
            String strSentencia ="      SELECT id_libro, id_prestamo, fecha_devolucion"
                                + "     FROM biblioteca.detalles_prestamo         ";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int id_libroTemp = resultado.getInt(1);
                int id_prestamoTemp = resultado.getInt(2);
                Date Fecha_devolucionTemp = resultado.getDate(3);
                
                
                arreglo_Detalles_prestamos.add(new Detalles_prestamo(id_libroTemp, id_prestamoTemp, Fecha_devolucionTemp));
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
        return arreglo_Detalles_prestamos;
    }
    
}
