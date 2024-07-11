package controller;

//import java.awt.Color;
import TDA.ArrayList;
import TDA.DoubleCircleLinkedList;
import TDA.List;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Historial;
import modelo.Motor;
import modelo.Placa;
import modelo.Reparacion;
import modelo.Servicio;
import modelo.Transmision;
import modelo.Ubicacion;
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

    //Esta variable es verdadera sí y solo sí se accedió a registro mediante la ventana del login
    private boolean isInicioSesion;

    private Usuario usuario;

    public void initialize() {
        usuario = UsuarioDataManager.getInstance().getUsuarioActual();
        inicializarValores();

        usrUsado.selectedProperty().addListener((observable, oldValue, newValue) -> {
            setUsadoFieldsEnabled(newValue);
        });

        setUsadoFieldsEnabled(false);

        isInicioSesion = false;
        tipoServ = new ArrayList<>();
        tipoRep = new ArrayList<>();
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
            //String tipoCosto = ""+ usrTipoCosto.getValue();
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
            //Color color = usrColor.getValue();
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

                Reparacion reparaciones = new Reparacion(historialReparaciones, fechaRep);
                Servicio servicios = new Servicio(historialServicios, fechaServ);

                tipoServ.addFirst(servicios);
                tipoRep.addFirst(reparaciones);
                //fotosList.addLast(fotoNombre);
                //(Usuario dueno, TipoCosto tipoCosto, String marca, String modelo, int año, int kilometraje, double precio, Motor motor, Transmision transmision, Ubicacion ubicacion, Historial historial, TipoTraccion traccion, TipoDireccion direccion, String color, boolean climatizado, int numHilera, int numPuerta, String foto, Placa placa) {
                VehiculoUsado nuevoVehiculoUsado = new VehiculoUsado(usuario, tipoCosto, marca, modelo, anio, kilometraje, precio, new Motor(tipoMotor, cilindraje), new Transmision(tipoTransmision, velocidades), new Ubicacion(ciudad, direccion),new Historial(tipoServ, tipoRep), tipoTraccion, tipoDireccion, color, climatizado, nHileras, nPuertas, fotoNombre, new Placa(ultimoDigito, provincia));
                //Vehiculo nuevoVehiculoUsado = new VehiculoUsado(usuario, marca, modelo, anio, kilometraje, precio, tipoCosto);

                VehiculoDataManager.getInstance().agregarVehiculo(nuevoVehiculoUsado);

            } else {
                //Vehiculo nuevoVehiculo = new Vehiculo(marca, modelo, anio, kilometraje, new Motor(new TipoMotor(tipoMotor), cilindraje), new Transmision(new TipoTransmision(tipoTransmicion), velocidades), new Ubicacion(ciudad, direccion), foto, new TipoTraccion(tipoTraccion), tipoDireccion, color, climatizado, nHileras, nPuertas, precio, new TipoCosto(tipoCosto));
                //Vehiculo nuevoVehiculo = new VehiculoNuevo(usuario, marca, modelo, anio, kilometraje, precio, tipoCosto);
                Vehiculo nuevoVehiculo = new VehiculoNuevo(usuario, tipoCosto, marca, modelo, anio, kilometraje, precio, new Motor(tipoMotor, cilindraje), new Transmision(tipoTransmision, velocidades), new Ubicacion(ciudad, direccion), tipoTraccion, tipoDireccion, color, climatizado, nHileras, nPuertas, fotoNombre);
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

}
