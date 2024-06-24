/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.*;
import java.io.Serializable;
import java.util.Objects;
import tipo.TipoVehiculo;

/**
 *
 * @author Michelle
 */
public class Vehiculo implements Serializable{

    private static int id = 0;
    private String marca;
    private String modelo;
    private int año;
    private int kilometraje;
    private double precio;
    private Motor motor;
    private Transmision transmision;
    private Ubicacion ubicacion;
    private Historial historial;
    private DoubleCircleLinkedList<Foto> fotos;
    private TipoVehiculo tipo;
    private boolean favorito;

    private static final long serialVersionUID = 587432992201266L;

    public Vehiculo() {
        Vehiculo.id = ++id;
        this.fotos = new DoubleCircleLinkedList<>();
    }

    public Vehiculo(String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, DoubleCircleLinkedList<Foto> fotos, TipoVehiculo tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.motor = motor;
        this.transmision = transmision;
        this.ubicacion = ubicacion;
        this.historial = historial;
        this.fotos = fotos;
        this.tipo = tipo;
        this.favorito = false;
    }

    public Vehiculo(String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, Foto foto, TipoVehiculo tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.motor = motor;
        this.transmision = transmision;
        this.ubicacion = ubicacion;
        this.historial = historial;
        this.fotos.addLast(foto);
        this.tipo = tipo;
        this.favorito = false;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Transmision getTransmision() {
        return transmision;
    }

    public void setTransmision(Transmision transmision) {
        this.transmision = transmision;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(DoubleCircleLinkedList<Foto> fotos) {
        this.fotos = fotos;
    }

    public void marcarComoFavorito() {
        this.favorito = true;
    }

    public void desmarcarComoFavorito() {
        this.favorito = false;
    }

    public static int getId() {
        return id;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.marca);
        hash = 59 * hash + Objects.hashCode(this.modelo);
        hash = 59 * hash + this.año;
        hash = 59 * hash + this.kilometraje;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.motor);
        hash = 59 * hash + Objects.hashCode(this.transmision);
        hash = 59 * hash + Objects.hashCode(this.ubicacion);
        hash = 59 * hash + Objects.hashCode(this.historial);
        hash = 59 * hash + Objects.hashCode(this.fotos);
        hash = 59 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        if (this.año != other.año) {
            return false;
        }
        if (this.kilometraje != other.kilometraje) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.motor, other.motor)) {
            return false;
        }
        if (!Objects.equals(this.transmision, other.transmision)) {
            return false;
        }
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        if (!Objects.equals(this.historial, other.historial)) {
            return false;
        }
        if (!Objects.equals(this.fotos, other.fotos)) {
            return false;
        }
        return this.tipo == other.tipo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "marca=" + marca + ", modelo=" + modelo + ", a\u00f1o=" + año + ", kilometraje=" + kilometraje + ", precio=" + precio + ", motor=" + motor + ", transmision=" + transmision + ", ubicacion=" + ubicacion + ", historial=" + historial + ", fotos=" + fotos + ", tipo=" + tipo + ", favorito=" + favorito + '}';
    }

}
