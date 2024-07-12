package controller;

import TDA.ArrayList;
import TDA.DoubleCircleLinkedList;
import TDA.List;
import java.io.File;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.*;
import tipo.*;
import util.Alertas;
import util.UsuarioDataManager;
import static util.Utilitario.abrirNuevaVentana;
import util.VehiculoDataManager;

public class AgregarVehiculoController {

    @FXML
    private TextField usrMarca;
    @FXML
    private TextField usrModelo;
    @FXML
    private TextField usranio;
    @FXML
    private TextField usrKilometraje;
    @FXML
    private ChoiceBox<TipoMotor> usrTipoMotor;
    @FXML
    private TextField usrCilindraje;
    @FXML
    private ChoiceBox<TipoTransmision> usrTipoTransmision;
    @FXML
    private TextField usrVelocidades;
    @FXML
    private TextField usrCiudad;
    @FXML
    private TextField udrDireccion;
    @FXML
    private ChoiceBox<TipoTraccion> usrTipoTraccion;
    @FXML
    private ChoiceBox<TipoDireccion> usrTipoDireccion;
    @FXML
    private ColorPicker usrColor;
    @FXML
    private CheckBox usrClimatizado;
    @FXML
    private TextField usrNHileras;
    @FXML
    private TextField usrNPuertas;
    @FXML
    private TextField usrPrecio;
    @FXML
    private ChoiceBox<TipoCosto> usrTipoCosto;
    @FXML
    private CheckBox usrUsado;
    @FXML
    private TextField usrUlDigito;
    @FXML
    private TextField usrProvincia;
    @FXML
    private DatePicker usrFechaRep;
    @FXML
    private DatePicker usrFechaServ;
    @FXML
    private TextArea usrTipoRep;
    @FXML
    private TextArea usrTipoServ;
    @FXML
    private Button CrearButton;
    @FXML
    private TextField usrFotoNombre;

    private List<Servicio> tipoServ;
    private List<Reparacion> tipoRep;

    private DoubleCircleLinkedList<String> fotosList;

    private boolean isInicioSesion;

    private Usuario usuario;
    @FXML
    private Button btnAgregarImagen;

    public void initialize() {
        usuario = UsuarioDataManager.getInstance().getUsuarioActual();
        inicializarValores();

        usrUsado.selectedProperty().addListener((observable, oldValue, newValue) -> {
            setUsadoFieldsEnabled(newValue);
        });

        usrFotoNombre.setDisable(false);
        setUsadoFieldsEnabled(false);

        isInicioSesion = false;
        tipoServ = new ArrayList<>();
        tipoRep = new ArrayList<>();
        fotosList = new DoubleCircleLinkedList<>();
        
        
    }

    public void parametros(boolean inicioSesion) {
        this.isInicioSesion = inicioSesion;
    }

    private void inicializarValores() {
        for (TipoMotor tm : TipoMotor.values()) {
            this.usrTipoMotor.getItems().addAll(tm);
        }

        for (TipoCosto tp : TipoCosto.values()) {
            this.usrTipoCosto.getItems().addAll(tp);
        }
        usrTipoCosto.setValue(TipoCosto.FIJO);

        for (TipoDireccion td : TipoDireccion.values()) {
            this.usrTipoDireccion.getItems().addAll(td);
        }

        for (TipoTransmision ttn : TipoTransmision.values()) {
            this.usrTipoTransmision.getItems().addAll(ttn);
        }

        for (TipoTraccion tt : TipoTraccion.values()) {
            this.usrTipoTraccion.getItems().addAll(tt);
        }
    }

    private void setUsadoFieldsEnabled(boolean enabled) {
        usrUlDigito.setDisable(!enabled);
        usrProvincia.setDisable(!enabled);
        usrFechaRep.setDisable(!enabled);
        usrFechaServ.setDisable(!enabled);
        usrTipoRep.setDisable(!enabled);
        usrTipoServ.setDisable(!enabled);
    }

