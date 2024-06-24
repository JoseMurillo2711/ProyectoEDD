/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.DoubleCircleLinkedList;
import java.awt.Color;
import java.io.Serializable;
import tipo.TipoDireccion;
import tipo.TipoTraccion;

/**
 *
 * @author Michelle
 */
public class VehiculoNuevo extends Vehiculo implements Serializable {

    private static final long serialVersionUID = 58992201266L;

    public VehiculoNuevo(String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, Foto foto) {
        super(marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, traccion, direccion, color, climatizado, numHilera, numPuerta, foto);
    }

    public VehiculoNuevo(String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<Foto> fotos) {
        super(marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, traccion, direccion, color, climatizado, numHilera, numPuerta, fotos);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
