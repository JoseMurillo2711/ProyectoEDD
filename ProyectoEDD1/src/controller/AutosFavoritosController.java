/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import TDA.ArrayList;
import TDA.DoubleCircleLinkedList;
import TDA.List;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Vehiculo;
import static util.CONSTANTES.PER_PAGE;
import util.UsuarioDataManager;
import util.Utilitario;
import static util.Utilitario.createCard;
import static util.Utilitario.panelVehiculos;
import util.VehiculoDataManager;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class AutosFavoritosController implements Initializable {

    @FXML
    private Button btnAnterior2;
    @FXML
    private Button btnSiguiente2;
    @FXML
    private TilePane tilePane2;
    @FXML
    private Label lblTitulo;
    
    private DoubleCircleLinkedList<ArrayList<Vehiculo>> paginas;
    
    private ListIterator<ArrayList<Vehiculo>> current;
    
    
    private static final int ITEMS_PER_PAGE = 10;
    
    
    @FXML
    private void regresar(ActionEvent event) {
        Utilitario.abrirNuevaVentana("main", "Bienvenido!");
        cerrarVentana();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrarAutos();
    }    
    
    private void mostrarAutos() {        
        paginas = new DoubleCircleLinkedList<>();
        
        // Cargar los vehículos desde el modelo o la base de datos
        
        List<Vehiculo> vehiculos = UsuarioDataManager.getInstance().getUsuarioActual().getVehiculosFavoritos();
//        vehiculos = VehiculoDataManager.getInstance().getVehiculos();
        // Dividir la lista de vehículos en páginas
        System.out.println(vehiculos.size());
        int totalPages = vehiculos.size() /  ITEMS_PER_PAGE;
        for (int i = 0; i <= totalPages; i++) {
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
        btnSiguiente2.setOnAction(event -> mostrarPaginaProxima());
        btnAnterior2.setOnAction(event -> mostrarPaginaAnterior());
        
    }
    
    private void mostrarPaginaProxima() {
        if (current.hasNext()) {
            List<Vehiculo> vls = current.next();
            tilePane2.getChildren().clear();
            for (Vehiculo vehiculo : vls) {
                VBox card = createCard(vehiculo);
                tilePane2.getChildren().add(card);
            }
        }
    }
    
    private void mostrarPaginaAnterior() {
        if (current.hasPrevious()) {
            ArrayList<Vehiculo> vls = current.previous();
            tilePane2.getChildren().clear();
            for (Vehiculo vehiculo : vls) {
                VBox card = createCard(vehiculo);
                tilePane2.getChildren().add(card);
            }
        }
    }
    
    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.lblTitulo.getScene().getWindow();
        ventanaActual.close();
    }
     
}
