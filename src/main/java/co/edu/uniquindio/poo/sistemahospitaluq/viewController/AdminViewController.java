package co.edu.uniquindio.poo.sistemahospitaluq.viewController;

import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import javafx.fxml.FXML;

public class AdminViewController {

    private Hospital hospital;

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @FXML
    private void onGestionarPacientes() {
        // app.openPacienteView(); // si querés navegar directamente
        System.out.println("Abriendo gestión de pacientes...");
    }

    @FXML
    private void onGestionarMedicos() {
        System.out.println("Abriendo gestión de médicos...");
    }

    @FXML
    private void onGestionarSalas() {
        System.out.println("Abriendo gestión de salas...");
    }

    @FXML
    private void onVerReportes() {
        System.out.println("Mostrando reportes...");
    }
}
