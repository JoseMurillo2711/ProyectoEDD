/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.DoubleCircleLinkedList;
import TDA.List;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Michelle
 */
public class Historial implements Serializable{

    private static int id = 0;
    private DoubleCircleLinkedList<Servicio> servicios;
    private DoubleCircleLinkedList<Reparacion> reparaciones;
    
    private static final long serialVersionUID = 5874329201266L;

    public Historial() {
        this.servicios = new DoubleCircleLinkedList<>();
        this.reparaciones = new DoubleCircleLinkedList<>();
    }

    public Historial(DoubleCircleLinkedList<Servicio> servicios, DoubleCircleLinkedList<Reparacion> reparaciones) {
        Historial.id = ++id;
        this.servicios = servicios;
        this.reparaciones = reparaciones;
    }

    public static int getId() {
        return id;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(DoubleCircleLinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(DoubleCircleLinkedList<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }

    public void agregarServicio(Servicio servicio) {
        this.servicios.addLast(servicio);
    }

    public void agregarReparacion(Reparacion reparacion) {
        this.reparaciones.addLast(reparacion);
    }

    public void eliminarServicio(Servicio servicio){
        int ind = this.servicios.indexOf(servicio);
        if (ind == -1) throw new NullPointerException("No se encuentra ese servicio registrado en el historial");
        this.servicios.remove(ind);
    }
    
    public void eliminarReparacion(Reparacion reparacion){
        int ind = this.reparaciones.indexOf(reparacion);
        if (ind == -1) throw new NullPointerException("No se encuentra esa reparacion registrada en el historial");
        this.reparaciones.remove(ind);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.servicios);
        hash = 17 * hash + Objects.hashCode(this.reparaciones);
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
        final Historial other = (Historial) obj;
        if (!Objects.equals(this.servicios, other.servicios)) {
            return false;
        }
        return Objects.equals(this.reparaciones, other.reparaciones);
    }

    @Override
    public String toString() {
        return "Historial{" +"id= "+id+ ", servicios=" + servicios + ", reparaciones=" + reparaciones + '}';
    }
}
