/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.*;
import java.awt.Color;
import java.io.Serializable;
import java.util.Objects;
import tipo.TipoCosto;
import tipo.TipoDireccion;
import tipo.TipoTraccion;
import static util.Utilitario.generateUniqueId;

/**
 *
 * @author Michelle
 */
public abstract class Vehiculo implements Serializable {

    private String id;
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
    private TipoTraccion traccion;
    private TipoDireccion direccion;
    private Color color;
    private boolean climatizado;
    private int numHilera;
    private int numPuerta;
    private TipoCosto tipoCosto;
    private Usuario dueno;

    private static final long serialVersionUID = 587432992201266L;

    public Vehiculo() {
        this.id = generateUniqueId();
        this.fotos = new DoubleCircleLinkedList<>();
    }

    public Vehiculo(Usuario dueno, String marca, String modelo, int año) {
        this();
        this.dueno = dueno;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;        
        
    }

    //constructor para agregar una sola foto a la lista de fotos
    public Vehiculo(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, Foto foto) {
        this();
        this.dueno = dueno;
        this.tipoCosto = tipoCosto;
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
        this.traccion = traccion;
        this.direccion = direccion;
        this.color = color;
        this.climatizado = climatizado;
        this.numHilera = numHilera;
        this.numPuerta = numPuerta;
    }

    //Recibe la lista de fotos y reemplaza la actual inicializada
    public Vehiculo(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<Foto> fotos) {
        this();
        this.dueno = dueno;
        this.tipoCosto = tipoCosto;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.motor = motor;
        this.transmision = transmision;
        this.ubicacion = ubicacion;
        this.historial = historial;
        this.traccion = traccion;
        this.direccion = direccion;
        this.color = color;
        this.climatizado = climatizado;
        this.numHilera = numHilera;
        this.numPuerta = numPuerta;
        this.fotos = fotos;
    }

    public TipoCosto getTipoCosto() {
        return tipoCosto;
    }

    public void setTipoCosto(TipoCosto tipoCosto) {
        this.tipoCosto = tipoCosto;
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

    public String getId() {
        return id;
    }

    public Usuario getDueno() {
        return dueno;
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

    public DoubleCircleLinkedList<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(DoubleCircleLinkedList<Foto> fotos) {
        this.fotos = fotos;
    }

    public TipoTraccion getTraccion() {
        return traccion;
    }

    public void setTraccion(TipoTraccion traccion) {
        this.traccion = traccion;
    }

    public TipoDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(TipoDireccion direccion) {
        this.direccion = direccion;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isClimatizado() {
        return climatizado;
    }

    public void setClimatizado(boolean climatizado) {
        this.climatizado = climatizado;
    }

    public int getNumHilera() {
        return numHilera;
    }

    public void setNumHilera(int numHilera) {
        this.numHilera = numHilera;
    }

    public int getNumPuerta() {
        return numPuerta;
    }

    public void setNumPuerta(int numPuerta) {
        this.numPuerta = numPuerta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "marca=" + marca + ", modelo=" + modelo + ", a\u00f1o=" + año + ", kilometraje=" + kilometraje + ", precio=" + precio + ", motor=" + motor + ", transmision=" + transmision + ", ubicacion=" + ubicacion + ", historial=" + historial + ", fotos=" + fotos + ", traccion=" + traccion + ", direccion=" + direccion + ", color=" + color + ", climatizado=" + climatizado + ", numHilera=" + numHilera + ", numPuerta=" + numPuerta + '}';
    }

    public void agregarFoto(Foto foto) {
        this.fotos.addLast(foto);
    }

    public void eliminarFoto(Foto foto) {
        int ind = this.fotos.indexOf(foto);
        if (ind == -1) {
            throw new NullPointerException("No se encuentra esa foto en la lista de fotos");
        }
        this.fotos.remove(ind);
    }
}
