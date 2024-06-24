/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author Michelle
 */
public class Utilitario {
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
}
