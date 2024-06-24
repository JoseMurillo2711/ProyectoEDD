/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Michelle
 */
public class Ubicacion implements Serializable{

    private static int id = 0;
    private String Ciudad;
    private String Direccion;

    private static final long serialVersionUID = 342201266L;
    
    public Ubicacion(String Ciudad, String Direccion) {
        Ubicacion.id = ++id;
        this.Ciudad = Ciudad;
        this.Direccion = Direccion;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.Ciudad);
        hash = 73 * hash + Objects.hashCode(this.Direccion);
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
        final Ubicacion other = (Ubicacion) obj;
        if (!Objects.equals(this.Ciudad, other.Ciudad)) {
            return false;
        }
        return Objects.equals(this.Direccion, other.Direccion);
    }

    @Override
    public String toString() {
        return "Ubicacion{" + "Ciudad=" + Ciudad + ", Direccion=" + Direccion + '}';
    }

}
