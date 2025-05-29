package co.edu.uniquindio.poo.sistemahospitaluq.viewController;

import co.edu.uniquindio.poo.sistemahospitaluq.model.HistorialMedico;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class HistorialPacienteViewController {

    @FXML private TableView<HistorialMedico> tblHistorial;
    @FXML private TableColumn<HistorialMedico, String> colFecha;
    @FXML private TableColumn<HistorialMedico, String> colMedico;
    @FXML private TableColumn<HistorialMedico, String> colDescripcion;

    private Hospital hospital;
    private String cedulaPaciente;

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
        cargarHistorial();
    }

    private void cargarHistorial() {
        List<HistorialMedico> historial = hospital.obtenerHistorialDePaciente(cedulaPaciente);

        if (historial.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Este paciente no tiene historial médico.");
            alert.showAndWait();
        }

        colFecha.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().fecha().toString()));
        colDescripcion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().descripcion()));
        colMedico.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().medico()));

        tblHistorial.setItems(FXCollections.observableArrayList(historial));
    }
}
