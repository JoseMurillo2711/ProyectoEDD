/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.DoubleCircleLinkedList;
import javafx.scene.paint.Color;
import java.io.Serializable;
import tipo.TipoCosto;
import tipo.TipoDireccion;
import tipo.TipoTraccion;

/**
 *
 * @author Michelle
 */
public class VehiculoUsado extends Vehiculo implements Serializable {

    private Placa placa;    
    private Historial historial;

    private static final long serialVersionUID = 92201266L;

    
    public VehiculoUsado(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, String foto, Placa placa) {
        super(dueno, tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, traccion, direccion, color, climatizado, numHilera, numPuerta, foto);
        this.placa = placa;
        this.historial = historial;
    }

    public VehiculoUsado(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<String> fotos, Placa placa) {
        super(dueno, tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, traccion, direccion, color, climatizado, numHilera, numPuerta, fotos);
        this.placa = placa;
        this.historial = historial;
    }

    public VehiculoUsado(Usuario dueno, String marca, String modelo, int ano, int kilometraje, double precio, TipoCosto tipoCosto) {
        super(dueno, marca, modelo, ano, kilometraje, precio, tipoCosto);
    }    

    public VehiculoUsado(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int ano, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, String foto) {
        super(dueno, tipoCosto, marca, modelo, ano, kilometraje, precio, motor, transmision, ubicacion, traccion, direccion, color, climatizado, numHilera, numPuerta, foto);
    }

    public VehiculoUsado(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<String> fotos) {
        super(dueno, tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, traccion, direccion, color, climatizado, numHilera, numPuerta, fotos);
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
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

