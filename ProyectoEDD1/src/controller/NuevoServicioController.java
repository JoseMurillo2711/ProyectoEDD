/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import modelo.Servicio;
import modelo.Vehiculo;
import modelo.VehiculoUsado;
import util.Alertas;
import static util.Utilitario.configureDatePicker;
import util.VehiculoDataManager;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class NuevoServicioController implements Initializable {

    @FXML
    private Button btnServicio;
    @FXML
    private DatePicker datePickServicio;
    @FXML
    private TextArea txtDescripcionSer;

    private MostrarInfoController mostraInfoController;
    private Vehiculo vehiculo;
    private Servicio servicio;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        configureDatePicker(datePickServicio);
    }

    public void reciribirParametros(MostrarInfoController mostraInfoController, String tipoAccion) {
        this.mostraInfoController = mostraInfoController;
        this.btnServicio.setText(tipoAccion);
        vehiculo = mostraInfoController.getVehiculo();
        configBoton();
    }

    public void recibirServicio(Servicio servicio) {
        this.servicio = servicio;
        this.datePickServicio.setValue(this.servicio.getFecha());
        this.txtDescripcionSer.setText(this.servicio.getTipo());
    }

    private void configBoton() {
        if (btnServicio.getText().strip().toLowerCase().contains("eliminar")) {
            this.datePickServicio.setDisable(true);
            this.txtDescripcionSer.setDisable(true);
            
            this.datePickServicio.setEditable(false);
            this.txtDescripcionSer.setEditable(false);
        }
        this.btnServicio.setOnAction(e -> {
            if (btnServicio.getText().strip().toLowerCase().contains("eliminar")) {
                preguntarEliminacion();
            }
            if (btnServicio.getText().strip().toLowerCase().contains("editar")) {
                editarServicio();
            }
            if (btnServicio.getText().strip().toLowerCase().contains("agregar")) {
                agregarServicio();
            }
        });

    }

    private Servicio crearServicio() {
        if (this.txtDescripcionSer.getText().isBlank() || this.datePickServicio.getValue() == null) {
            Alertas.alertaError("No se puede crear servicio", "Error", "Debe ingresar la fecha y descripcion del servicio");
            return null;
        }

        Servicio servicioN = new Servicio(this.datePickServicio.getValue(), this.txtDescripcionSer.getText());
        return servicioN;
    }

    private void agregarServicio() {
        if (crearServicio() != null) {
            ((VehiculoUsado) vehiculo).getHistorial().agregarServicio(crearServicio());
            try {
                VehiculoDataManager.getInstance().editarVehiculo(this.vehiculo);
                Alertas.alertaInfo("Servicio agregado", "Se ha añadido correctamente");
                mostraInfoController.setVehiculo(vehiculo);
                mostraInfoController.llenarTablas();
                cerrarVentana();
                System.out.println("VEHICULO HISTORAIL " + ((VehiculoUsado) vehiculo).getHistorial());
            } catch (Exception ex) {
                Alertas.alertaError("No se puede crear servicio", "Error", "Sesion no iniciada" + ex.getMessage());
            }
        }
    }

    private void editarServicio() {
        this.servicio.setFecha(this.datePickServicio.getValue());
        this.servicio.setTipo(this.txtDescripcionSer.getText());
        ((VehiculoUsado) vehiculo).getHistorial().editarServicio(servicio);
        try {
            VehiculoDataManager.getInstance().editarVehiculo(this.vehiculo);
            Alertas.alertaInfo("Servicio editado", "Se ha editado correctamente");
            mostraInfoController.setVehiculo(vehiculo);
            mostraInfoController.llenarTablas();
            cerrarVentana();
        } catch (Exception ex) {
            Alertas.alertaError("No se puede edotar eñ servicio", "Error", "Sesion no iniciada" + ex.getMessage());
        }
    }

    private void eliminarServicio() {
        try {
            ((VehiculoUsado) vehiculo).getHistorial().eliminarServicio(servicio);
            VehiculoDataManager.getInstance().editarVehiculo(this.vehiculo);
            Alertas.alertaInfo("Servicio eliminado", "Se ha eliminado correctamente");
            mostraInfoController.setVehiculo(vehiculo);
            mostraInfoController.llenarTablas();
            cerrarVentana();
        } catch (Exception ex) {
            //Logger.getLogger(NuevaReparacionController.class.getName()).log(Level.SEVERE, null, ex);
            Alertas.alertaError("No se puede eliminar el servicio", "Error", "Error: " + ex);
        }
    }

    private void preguntarEliminacion() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Está seguro de eliminar este servicio?");
        alert.setContentText("Esta acción es irreversible.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                eliminarServicio();
            } else {
                System.out.println("Acción cancelada");
            }
        });
    }

    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnServicio.getScene().getWindow();
        ventanaActual.close();
    }
}
