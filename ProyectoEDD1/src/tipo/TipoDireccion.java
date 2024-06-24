/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package tipo;

import java.io.Serializable;

/**
 *
 * @author Michelle
 */
public enum TipoDireccion implements Serializable{
    ELECTRONICA("Electronica"),
    MECANICA("Mecanica");

    private final String tipo;
    
    private static final long serialVersionUID = 7454413255L;

    TipoDireccion(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
