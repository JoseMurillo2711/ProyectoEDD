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
public enum TipoTransmision implements Serializable{
    MANUAL("Manual"),
    AUTOMATICO("Automatico");

    private final String tipo;
    
    private static final long serialVersionUID = 87854413255L;

    TipoTransmision(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
