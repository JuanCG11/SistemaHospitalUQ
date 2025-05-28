package co.edu.uniquindio.poo.sistemahospitaluq.app;

import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.viewController.InicioViewController;
import co.edu.uniquindio.poo.sistemahospitaluq.viewController.PacienteViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private final Hospital hospital = new Hospital("Hospital UQ");
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/inicioView.fxml"));
        Scene scene = new Scene(loader.load());

        InicioViewController controller = loader.getController();
        controller.setApp(this);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hospital UQ");
        primaryStage.show();
    }

    public void openPacienteView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/pacienteView.fxml"));
            Scene scene = new Scene(loader.load());

            PacienteViewController controller = loader.getController();
            controller.setHospital(hospital);

            Stage stage = new Stage(); // O reutilizás primaryStage si lo guardás
            stage.setTitle("Gestión de Pacientes");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al abrir pacienteView: " + e.getMessage());

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
