/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import TDA.ArrayList;
import TDA.DoubleCircleLinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Usuario;
import modelo.Vehiculo;
import util.Alertas;

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
    
    @FXML
    private TilePane tilePane;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Button btnAnterior;

    private DoubleCircleLinkedList<ArrayList<Vehiculo>> paginas;
    
    private ListIterator<ArrayList<Vehiculo>> current;
    
    private static final int ITEMS_PER_PAGE = 10;
    

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
        configIniciales();
        mostrarAutos();
    }

    public void controlador(Usuario usuario) {
        System.out.println("Es null? " + this.usuario);
        this.usuario = usuario;
        System.out.println("Es null? " + this.usuario);
        Platform.runLater(() -> configIniciales());

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
            System.out.println("Esto es solo una prubea");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/registro.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage st = new Stage();
            st.setTitle("Registrarse");
            Scene sc = new Scene(root);
            st.setScene(sc);
            cerrarVentana();
            st.show();
        } catch (IOException ex) {
            Alertas.alertaError("Ha ocurrido un error", ex.getMessage());
        }
    }

    @FXML
    private void mostrarMisAutos(ActionEvent event) {
    }

    @FXML
    private void verFavoritos(ActionEvent event) {
    }

   

    @FXML
    private void mostrarAutosNuevos(ActionEvent event) {
    }

    @FXML
    private void mostrarAutosViejos(ActionEvent event) {
    }

    @FXML
    private void agregarAuto(ActionEvent event) {
    }

    @FXML
    private void inicioDePagina(MouseEvent event) {
        
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        mostrarVentanaDeInicioSesion();
        Alertas.alertaInfo("Acabas de cerrar Sesion", "Sesion cerrada correctamente");
    }
   
    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnBuscar.getScene().getWindow();
        ventanaActual.close();
    }

    private void mostrarVentanaDeInicioSesion(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/login.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage st = new Stage();
            st.setTitle("Iniciar Sesión");
            Scene sc = new Scene(root);
            st.setScene(sc);
            cerrarVentana();
            st.show();
        } catch (IOException ex) {
            Alertas.alertaError("Ha ocurrido un error", ex.getMessage());
        }
    }
    
    private void mostrarAutos() {
        System.out.println("imprimeee");
        paginas = new DoubleCircleLinkedList<>();
        
        System.out.println("123");

        // Cargar los vehículos desde el modelo o la base de datos
        ArrayList<Vehiculo> vehiculos = cargarVehiculos(); // Implementa este método según tus necesidades

        // Dividir la lista de vehículos en páginas
        int totalPages = (int) Math.ceil(vehiculos.size() / (double) ITEMS_PER_PAGE);
        for (int i = 0; i < totalPages; i++) {
            ArrayList<Vehiculo> page = new ArrayList<>();
            for (int j = i * ITEMS_PER_PAGE; j < (i + 1) * ITEMS_PER_PAGE && j < vehiculos.size(); j++) {
                page.addLast(vehiculos.get(j));
            }
            paginas.addLast(page);
        }
        
        System.out.println("Paginas completas: " + paginas);

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

    private VBox createCard(Vehiculo vehiculo) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
        Label marcaLabel = new Label("Marca: " + vehiculo.getMarca());
        Label modeloLabel = new Label("Modelo: " + vehiculo.getModelo());
        Label anoLabel = new Label("Año: " + vehiculo.getAño());
        card.getChildren().addAll(marcaLabel, modeloLabel, anoLabel);
        return card;
    }
    
    
    public ArrayList<Vehiculo> cargarVehiculos() {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.addLast(new Vehiculo("Toyota", "Corolla", 2020));
        vehiculos.addLast(new Vehiculo("Mazda", "3", 2020));
        vehiculos.addLast(new Vehiculo("Chevrolet", "Aveo", 2020));
        vehiculos.addLast(new Vehiculo("Honda", "Civic", 2021));
        vehiculos.addLast(new Vehiculo("Ford", "Focus", 2021));
        vehiculos.addLast(new Vehiculo("Nissan", "Sentra", 2021));
        vehiculos.addLast(new Vehiculo("Volkswagen", "Jetta", 2021));
        vehiculos.addLast(new Vehiculo("Hyundai", "Elantra", 2021));
        vehiculos.addLast(new Vehiculo("Kia", "Forte", 2021));
        vehiculos.addLast(new Vehiculo("Subaru", "Impreza", 2021));
        vehiculos.addLast(new Vehiculo("Toyota", "Camry", 2022));
        vehiculos.addLast(new Vehiculo("Mazda", "6", 2022));
        vehiculos.addLast(new Vehiculo("Chevrolet", "Malibu", 2022));
        vehiculos.addLast(new Vehiculo("Honda", "Accord", 2022));
        vehiculos.addLast(new Vehiculo("Ford", "Fusion", 2022));
        vehiculos.addLast(new Vehiculo("Nissan", "Altima", 2022));
        vehiculos.addLast(new Vehiculo("Volkswagen", "Passat", 2022));
        vehiculos.addLast(new Vehiculo("Hyundai", "Sonata", 2022));
        vehiculos.addLast(new Vehiculo("Kia", "Optima", 2022));
        vehiculos.addLast(new Vehiculo("Subaru", "Legacy", 2022));
        vehiculos.addLast(new Vehiculo("Toyota", "Avalon", 2023));
        vehiculos.addLast(new Vehiculo("Mazda", "CX-5", 2023));
        vehiculos.addLast(new Vehiculo("Chevrolet", "Equinox", 2023));
        vehiculos.addLast(new Vehiculo("Honda", "CR-V", 2023));
        vehiculos.addLast(new Vehiculo("Ford", "Escape", 2023));
        vehiculos.addLast(new Vehiculo("Nissan", "Rogue", 2023));
        vehiculos.addLast(new Vehiculo("Volkswagen", "Tiguan", 2023));
        vehiculos.addLast(new Vehiculo("Hyundai", "Tucson", 2023));
        vehiculos.addLast(new Vehiculo("Kia", "Sportage", 2023));
        vehiculos.addLast(new Vehiculo("Subaru", "Forester", 2023));
        vehiculos.addLast(new Vehiculo("Toyota", "RAV4", 2024));
        vehiculos.addLast(new Vehiculo("Mazda", "CX-30", 2024));
        vehiculos.addLast(new Vehiculo("Chevrolet", "Trailblazer", 2024));
        vehiculos.addLast(new Vehiculo("Honda", "HR-V", 2024));
        vehiculos.addLast(new Vehiculo("Ford", "Bronco", 2024));
        vehiculos.addLast(new Vehiculo("Nissan", "Kicks", 2024));
        vehiculos.addLast(new Vehiculo("Volkswagen", "Atlas", 2024));
        vehiculos.addLast(new Vehiculo("Hyundai", "Santa Fe", 2024));
        vehiculos.addLast(new Vehiculo("Kia", "Seltos", 2024));
        vehiculos.addLast(new Vehiculo("Subaru", "Ascent", 2024));
        vehiculos.addLast(new Vehiculo("Toyota", "Highlander", 2025));
        vehiculos.addLast(new Vehiculo("Mazda", "CX-9", 2025));
        vehiculos.addLast(new Vehiculo("Chevrolet", "Traverse", 2025));
        vehiculos.addLast(new Vehiculo("Honda", "Pilot", 2025));
        vehiculos.addLast(new Vehiculo("Ford", "Explorer", 2025));
        return vehiculos;
    }
    
}
