/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Michelle
 */
public class Placa {
    private int ultimoDigito;
    private String provincia;

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
