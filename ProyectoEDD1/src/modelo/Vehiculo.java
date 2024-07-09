/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import javafx.scene.paint.Color;
import tipo.TipoCosto;
import tipo.TipoDireccion;
import tipo.TipoTraccion;
import static util.Utilitario.generateUniqueId;

/**
 *
 * @author Michelle
 */
public abstract class Vehiculo implements Serializable, Comparable<Vehiculo> {

    private String id;
    private String marca;
    private String modelo;
    private int ano;
    private int kilometraje;
    private double precio;
    private Motor motor;
    private Transmision transmision;
    private Ubicacion ubicacion;    
    private DoubleCircleLinkedList<String> url_fotos;
    private TipoTraccion traccion;
    private TipoDireccion direccion;
    private Color color;
    private boolean climatizado;
    private int numHilera;
    private int numPuerta;
    private TipoCosto tipoCosto;
    private Usuario dueno;
    private boolean vendido;
    private boolean favorite;

    private static final long serialVersionUID = 587432992201266L;

    private Vehiculo() {
        this.id = generateUniqueId();
        this.url_fotos = new DoubleCircleLinkedList<>();
        this.vendido = false;
        this.favorite = false;
    }

    public Vehiculo(Usuario dueno, String marca, String modelo, int ano, int kilometraje, double precio, TipoCosto tipoCosto) {
        this();
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.dueno = dueno;
        this.tipoCosto = tipoCosto;
    }

    //constructor para agregar una sola foto a la lista de fotos
    public Vehiculo(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int ano, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, String foto) {
        this();
        this.dueno = dueno;
        this.tipoCosto = tipoCosto;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.motor = motor;
        this.transmision = transmision;
        this.ubicacion = ubicacion;
        this.url_fotos.addLast(foto);        
        this.traccion = traccion;
        this.direccion = direccion;
        this.color = color;
        this.climatizado = climatizado;
        this.numHilera = numHilera;
        this.numPuerta = numPuerta;        
    }

    //Recibe la lista de fotos y reemplaza la actual inicializada
    public Vehiculo(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, TipoTraccion traccion, TipoDireccion direccion, Color color, boolean climatizado, int numHilera, int numPuerta, DoubleCircleLinkedList<String> fotos) {
        this();
        this.dueno = dueno;
        this.tipoCosto = tipoCosto;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = año;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.motor = motor;
        this.transmision = transmision;
        this.ubicacion = ubicacion;
        this.traccion = traccion;
        this.direccion = direccion;
        this.color = color;
        this.climatizado = climatizado;
        this.numHilera = numHilera;
        this.numPuerta = numPuerta;
        this.url_fotos = fotos;
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
        return ano;
    }

    public void setAño(int año) {
        this.ano = año;
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

    public DoubleCircleLinkedList<String> getFotos() {
        return url_fotos;
    }

    public void setFotos(DoubleCircleLinkedList<String> fotos) {
        this.url_fotos = fotos;
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

    public DoubleCircleLinkedList<String> getUrl_fotos() {
        return url_fotos;
    }

    public void setUrl_fotos(DoubleCircleLinkedList<String> url_fotos) {
        this.url_fotos = url_fotos;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public void venderAuto(){
        this.vendido = true;
    }
    
    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    
    public String getStar(){
        String star = "☆";
        if (favorite){
            star = "★";
        }
        return star;
    }
    
    public void onchangeFavorite(){
        favorite = !favorite;
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
        return "Vehiculo{" + "marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + ", kilometraje=" + kilometraje + ", precio=" + precio + ", tipoCosto=" + tipoCosto + '}';
    }

    public void agregarFoto(String foto) {
        this.url_fotos.addLast(foto);
    }

    public void eliminarFoto(String foto) {
        int ind = this.url_fotos.indexOf(foto);
        if (ind == -1) {
            throw new NullPointerException("No se encuentra esa foto en la lista de fotos");
        }
        this.url_fotos.remove(ind);
    }
    
    @Override
    public int compareTo(Vehiculo other) {
        int precioComp = Double.compare(this.precio, other.precio);
        if (precioComp != 0) {
            return precioComp;
        } else {
            return Integer.compare(this.kilometraje, other.kilometraje);
        }
    }
    
    public static final Comparator<Vehiculo> COMPARATOR = (v1, v2) -> {
        int precioComp = Double.compare(v1.getPrecio(), v2.getPrecio());
        if (precioComp != 0) {
            return precioComp;
        } else {
            return Integer.compare(v1.getKilometraje(), v2.getKilometraje());
        }
    };
}
