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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Usuario;
import util.Alertas;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnIniciarSesion;
    
    private Stage stage;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/main.fxml"));
        Parent root;
        try {
            root = loader.load();
            MainController controller = loader.getController();
            controller.controlador(new Usuario());            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Welcome!");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        } catch (IOException ex) {
            Alertas.alertaError("Ha ocurrido un error", ex.getMessage());
        }
    }

    @FXML
    private void registrarse(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/registro.fxml"));
        Parent root;
        try {
            root = loader.load();
            RegistroController registro = loader.getController();
            registro.parametros(true);
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
    private void regresarComoInvitado(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/main.fxml"));
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
        Stage ventanaActual = (Stage) this.btnIniciarSesion.getScene().getWindow();
        ventanaActual.close();
    }
}


