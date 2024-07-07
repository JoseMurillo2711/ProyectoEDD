package util;

import TDA.DoubleCircleLinkedList;
import TDA.List;
import modelo.Vehiculo;

import java.io.*;
import javafx.application.Platform;

import static util.CONSTANTES.MENSAJE_ERROR;
import static util.CONSTANTES.VEHICULOS_FILE;

/**
 *
 * @autor Michelle
 */
public class VehiculoDataManager {

    private static VehiculoDataManager instance;

    private List<Vehiculo> vehiculos;

    private VehiculoDataManager() {
        vehiculos = leerArchivoVehiculos();
    }

    public static VehiculoDataManager getInstance() {
        if (instance == null) {
            instance = new VehiculoDataManager();
        }
        return instance;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        escribirArchivoVehiculos();
    }

    private List<Vehiculo> leerArchivoVehiculos() {
        File archivo = new File(VEHICULOS_FILE);
        if (!archivo.exists()) {
            return new DoubleCircleLinkedList<>();
        }
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Vehiculo>) obj.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new DoubleCircleLinkedList<>();
        }
    }

    private void escribirArchivoVehiculos() {
        File archivo = new File(VEHICULOS_FILE);
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile(); 
            }
            try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(archivo))) {
                obj.writeObject(vehiculos);
            } catch (Exception e) {
                System.err.println("Ha ocurrido un error " + e.getMessage());
                Platform.runLater(() -> Alertas.alertaError(MENSAJE_ERROR, e.getMessage()));
            }
        } catch (IOException e) {
            System.err.println("Ha ocurrido un error " + e.getMessage());
            Platform.runLater(() -> Alertas.alertaError(MENSAJE_ERROR, e.getMessage()));
        }
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.addFirst(vehiculo);
        escribirArchivoVehiculos();
    }

    public void borrarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
        escribirArchivoVehiculos();
    }

    public void editarVehiculo(Vehiculo vehiculo) {
        int ind = this.vehiculos.indexOf(vehiculo);
        this.vehiculos.set(ind, vehiculo);
        escribirArchivoVehiculos();
    }
}
