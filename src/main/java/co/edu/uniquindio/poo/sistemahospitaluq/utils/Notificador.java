package co.edu.uniquindio.poo.sistemahospitaluq.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

    public class Notificador {

        // Constructor privado para evitar instanciación
        private Notificador() {}

        public static void enviarNotificacion(String destinatario, String mensaje) {

            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notificación");
                alert.setHeaderText("Para: " + destinatario);
                alert.setContentText(mensaje);
                alert.showAndWait();
            });
        }
    }
