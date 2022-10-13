/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_datos;

import clases.Conexion;
import clases.Socio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class SocioDao {
//crear cliente en la base de datos.
    public boolean guardar_Socio(Socio socio){
        //Crear el ingreso a base de datos de Cliente.
        Connection conexionBaseDatos = null;
        boolean guardo_datos = true;
        
        //abrir conexion
        conexionBaseDatos = Conexion.getConexion();
        PreparedStatement prdStmt = null;
        
        try{
            String strSentencia =  "insert into biblioteca.Socio" +
                                   "(id_socio, nombre, telefono, direccion, email)" +
                                   "values(?, ?, ?, ?, ?)";
            
            prdStmt = conexionBaseDatos.prepareStatement(strSentencia);
            prdStmt.setInt(1, socio.getId_socio());
            prdStmt.setString(2, socio.getNombre());
            prdStmt.setString(3, socio.getTelefono());
            prdStmt.setString(4, socio.getDireccion());
            prdStmt.setString(5, socio.getEmail());
            
            guardo_datos = prdStmt.execute();
        }
        catch(Exception e){
            System.out.println("Se presento error al guardar el socio" + e);            
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
    
    public ArrayList<Socio> consultar_socio(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Socio> arreglo_socios = new ArrayList<Socio>();
        
        try{
            String strSentencia ="      SELECT id_socio, nombre, telefono, direccion, email"
                                + "     FROM biblioteca.socio         ";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int idTemp = resultado.getInt(1);
                String nombreTemp = resultado.getString(2);
                String telefonoTemp = resultado.getString(3);
                String direccionTemp = resultado.getString(4);
                String emailTemp = resultado.getString(5);
                
                arreglo_socios.add(new Socio(idTemp, nombreTemp, telefonoTemp, direccionTemp, emailTemp));
            }
            
            
        } catch(Exception e){
                System.out.println("Se presento error al consultar socio "+e);
        }finally{
                Conexion.cloConexion();
                try{
                    prdStmt.close();
                }catch(Exception e){
                    System.out.println("Se presento error al cerrar prepared stament "+e);
                }
        }
        return arreglo_socios;
    }
    
    public ArrayList<Socio> consultar_SocioCB(){
        //crear el ingreso a base de datos de Cliente
        Connection conexionBaseDatos=null;
        //abrir conexion
        conexionBaseDatos=Conexion.getConexion();
        PreparedStatement prdStmt=null;
        ArrayList<Socio> arreglo_socios = new ArrayList<Socio>();
        
        try{
            String strSentencia ="   SELECT id_socio, rtrim(nombre)"
                                + "  FROM biblioteca.Socio"
                                + "  ORDER BY id_socio ASC";
            
            prdStmt= conexionBaseDatos.prepareStatement(strSentencia);
            
            ResultSet resultado = prdStmt.executeQuery();
            while(resultado.next()){
                int id_socioTemp = resultado.getInt(1);
                String nombreTemp = resultado.getString(2);
                
                arreglo_socios.add(new Socio(id_socioTemp, nombreTemp));
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
        return arreglo_socios;        
    }   
}
