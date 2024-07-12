package proyectoedd1;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Usuario;
import modelo.VehiculoNuevo;
import modelo.VehiculoUsado;
import tipo.TipoCosto;
import util.UsuarioDataManager;
import util.VehiculoDataManager;

public class ProyectoEDD1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/main.fxml"));
            Parent root = loader.load();
            // Configurar la escena y el escenario principal
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Mi Aplicación JavaFX");
            primaryStage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });
            primaryStage.show();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        System.out.println(VehiculoDataManager.getInstance().getVehiculos());
        System.out.println(UsuarioDataManager.getInstance().getUsuarios());
        //agregarDatos();

        launch(args);

    }

    private static void agregarDatos() {
        agregarUsuarios();
        UsuarioDataManager dataUser = UsuarioDataManager.getInstance();
        Usuario user1 = dataUser.obtenerUsuario("admin");
        Usuario user2 = dataUser.obtenerUsuario("admi");
        Usuario user3 = dataUser.obtenerUsuario("anckpop");
        VehiculoDataManager vehiculoDataManager = VehiculoDataManager.getInstance();
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "KIA", "Seltos", 2024, 0, 132654.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Toyota", "Corolla", 2023, 15000, 20000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Honda", "Civic", 2023, 0, 22000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Ford", "Mustang", 2022, 5000, 35000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Audi", "Q7", 2024, 0, 55000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Chevrolet", "Camaro", 2021, 8000, 40000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Tesla", "Model 3", 2023, 0, 60000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Hyundai", "Elantra", 2022, 8000, 19000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Nissan", "Altima", 2024, 0, 24000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Mazda", "CX-5", 2023, 15000, 28000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Volkswagen", "Golf", 2023, 0, 23000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Mercedes-Benz", "C-Class", 2021, 10000, 45000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "BMW", "X5", 2022, 0, 50000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Jeep", "Wrangler", 2021, 20000, 35000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Subaru", "Outback", 2022, 0, 32000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Hyundai", "Santa Fe", 2024, 0, 35000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Honda", "Accord", 2021, 12000, 25000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Chevrolet", "Tahoe", 2024, 0, 50000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user2, "Kia", "Sorento", 2022, 10000, 28000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Ford", "Explorer", 2023, 0, 45000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user3, "Nissan", "Pathfinder", 2021, 15000, 30000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Mazda", "MX-5", 2024, 0, 28000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user3, "Subaru", "Impreza", 2023, 8000, 20000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Volkswagen", "Tiguan", 2024, 0, 32000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user3, "Mitsubishi", "Outlander", 2022, 11000, 27000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Lexus", "RX", 2024, 0, 50000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user3, "Acura", "MDX", 2021, 14000, 35000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Genesis", "GV80", 2023, 0, 60000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoUsado(user3, "Infiniti", "QX60", 2022, 12000, 40000.0, TipoCosto.NEGOCIABLE));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Cadillac", "XT6", 2024, 0, 55000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Ford", "Explorer", 2023, 0, 45000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Ford", "Mustang", 2023, 0, 55000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Ford", "F-150", 2023, 0, 40000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Ford", "Edge", 2023, 0, 39000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Ford", "Fusion", 2023, 0, 36000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user2, "Ford", "Escape", 2023, 0, 37000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Ford", "Expedition", 2023, 0, 55000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Ford", "Ranger", 2023, 0, 42000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Ford", "Bronco", 2023, 0, 48000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user3, "Ford", "Maverick", 2023, 0, 35000.0, TipoCosto.FIJO));
        vehiculoDataManager.agregarVehiculo(new VehiculoNuevo(user1, "Ford", "Taurus", 2023, 0, 41000.0, TipoCosto.FIJO));
    }

    private static void agregarUsuarios() {
        try {
            UsuarioDataManager.getInstance().agregarUsuario(new Usuario("anckpop", "123"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            UsuarioDataManager.getInstance().agregarUsuario(new Usuario("admin", "admin"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            UsuarioDataManager.getInstance().agregarUsuario(new Usuario("admi", "admi"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
