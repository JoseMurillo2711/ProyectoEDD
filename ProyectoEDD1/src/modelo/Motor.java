/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import tipo.TipoMotor;

/**
 *
 * @author Michelle
 */
public class Motor implements Serializable{

    private static int id = 0;
    private TipoMotor tipo;
    private int cilindraje;
    
    private static final long serialVersionUID = 58992201266L;

    public Motor(TipoMotor tipo, int cilindrada) {
        Motor.id = ++id;
        this.tipo = tipo;
        this.cilindraje = cilindrada;
    }

    public int getId() {
        return id;
    }

    public TipoMotor getTipo() {
        return tipo;
    }

    public void setTipo(TipoMotor tipo) {
        this.tipo = tipo;
    }

    public int getCilindrada() {
        return cilindraje;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindraje = cilindrada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.tipo);
        hash = 47 * hash + this.cilindraje;
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
        final Motor other = (Motor) obj;
        if (this.cilindraje != other.cilindraje) {
            return false;
        }
        return Objects.equals(this.tipo, other.tipo);
    }

    @Override
    public String toString() {
        return "Motor{" +"id= "+id+ ", tipo=" + tipo + ", cilindrada=" + cilindraje + '}';
    }

}
