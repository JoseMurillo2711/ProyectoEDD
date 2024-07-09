/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Michelle
 */
public class Placa implements Serializable {

    private int ultimoDigito;
    private String provincia;

    private static final long serialVersionUID = 5201266L;

    public int getUltimoDigito() {
        return ultimoDigito;
    }

    public void setUltimoDigito(int ultimoDigito) {
        this.ultimoDigito = ultimoDigito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Placa(int ultimoDigito, String provincia) {
        this.ultimoDigito = ultimoDigito;
        this.provincia = provincia;
    }

}
