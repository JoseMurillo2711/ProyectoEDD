/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Michelle
 */
public class Foto implements Serializable{

    private static int id = 0;
    private String nombre;
    private String descripcion;
    
    private static final long serialVersionUID = 5874329921266L;

    public Foto(String nombre, String descripcion) {
        Foto.id = ++id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Foto{" + "nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
