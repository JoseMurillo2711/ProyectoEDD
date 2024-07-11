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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import modelo.Servicio;
import modelo.Vehiculo;
import modelo.VehiculoUsado;
import util.Alertas;
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
    private String tipoAccion;
    private Vehiculo vehiculo;

    /**
     * Initializes the controller class.
     * @param url
     * param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configBoton();
    }

    public void reciribirParametros(MostrarInfoController mostraInfoController, String tipoAccion) {
        this.mostraInfoController = mostraInfoController;
        this.tipoAccion = tipoAccion;
        this.btnServicio.setText(tipoAccion);
        vehiculo = mostraInfoController.getVehiculo();
    }

    private void configBoton() {
        this.btnServicio.setOnAction(e -> {
            if (btnServicio.getText().strip().equalsIgnoreCase("eliminar"))eliminarServicio();
            if (btnServicio.getText().strip().equalsIgnoreCase("editar"))editarServicio();
            if (btnServicio.getText().strip().equalsIgnoreCase("agregar")) agregarServicio();            
        });

    }
    
    private Servicio crearServicio(){
        if(this.txtDescripcionSer.getText().isBlank() || this.datePickServicio.getValue() == null){
            Alertas.alertaError("No se puede crear servicio", "Error", "Debe ingresar la fecha y descripcion del servicio");
            return null;
        }
        
        Servicio servicio = new Servicio(this.txtDescripcionSer.getText(), this.datePickServicio.getValue());        
        return servicio;
    }
    
    private void agregarServicio(){
        if(crearServicio()!=null){
            
            ((VehiculoUsado)vehiculo).getHistorial().agregarServicio(crearServicio());    
            try {
                VehiculoDataManager.getInstance().editarVehiculo(this.vehiculo);
                Alertas.alertaInfo("Servicio agregado", "Se ha añadido correctamente");
                mostraInfoController.setVehiculo(vehiculo);
                mostraInfoController.llenarTablas();
                cerrarVentana();
                System.out.println("VEHICULO HISTORAIL " + ((VehiculoUsado)vehiculo).getHistorial());
            } catch (Exception ex) {
                Alertas.alertaError("No se puede crear servicio", "Error", "Sesion no iniciada" + ex.getMessage());
            }
        }
        
    }
    
    private void editarServicio(){
        
    }
    
    private void eliminarServicio(){
        
    }
    
    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnServicio.getScene().getWindow();
        ventanaActual.close();
    }
}
