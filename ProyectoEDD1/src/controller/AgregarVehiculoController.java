package controller;

//import java.awt.Color;
import TDA.DoubleCircleLinkedList;
import javafx.scene.paint.Color;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Reparacion;
import modelo.Servicio;
import modelo.Usuario;
import modelo.Vehiculo;
import modelo.VehiculoNuevo;
import modelo.VehiculoUsado;
import tipo.TipoCosto;
import tipo.TipoDireccion;
import tipo.TipoMotor;
import tipo.TipoTraccion;
import tipo.TipoTransmision;
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
    private MenuButton usrTipoMotor;
    @FXML
    private TextField usrCilindraje;
    @FXML
    private MenuButton usrTipoTransmicion;
    @FXML
    private TextField usrVelocidades;
    @FXML
    private TextField usrCiudad;
    @FXML
    private TextField udrDireccion;
    @FXML
    private MenuButton usrTipoTraccion;
    @FXML
    private MenuButton usrTipoDireccion;
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

    private DoubleCircleLinkedList<Servicio> tipoServ;
    private DoubleCircleLinkedList<Reparacion> tipoRep;
    private DoubleCircleLinkedList<String> fotosList;

    private Usuario usuario;

    public void initialize() {
        usuario = UsuarioDataManager.getInstance().getUsuarioActual();
        initializeMenuButton(usrTipoMotor, TipoMotor.values());
        initializeMenuButton(usrTipoTransmicion, TipoTransmision.values());
        initializeMenuButton(usrTipoTraccion, TipoTraccion.values());
        initializeMenuButton(usrTipoDireccion, TipoDireccion.values());
        inicializarValores();

        usrUsado.selectedProperty().addListener((observable, oldValue, newValue) -> {
            setUsadoFieldsEnabled(newValue);
        });

        setUsadoFieldsEnabled(false);
    }

    private <T extends Enum<T>> void initializeMenuButton(MenuButton menuButton, T[] values) {
        for (T value : values) {
            MenuItem item = new MenuItem(value.name());
            item.setOnAction(event -> menuButton.setText(value.name()));
            menuButton.getItems().add(item);
        }
    }

    private void inicializarValores() {
        for (TipoCosto tp : TipoCosto.values()) {
            this.usrTipoCosto.getItems().addAll(tp);
        }
        usrTipoCosto.setValue(TipoCosto.FIJO);
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

            String marca = usrMarca.getText();
            String modelo = usrModelo.getText();
            int anio = Integer.parseInt(usranio.getText());
            int kilometraje = Integer.parseInt(usrKilometraje.getText());
            String tipoMotor = usrTipoMotor.getText();
            int cilindraje = Integer.parseInt(usrCilindraje.getText());
            String tipoTransmicion = usrTipoTransmicion.getText();
            int velocidades = Integer.parseInt(usrVelocidades.getText());
            String ciudad = usrCiudad.getText();
            String direccion = udrDireccion.getText();
            String fotoNombre = usrFotoNombre.getText();
            String tipoTraccion = usrTipoTraccion.getText();
            String tipoDireccion = usrTipoDireccion.getText();
            Color color = usrColor.getValue();
            boolean climatizado = usrClimatizado.isSelected();
            int nHileras = Integer.parseInt(usrNHileras.getText());
            int nPuertas = Integer.parseInt(usrNPuertas.getText());
            double precio = Double.parseDouble(usrPrecio.getText());
            TipoCosto tipoCosto = usrTipoCosto.getValue();
            boolean usado = usrUsado.isSelected();

            if (usado) {
                int ultimoDigito = Integer.parseInt(usrUlDigito.getText());
                String provincia = usrProvincia.getText();
                String historialReparaciones = usrTipoRep.getText();
                String historialServicios = usrTipoServ.getText();
                Date fechaRep = java.sql.Date.valueOf(usrFechaRep.getValue());
                Date fechaServ = java.sql.Date.valueOf(usrFechaServ.getValue());

                Reparacion reparaciones = new Reparacion(historialReparaciones, fechaRep);
                Servicio servicios = new Servicio(historialServicios, fechaServ);

                tipoServ.addFirst(servicios);
                tipoRep.addFirst(reparaciones);
                fotosList.addLast(fotoNombre);

                //VehiculoUsado nuevoVehiculo = new VehiculoUsado(marca, modelo, anio, kilometraje, precio, new Motor(new TipoMotor(tipoMotor), cilindraje), new Transmision(new TipoTransmision(tipoTransmicion), velocidades), new Ubicacion(ciudad, direccion), new Historial(tipoServ, tipoRep), fotosList, new TipoTraccion(tipoTraccion), new TipoDireccion(tipoDireccion), color, climatizado, nHileras, nPuertas, new TipoCosto(tipoCosto), new Placa(ultimoDigito, provincia), new Usuario());
                Vehiculo nuevoVehiculoUsado = new VehiculoUsado(usuario, marca, modelo, anio, kilometraje, precio, tipoCosto);
                //System.out.println("Vehículo usado creado: " + nuevoVehiculo);
                VehiculoDataManager.getInstance().agregarVehiculo(nuevoVehiculoUsado);

            } else {
                //Vehiculo nuevoVehiculo = new Vehiculo(marca, modelo, anio, kilometraje, new Motor(new TipoMotor(tipoMotor), cilindraje), new Transmision(new TipoTransmision(tipoTransmicion), velocidades), new Ubicacion(ciudad, direccion), foto, new TipoTraccion(tipoTraccion), tipoDireccion, color, climatizado, nHileras, nPuertas, precio, new TipoCosto(tipoCosto));
                Vehiculo nuevoVehiculo = new VehiculoNuevo(usuario, marca, modelo, anio, kilometraje, precio, tipoCosto);
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

}
