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

    private static final long serialVersionUID = 92201266L;

    public VehiculoUsado(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, Foto foto, Placa placa) {
        super(dueno, tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, traccion, direccion, color, climatizado, numHilera, numPuerta, foto);
        this.placa = placa;
    }

    public VehiculoUsado(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<Foto> fotos, Placa placa) {
        super(dueno, tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, historial, traccion, direccion, color, climatizado, numHilera, numPuerta, fotos);
        this.placa = placa;
    }

    public VehiculoUsado(Placa placa, Usuario dueno, String marca, String modelo, int año) {
        super(dueno,marca, modelo, año);
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
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

