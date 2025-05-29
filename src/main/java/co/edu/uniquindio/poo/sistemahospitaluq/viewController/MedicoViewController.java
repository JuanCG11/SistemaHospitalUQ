package co.edu.uniquindio.poo.sistemahospitaluq.viewController;


import co.edu.uniquindio.poo.sistemahospitaluq.controller.HistorialController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.HistorialMedico;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class MedicoViewController {
    private Hospital hospital;

    @FXML
    private TextField txtCedulaPaciente;
    @FXML
    private TextArea areaHistorial;
    @FXML
    private TextArea txtDiagnostico;

    private HistorialController historialController;

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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
        String cedulaMedico = "MED001"; // O lo que uses para identificar al médico

        if (descripcion == null || descripcion.isBlank()) {
            areaHistorial.setText("La descripción no puede estar vacía.");
            return;
        }

        // Crear entrada
        HistorialMedico entrada = new HistorialMedico(LocalDate.now(), descripcion, cedulaMedico);

        // Verificamos si el historial existe, si no, se crea automáticamente

        if (!hospital.existeHistorial(cedulaPaciente)) {
            hospital.crearHistorial(cedulaPaciente);
        }

        boolean exito = hospital.agregarEntradaHistorial(cedulaPaciente, entrada);

        if (exito) {
            txtDiagnostico.clear();
            onVerHistorial(); // recargar historial
        } else {
            areaHistorial.setText("No se pudo registrar el diagnóstico.");
        }
    }
    private void mostrarAlerta() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText("Datos incompletos");
        alerta.showAndWait();
    }

}