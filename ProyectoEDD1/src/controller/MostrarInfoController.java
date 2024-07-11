package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
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
import tipo.*;
import util.Alertas;
import static util.CONSTANTES.IMAGEN_NOT_FOUND;
import static util.CONSTANTES.RUTA_IMAGEN_CARROS;
import util.Utilitario;
import static util.Utilitario.abrirNuevaVentana;
import static util.Utilitario.formatoNumerico;
import static util.Utilitario.formatoNumericoDecimal;
import util.VehiculoDataManager;
import TDA.ArrayList;
import TDA.DoubleCircleLinkedList;
import javafx.fxml.FXMLLoader;

public class MostrarInfoController implements Initializable {

    private Vehiculo vehiculo;
    private boolean editable;
    private ListIterator<String> imageIterator;
    private DoubleCircleLinkedList<String> listaImagenes;
    private ArrayList<String> imagenesOriginales;
    private ArrayList<String> imagenesAEliminar;
    private ArrayList<String> imagenesAAgregar;

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
    @FXML
    private VBox vbImagenesEdicion;
    @FXML
    private Button btnEliminar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modificarFormto();
        txtUsuario.setEditable(false);
        txtUsuario.setDisable(true);
        chkNuevo.setDisable(true);
        txtKm.setDisable(true);
        listaImagenes = new DoubleCircleLinkedList<>();
        imagenesAEliminar = new ArrayList<>();
        imagenesAAgregar = new ArrayList<>();
    }

    public void recibirVehiculo(Vehiculo vehiculo, boolean editable) {
        this.vehiculo = vehiculo;
        System.out.println(this.vehiculo);
        this.editable = editable;

        if (vehiculo != null && vehiculo.getFotos() != null) {
            imagenesOriginales = new ArrayList<>();
            for (String foto : vehiculo.getFotos()) {
                imagenesOriginales.addLast(foto);
            }
        } else {
            imagenesOriginales = new ArrayList<>();
        }

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
        vbImagenesEdicion.setVisible(false);
        vbImagenesEdicion.setManaged(false);
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

            this.llenarTablas();
        } else {
            tableServicios.getItems().clear();
            tablaReparacion.getItems().clear();
        }
    }

    private void mostrarImagenes() {
        File archivo = new File(IMAGEN_NOT_FOUND);
        if (vehiculo == null || vehiculo.getFotos() == null || vehiculo.getFotos().isEmpty()) {
            imgVehiculo.setImage(new Image(archivo.toURI().toString(), true));
            btnEliminar.setVisible(false);
            return;
        }
        listaImagenes = vehiculo.getFotos();
        imageIterator = listaImagenes.listIterator();
        btnEliminar.setVisible(true);

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
        if (listaImagenes != null && !listaImagenes.isEmpty()) {
            if (!imageIterator.hasPrevious()) {
                imageIterator = listaImagenes.listIterator(listaImagenes.size());
            }
            if (imageIterator.hasPrevious()) {
                setImagen(imageIterator.previous());
            }
        }
    }

    @FXML
    private void mostrarSgte(ActionEvent event) {
        if (listaImagenes != null && !listaImagenes.isEmpty()) {
            if (!imageIterator.hasNext()) {
                imageIterator = listaImagenes.listIterator();
            }
            if (imageIterator.hasNext()) {
                setImagen(imageIterator.next());
            }
        }
    }

    @FXML
    private void agregarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.jpeg"));
        Stage stage = (Stage) btnAgregarImagen.getScene().getWindow();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            String nombreArchivo = archivoSeleccionado.getName();
            File destino = new File(RUTA_IMAGEN_CARROS + nombreArchivo);

            int i = 1;
            while (destino.exists()) {
                String nuevoNombre = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.')) + i + nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
                destino = new File(RUTA_IMAGEN_CARROS + nuevoNombre);
                i++;
            }

            try {
                Files.copy(archivoSeleccionado.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                listaImagenes.addLast(destino.getName());
                imagenesAAgregar.addLast(destino.getName());
                imageIterator = listaImagenes.listIterator(listaImagenes.size() - 1);
                setImagen(destino.getName());
                Alertas.alertaInfo("Imagen agregada", "La imagen ha sido agregada exitosamente.");
            } catch (IOException e) {
                Alertas.alertaError("Error", "No se pudo guardar la imagen", "Error: " + e.getMessage());
            }
        }
    }

    @FXML
    private void guardarEdicion(ActionEvent event) {
        modificarVehiculo();
        try {
            VehiculoDataManager.getInstance().editarVehiculo(this.vehiculo);

            for (String imagen : imagenesAEliminar) {
                File archivo = new File(RUTA_IMAGEN_CARROS + imagen);
                if (archivo.exists()) {
                    archivo.delete();
                }
            }

            abrirNuevaVentana("misAutos", "Mis autos");
            this.cerrarVentana();
        } catch (Exception ex) {
            Alertas.alertaError("Error", "No se pudo editar el vehiculo", "Error en edicion: " + ex.getCause());
        }
    }

    @FXML
    private void cancelarEdicion(ActionEvent event) {
        listaImagenes.clear();
        listaImagenes.addAll(imagenesOriginales);

        for (String imagen : imagenesAAgregar) {
            File archivo = new File(RUTA_IMAGEN_CARROS + imagen);
            if (archivo.exists()) {
                archivo.delete();
            }
        }
        imagenesAEliminar.clear();
        imagenesAAgregar.clear();

        mostrarImagenes();

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
        FXMLLoader serv = abrirNuevaVentana("nuevoServicio","Servicios");
        NuevoServicioController nuevoSer = serv.getController();
        nuevoSer.reciribirParametros(this, "agregar");
    }

    @FXML
    private void editarServicio(ActionEvent event) {
        FXMLLoader serv = abrirNuevaVentana("nuevoServicio","Servicios");
        NuevoServicioController nuevoSer = serv.getController();
        nuevoSer.reciribirParametros(this, "editar");
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

        this.vehiculo.setFotos(listaImagenes);

        if (vehiculo instanceof VehiculoUsado vehiculoUsado) {
            Placa placa = new Placa(!this.txtPlaca.getText().isEmpty() ? Integer.parseInt(this.txtPlaca.getText().strip()) : 0, this.txtProvincia.getText().strip());
            vehiculoUsado.setPlaca(placa);
        }
    }

    @FXML
    private void eliminarImagen(ActionEvent event) {
        if (listaImagenes != null && !listaImagenes.isEmpty()) {
            if (Alertas.alertaConfirmacion("Eliminar Imagen", "¿Está seguro de eliminar esta imagen?")) {
                String imagenActual;
                if (imageIterator.hasPrevious()) {
                    imagenActual = imageIterator.previous();
                    imageIterator.remove();
                } else {
                    imageIterator = listaImagenes.listIterator();
                    if (imageIterator.hasNext()) {
                        imagenActual = imageIterator.next();
                        imageIterator.remove();
                    } else {
                        return;
                    }
                }

                if (!imagenesAEliminar.contains(imagenActual)) {
                    imagenesAEliminar.addLast(imagenActual);
                }

                if (listaImagenes.isEmpty()) {
                    File archivoN = new File(IMAGEN_NOT_FOUND);
                    imgVehiculo.setImage(new Image(archivoN.toURI().toString()));
                    btnEliminar.setVisible(false);
                } else {
                    if (imageIterator.hasNext()) {
                        setImagen(imageIterator.next());
                    } else if (imageIterator.hasPrevious()) {
                        setImagen(imageIterator.previous());
                    }
                }
                Alertas.alertaInfo("Imagen eliminada", "La imagen ha sido eliminada exitosamente.");
            }
        }
    }

    public void llenarTablas() {
        if (vehiculo instanceof VehiculoUsado vehiculoUsado) {
            Historial historial = vehiculoUsado.getHistorial();
            if (historial != null) {
                if (historial.getServicios() != null && !historial.getServicios().isEmpty()) {
                    ArrayList<Servicio> serviciosObservableList = new ArrayList<>();
                    for (Servicio servicio : historial.getServicios()) {
                        serviciosObservableList.addLast(servicio);
                    }
                    tableServicios.getItems().setAll(serviciosObservableList.toArray(new Servicio[0]));
                } else {
                    tableServicios.getItems().clear();
                }

                if (historial.getReparaciones() != null && !historial.getReparaciones().isEmpty()) {
                    ArrayList<Reparacion> reparacionesObservableList = new ArrayList<>();
                    for (Reparacion reparacion : historial.getReparaciones()) {
                        reparacionesObservableList.addLast(reparacion);
                    }
                    tablaReparacion.getItems().setAll(reparacionesObservableList.toArray(new Reparacion[0]));
                } else {
                    tablaReparacion.getItems().clear();
                }
            } else {
                tableServicios.getItems().clear();
                tablaReparacion.getItems().clear();
            }
        }

    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
