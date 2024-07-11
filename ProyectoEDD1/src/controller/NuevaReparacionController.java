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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class NuevaReparacionController implements Initializable {

    @FXML
    private Button btnReparacion;
    @FXML
    private DatePicker datePick;
    @FXML
    private TextArea txtDescripcion;
    
    private MostrarInfoController mostraInfoController;
    private String tipoAccion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void reciribirParametros(MostrarInfoController mostraInfoController, String tipoAccion){
        this.mostraInfoController = mostraInfoController;
        this.tipoAccion = tipoAccion;
    }

}
