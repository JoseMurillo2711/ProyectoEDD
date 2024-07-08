/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class MostrarInfoController implements Initializable {

    private Vehiculo vehiculo;
    private boolean editable;
    @FXML
    private Button btnAtras;
    @FXML
    private ImageView imgVehiculo;
    @FXML
    private Button btnAdelante;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void recibirVehiculo(Vehiculo vehiculo, boolean editable){
        this.vehiculo = vehiculo;
        System.out.println(this.vehiculo);
        this.editable = editable;
    }
}
