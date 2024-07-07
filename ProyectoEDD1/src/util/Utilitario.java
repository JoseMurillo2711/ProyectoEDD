/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import proyectoedd1.ProyectoEDD1;
import static util.CONSTANTES.CHARACTERS;
import static util.CONSTANTES.ID_LENGTH;

/**
 *
 * @author Michelle
 */
public class Utilitario {

    private static final Random RANDOM = new SecureRandom();

    public static void formatoAlfanumerico(TextField textField) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String nuevoTexto = change.getControlNewText();
            if (nuevoTexto.matches("[a-zA-Z0-9]*")) {
                return change;
            } else {
                return null;
            }
        });
        textField.setTextFormatter(textFormatter);
    }

    public static String generateUniqueId() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
    
    public static FXMLLoader abrirNuevaVentana(String vista, String titulo){        
        try {            
            FXMLLoader loader = new FXMLLoader(ProyectoEDD1.class.getResource("/vista/" + vista + ".fxml"));
            Parent root = loader.load();                   
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();  
            return loader;
        } catch (IOException ex) {
            Alertas.alertaError("Ha ocurrido un error", ex.getMessage());            
            return null;
        }
    }
}
