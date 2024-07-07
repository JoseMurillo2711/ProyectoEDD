package proyectoedd1;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
            primaryStage.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(VehiculoDataManager.getInstance().getVehiculos());
        System.out.println(UsuarioDataManager.getInstance().getUsuarios());
        launch(args);      
        
    }
}
