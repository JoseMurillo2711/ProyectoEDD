/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import TDA.List;
import controller.MostrarInfoController;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Vehiculo;
import modelo.VehiculoNuevo;
import modelo.VehiculoUsado;
import proyectoedd1.ProyectoEDD1;
import static util.CONSTANTES.CHARACTERS;
import static util.CONSTANTES.ID_LENGTH;
import static util.CONSTANTES.IMAGEN_NOT_FOUND;
import static util.CONSTANTES.RUTA_IMAGEN_CARROS;
import static util.CONSTANTES.RUTA_IMAGEN_LOGO;

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

    public static void formatoNumerico(TextField textField) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String nuevoTexto = change.getControlNewText();
            if (nuevoTexto.matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        });
        textField.setTextFormatter(textFormatter);
    }

    public static void formatoNumericoDecimal(TextField textField) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String nuevoTexto = change.getControlNewText();
            if (nuevoTexto.matches("\\d*|\\d+\\.\\d*")) {
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

    public static FXMLLoader abrirNuevaVentana(String vista, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(ProyectoEDD1.class.getResource("/vista/" + vista + ".fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            if (vista.equalsIgnoreCase("main")) {
                stage.setOnCloseRequest(event -> {
                    Platform.exit();
                    System.exit(0);
                });
            }
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
            return loader;
        } catch (IOException ex) {
            Alertas.alertaError("Ha ocurrido un error", ex.getMessage());
            return null;
        }
    }

    public static FlowPane panelVehiculos(List<Vehiculo> vehiculos) {
        FlowPane panel = new FlowPane();
        for (Vehiculo ve : vehiculos) {
            VBox carta = createCard(ve);
            carta.setOnMouseClicked((MouseEvent event) -> {
                mostrarInformacion(ve);
            });
            panel.getChildren().add(carta);
        }
        return panel;
    }

    private static void mostrarInformacion(Vehiculo vehiculo) {
        FXMLLoader loader = abrirNuevaVentana("mostrarInfo", "Informacion del vehiculo");
        MostrarInfoController info = loader.getController();
        info.recibirVehiculo(vehiculo, false);
    }

    public static VBox createCard(Vehiculo vehiculo) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
        card.setId("card");

        Button favorite = new Button(vehiculo.getStar());
        Label marcaLabel = new Label(vehiculo.getMarca() + " " + vehiculo.getModelo());
        Label anoLabel = new Label("Año: " + vehiculo.getAño());
        Label precioLbl = new Label("Precio: $: " + vehiculo.getPrecio());
        String tipo = vehiculo instanceof VehiculoUsado ? "Usado - " : "Nuevo - ";
        Label ngociable = new Label(tipo + vehiculo.getTipoCosto().toString());
        ngociable.setId("lblPrecio");
        ImageView imageView = new ImageView();
        File archivo = new File(IMAGEN_NOT_FOUND);
        favorite.setOnAction(event -> {
            vehiculo.onchangeFavorite();
            favorite.setText(vehiculo.getStar());
        });
        if (!vehiculo.getUrl_fotos().isEmpty()) {
            String imageUrl = vehiculo.getUrl_fotos().getFirst();
            try {
                archivo = new File(RUTA_IMAGEN_CARROS + imageUrl);
                Image image = new Image(archivo.toURI().toString(), true);
                imageView.setImage(image);
            } catch (Exception e) {
                imageView.setImage(new Image(archivo.toURI().toString()));
            }
        } else {
            imageView.setImage(new Image(archivo.toURI().toString()));
        }

        imageView.setFitWidth(85);
        imageView.setFitHeight(75);
        imageView.setPreserveRatio(true);
        if (UsuarioDataManager.getInstance().getUsuarioActual() != null) {
            card.getChildren().addAll(imageView, favorite, marcaLabel, anoLabel, precioLbl, ngociable);
        } else {
            card.getChildren().addAll(imageView, marcaLabel, anoLabel, precioLbl, ngociable);
        }
        card.setOnMouseClicked(e->{mostrarInformacion(vehiculo);});
        return card;
    }

    public static VBox createCardMarca(Vehiculo vehiculo) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
        card.setId("card");
        Label marcaLabel = new Label(vehiculo.getMarca());
        marcaLabel.setId("lblPrecio");
        ImageView imageView = new ImageView();
        File archivo;
        String baseImageUrl = vehiculo.getMarca().strip().toLowerCase();
        String imageUrlJpg = baseImageUrl + ".jpg";
        String imageUrlPng = baseImageUrl + ".png";

        try {
            archivo = new File(RUTA_IMAGEN_LOGO + imageUrlJpg);
            Image image = new Image(archivo.toURI().toString(), true);
            imageView.setImage(image);
        } catch (Exception e1) {
            try {
                archivo = new File(RUTA_IMAGEN_LOGO + imageUrlPng);
                Image image = new Image(archivo.toURI().toString(), true);
                imageView.setImage(image);
            } catch (Exception e2) {
                imageView.setImage(new Image(new File(IMAGEN_NOT_FOUND).toURI().toString()));
            }
        }

        imageView.setFitWidth(110);
        imageView.setFitHeight(90);
        imageView.setPreserveRatio(true);
        card.getChildren().addAll(imageView, marcaLabel);
        card.setAlignment(Pos.CENTER);
        return card;
    }

    public static HBox createCardMios(Vehiculo vehiculo) {
        VBox card = new VBox(10);
        HBox info = new HBox(10);
        info.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
        info.setId("card");

        Label marcaLabel = new Label(vehiculo.getMarca() + " " + vehiculo.getModelo());
        Label anoLabel = new Label("Año: " + vehiculo.getAño());
        Label precioLbl = new Label("Precio: $: " + vehiculo.getPrecio());
        String antes = vehiculo instanceof VehiculoNuevo ? "Nuevo - " : "Usado - ";
        String valor = antes + "Precio " + vehiculo.getTipoCosto().toString();
        Label ngociable = new Label(valor);
        ngociable.setMaxWidth(Double.MAX_VALUE);
        ngociable.setId("lblPrecio");
        ImageView imageView = new ImageView();
        File archivo = new File(IMAGEN_NOT_FOUND);
        if (!vehiculo.getUrl_fotos().isEmpty()) {
            String imageUrl = vehiculo.getUrl_fotos().getFirst();
            try {
                archivo = new File(RUTA_IMAGEN_CARROS + imageUrl);
                Image image = new Image(archivo.toURI().toString(), true);
                imageView.setImage(image);
            } catch (Exception e) {
                imageView.setImage(new Image(archivo.toURI().toString()));
            }
        } else {
            imageView.setImage(new Image(archivo.toURI().toString()));
        }

        imageView.setFitWidth(85);
        imageView.setFitHeight(75);
        imageView.setPreserveRatio(true);
        card.getChildren().addAll(marcaLabel, anoLabel, precioLbl, ngociable);
        info.getChildren().addAll(imageView, card);
        return info;
    }
    
    public static void configureDatePicker(DatePicker datePicker) {
        datePicker.setDayCellFactory((final DatePicker datePicker1) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        datePicker.setValue(LocalDate.now());
    }
}
