/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import tipo.TipoTransmision;

/**
 *
 * @author Michelle
 */
public class Transmision implements Serializable{
    private static int id = 0;
    private TipoTransmision tipo;
    private int velocidades;
    
    private static final long serialVersionUID = 1292201266L;
    
    public Transmision(TipoTransmision tipo, int velocidades) {
        Transmision.id = ++id;
        this.tipo = tipo;
        this.velocidades = velocidades;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Transmision.id = id;
    }

    public TipoTransmision getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransmision tipo) {
        this.tipo = tipo;
    }

    public int getVelocidades() {
        return velocidades;
    }

    public void setVelocidades(int velocidades) {
        this.velocidades = velocidades;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.tipo);
        hash = 67 * hash + this.velocidades;
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
        final Transmision other = (Transmision) obj;
        if (this.velocidades != other.velocidades) {
            return false;
        }
        return this.tipo == other.tipo;
    }

    @Override
    public String toString() {
        return "Transmision{" + "tipo=" + tipo + ", velocidades=" + velocidades + '}';
    }
}
