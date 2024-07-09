package controller;

import TDA.List;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.*;
import modelo.Vehiculo;
import tipo.*;
import util.Alertas;
import static util.CONSTANTES.IMAGEN_NOT_FOUND;
import static util.CONSTANTES.RUTA_IMAGEN_CARROS;
import util.Utilitario;
import static util.Utilitario.abrirNuevaVentana;
import static util.Utilitario.formatoNumerico;
import static util.Utilitario.formatoNumericoDecimal;
import util.VehiculoDataManager;

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
    private TextField txtNumHileras;
    @FXML
    private TextField txtCilindraje;
    @FXML
    private CheckBox chkNuevo;
    @FXML
    private TextField txtColor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        modificarFormto();
        txtUsuario.setEditable(false);
        txtUsuario.setDisable(true);
        chkNuevo.setDisable(true);
        //txtKm.setEditable(false);
        txtKm.setDisable(true);

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
            chkNuevo.setSelected(true);
        } else {
            chkNuevo.setSelected(false);
            txtKm.setEditable(true);
            txtKm.setDisable(false);
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

    private void modificarFormto() {
        formatoNumerico(txtAnio);
        formatoNumerico(txtKm);
        formatoNumericoDecimal(txtPrecio);
        formatoNumerico(txtNumPuertas);
        formatoNumerico(txtVelo);
        formatoNumerico(txtPlaca);
        formatoNumerico(txtNumHileras);
        formatoNumerico(txtCilindraje);
    }

    private void desactivarEdicion() {
        txtMarca.setEditable(false);
        txtMarca.setDisable(true);

        txtModelo.setEditable(false);
        txtModelo.setDisable(true);

        txtAnio.setEditable(false);
        txtAnio.setDisable(true);

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
        txtKm.setText(String.valueOf(vehiculo.getKilometraje()));
        txtPrecio.setText(String.valueOf(vehiculo.getPrecio()));
        System.out.println("");
        txtNumPuertas.setText(vehiculo.getNumPuerta() != 0 ? String.valueOf(vehiculo.getNumPuerta()) : "");
        txtCiudad.setText(vehiculo.getUbicacion() != null ? vehiculo.getUbicacion().getCiudad() : "");
        txtDireccion.setText(vehiculo.getUbicacion() != null ? vehiculo.getUbicacion().getDireccion() : "");
        txtVelo.setText(String.valueOf(vehiculo.getMotor() != null ? vehiculo.getTransmision().getVelocidades() : 0));
        txtUsuario.setText(vehiculo.getDueno() != null ? vehiculo.getDueno().getNickname() : "");
        cboMotor.getItems().clear();
        for (TipoMotor tm : TipoMotor.values()) {
            cboMotor.getItems().add(tm);
        }
        if (vehiculo.getMotor() != null) {
            cboMotor.setValue(vehiculo.getMotor().getTipo());
            txtCilindraje.setText(String.valueOf(vehiculo.getMotor().getCilindrada()));
        }

        cbTraccion.getItems().clear();
        for (TipoTraccion tt : TipoTraccion.values()) {
            cbTraccion.getItems().add(tt);
        }
        if (vehiculo.getTraccion() != null) {
            cbTraccion.setValue(vehiculo.getTraccion());
        }

        cbDireccion.getItems().clear();
        for (TipoDireccion td : TipoDireccion.values()) {
            cbDireccion.getItems().add(td);
        }
        if (vehiculo.getDireccion() != null) {
            cbDireccion.setValue(vehiculo.getDireccion());
        }

        cbTipoPrecio.getItems().clear();
        for (TipoCosto tp : TipoCosto.values()) {
            cbTipoPrecio.getItems().add(tp);
        }
        if (vehiculo.getTipoCosto() != null) {
            cbTipoPrecio.setValue(vehiculo.getTipoCosto());
        }

        cbTransmision.getItems().clear();
        for (TipoTransmision tt : TipoTransmision.values()) {
            cbTransmision.getItems().add(tt);
        }
        if (vehiculo.getTransmision() != null) {
            cbTransmision.setValue(vehiculo.getTransmision().getTipo());
        }

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        List<File> selectedFiles = (List<File>) fileChooser.showOpenMultipleDialog(imgVehiculo.getScene().getWindow());

        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                try {
                    File dest = new File(RUTA_IMAGEN_CARROS + file.getName());
                    Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    vehiculo.getFotos().addLast(file.getName());
                } catch (Exception e) {
                    Alertas.alertaError("Error", "Error al agregar la imagen", e.getMessage());
                }
            }
            mostrarImagenes();
        }
    }

    @FXML
    private void guardarEdicion(ActionEvent event) {
        modificarVehiculo();
        try {
            VehiculoDataManager.getInstance().editarVehiculo(this.vehiculo);
            abrirNuevaVentana("misAutos", "Mis autos");
            this.cerrarVentana();
        } catch (Exception ex) {
            Alertas.alertaError("Error", "No se pudo editar el vehiculo", "Error en edicion: " + ex.getCause());
        }
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

    private void modificarVehiculo() {
        // Verificar si los campos obligatorios están llenos
        if (txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty() || txtKm.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
            Alertas.alertaError("Error", "Campos obligatorios vacíos", "Por favor, complete los campos de Marca, Modelo, Km y Precio.");
            return;
        }

        this.vehiculo.setMarca(this.txtMarca.getText().strip());
        this.vehiculo.setModelo(this.txtModelo.getText().strip());
        this.vehiculo.setAño(!this.txtAnio.getText().isEmpty() ? Integer.parseInt(this.txtAnio.getText().strip()) : 0);
        this.vehiculo.setKilometraje(Integer.parseInt(this.txtKm.getText().strip()));
        this.vehiculo.setPrecio(Double.parseDouble(this.txtPrecio.getText().strip()));
        this.vehiculo.setNumPuerta(!this.txtNumPuertas.getText().isEmpty() ? Integer.parseInt(this.txtNumPuertas.getText().strip()) : 0);
        this.vehiculo.setUbicacion(new Ubicacion(this.txtCiudad.getText().strip(), this.txtDireccion.getText().strip()));
        this.vehiculo.setTransmision(new Transmision(cbTransmision.getValue(), !this.txtVelo.getText().isEmpty() ? Integer.parseInt(this.txtVelo.getText().strip()) : 0));
        this.vehiculo.setTipoCosto(cbTipoPrecio.getValue());
        this.vehiculo.setMotor(new Motor(this.cboMotor.getValue(), !this.txtCilindraje.getText().isEmpty() ? Integer.parseInt(this.txtCilindraje.getText().strip()) : 0));
        this.vehiculo.setTraccion(cbTraccion.getValue());
        this.vehiculo.setDireccion(cbDireccion.getValue());
        this.vehiculo.setColor(this.txtColor.getText().strip());
        this.vehiculo.setClimatizado(chkClima.isSelected());
        this.vehiculo.setNumHilera(!this.txtNumHileras.getText().isEmpty() ? Integer.parseInt(this.txtNumHileras.getText().strip()) : 0);

        if (vehiculo instanceof VehiculoUsado vehiculoUsado) {
            Placa placa = new Placa(!this.txtPlaca.getText().isEmpty() ? Integer.parseInt(this.txtPlaca.getText().strip()) : 0, this.txtProvincia.getText().strip());
            vehiculoUsado.setPlaca(placa);
        }
    }

}
