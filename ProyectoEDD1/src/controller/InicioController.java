/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import TDA.ArrayList;
import TDA.DoubleCircleLinkedList;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modelo.Vehiculo;
import modelo.VehiculoNuevo;
import static util.Utilitario.createCard;
import util.VehiculoDataManager;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class InicioController implements Initializable {

    @FXML
    private Button btnAnterior;
    @FXML
    private Button btnSiguiente;
    @FXML
    private TilePane tilePane;
    
    private DoubleCircleLinkedList<ArrayList<Vehiculo>> paginas;
    
    private ListIterator<ArrayList<Vehiculo>> current;
    
    private static final int ITEMS_PER_PAGE = 10;
    @FXML
    private ChoiceBox<String> cbMarca;
    @FXML
    private ChoiceBox<String> cbModelo;
    @FXML
    private TextField tfKilometrajeMin;
    @FXML
    private TextField tfKilometrajeMax;
    @FXML
    private TextField tfPrecioMin;
    @FXML
    private TextField tfPrecioMax;
    @FXML
    private Button btnFiltrar;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrarAutos();
        btnFiltrar.setOnAction(event -> filtrarAutos());
        
        configComboBox();
        cargarMarcas();
    }
    
    private void configComboBox() {
        cbMarca.setOnAction(event -> {
            String selectedMarca = cbMarca.getSelectionModel().getSelectedItem();
            if (selectedMarca != null) {
                cbModelo.setDisable(false);
                cargarModelos(selectedMarca);
            }
        });
    }

    private void cargarMarcas() {
        Map<String, DoubleCircleLinkedList<Vehiculo>> mapMarcas = vehiculosMarcas();
        mapMarcas.keySet().forEach(marca -> cbMarca.getItems().add(marca));
    }
    
    private void cargarModelos(String marca) {
        cbModelo.getItems().clear(); 
        DoubleCircleLinkedList<Vehiculo> vehiculosDeMarca = vehiculosMarcas().get(marca);
        Set<String> modelos = new HashSet<>();
        for (Vehiculo vehiculo : vehiculosDeMarca) {
            modelos.add(vehiculo.getModelo());
        }
        modelos.forEach(modelo -> cbModelo.getItems().add(modelo));
    }

    private Map<String, DoubleCircleLinkedList<Vehiculo>> vehiculosMarcas() {
        Map<String, DoubleCircleLinkedList<Vehiculo>> valores = new HashMap<>();
        for (Vehiculo ve : VehiculoDataManager.getInstance().getVehiculos()) {
            String marca = ve.getMarca().toUpperCase();
            if (ve instanceof VehiculoNuevo) {
                valores.computeIfAbsent(marca, k -> new DoubleCircleLinkedList<>()).addLast(ve);
            }
        }
        return valores;
    }
    

    private void mostrarAutos() {        
        paginas = new DoubleCircleLinkedList<>();
        
        // Cargar los vehículos desde el modelo o la base de datos
        ArrayList<Vehiculo> vehiculos = new ArrayList<>(); // Implementa este método según tus necesidades
        vehiculos.addAll(VehiculoDataManager.getInstance().getVehiculos());
        // Dividir la lista de vehículos en páginas
        int totalPages = vehiculos.size() /  ITEMS_PER_PAGE;
        for (int i = 0; i < totalPages; i++) {
            ArrayList<Vehiculo> page = new ArrayList<>();
            for (int j = i * ITEMS_PER_PAGE; j < (i + 1) * ITEMS_PER_PAGE && j < vehiculos.size(); j++) {
                page.addLast(vehiculos.get(j));
            }
            paginas.addLast(page);
        }        

       current = paginas.listIterator();

        if (current.hasNext()) {
            mostrarPaginaProxima();
        }

        // Configurar los botones
        btnSiguiente.setOnAction(event -> mostrarPaginaProxima());
        btnAnterior.setOnAction(event -> mostrarPaginaAnterior());
        
    }
    
    private void mostrarPaginaProxima() {
        if (current.hasNext()) {
            ArrayList<Vehiculo> vehiculos = current.next();
            tilePane.getChildren().clear();
            for (Vehiculo vehiculo : vehiculos) {
                VBox card = createCard(vehiculo);
                tilePane.getChildren().add(card);
            }
        }
    }
    
    private void mostrarPaginaAnterior() {
        if (current.hasPrevious()) {
            ArrayList<Vehiculo> vehiculos = current.previous();
            tilePane.getChildren().clear();
            for (Vehiculo vehiculo : vehiculos) {
                VBox card = createCard(vehiculo);
                tilePane.getChildren().add(card);
            }
        }
    }

    private void filtrarAutos() {
        String marca = cbMarca.getValue();
        String modelo = cbModelo.getValue();
        String kilometrajeMinStr = tfKilometrajeMin.getText();
        String kilometrajeMaxStr = tfKilometrajeMax.getText();
        String precioMinStr = tfPrecioMin.getText();
        String precioMaxStr = tfPrecioMax.getText();

        int kilometrajeMin = kilometrajeMinStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(kilometrajeMinStr);
        int kilometrajeMax = kilometrajeMaxStr.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(kilometrajeMaxStr);
        double precioMin = precioMinStr.isEmpty() ? Double.MIN_VALUE : Double.parseDouble(precioMinStr);
        double precioMax = precioMaxStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(precioMaxStr);

        ArrayList<Vehiculo> vehiculos = new ArrayList<>(); // Implementa este método según tus necesidades
        vehiculos.addAll(VehiculoDataManager.getInstance().getVehiculos());

        ArrayList<Vehiculo> vehiculosFiltrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            boolean matches = (marca == null || marca.isEmpty() || vehiculo.getMarca().equals(marca)) &&
                              (modelo == null || modelo.isEmpty() || vehiculo.getModelo().equals(modelo)) &&
                              vehiculo.getKilometraje() >= kilometrajeMin &&
                              vehiculo.getKilometraje() <= kilometrajeMax &&
                              vehiculo.getPrecio() >= precioMin &&
                              vehiculo.getPrecio() <= precioMax;

            if (matches) {
                vehiculosFiltrados.add(vehiculosFiltrados.size(), vehiculo);
            }
        }

        paginas.clear();
        int totalPages = vehiculosFiltrados.size() / ITEMS_PER_PAGE;
        for (int i = 0; i < totalPages; i++) {
            ArrayList<Vehiculo> page = new ArrayList<>();
            for (int j = i * ITEMS_PER_PAGE; j < (i + 1) * ITEMS_PER_PAGE && j < vehiculosFiltrados.size(); j++) {
                page.addLast(vehiculosFiltrados.get(j));
            }
            paginas.addLast(page);
        }

        current = paginas.listIterator();

        if (current.hasNext()) {
            mostrarPaginaProxima();
        }
    }

}
