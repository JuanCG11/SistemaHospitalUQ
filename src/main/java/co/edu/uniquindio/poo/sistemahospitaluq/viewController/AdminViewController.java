package co.edu.uniquindio.poo.sistemahospitaluq.viewController;

import co.edu.uniquindio.poo.sistemahospitaluq.app.App;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import javafx.fxml.FXML;


public class AdminViewController {
    private App app;
    private Hospital hospital;

    public void setApp(App app) {
        this.app = app;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @FXML
    private void onGestionarPacientes() {
        app.openPacienteView();

    }

    @FXML
    private void onGestionarMedicos() {
        System.out.println("Abriendo gestión de médicos...");
    }

    @FXML
    private void onGestionarSalas() {
        app.openSalaView();
    }

    @FXML
    private void onVerReportes() {
        app.openReportesView();
    }

    @FXML
    private void onGestionarCitas() {
        app.openCitaView();
    }

    public Hospital getHospital() {
        return hospital;
    }
    @FXML
    private void onGestionarCrudMedicos() {
        app.openCrudMedicoView();
    }
}
