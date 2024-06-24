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
public enum TipoTraccion implements Serializable{
    CUATRO_POR_CUATRO("4X4"),
    CUATRO_POR_DOS("4X2");
    
    private final String tipo;
    
    private static final long serialVersionUID = 1859413255L;

    TipoTraccion(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
