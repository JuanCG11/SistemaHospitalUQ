package co.edu.uniquindio.poo.sistemahospitaluq.app;

import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.viewController.PacienteViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private final Hospital hospital = new Hospital("Hospital UQ");

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/pacienteView.fxml"));
        Scene scene = new Scene(loader.load());

        PacienteViewController controller = loader.getController();
        controller.setHospital(hospital);

        stage.setTitle("Gestión de Pacientes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
