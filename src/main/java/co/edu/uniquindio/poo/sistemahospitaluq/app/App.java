package co.edu.uniquindio.poo.sistemahospitaluq.app;

import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.viewController.*;
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
            controller.setApp(this);
            controller.setHospital(hospital);


            Stage stage = new Stage(); // O reutilizás primaryStage si lo guardás
            stage.setTitle("Gestión de Pacientes");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al abrir pacienteView: " + e.getMessage());

        }
    }
    public void openMedicoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/medicoView.fxml"));
            Scene scene = new Scene(loader.load());

            MedicoViewController controller = loader.getController();
            controller.setHospital(hospital);
            controller.setApp(this);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Panel del Médico");
            stage.show();

        } catch (Exception e) {
            System.err.println("Error al abrir medicoView: " + e.getMessage());
        }
    }

    public void openAdminView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/adminView.fxml"));
            Scene scene = new Scene(loader.load());

            AdminViewController controller = loader.getController();
            controller.setHospital(hospital);
            controller.setApp(this);

            Stage stage = new Stage();
            stage.setTitle("Panel del Administrador");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al abrir adminView: " + e.getMessage());
        }
    }
    public void openCitaView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/citaView.fxml"));
            Scene scene = new Scene(loader.load());

            CitaViewController controller = loader.getController();
            controller.setHospital(hospital);

            Stage stage = new Stage();
            stage.setTitle("Gestión de Citas");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al abrir citaView: " + e.getMessage());
        }
    }
    public void openSalaView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/salaView.fxml"));
            Scene scene = new Scene(loader.load());

            SalaViewController controller = loader.getController();
            controller.setHospital(hospital);

            Stage stage = new Stage();
            stage.setTitle("Gestión de Salas");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al abrir salaView: " + e.getMessage());
        }
    }
    public void openReportesView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/reporteView.fxml"));
            Scene scene = new Scene(loader.load());

            ReporteViewController controller = loader.getController();
            controller.setHospital(hospital);

            Stage stage = new Stage();
            stage.setTitle("Reportes del Hospital");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al abrir reportesView: " + e.getMessage());
        }
    }
    public void openHistorialPacienteView(String cedulaPaciente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/historialPacienteView.fxml"));
            Scene scene = new Scene(loader.load());

            HistorialPacienteViewController controller = loader.getController();
            controller.setHospital(hospital);
            controller.setCedulaPaciente(cedulaPaciente);

            Stage stage = new Stage();
            stage.setTitle("Historial Médico");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al abrir historialPacienteView: " + e.getMessage());
        }
    }
    public void openCrudMedicoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemahospitaluq/crudMedicoView.fxml"));
            Scene scene = new Scene(loader.load());

            CrudMedicoViewController controller = loader.getController();
            controller.setHospital(hospital);

            Stage stage = new Stage();
            stage.setTitle("Gestión de Médicos");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al abrir medicoView: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
