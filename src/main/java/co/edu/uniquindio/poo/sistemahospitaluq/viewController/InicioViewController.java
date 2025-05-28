package co.edu.uniquindio.poo.sistemahospitaluq.viewController;


import co.edu.uniquindio.poo.sistemahospitaluq.app.App;
import javafx.fxml.FXML;

public class InicioViewController {

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void onPaciente() {
        app.openPacienteView(); // Método que cambia a la vista de paciente
    }

    @FXML
    private void onMedico() {
        app.openMedicoView();
    }

    @FXML
    private void onAdministrador() {
        app.openAdminView();
    }
}
