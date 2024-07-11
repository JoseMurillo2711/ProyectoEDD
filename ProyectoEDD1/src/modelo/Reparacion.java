/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import static util.Utilitario.generateUniqueId;

/**
 *
 * @author Michelle
 */
public class Reparacion implements Serializable {

    private String id;
    private String descripcion;
    private LocalDate fecha;

    private static final long serialVersionUID = 587402201266L;

    public Reparacion(String descripcion, LocalDate fecha) {
        this.id = generateUniqueId();
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    } 

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.descripcion);
        hash = 53 * hash + Objects.hashCode(this.fecha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reparacion other = (Reparacion) obj;
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }

    @Override
    public String toString() {
        return "Reparacion{" + "id= " + id + ", descripcion=" + descripcion + ", fecha=" + fecha + '}';
    }

}
