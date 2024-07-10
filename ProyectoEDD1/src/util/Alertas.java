/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 *
 * @author Michelle
 */
public class Alertas {
    /*
    public static void alertaConfirmacion(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.CONFIRMATION);        
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }*/
    
    public static void alertaAdvertencia(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.WARNING);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void alertaError(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.ERROR);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void alertaInfo(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.INFORMATION);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void alertaConfirmacion(String title, String mensaje, String headerText){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.CONFIRMATION);        
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setHeaderText(headerText);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void alertaAdvertencia(String title, String mensaje, String headerText){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.WARNING);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.setHeaderText(headerText);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void alertaError(String title, String mensaje, String headerText){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.ERROR);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.setHeaderText(headerText);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void alertaInfo(String title, String mensaje, String headerText){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.INFORMATION);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.setHeaderText(headerText);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static boolean alertaConfirmacion(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
}
