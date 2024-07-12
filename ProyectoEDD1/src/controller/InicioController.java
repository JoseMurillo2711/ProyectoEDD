package controller;

import tipo.*;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modelo.Vehiculo;
import modelo.VehiculoNuevo;
import static util.Utilitario.createCard;
import util.VehiculoDataManager;
import tipo.TipoCosto;

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
    private ChoiceBox<TipoMotor> cbCategoria;
    @FXML
    private ChoiceBox<TipoTransmision> cbTransmision;
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

    private ArrayList<Vehiculo> vehiculosOriginales;
    @FXML
    private TextField tfAnioMin;
    @FXML
    private TextField tfAnioMax;
    @FXML
    private ChoiceBox<String> cbTipoCosto;
    @FXML
    private Button btnReset;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculosOriginales = new ArrayList<>();
        vehiculosOriginales.addAll(VehiculoDataManager.getInstance().getVehiculos());
        mostrarAutos(vehiculosOriginales);

        btnFiltrar.setOnAction(event -> {
            try {
                filtrarAutos();
            } catch (Exception e) {
                mostrarAlerta("No se encontraron vehículos con los criterios de búsqueda.");
            }
        });

        btnReset.setOnAction(event -> resetFilters());

        configComboBox();
        cargarMarcas();
        cargarTiposDeCosto();
    }

    private void configComboBox() {
        cbMarca.setOnAction(event -> {
            String selectedMarca = cbMarca.getSelectionModel().getSelectedItem();
            if (selectedMarca != null) {
                cbModelo.setDisable(false);
                cargarModelos(selectedMarca);
            }
        });
        cbCategoria.getItems().setAll(TipoMotor.values());
        cbTransmision.getItems().setAll(TipoTransmision.values());
        
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

    private void cargarTiposDeCosto() {
        for (TipoCosto tipo : TipoCosto.values()) {
            cbTipoCosto.getItems().add(tipo.toString());
        }
    }

    private Map<String, DoubleCircleLinkedList<Vehiculo>> vehiculosMarcas() {
        Map<String, DoubleCircleLinkedList<Vehiculo>> valores = new HashMap<>();
        for (Vehiculo ve : VehiculoDataManager.getInstance().getVehiculos()) {
            String marca = ve.getMarca();
            if (ve instanceof VehiculoNuevo) {
                valores.computeIfAbsent(marca, k -> new DoubleCircleLinkedList<>()).addLast(ve);
            }
        }
        return valores;
    }

    private void mostrarAutos(ArrayList<Vehiculo> vehiculos) {
        paginas = new DoubleCircleLinkedList<>();
        int totalPages = (vehiculos.size() + ITEMS_PER_PAGE - 1) / ITEMS_PER_PAGE;
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

        btnSiguiente.setOnAction(event -> mostrarPaginaProxima());
        btnAnterior.setOnAction(event -> mostrarPaginaAnterior());
    }

    private void mostrarPaginaProxima() {
        if (current != null && current.hasNext()) {
            ArrayList<Vehiculo> vehiculos = current.next();
            tilePane.getChildren().clear();
            for (Vehiculo vehiculo : vehiculos) {
                VBox card = createCard(vehiculo);
                tilePane.getChildren().add(card);
            }
        }
    }

    private void mostrarPaginaAnterior() {
        if (current != null && current.hasPrevious()) {
            ArrayList<Vehiculo> vehiculos = current.previous();
            tilePane.getChildren().clear();
            for (Vehiculo vehiculo : vehiculos) {
                VBox card = createCard(vehiculo);
                tilePane.getChildren().add(card);
            }
        }
    }

    private void filtrarAutos() throws Exception {
        String marca = cbMarca.getValue();
        String modelo = cbModelo.getValue();
        String kilometrajeMinStr = tfKilometrajeMin.getText();
        String kilometrajeMaxStr = tfKilometrajeMax.getText();
        String precioMinStr = tfPrecioMin.getText();
        String precioMaxStr = tfPrecioMax.getText();
        String anioMinStr = tfAnioMin.getText();
        String anioMaxStr = tfAnioMax.getText();
        String tipoCosto = cbTipoCosto.getValue();

        int kilometrajeMin = kilometrajeMinStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(kilometrajeMinStr);
        int kilometrajeMax = kilometrajeMaxStr.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(kilometrajeMaxStr);
        double precioMin = precioMinStr.isEmpty() ? Double.MIN_VALUE : Double.parseDouble(precioMinStr);
        double precioMax = precioMaxStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(precioMaxStr);
        int anioMin = anioMinStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(anioMinStr);
        int anioMax = anioMaxStr.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(anioMaxStr);

        ArrayList<Vehiculo> vehiculosFiltrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculosOriginales) {
            boolean matches = (marca == null || marca.isEmpty() || vehiculo.getMarca().equals(marca)) &&
                              (modelo == null || modelo.isEmpty() || vehiculo.getModelo().equals(modelo)) &&
                              vehiculo.getKilometraje() >= kilometrajeMin &&
                              vehiculo.getKilometraje() <= kilometrajeMax &&
                              vehiculo.getPrecio() >= precioMin &&
                              vehiculo.getPrecio() <= precioMax &&
                              vehiculo.getAño() >= anioMin &&
                              vehiculo.getAño() <= anioMax &&
                              vehiculo.getTransmision().getTipo() == cbTransmision.getValue() &&
                              vehiculo.getMotor().getTipo() == cbCategoria.getValue() &&
                              (tipoCosto == null || tipoCosto.isEmpty() || vehiculo.getTipoCosto().toString().equals(tipoCosto));

            if (matches) {
                vehiculosFiltrados.addLast(vehiculo);
            }
        }

        if (vehiculosFiltrados.isEmpty()) {
            throw new Exception("No se encontraron vehículos.");
        }

        mostrarAutos(vehiculosFiltrados);
    }

    private void resetFilters() {
        cbMarca.setValue(null);
        cbModelo.setValue(null);
        cbCategoria.setValue(null);
        cbTransmision.setValue(null);
        tfKilometrajeMin.clear();
        tfKilometrajeMax.clear();
        tfPrecioMin.clear();
        tfPrecioMax.clear();
        tfAnioMin.clear();
        tfAnioMax.clear();
        cbTipoCosto.setValue(null);
        mostrarAutos(vehiculosOriginales);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