    @FXML
    private void crearVehiculo(ActionEvent event) {
        try {
            TipoCosto tipoCosto = usrTipoCosto.getValue();
            String marca = usrMarca.getText();
            String modelo = usrModelo.getText();
            int anio = Integer.parseInt(usranio.getText());
            int kilometraje = Integer.parseInt(usrKilometraje.getText());
            double precio = Double.parseDouble(usrPrecio.getText());
            TipoMotor tipoMotor = usrTipoMotor.getValue();
            int cilindraje = Integer.parseInt(usrCilindraje.getText());
            TipoTransmision tipoTransmision = usrTipoTransmision.getValue();
            int velocidades = Integer.parseInt(usrVelocidades.getText());
            String ciudad = usrCiudad.getText();
            String direccion = udrDireccion.getText();
            String fotoNombre = usrFotoNombre.getText();
            TipoTraccion tipoTraccion = usrTipoTraccion.getValue();
            TipoDireccion tipoDireccion = usrTipoDireccion.getValue();
            String color = "" + usrColor.getValue();
            boolean climatizado = usrClimatizado.isSelected();
            int nHileras = Integer.parseInt(usrNHileras.getText());
            int nPuertas = Integer.parseInt(usrNPuertas.getText());

            boolean usado = usrUsado.isSelected();
            if (usado) {
                int ultimoDigito = Integer.parseInt(usrUlDigito.getText());
                String provincia = usrProvincia.getText();
                String historialReparaciones = usrTipoRep.getText();
                String historialServicios = usrTipoServ.getText();
                LocalDate fechaRep = usrFechaRep.getValue();
                LocalDate fechaServ = usrFechaServ.getValue();

                Reparacion reparaciones = new Reparacion(fechaRep, historialReparaciones);
                Servicio servicios = new Servicio(fechaServ, historialServicios);

                tipoServ.addFirst(servicios);
                tipoRep.addFirst(reparaciones);

                VehiculoUsado nuevoVehiculoUsado = new VehiculoUsado(usuario, tipoCosto, marca, modelo, anio, kilometraje, precio, new Motor(tipoMotor, cilindraje), new Transmision(tipoTransmision, velocidades), new Ubicacion(ciudad, direccion), new Historial(tipoServ, tipoRep), tipoTraccion, tipoDireccion, color, climatizado, nHileras, nPuertas, fotoNombre, new Placa(ultimoDigito, provincia));
                VehiculoDataManager.getInstance().agregarVehiculo(nuevoVehiculoUsado);

            } else {
                VehiculoNuevo nuevoVehiculo = new VehiculoNuevo(usuario, tipoCosto, marca, modelo, anio, kilometraje, precio, new Motor(tipoMotor, cilindraje), new Transmision(tipoTransmision, velocidades), new Ubicacion(ciudad, direccion), tipoTraccion, tipoDireccion, color, climatizado, nHileras, nPuertas, fotoNombre);
                VehiculoDataManager.getInstance().agregarVehiculo(nuevoVehiculo);

            }

            Alertas.alertaInfo("Se ha agregado el vehiculo correctamente", "Vehiculo agregado con exito");
            abrirNuevaVentana("main", "Welcome!");
            cerrarVentana();
        } catch (NumberFormatException e) {
            System.err.println("Error. Revise haber colocado bien los datos." + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al crear el vehículo: " + e.getMessage());
        }
    }

    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.CrearButton.getScene().getWindow();
        ventanaActual.close();
    }

    private void regresar(ActionEvent event) {
        String ruta = "main";
        if (this.isInicioSesion) {
            ruta = "login";
        }
        abrirNuevaVentana("main", "Welcome!");
        cerrarVentana();
    }

    @FXML
    private void agregarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String fotoNombre = selectedFile.getName();
            usrFotoNombre.setText(fotoNombre);
            fotosList.addLast(fotoNombre);
        }
    }
}
