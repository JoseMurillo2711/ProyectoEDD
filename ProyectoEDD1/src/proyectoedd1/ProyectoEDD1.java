package proyectoedd1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProyectoEDD1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();
            
            // Configurar la escena y el escenario principal
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Mi Aplicación JavaFX");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();  // Imprime el stack trace para entender mejor el error
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
