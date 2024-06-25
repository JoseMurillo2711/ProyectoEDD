/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tipo;

import java.io.Serializable;

/**
 *
 * @author Michelle
 */
public enum TipoCosto implements Serializable{
    FIJO("Fijo"),
    NEGOCIABLE("Negociable");

    private final String tipo;
    
    private static final long serialVersionUID = 1024513255L;

    TipoCosto(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}