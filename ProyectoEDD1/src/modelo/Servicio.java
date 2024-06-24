/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Michelle
 */
public class Servicio implements Serializable{

    private static int id = 0;
    private String tipo;
    private Date fecha;

    private static final long serialVersionUID = 432992201266L;
    
    public Servicio(String tipo, Date fecha) {
        Servicio.id = ++id;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public static int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tipo);
        hash = 97 * hash + Objects.hashCode(this.fecha);
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
        final Servicio other = (Servicio) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }    

    @Override
    public String toString() {
        return "Servicio{" +"id= "+id+ ", tipo=" + tipo + ", fecha=" + fecha + '}';
    }
    
}
