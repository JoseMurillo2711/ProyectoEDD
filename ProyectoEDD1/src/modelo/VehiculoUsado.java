/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.DoubleCircleLinkedList;
import java.awt.Color;
import java.io.Serializable;
import java.util.Objects;
import tipo.TipoDireccion;
import tipo.TipoTraccion;

/**
 *
 * @author Michelle
 */
public class VehiculoUsado extends Vehiculo implements Serializable {

    private Placa placa;
    
    private static final long serialVersionUID = 92201266L;

    public VehiculoUsado(String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, Foto foto, Placa placa) {
        super(marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, traccion, direccion, color, climatizado, numHilera, numPuerta, foto);
        this.placa = placa;
    }

    public VehiculoUsado(String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<Foto> fotos, Placa placa) {
        super(marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, traccion, direccion, color, climatizado, numHilera, numPuerta, fotos);
        this.placa = placa;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        VehiculoUsado that = (VehiculoUsado) o;
        return Objects.equals(placa, that.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), placa);
    }
}
