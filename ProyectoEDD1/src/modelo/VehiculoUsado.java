/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.DoubleCircleLinkedList;
import java.awt.Color;
import java.io.Serializable;
import java.util.Objects;
import tipo.TipoCosto;
import tipo.TipoDireccion;
import tipo.TipoTraccion;

/**
 *
 * @author Michelle
 */
public class VehiculoUsado extends Vehiculo implements Serializable {

    private Placa placa;
    private Usuario dueno;

    private static final long serialVersionUID = 92201266L;

    public VehiculoUsado(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, Foto foto, Placa placa) {
        super(tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, traccion, direccion, color, climatizado, numHilera, numPuerta, foto);
        this.placa = placa;
        this.dueno = dueno;
    }

    public VehiculoUsado(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<Foto> fotos, Placa placa) {
        super(tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, traccion, direccion, color, climatizado, numHilera, numPuerta, fotos);
        this.placa = placa;
        this.dueno = dueno;
    }

    public VehiculoUsado(String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, DoubleCircleLinkedList<Foto> fotos, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, TipoCosto tipoCosto, Placa placa, Usuario dueno) {
        super(marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, fotos, traccion, direccion, color, climatizado, numHilera, numPuerta, tipoCosto);
        this.placa = placa;
        this.dueno = dueno;
    }

    public VehiculoUsado(Placa placa, Usuario dueno, String marca, String modelo, int año) {
        super(marca, modelo, año);
        this.placa = placa;
        this.dueno = dueno;
    }

    
    
    

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    public Usuario getDueno() {
        return dueno;
    }

    public void setDueno(Usuario dueno) {
        this.dueno = dueno;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.placa);
        hash = 41 * hash + Objects.hashCode(this.dueno);
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
        if (!super.equals(obj)) {
            return false;
        }

        final VehiculoUsado other = (VehiculoUsado) obj;
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        return Objects.equals(this.dueno, other.dueno);
    }

}

