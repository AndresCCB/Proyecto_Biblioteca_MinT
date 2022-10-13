/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author andre
 */
public class Libro {
    private int id_libro;
    private int id_autor; //tiene que ser el de la optra clase xd, arreglarlo
    private String nombre;
    private String editorial;
    private int año;
    private String estado;

    public Libro(int id_libro, String nombre, int id_autor, String editorial, int año, String estado) {
        this.id_libro = id_libro;
        this.id_autor = id_autor;
        this.nombre = nombre;
        this.editorial = editorial;
        this.año = año;
        this.estado = estado;
    }

    public Libro(String nombre, int id_autor, String editorial, int año, String estado) {
        this.id_autor = id_autor;
        this.nombre = nombre;
        this.editorial = editorial;
        this.año = año;
        this.estado = estado;
    }

    public Libro(int id_libro, String nombre) {
        this.id_libro = id_libro;
        this.nombre = nombre;
    }

    public Libro() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return id_libro + " - " + nombre;
    }   
}
