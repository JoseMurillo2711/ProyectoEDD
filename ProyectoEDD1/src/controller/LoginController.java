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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Alertas;
import util.UsuarioDataManager;
import static util.Utilitario.abrirNuevaVentana;

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
    @FXML
    private TextField txtNickname;
    @FXML
    private PasswordField txtPass;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        boolean iniciarSesion = UsuarioDataManager.getInstance().iniciarSesion(txtNickname.getText().strip(), txtPass.getText());
        if (iniciarSesion) {
            abrirNuevaVentana("main", "Welcome!");
            cerrarVentana();
        } else {
            Alertas.alertaError("No se pudo iniciar sesion", "Usario y/o contraseña incorrecta");
        }
    }

    @FXML
    private void registrarse(ActionEvent event) {
        FXMLLoader loader = abrirNuevaVentana("registro", "Registro de Usuario");
        RegistroController registro = loader.getController();
        registro.parametros(true);
        cerrarVentana();

    }

    @FXML
    private void regresarComoInvitado(ActionEvent event) {
        abrirNuevaVentana("main", "Welcome!");
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnIniciarSesion.getScene().getWindow();
        ventanaActual.close();
    }
}
