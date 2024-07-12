package controller;

import TDA.DoubleCircleLinkedList;
import TDA.List;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Usuario;
import modelo.Vehiculo;
import util.Alertas;
import static util.CONSTANTES.PER_PAGE;
import util.UsuarioDataManager;
import util.Utilitario;
import static util.Utilitario.abrirNuevaVentana;
import static util.Utilitario.panelVehiculos;
import util.VehiculoDataManager;
import static util.VehiculoDataManager.buscarVehiculos;
import static util.VehiculoDataManager.buscarVehiculosPorMarca;

/**
 * FXML Controller class
 */
public class MainController implements Initializable {
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private BorderPane rootBorderPane;
    @FXML
    private HBox hbIniciarSesion;
    @FXML
    private MenuBar menuUser;
    @FXML
    private HBox hbBusqueda;

    private Usuario usuario;
   private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosBuscados;
    private boolean seccionNuevo;
    private boolean seccionUsado;
    private NuevosController nuevosController;
    private int currentStartIndex;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatoEncabezado();
        vehiculos = VehiculoDataManager.getInstance().getVehiculos();
        seccionNuevo = false;
        seccionUsado = false;
        this.usuario = UsuarioDataManager.getInstance().getUsuarioActual();
        configIniciales();
        cargarPagina("inicio");
    }

    private void limpiarCampos() {
        this.txtBusqueda.setText("");
        seccionNuevo = false;
        seccionUsado = false;
        this.hbBusqueda.setVisible(true);
        this.txtBusqueda.setPromptText("Buscar un auto...");
    }

    private void formatoEncabezado() {
        ImageView lupa = new ImageView(new Image("recursos/lupa.png"));
        lupa.setFitHeight(20);
        lupa.setFitWidth(25);
        this.btnBuscar.setGraphic(lupa);
        Tooltip tltp = new Tooltip("Buscar carro");
        this.btnBuscar.setTooltip(tltp);
    }

    private void configIniciales() {
        if (this.usuario == null) {
            this.menuUser.setVisible(false);
            this.menuUser.setManaged(false);
            this.hbIniciarSesion.setVisible(true);
            this.hbIniciarSesion.setManaged(true);
        } else {
            this.menuUser.getMenus().get(0).setText("Bienvenido/a, " + this.usuario.getNickname() + "!");
            this.menuUser.setVisible(true);
            this.menuUser.setManaged(true);
            this.hbIniciarSesion.setVisible(false);
            this.hbIniciarSesion.setManaged(false);
        }
        this.txtBusqueda.setPromptText("Buscar un auto...");
    }

    @FXML
    private void iniciarSesion(MouseEvent event) {
        mostrarVentanaDeInicioSesion();
    }

    @FXML
    private void registrarse(MouseEvent event) {
        abrirNuevaVentana("registro", "Registro de Usuario");
        cerrarVentana();
    }

    @FXML
    private void mostrarMisAutos(ActionEvent event) {
        Utilitario.abrirNuevaVentana("misAutos", "Autos registrados");
        cerrarVentana();
    }

    @FXML
    private void verFavoritos(ActionEvent event) {
        Utilitario.abrirNuevaVentana("misFavoritos", "Mis autos favoritos");
        cerrarVentana();
    }

    @FXML
    private void mostrarAutosNuevos(ActionEvent event) {
        cargarPagina("vehiculosNuevos");
        this.hbBusqueda.setVisible(false);
        seccionNuevo = true;
        this.txtBusqueda.setPromptText("Buscar un auto nuevo");
    }

    @FXML
    private void mostrarAutosViejos(ActionEvent event) {
        cargarPagina("vehiculosViejos");
        seccionUsado = true;
        this.txtBusqueda.setPromptText("Buscar un auto usado");
    }

    @FXML
    private void agregarAuto(ActionEvent event) {
        if (usuario == null) {
            Alertas.alertaError("No puedes continuar", "Para poder agregar vehiculos debe iniciar sesión");
        } else {
            abrirNuevaVentana("agregarVehiculo", "Agregar vehiculo");
            cerrarVentana();
        }
    }

    @FXML
    private void inicioDePagina(MouseEvent event) {
        cargarPagina("inicio");
        this.txtBusqueda.setPromptText("Buscar un auto...");
    }

    public void cargarPagina(String fxmlFile) {
        limpiarCampos();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/" + fxmlFile + ".fxml"));
            Node pagina = loader.load();
            if (fxmlFile.equals("vehiculosNuevos")) {
                nuevosController = loader.getController();
                nuevosController.setMainController(this);
            }
            rootBorderPane.setCenter(pagina);
        } catch (IOException e) {
            Alertas.alertaError("Ha ocurrido un error", e.getMessage());
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        mostrarVentanaDeInicioSesion();
        UsuarioDataManager.getInstance().cerrarSesion();
        Alertas.alertaInfo("Acabas de cerrar Sesion", "Sesion cerrada correctamente");
    }

    private void cerrarVentana() {
        Stage ventanaActual = (Stage) this.btnBuscar.getScene().getWindow();
        ventanaActual.close();
    }

    private void mostrarVentanaDeInicioSesion() {
        abrirNuevaVentana("login", "Iniciar Sesión");
        cerrarVentana();
    }

    @FXML
    private void buscarAuto(ActionEvent event) {
        if (this.txtBusqueda.getText().strip().isBlank()) {
            Alertas.alertaError("Campo vacio", "No se puede realizar la busqueda porque no ha ingresado los criterios");
        } else {
            if (this.seccionNuevo) {
                vehiculosBuscados = buscarVehiculosPorMarca(this.txtBusqueda.getText().strip() + " NUEVO ", this.nuevosController.getVehiculosPorMarca());                
            } else if (this.seccionUsado) {
                vehiculosBuscados = buscarVehiculos(this.txtBusqueda.getText().strip() + " USADO ");
            } else {
                vehiculosBuscados = buscarVehiculos(this.txtBusqueda.getText().strip());
            }
            currentStartIndex = 0;
            mostrarResultados();
        }
    }

    private void mostrarResultados() {
        if (vehiculosBuscados.isEmpty()) {
            VBox vbox = new VBox();
            Label lbl = new Label("No se encontró ningun resultado =(");
            vbox.getChildren().add(lbl);
            vbox.setAlignment(Pos.CENTER);
            Platform.runLater(() -> this.rootBorderPane.setCenter(vbox));
        } else {
            List<Vehiculo> subList = getSubList(vehiculosBuscados, currentStartIndex, PER_PAGE);
            FlowPane panelAutos = panelVehiculos(subList);
            panelAutos.setId("vehiculosPane");

            VBox vbox = new VBox(panelAutos);
            vbox.setMaxWidth(Double.MAX_VALUE);
            VBox.setVgrow(panelAutos, Priority.ALWAYS);
            vbox.setAlignment(Pos.TOP_CENTER);
            vbox.setSpacing(10);

            if (vehiculosBuscados.size() > PER_PAGE) {
                HBox navigationBox = new HBox();
                navigationBox.setAlignment(Pos.CENTER);
                navigationBox.setSpacing(10);

                Button btnPrevious = new Button("«");
                btnPrevious.setId("boton");
                btnPrevious.setOnAction(e -> previousResults());

                Button btnNext = new Button("»");
                btnNext.setOnAction(e -> nextResults());
                btnNext.setId("boton");

                navigationBox.getChildren().addAll(btnPrevious, btnNext);
                vbox.getChildren().add(navigationBox);
            }

            Platform.runLater(() -> this.rootBorderPane.setCenter(vbox));
        }
    }

    private List<Vehiculo> getSubList(List<Vehiculo> list, int startIndex, int count) {
        DoubleCircleLinkedList<Vehiculo> subList = new DoubleCircleLinkedList<>();
        int size = list.size();

        for (int i = 0; i < count && i < size; i++) {
            int index = (startIndex + i) % size;
            if (!subList.contains(list.get(index))) {
                subList.addLast(list.get(index));
            }
        }

        return subList;
    }

    private void previousResults() {
        currentStartIndex = (currentStartIndex - PER_PAGE + vehiculosBuscados.size()) % vehiculosBuscados.size();
        mostrarResultados();
    }

    private void nextResults() {
        currentStartIndex = (currentStartIndex + PER_PAGE) % vehiculosBuscados.size();
        mostrarResultados();
    }

    @FXML
    private void irPaginaPrincipal(ActionEvent event) {
        cargarPagina("inicio");
        this.txtBusqueda.setPromptText("Buscar un auto...");
    }

    public void showSearchBar() {
        this.hbBusqueda.setVisible(true);
    }

    public void setSeccionNuevo(boolean seccionNuevo) {
        this.seccionNuevo = seccionNuevo;
    }  
}
