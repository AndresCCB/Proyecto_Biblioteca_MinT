/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Date;

/**
 *
 * @author andre
 */
public class Detalles_prestamo {
    private int id_prestamo;
    private int id_libro;
    private Date fecha_devolucion;

    public Detalles_prestamo(int id_prestamo, int id_libro, Date fecha_devolucion) {
        this.id_prestamo = id_prestamo;
        this.id_libro = id_libro;
        this.fecha_devolucion = fecha_devolucion;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    @Override
    public String toString() {
        return "Detalles_prestamo{" + "id_prestamo=" + id_prestamo + ", id_libro=" + id_libro + ", fecha_devolucion=" + fecha_devolucion + '}';
    }
}
