package controller;

import java.io.File;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.*;
import modelo.Vehiculo;
import tipo.*;
import static util.CONSTANTES.IMAGEN_NOT_FOUND;
import static util.CONSTANTES.RUTA_IMAGEN_CARROS;
import util.Utilitario;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class MostrarInfoController implements Initializable {

    private Vehiculo vehiculo;
    private boolean editable;
    private ListIterator<String> imageIterator;
    private ObservableList<String> imagenes;

    @FXML
    private Button btnAtras;
    @FXML
    private ImageView imgVehiculo;
    @FXML
    private Button btnAdelante;
    @FXML
    private HBox hbEdicion;
    @FXML
    private VBox vbInfoUsado;
    @FXML
    private Button btnAgregarImagen;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtAnio;
    @FXML
    private TextField txtKm;
    @FXML
    private TextField txtPrecio;
    @FXML
    private ComboBox<TipoMotor> cboMotor;
    @FXML
    private ComboBox<TipoTraccion> cbTraccion;
    @FXML
    private ComboBox<TipoDireccion> cbDireccion;
    @FXML
    private ComboBox<TipoCosto> cbTipoPrecio;
    @FXML
    private CheckBox chkClima;
    @FXML
    private TextField txtNumPuertas;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtVelo;
    @FXML
    private ComboBox<TipoTransmision> cbTransmision;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtProvincia;
    @FXML
    private TableView<Servicio> tableServicios;
    @FXML
    private TableView<Reparacion> tablaReparacion;
    @FXML
    private HBox hbEdicionServicios;
    @FXML
    private HBox hbEdicionReparaciones;
    @FXML
    private ColorPicker cbColor;
    @FXML
    private TextField txtNumHileras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void recibirVehiculo(Vehiculo vehiculo, boolean editable) {
        this.vehiculo = vehiculo;
        System.out.println(this.vehiculo);
        this.editable = editable;

        if (!editable) {
            ocultarAtributosEdicion();
            desactivarEdicion();
        }

        if (!(vehiculo instanceof VehiculoUsado)) {
            ocultarAtributosUsados();
        }
        confMensajeTablas();
        llenarInfoCampos();
    }

    private void confMensajeTablas() {
        Label noDataLabelServicios = new Label("No hay datos que mostrar");
        noDataLabelServicios.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
        tableServicios.setPlaceholder(noDataLabelServicios);

        Label noDataLabelReparaciones = new Label("No hay datos que mostrar");
        noDataLabelReparaciones.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
        tablaReparacion.setPlaceholder(noDataLabelReparaciones);
    }

    private void desactivarEdicion() {
        txtMarca.setEditable(false);
        txtMarca.setDisable(true);

        txtModelo.setEditable(false);
        txtModelo.setDisable(true);

        txtAnio.setEditable(false);
        txtAnio.setDisable(true);

        txtKm.setEditable(false);
        txtKm.setDisable(true);

        txtPrecio.setEditable(false);
        txtPrecio.setDisable(true);

        txtNumPuertas.setEditable(false);
        txtNumPuertas.setDisable(true);

        txtCiudad.setEditable(false);
        txtCiudad.setDisable(true);

        txtDireccion.setEditable(false);
        txtDireccion.setDisable(true);

        txtVelo.setEditable(false);
        txtVelo.setDisable(true);

        txtUsuario.setEditable(false);
        txtUsuario.setDisable(true);

        txtNumHileras.setEditable(false);
        txtNumHileras.setDisable(true);

        txtPlaca.setEditable(false);
        txtPlaca.setDisable(true);

        txtProvincia.setEditable(false);
        txtProvincia.setDisable(true);

        cboMotor.setDisable(true);
        cbTraccion.setDisable(true);
        cbDireccion.setDisable(true);
        cbTipoPrecio.setDisable(true);
        cbTransmision.setDisable(true);
        cbColor.setDisable(true);
        chkClima.setDisable(true);
        tableServicios.setDisable(true);
        tablaReparacion.setDisable(true);
    }

    private void ocultarAtributosEdicion() {
        hbEdicion.setVisible(false);
        hbEdicion.setManaged(false);
        hbEdicionServicios.setVisible(false);
        hbEdicionServicios.setManaged(false);
        hbEdicionReparaciones.setVisible(false);
        hbEdicionReparaciones.setManaged(false);
        btnAgregarImagen.setVisible(false);
        btnAgregarImagen.setManaged(false);
    }

    private void llenarInfoCampos() {
        if (vehiculo == null) {
            return;
        }
        txtMarca.setText(vehiculo.getMarca() != null ? vehiculo.getMarca() : "");
        txtModelo.setText(vehiculo.getModelo() != null ? vehiculo.getModelo() : "");
        txtAnio.setText(vehiculo.getAño() != 0 ? String.valueOf(vehiculo.getAño()) : "");
        txtKm.setText(vehiculo.getKilometraje() != 0 ? String.valueOf(vehiculo.getKilometraje()) : "");
        txtPrecio.setText(vehiculo.getPrecio() != 0 ? String.valueOf(vehiculo.getPrecio()) : "");
        txtNumPuertas.setText(vehiculo.getNumPuerta() != 0 ? String.valueOf(vehiculo.getNumPuerta()) : "");
        txtCiudad.setText(vehiculo.getUbicacion() != null ? vehiculo.getUbicacion().getCiudad() : "");
        txtDireccion.setText(vehiculo.getUbicacion() != null ? vehiculo.getUbicacion().getDireccion() : "");
        txtVelo.setText(String.valueOf(vehiculo.getMotor() != null ? vehiculo.getTransmision().getVelocidades() : 0));
        txtUsuario.setText(vehiculo.getDueno() != null ? vehiculo.getDueno().getNickname() : "");
        cboMotor.setValue(vehiculo.getMotor() != null ? vehiculo.getMotor().getTipo() : null);
        cbTraccion.setValue(vehiculo.getTraccion() != null ? vehiculo.getTraccion() : null);
        cbDireccion.setValue(vehiculo.getDireccion() != null ? vehiculo.getDireccion() : null);
        cbTipoPrecio.setValue(vehiculo.getTipoCosto() != null ? vehiculo.getTipoCosto() : null);
        cbTransmision.setValue(vehiculo.getTransmision() != null ? vehiculo.getTransmision().getTipo() : null);
        cbColor.setValue(vehiculo.getColor() != null ? vehiculo.getColor() : null);
        chkClima.setSelected(vehiculo.isClimatizado());
        mostrarImagenes();
        if (vehiculo instanceof VehiculoUsado vehiculoUsado) {
            Placa placa = vehiculoUsado.getPlaca();
            if (placa != null) {
                txtPlaca.setText(String.valueOf(placa.getUltimoDigito()));
            } else {
                txtPlaca.setText("");
            }

            Historial historial = vehiculoUsado.getHistorial();
            if (historial != null) {
                if (historial.getServicios() != null && !historial.getServicios().isEmpty()) {
                    ObservableList<Servicio> serviciosObservableList = FXCollections.observableArrayList(historial.getServicios().toArray(new Servicio[0]));
                    tableServicios.setItems(serviciosObservableList);
                } else {
                    tableServicios.setItems(FXCollections.observableArrayList());
                }

                if (historial.getReparaciones() != null && !historial.getReparaciones().isEmpty()) {
                    ObservableList<Reparacion> reparacionesObservableList = FXCollections.observableArrayList(historial.getReparaciones().toArray(new Reparacion[0]));
                    tablaReparacion.setItems(reparacionesObservableList);
                } else {
                    tablaReparacion.setItems(FXCollections.observableArrayList());
                }
            } else {
                tableServicios.setItems(FXCollections.observableArrayList());
                tablaReparacion.setItems(FXCollections.observableArrayList());
            }
        } else {
            tableServicios.setItems(FXCollections.observableArrayList());
            tablaReparacion.setItems(FXCollections.observableArrayList());
        }
    }

    private void mostrarImagenes() {
        File archivo = new File(IMAGEN_NOT_FOUND);
        if (vehiculo == null || vehiculo.getFotos() == null || vehiculo.getFotos().isEmpty()) {
            imgVehiculo.setImage(new Image(archivo.toURI().toString(), true));
            return;
        }
        imagenes = FXCollections.observableArrayList(vehiculo.getFotos().toArray(new String[0]));
        imageIterator = imagenes.listIterator();

        if (imageIterator.hasNext()) {
            setImagen(imageIterator.next());
        }
    }

    private void setImagen(String nombreImagen) {
        File notFound = new File(IMAGEN_NOT_FOUND);
        String rutaImagen = RUTA_IMAGEN_CARROS + nombreImagen;
        File archivo = new File(rutaImagen);
        try {
            Image image = new Image(archivo.toURI().toString());
            if (image.isError()) {
                imgVehiculo.setImage(new Image(notFound.toURI().toString()));
            } else {
                imgVehiculo.setImage(image);
            }
        } catch (Exception e) {
            imgVehiculo.setImage(new Image(notFound.toURI().toString()));
        }
    }

    private void ocultarAtributosUsados() {
        vbInfoUsado.setVisible(false);
        vbInfoUsado.setManaged(false);
    }

    @FXML
    private void mostrarAnterior(ActionEvent event) {
        if (imagenes != null && !imagenes.isEmpty()) {
            if (!imageIterator.hasPrevious()) {
                imageIterator = imagenes.listIterator(imagenes.size());
            }
            setImagen(imageIterator.previous());
        }
    }

    @FXML
    private void mostrarSgte(ActionEvent event) {
        if (imagenes != null && !imagenes.isEmpty()) {
            if (!imageIterator.hasNext()) {
                imageIterator = imagenes.listIterator();
            }
            setImagen(imageIterator.next());
        }
    }

    @FXML
    private void agregarImagen(ActionEvent event) {
    }

    @FXML
    private void guardarEdicion(ActionEvent event) {
    }

    @FXML
    private void cancelarEdicion(ActionEvent event) {
        if (!this.editable) {
            Utilitario.abrirNuevaVentana("main", "Bienvenido!");
            this.cerrarVentana();
            
        } else {
            Utilitario.abrirNuevaVentana("misAutos", "Mis autos");
            this.cerrarVentana();
        }
    }
    
    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnAdelante.getScene().getWindow();
        ventanaActual.close();
    }

    @FXML
    private void agregarServicio(ActionEvent event) {
    }

    @FXML
    private void editarServicio(ActionEvent event) {
    }

    @FXML
    private void eliminarServicio(ActionEvent event) {
    }

    @FXML
    private void agregarReparacion(ActionEvent event) {
    }

    @FXML
    private void editarReparacion(ActionEvent event) {
    }

    @FXML
    private void eliminarReparacion(ActionEvent event) {
    }
}
