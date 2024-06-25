/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.Alertas;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class RegistroController implements Initializable {

    @FXML
    private Button btnRegresar;
    
    //Esta variable es verdadera sí y solo sí se accedió a registro mediante la ventana del login
    private boolean inicioSesion;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicioSesion = false;
    }    

    public void parametros(boolean inicioSesion){
        this.inicioSesion = inicioSesion;
        
    }
    
    @FXML
    private void regresar(ActionEvent event) {
        String ruta = "../vista/main.fxml";
        if(this.inicioSesion)
            ruta = "../vista/login.fxml";        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
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

    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnRegresar.getScene().getWindow();
        ventanaActual.close();
    }
    
}
