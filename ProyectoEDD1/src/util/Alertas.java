/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javafx.scene.control.Alert;

/**
 *
 * @author Michelle
 */
public class Alertas {
    public static void alertaConfirmacion(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.CONFIRMATION);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
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
}
