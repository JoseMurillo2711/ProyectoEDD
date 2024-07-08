/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import TDA.DoubleCircleLinkedList;
import TDA.List;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Vehiculo;
import modelo.VehiculoNuevo;
import tipo.TipoCosto;
import static util.CONSTANTES.PER_PAGE;
import static util.Utilitario.abrirNuevaVentana;
import static util.Utilitario.createCardMarca;
import static util.Utilitario.panelVehiculos;
import util.VehiculoDataManager;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class NuevosController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private ComboBox<String> cbMarca;
    @FXML
    private ComboBox<String> cbModelo;
    @FXML
    private FlowPane flowPane;
    @FXML
    private VBox vbMarcas;

    private Map<String, DoubleCircleLinkedList<Vehiculo>> mapMarcas;
    private DoubleCircleLinkedList<Vehiculo> vehiculos;
    private DoubleCircleLinkedList<Vehiculo> vehiculosMostrados;
    private DoubleCircleLinkedList<Vehiculo> vehiculosPorMarca;
    private ListIterator<Vehiculo> iteratorVehiculo;
    private Button btnSiguiente;
    private Button btnAnterior;
    private Button btnRegresar;
    private MainController mainController;
    private String selectedMarca;
    private HBox bottom;
    @FXML
    private ScrollPane scroll;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        configBotones();

        vehiculos = (DoubleCircleLinkedList) VehiculoDataManager.getInstance().getVehiculos();
        mapMarcas = vehiculosMarcas();
        congfigBottom();
        vehiculosMostrados = new DoubleCircleLinkedList<>();
        mostrarMarcas();
        confComboBox();
    }

    private void confComboBox() {
        cbMarca.setOnAction(event -> {
            selectedMarca = cbMarca.getSelectionModel().getSelectedItem();
            if (selectedMarca != null) {
                cbModelo.setDisable(false);
                cargarModelos(selectedMarca);
            }
        });
    }

    private void cargarModelos(String marca) {
        DoubleCircleLinkedList<Vehiculo> vehiculosDeMarca = mapMarcas.get(marca);
        Set<String> modelos = new HashSet<>();
        for (Vehiculo vehiculo : vehiculosDeMarca) {
            modelos.add(vehiculo.getModelo());
        }
        modelos.forEach(e -> cbModelo.getItems().add(e));
    }

    @FXML
    private void buscarAutoNuevo(ActionEvent event) {
        if (cbMarca.getValue() != null && cbModelo.getValue() != null) {
            mostrarInformacion(vehiculoMarcaModelo());
        }
        cbMarca.getSelectionModel().clearSelection();
        cbModelo.getSelectionModel().clearSelection();
        cbModelo.setDisable(true);
        cbMarca.setPromptText("Seleccionar marca");
        cbModelo.setPromptText("Seleccionar modelo");
    }

    private void mostrarInformacion(Vehiculo vehiculo) {
        FXMLLoader loader = abrirNuevaVentana("mostrarInfo", "Informacion del vehiculo");
    }

    private Vehiculo vehiculoMarcaModelo() {
        DoubleCircleLinkedList<Vehiculo> vehiculosDeMarca = mapMarcas.get(selectedMarca);
        Vehiculo vehiculo = null;
        for (Vehiculo v : vehiculosDeMarca) {
            if (v.getMarca().toUpperCase().equals(cbMarca.getValue().toUpperCase())
                    && v.getModelo().toUpperCase().equals(cbModelo.getValue().toUpperCase())) {
                return v;
            }
        }
        return vehiculo;
    }

    private void mostrarMarcas() {
        Label lblTitulo = new Label("Marcas de Autos");
        lblTitulo.setId("lblAutos");
        vbMarcas.getChildren().add(0, lblTitulo);
        mapMarcas.forEach((c, v) -> {
            VBox carta = createCardMarca(v.get(0));
            flowPane.getChildren().add(carta);
            carta.setOnMouseClicked((MouseEvent event) -> {
                lblTitulo.setText("MODELOS "+v.get(0).getMarca());
                this.borderPane.setTop(null);
                this.borderPane.setBottom(bottom);
                mainController.showSearchBar();
                selectedMarca = v.get(0).getMarca();
                vehiculosPorMarca = mapMarcas.get(selectedMarca.toUpperCase());
                iteratorVehiculo = vehiculosPorMarca.listIterator();
                llenarPantallaModelos(true, false);
            });
        });
    }

    private Map<String, DoubleCircleLinkedList<Vehiculo>> vehiculosMarcas() {
        Map<String, DoubleCircleLinkedList<Vehiculo>> valores = new HashMap<>();
        for (Vehiculo ve : VehiculoDataManager.getInstance().getVehiculos()) {
            String marca = ve.getMarca().toUpperCase();
            if (ve instanceof VehiculoNuevo) {
                valores.computeIfAbsent(marca, k -> {
                    cbMarca.getItems().add(marca);
                    return new DoubleCircleLinkedList<>();
                }).addLast(ve);
            }
        }
        return valores;
    }

    private void configBotones() {
        btnSiguiente = new Button("»");
        this.btnSiguiente.setId("buttonFill");
        btnAnterior = new Button("«");
        this.btnAnterior.setId("buttonFill");
        this.btnRegresar = new Button("Regresar");
        this.btnRegresar.setPrefWidth(200);
        this.btnRegresar.setId("buttonFill");
        btnSiguiente.setOnAction(e -> adelante());
        this.btnAnterior.setOnAction(eh -> atras());
        this.btnRegresar.setOnAction(e -> this.mainController.cargarPagina("vehiculosNuevos"));
    }

    private void congfigBottom() {
        bottom = new HBox(10);
        bottom.setId("botones");
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(this.btnAnterior, this.btnRegresar, this.btnSiguiente);
    }

    private void adelante() {
        llenarPantallaModelos(true, false);
    }

    private void atras() {
        llenarPantallaModelos(false, false);
    }

    private void llenarPantallaModelos(boolean adelante, boolean busqueda) {
        vehiculosMostrados.clear();
        Set<Vehiculo> vehiculosUnicos = new HashSet<>();
        int cont = 0;

        while (iteratorVehiculo.hasNext() && vehiculosMostrados.size() < PER_PAGE) {
            Vehiculo vehiculo = iteratorVehiculo.next();
            if (vehiculo.getTipoCosto().equals(TipoCosto.FIJO) && vehiculosUnicos.add(vehiculo)) {
                vehiculosMostrados.addLast(vehiculo);
            }
        }
        System.out.println("LISTA: "+iteratorVehiculo);

        while (iteratorVehiculo.hasPrevious() && vehiculosMostrados.size() < PER_PAGE) {
            Vehiculo vehiculo = iteratorVehiculo.previous();
            if (vehiculo.getTipoCosto().equals(TipoCosto.FIJO) && vehiculosUnicos.add(vehiculo)) {
                vehiculosMostrados.addFirst(vehiculo);
            }
        }

        FlowPane flowNuevo = panelVehiculos(vehiculosMostrados);
        flowNuevo.getStyleClass().add("flow-pane-custom");

        for (Node child : flowNuevo.getChildren()) {
            FlowPane.setMargin(child, new Insets(10));
        }

        this.scroll.setContent(flowNuevo);

        if (vehiculosMostrados.size() < PER_PAGE) {
            ocultarBotones();
        } else {
            mostrarBotones();
        }
    }

    private void ocultarBotones() {
        this.btnAnterior.setVisible(false);
        this.btnSiguiente.setVisible(false);
    }

    private void mostrarBotones() {
        this.btnAnterior.setVisible(true);
        this.btnSiguiente.setVisible(true);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public String getSelectedMarca() {
        return selectedMarca.toUpperCase();
    }

    public List<Vehiculo> getVehiculosPorMarca() {
        return (List<Vehiculo>) vehiculosPorMarca;
    }

}
