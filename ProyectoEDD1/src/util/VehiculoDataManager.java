package util;

import TDA.DoubleCircleLinkedList;
import TDA.List;
import modelo.Vehiculo;
import java.io.*;
import javafx.application.Platform;
import modelo.VehiculoNuevo;
import modelo.VehiculoUsado;

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
        ordenarVehiculos(); 
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
        ordenarVehiculos(); 
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
        ordenarVehiculos(); 
        escribirArchivoVehiculos();
    }

    public void borrarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
        ordenarVehiculos(); 
        escribirArchivoVehiculos();
    }

    public void editarVehiculo(Vehiculo vehiculo) throws Exception {
        int ind = this.vehiculos.indexOf(vehiculo);
        this.vehiculos.set(ind, vehiculo);
        ordenarVehiculos(); 
        escribirArchivoVehiculos();
        UsuarioDataManager.getInstance().editarVehiculo(vehiculo);
    }

    private void ordenarVehiculos() {
        vehiculos.sort(Vehiculo.COMPARATOR);
    }
    
    public static List<Vehiculo> buscarVehiculos(String criterio) {
        String[] palabrasClave = criterio.toLowerCase().split("\\s+");
        List<Vehiculo> resultados = new DoubleCircleLinkedList<>();

        for (Vehiculo vehiculo : VehiculoDataManager.getInstance().getVehiculos()) {
            boolean coincide = false;
            boolean ano = false;
            for (String palabra : palabrasClave) {
                if (vehiculo.getMarca().toLowerCase().contains(palabra)
                        || vehiculo.getModelo().toLowerCase().contains(palabra)
                        || (palabra.toLowerCase().equals("usado") && vehiculo instanceof VehiculoUsado)
                        || (palabra.toLowerCase().equals("nuevo") && vehiculo instanceof VehiculoNuevo)) {
                    coincide = true;
                }
                if (Integer.toString(vehiculo.getAño()).contains(palabra)) {
                    ano = true;
                }
            }
            if (coincide || (ano && palabrasClave.length > 1)) 
                resultados.addLast(vehiculo);            
        }

        resultados.sort((Vehiculo.COMPARATOR));

        return resultados;
    }
}
