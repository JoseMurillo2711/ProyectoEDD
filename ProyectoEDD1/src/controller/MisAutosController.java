package controller;

import TDA.ArrayList;
import TDA.List;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Usuario;
import modelo.Vehiculo;
import util.Alertas;
import util.UsuarioDataManager;
import util.Utilitario;
import static util.Utilitario.abrirNuevaVentana;
import static util.Utilitario.createCardMios;
import util.VehiculoDataManager;

public class MisAutosController implements Initializable {

    @FXML
    private Label lblTitulo;
    @FXML
    private VBox vbAutos;

    private Usuario user;
    private VehiculoDataManager vehiculoData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = UsuarioDataManager.getInstance().getUsuarioActual();
        this.lblTitulo.setText(user.getNickname() + ", estos son tus autos");
        vehiculoData = VehiculoDataManager.getInstance();
        llenarVentana();
    }

    @FXML
    private void regresar(ActionEvent event) {
        Utilitario.abrirNuevaVentana("main", "Bienvenido!");
        cerrarVentana();
    }

    private List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> listV = new ArrayList<>();
        for (Vehiculo v : vehiculoData.getVehiculos()) {
            if (v.getDueno().equals(user)) {
                listV.addLast(v);
            }
        }
        return listV;
    }

    private void llenarVentana() {
        List<Vehiculo> listV = obtenerVehiculos();
        if (!listV.isEmpty()) {
            this.vbAutos.getChildren().clear();
            for (Vehiculo v : listV) {
                HBox nuevo = new HBox(10);
                HBox auto = createCardMios(v);
                HBox.setHgrow(auto, Priority.ALWAYS);
                VBox.setVgrow(nuevo, Priority.ALWAYS);
                nuevo.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(nuevo, Priority.ALWAYS);

                nuevo.getChildren().addAll(auto, opciones(v));
                vbAutos.getChildren().add(nuevo);
            }

        } else {
            Label noAutosLabel = new Label("No tienes autos registrados.");
            vbAutos.getChildren().add(noAutosLabel);
        }
    }

    private VBox opciones(Vehiculo v) {
        VBox opciones = new VBox(10);
        opciones.setAlignment(Pos.CENTER);
        Button editar = new Button("Editar");
        Button eliminar = new Button("Eliminar");
        
        editar.setMaxWidth(Double.MAX_VALUE);
        eliminar.setMaxWidth(Double.MAX_VALUE);

        opciones.getChildren().addAll(editar, eliminar);
        VBox.setVgrow(editar, Priority.ALWAYS);
        VBox.setVgrow(eliminar, Priority.ALWAYS);

        editar.setOnAction(e -> {
            FXMLLoader loader = abrirNuevaVentana("mostrarInfo", "Modificar Auto");
            MostrarInfoController controller = loader.getController();
            controller.recibirVehiculo(v, true);
            this.cerrarVentana();
        });

        eliminar.setOnAction(e -> confirmarEliminacion(v));

        return opciones;
    }

    private void confirmarEliminacion(Vehiculo v) {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmación");
        confirmDialog.setHeaderText("Confirmar eliminación");
        String detalles = "Marca: " + v.getMarca() + "\nModelo: " + v.getModelo() + " " + v.getAño();
        confirmDialog.setContentText(detalles + "\n¿Estás seguro de que deseas eliminar este vehículo?");
        confirmDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                eliminarVehiculo(v);
            }
        });
    }

    private void eliminarVehiculo(Vehiculo v) {
        try {
            vehiculoData.borrarVehiculo(v);
            llenarVentana();
        } catch (Exception e) {
            Alertas.alertaError("Erroe", "Error al eliminar el vehiculo", "No se pudo eliminar el vehículo: " + e.getMessage());
        }
    }

    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.lblTitulo.getScene().getWindow();
        ventanaActual.close();
    }
}
