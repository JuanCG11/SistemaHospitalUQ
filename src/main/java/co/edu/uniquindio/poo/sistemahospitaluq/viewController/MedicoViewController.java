package co.edu.uniquindio.poo.sistemahospitaluq.viewController;


import co.edu.uniquindio.poo.sistemahospitaluq.app.App;
import co.edu.uniquindio.poo.sistemahospitaluq.controller.HistorialController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.HistorialMedico;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class MedicoViewController {
    private Hospital hospital;



    @FXML
    private TextField txtCedulaPaciente;
    @FXML
    private TextArea txtDiagnostico;
    @FXML
    private Label lblEstado;

    private HistorialController historialController;
    private App app;

    public void setApp(App app) {
        this.app = app;
    }


    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        this.historialController = new HistorialController(hospital);
    }

    @FXML
    private void onVerHistorial() {
        String cedula = txtCedulaPaciente.getText();

        if (cedula == null || cedula.isBlank()) {
            mostrarAlerta("Por favor ingrese una cédula válida.");
            return;
        }

        if (!hospital.existeHistorial(cedula)) {
            mostrarAlerta("No hay historial disponible para este paciente.");
            return;
        }

        // Abrir ventana de historial

        app.openHistorialPacienteView(cedula);
    }


    @FXML
    private void onAgregarEntrada() {
        String cedulaPaciente = txtCedulaPaciente.getText();
        String descripcion = txtDiagnostico.getText();
        String cedulaMedico = "medico asignado"; // O lo que uses para identificar al médico

        if (descripcion == null || descripcion.isBlank()) {
            lblEstado.setText("La descripción no puede estar vacía.");
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
            lblEstado.setText("Diagnóstico agregado exitosamente.");
            onVerHistorial(); // recargar historial
        } else {
            lblEstado.setText("No se pudo registrar el diagnóstico.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}