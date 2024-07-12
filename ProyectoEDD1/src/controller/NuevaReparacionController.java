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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import modelo.Reparacion;
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
public class NuevaReparacionController implements Initializable {

    @FXML
    private Button btnReparacion;
    @FXML
    private DatePicker datePick;
    @FXML
    private TextArea txtDescripcion;

    private MostrarInfoController mostraInfoController;
    private Vehiculo vehiculo;
    private Reparacion reparacion;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        configureDatePicker(datePick);
    }

    public void reciribirParametros(MostrarInfoController mostraInfoController, String tipoAccion) {
        this.mostraInfoController = mostraInfoController;
        this.btnReparacion.setText(tipoAccion);
        vehiculo = mostraInfoController.getVehiculo();
        configBoton();
    }

    public void recibirReparacion(Reparacion servicio) {
        this.reparacion = servicio;
        this.datePick.setValue(this.reparacion.getFecha());
        this.txtDescripcion.setText(this.reparacion.getDescripcion());
    }

    private void configBoton() {
        if (btnReparacion.getText().strip().toLowerCase().contains("eliminar")) {            
            this.datePick.setDisable(true);            
            this.datePick.setEditable(false);
            this.txtDescripcion.setEditable(false);
        }
        this.btnReparacion.setOnAction(e -> {
            if (btnReparacion.getText().strip().toLowerCase().contains("eliminar")) {
                preguntarEliminacion();
            }
            if (btnReparacion.getText().strip().toLowerCase().contains("editar")) {
                editarServicio();
            }
            if (btnReparacion.getText().strip().toLowerCase().contains("agregar")) {
                agregarServicio();
            }
        });

    }

    private Reparacion crearReparacion() {
        if (this.txtDescripcion.getText().isBlank() || this.datePick.getValue() == null) {
            Alertas.alertaError("No se puede crear servicio", "Error", "Debe ingresar la fecha y descripcion del servicio");
            return null;
        }

        Reparacion servicioN = new Reparacion(this.datePick.getValue(), this.txtDescripcion.getText());
        return servicioN;
    }

    private void agregarServicio() {
        if (crearReparacion() != null) {
            ((VehiculoUsado) vehiculo).getHistorial().agregarReparacion(crearReparacion());
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
        this.reparacion.setFecha(this.datePick.getValue());
        this.reparacion.setDescripcion(this.txtDescripcion.getText());
        ((VehiculoUsado) vehiculo).getHistorial().editarReparacion(reparacion);
        try {
            VehiculoDataManager.getInstance().editarVehiculo(this.vehiculo);
            Alertas.alertaInfo("Reparacion editada", "Se ha editado correctamente");
            mostraInfoController.setVehiculo(vehiculo);
            mostraInfoController.llenarTablas();
            cerrarVentana();
        } catch (Exception ex) {
            Alertas.alertaError("No se puede agreagar la reparacion", "Error", "Sesion no iniciada" + ex.getMessage());
        }
    }

    private void eliminarServicio() {
        try {
            ((VehiculoUsado) vehiculo).getHistorial().eliminarReparacion(reparacion);
            VehiculoDataManager.getInstance().editarVehiculo(this.vehiculo);
            Alertas.alertaInfo("Reparacion eliminada", "Se ha editado correctamente");
            mostraInfoController.setVehiculo(vehiculo);
            mostraInfoController.llenarTablas();
            cerrarVentana();
        } catch (Exception ex) {
            Alertas.alertaError("No se puede eliminar la reparacion", "Error", "Error: " + ex);
        }
    }

    private void preguntarEliminacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Está seguro de eliminar esta reparacion?");
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
        Stage ventanaActual = (Stage) this.btnReparacion.getScene().getWindow();
        ventanaActual.close();
    }
}
