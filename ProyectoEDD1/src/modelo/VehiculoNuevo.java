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
public class VehiculoNuevo extends Vehiculo implements Serializable {

    private static final long serialVersionUID = 58992201266L;

    public VehiculoNuevo(Usuario dueno, String marca, String modelo, int ano, int kilometraje, double precio, TipoCosto tipoCosto) {
        super(dueno, marca, modelo, ano, kilometraje, precio, tipoCosto);
    }  

    public VehiculoNuevo(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, String foto) {
        super(dueno, tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, traccion, direccion, color, climatizado, numHilera, numPuerta, foto);
    }

    public VehiculoNuevo(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<String> fotos) {
        super(dueno, tipoCosto, marca, modelo, año, kilometraje, precio, motor, transmision, ubicacion, traccion, direccion, color, climatizado, numHilera, numPuerta, fotos);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    

}
