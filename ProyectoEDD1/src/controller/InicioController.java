/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import TDA.ArrayList;
import TDA.DoubleCircleLinkedList;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modelo.Vehiculo;
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
        
        ArrayList<Vehiculo> vehiculos = new ArrayList<>(); // Implementa este método según tus necesidades
        vehiculos.addAll(VehiculoDataManager.getInstance().getVehiculos());
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
}
