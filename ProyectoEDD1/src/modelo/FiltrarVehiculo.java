package modelo;

import TDA.DoubleCircleLinkedList;
import java.util.List;

public class FiltrarVehiculo {

    public DoubleCircleLinkedList<Vehiculo> filtrarPorMarcaYModelo(DoubleCircleLinkedList<Vehiculo> vehiculos, String marca, String modelo) {
        DoubleCircleLinkedList<Vehiculo> resultado = new DoubleCircleLinkedList<>();
        for (Vehiculo v : vehiculos) {
            if (v.getMarca().equalsIgnoreCase(marca) && v.getModelo().equalsIgnoreCase(modelo)) {
                resultado.addLast(v);
            }
        }
        return resultado;
    }

    public DoubleCircleLinkedList<Vehiculo> filtrarPorRangoPrecio(DoubleCircleLinkedList<Vehiculo> vehiculos, double precioMin, double precioMax) {
        DoubleCircleLinkedList<Vehiculo> resultado = new DoubleCircleLinkedList<>();
        for (Vehiculo v : vehiculos) {
            if (v.getPrecio() >= precioMin && v.getPrecio() <= precioMax) {
                resultado.addLast(v);
            }
        }
        return resultado;
    }

    public DoubleCircleLinkedList<Vehiculo> filtrarPorRangoKilometraje(DoubleCircleLinkedList<Vehiculo> vehiculos, int kmMin, int kmMax) {
        DoubleCircleLinkedList<Vehiculo> resultado = new DoubleCircleLinkedList<>();
        for (Vehiculo v : vehiculos) {
            if (v.getKilometraje() >= kmMin && v.getKilometraje() <= kmMax) {
                resultado.addLast(v);
            }
        }
        return resultado;
    }
}
