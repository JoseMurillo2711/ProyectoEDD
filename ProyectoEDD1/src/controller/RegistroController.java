/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Usuario;
import util.Alertas;
import util.UsuarioDataManager;
import static util.Utilitario.abrirNuevaVentana;
import static util.Utilitario.formatoAlfanumerico;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class RegistroController implements Initializable {

    @FXML
    private Button btnRegresar;

    //Esta variable es verdadera sí y solo sí se accedió a registro mediante la ventana del login
    private boolean isInicioSesion;
    @FXML
    private TextField txtNickname;
    @FXML
    private PasswordField txtPass;
    @FXML
    private VBox vbCentro;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        isInicioSesion = false;
        formatoAlfanumerico(txtNickname);
    }

    public void parametros(boolean inicioSesion) {
        this.isInicioSesion = inicioSesion;
    }

    @FXML
    private void regresar(ActionEvent event) {
        String ruta = "main";
        if (this.isInicioSesion) {
            ruta = "login";
        }
        abrirNuevaVentana("main", "Welcome!");
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnRegresar.getScene().getWindow();
        ventanaActual.close();
    }

    @FXML
    private void registrarse(ActionEvent event) {
        if (this.txtNickname.getText().isBlank() || this.txtPass.getText().isBlank()) {
            Alertas.alertaAdvertencia("Nombre de usuario y/o contraseña vacia", "Por favor ingrese un nombre de usuario o contraseña");
        } else if (!this.txtPass.getText().isBlank() && (this.txtPass.getText().length() < 3 || this.txtPass.getText().length() > 16)) {
            Alertas.alertaError("Contraseña no valida", "La contraseña debe tener entre 3 y 16 caracteres");
        } else {
            try {
                Usuario nuevo = new Usuario(this.txtNickname.getText().strip(), this.txtPass.getText());
                UsuarioDataManager.getInstance().agregarUsuario(nuevo);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Usuario creado");
                alert.setHeaderText(null);
                alert.setContentText("Usuario creado correctamente");

                alert.showAndWait().ifPresent(response -> {
                    this.btnRegresar.fire();
                });
            } catch (Exception ex) {
                Alertas.alertaError("Usuario existente", "El nombre de usuario elegido ya está en uso\nElija uno nuevo o inicie sesion", "Usuario ya existe");
            }

        }

    }
}
