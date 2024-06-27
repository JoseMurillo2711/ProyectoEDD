package controller;

//import java.awt.Color;
import TDA.DoubleCircleLinkedList;
import javafx.scene.paint.Color;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.Foto;
import modelo.Historial;
import modelo.Motor;
import modelo.Placa;
import modelo.Reparacion;
import modelo.Servicio;
import modelo.Transmision;
import modelo.Ubicacion;
import modelo.Usuario;
import modelo.Vehiculo;
import modelo.VehiculoUsado;
import tipo.TipoCosto;
import tipo.TipoDireccion;
import tipo.TipoMotor;
import tipo.TipoTraccion;
import tipo.TipoTransmision;


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
    private MenuButton usrTipoCosto;
    @FXML
    private CheckBox usrUsado;
    @FXML
    private TextField usrUlDigito;
    @FXML
    private TextField usrProvincia;
    @FXML
    private TextField usrDuenio;
    @FXML
    private DatePicker usrFechaRep;
    @FXML
    private DatePicker usrFechaServ;
    @FXML
    private TextArea usrTipoRep;
    @FXML
    private TextArea usrTipoServ;
    @FXML
    private Button RegresarButton;
    @FXML
    private Button CrearButton;
    @FXML
    private TextField usrFotoNombre;
    @FXML
    private TextField usrDescripcionFoto;

    private DoubleCircleLinkedList<Servicio> tipoServ;
    private DoubleCircleLinkedList<Reparacion> tipoRep;
    private DoubleCircleLinkedList<Foto> fotosList;
    
    
    public void initialize() {
        initializeMenuButton(usrTipoMotor, TipoMotor.values());
        initializeMenuButton(usrTipoTransmicion, TipoTransmision.values());
        initializeMenuButton(usrTipoTraccion, TipoTraccion.values());
        initializeMenuButton(usrTipoDireccion, TipoDireccion.values());
        initializeMenuButton(usrTipoCosto, TipoCosto.values());

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
    
    private void setUsadoFieldsEnabled(boolean enabled) {
        usrUlDigito.setDisable(!enabled);
        usrProvincia.setDisable(!enabled);
        usrDuenio.setDisable(!enabled);
        usrFechaRep.setDisable(!enabled);
        usrFechaServ.setDisable(!enabled);
        usrTipoRep.setDisable(!enabled);
        usrTipoServ.setDisable(!enabled);
    }
    
    @FXML
    private void RegresarMain(ActionEvent event) {
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
            String fotoDescripcion = usrDescripcionFoto.getText();
            String tipoTraccion = usrTipoTraccion.getText();
            String tipoDireccion = usrTipoDireccion.getText();
            Color color = usrColor.getValue();
            boolean climatizado = usrClimatizado.isSelected();
            int nHileras = Integer.parseInt(usrNHileras.getText());
            int nPuertas = Integer.parseInt(usrNPuertas.getText());
            double precio = Double.parseDouble(usrPrecio.getText());
            String tipoCosto = usrTipoCosto.getText();
            boolean usado = usrUsado.isSelected();

            if (usado) {
                int ultimoDigito = Integer.parseInt(usrUlDigito.getText());
                String provincia = usrProvincia.getText();
                String duenio = usrDuenio.getText();
                
                String historialReparaciones = usrTipoRep.getText();
                String historialServicios = usrTipoServ.getText();
                Date fechaRep = java.sql.Date.valueOf(usrFechaRep.getValue());
                Date fechaServ = java.sql.Date.valueOf(usrFechaServ.getValue());

                
                Reparacion reparaciones = new Reparacion(historialReparaciones, fechaRep);
                Servicio servicios = new Servicio(historialServicios, fechaServ);
                
                tipoServ.addFirst(servicios);
                tipoRep.addFirst(reparaciones);
                Foto foto = new Foto(fotoNombre, fotoDescripcion);
                fotosList.addLast(foto);
                
                
                VehiculoUsado nuevoVehiculo = new VehiculoUsado(marca, modelo, anio, kilometraje, precio, new Motor(new TipoMotor(tipoMotor), cilindraje), new Transmision(new TipoTransmision(tipoTransmicion), velocidades), new Ubicacion(ciudad, direccion), new Historial(tipoServ, tipoRep), fotosList, new TipoTraccion(tipoTraccion), new TipoDireccion(tipoDireccion), color, climatizado, nHileras, nPuertas, new TipoCosto(tipoCosto), new Placa(ultimoDigito, provincia), new Usuario());
                
                
                System.out.println("Vehículo usado creado: " + nuevoVehiculo);
            } else {
                // Crea un objeto Vehiculo con los datos obtenidos
                Vehiculo nuevoVehiculo = new Vehiculo(marca, modelo, anio, kilometraje, new Motor(new TipoMotor(tipoMotor), cilindraje), new Transmision(new TipoTransmision(tipoTransmicion), velocidades), new Ubicacion(ciudad, direccion), foto, new TipoTraccion(tipoTraccion), tipoDireccion, color, climatizado, nHileras, nPuertas, precio, new TipoCosto(tipoCosto));
                System.out.println("Vehículo nuevo creado: " + nuevoVehiculo);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error. Revise haber colocado bien los datos." + e.getMessage());
        } catch (Exception e) {
            // Manejo de otros posibles errores
            System.err.println("Error al crear el vehículo: " + e.getMessage());
        }
    }


}
