package co.edu.uniquindio.poo.sistemahospitaluq.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.function.BiConsumer;

public class Notificador {

    // Interfaz funcional que permite inyectar comportamiento para pruebas
    static BiConsumer<String, String> manejadorNotificacion = Notificador::mostrarAlerta;

    // Constructor privado para evitar instanciación
    private Notificador() {}

    public static void enviarNotificacion(String destinatario, String mensaje) {
        manejadorNotificacion.accept(destinatario, mensaje);
    }

    // Método real que muestra la alerta en JavaFX

    private static void mostrarAlerta(String destinatario, String mensaje) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notificación");
            alert.setHeaderText("Para: " + destinatario);
            alert.setContentText(mensaje);
            alert.showAndWait();
        });
    }

    // Permite sobrescribir el comportamiento en pruebas
    public static void setManejadorNotificacion(BiConsumer<String, String> manejador) {
        manejadorNotificacion = manejador;
    }

    // Permite restaurar el comportamiento original
    public static void restaurarManejadorOriginal() {
        manejadorNotificacion = Notificador::mostrarAlerta;
    }
}