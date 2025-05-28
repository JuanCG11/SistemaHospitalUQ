package co.edu.uniquindio.poo.sistemahospitaluq.viewController;


import co.edu.uniquindio.poo.sistemahospitaluq.controller.HistorialController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.HistorialMedico;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MedicoViewController {

    @FXML private TextField txtCedulaPaciente;
    @FXML private TextArea areaHistorial;
    @FXML private TextArea txtDiagnostico;

    private HistorialController historialController;

    public void setHospital(Hospital hospital) {
        this.historialController = new HistorialController(hospital);
    }

    @FXML
    private void onVerHistorial() {
        String cedula = txtCedulaPaciente.getText();
        var historial = historialController.obtenerHistorialDePaciente(cedula);
        areaHistorial.clear();
        for (HistorialMedico entrada : historial) {
            areaHistorial.appendText(entrada.toString() + "\n");
        }
    }

    @FXML
    private void onAgregarEntrada() {
        String cedulaPaciente = txtCedulaPaciente.getText();
        String descripcion = txtDiagnostico.getText();
        String cedulaMedico = "MED001"; // temporal o simulado

        if (historialController.agregarEntradaHistorial(cedulaPaciente, cedulaMedico, descripcion)) {
            txtDiagnostico.clear();
            onVerHistorial();
        } else {
            areaHistorial.setText("No se pudo registrar el diagnóstico.");
        }
    }
}