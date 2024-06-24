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
public enum TipoMotor implements Serializable{    
    GASOLINA("Gasolina"),
    DIESEL("Diésel"),
    ELECTRICO("Eléctrico"),
    GLP("GLP (Gas licuado del petróleo)"),
    GNC("GNC (Gas natural comprimido)");

    private final String tipo;
    
    private static final long serialVersionUID = 154488255L;

    TipoMotor(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
