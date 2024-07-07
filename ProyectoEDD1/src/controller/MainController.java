/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import TDA.DoubleCircleLinkedList;
import TDA.List;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Usuario;
import modelo.Vehiculo;
import modelo.VehiculoNuevo;
import modelo.VehiculoUsado;
import util.Alertas;
import util.UsuarioDataManager;
import static util.Utilitario.abrirNuevaVentana;
import static util.Utilitario.panelVehiculos;
import util.VehiculoDataManager;
import static util.VehiculoDataManager.buscarVehiculos;

/**
 * FXML Controller class
 *
 * @author COTRINA
 */
public class MainController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private BorderPane rootBorderPane;
    @FXML
    private HBox hbIniciarSesion;
    @FXML
    private MenuBar menuUser;

    private Usuario usuario;

    private List<Vehiculo> vehiculos;
    private boolean seccionNuevo;
    private boolean seccionUsado;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        formatoEncabezado();
        vehiculos = VehiculoDataManager.getInstance().getVehiculos();
        seccionNuevo = false;
        seccionUsado = false;
        this.usuario = UsuarioDataManager.getInstance().getUsuarioActual();
        configIniciales();
        cargarPagina("inicio.fxml");
    }

    private void limpiarCampos(){
        this.txtBusqueda.setText("");
    }
    
    private void formatoEncabezado() {
        ImageView lupa = new ImageView(new Image("recursos/lupa.png"));
        lupa.setFitHeight(20);
        lupa.setFitWidth(25);
        this.btnBuscar.setGraphic(lupa);
        Tooltip tltp = new Tooltip("Buscar carro");
        this.btnBuscar.setTooltip(tltp);
    }

    private void configIniciales() {
        if (this.usuario == null) {
            this.menuUser.setVisible(false);
            this.menuUser.setManaged(false);
            this.hbIniciarSesion.setVisible(true);
            this.hbIniciarSesion.setManaged(true);
        } else {
            this.menuUser.getMenus().get(0).setText("Bienvenido/a, " + this.usuario.getNickname() + "!");
            this.menuUser.setVisible(true);
            this.menuUser.setManaged(true);
            this.hbIniciarSesion.setVisible(false);
            this.hbIniciarSesion.setManaged(false);
        }
    }

    @FXML
    private void iniciarSesion(MouseEvent event) {
        mostrarVentanaDeInicioSesion();
    }

    @FXML
    private void registrarse(MouseEvent event) {
        abrirNuevaVentana("registro", "Registro de Usuario");
        cerrarVentana();
    }

    @FXML
    private void mostrarMisAutos(ActionEvent event) {
    }

    @FXML
    private void verFavoritos(ActionEvent event) {

    }

    @FXML
    private void mostrarAutosNuevos(ActionEvent event) {
        cargarPagina("vehiculosNuevos.fxml");
        seccionNuevo = true;
    }

    @FXML
    private void mostrarAutosViejos(ActionEvent event) {
        cargarPagina("vehiculosViejos.fxml");
        seccionUsado = true;
    }

    @FXML
    private void agregarAuto(ActionEvent event) {
        if (usuario == null) {
            Alertas.alertaError("No puedes continuar", "Para poder agregar vehiculos debe iniciar sesión");
        } else {
            abrirNuevaVentana("agregarVehiculo", "Agregar vehiculo");
            cerrarVentana();
        }
    }

    @FXML
    private void inicioDePagina(MouseEvent event) {
        cargarPagina("inicio.fxml");
    }

    private void cargarPagina(String fxmlFile) {
        limpiarCampos();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/" + fxmlFile));
            Node pagina = loader.load();
            rootBorderPane.setCenter(pagina);
        } catch (IOException e) {
            Alertas.alertaError("Ha ocurrido un error", e.getMessage());
        }
        seccionNuevo = false;
        seccionUsado = false;
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        mostrarVentanaDeInicioSesion();
        UsuarioDataManager.getInstance().cerrarSesion();
        Alertas.alertaInfo("Acabas de cerrar Sesion", "Sesion cerrada correctamente");
    }

    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnBuscar.getScene().getWindow();
        ventanaActual.close();
    }

    private void mostrarVentanaDeInicioSesion() {
        abrirNuevaVentana("login", "Iniciar Sesión");
        cerrarVentana();
    }

    @FXML
    private void buscarAuto(ActionEvent event) {
        List<Vehiculo> vehiculosBuscados;
        if(this.seccionNuevo)
            vehiculosBuscados = buscarVehiculos(this.txtBusqueda.getText().strip() + "NUEVO");
        else if(this.seccionUsado)
            vehiculosBuscados = buscarVehiculos(this.txtBusqueda.getText().strip() + "USADO");
        else
            vehiculosBuscados = buscarVehiculos(this.txtBusqueda.getText().strip());
        
        if(vehiculosBuscados.isEmpty()){
            VBox vbox = new VBox();
            Label lbl = new Label("No se encontró ningun resultado =(");
            vbox.getChildren().add(lbl);
            vbox.setAlignment(Pos.CENTER);
            Platform.runLater(()->this.rootBorderPane.setCenter(vbox));
        }            
        else
            Platform.runLater(()->this.rootBorderPane.setCenter(panelVehiculos(vehiculosBuscados)));
    }

    
    
}
