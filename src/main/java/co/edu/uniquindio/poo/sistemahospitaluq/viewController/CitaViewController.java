package co.edu.uniquindio.poo.sistemahospitaluq.viewController;

import co.edu.uniquindio.poo.sistemahospitaluq.controller.CitaController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class CitaViewController {

    @FXML private ComboBox<Paciente> cmbPacientes;
    @FXML private ComboBox<Medico> cmbMedicos;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtHora;
    @FXML private TableColumn<CitaMedica, String> colSala;

    @FXML private TableView<CitaMedica> tblCitas;
    @FXML private TableColumn<CitaMedica, String> colId;
    @FXML private TableColumn<CitaMedica, String> colPaciente;
    @FXML private TableColumn<CitaMedica, String> colMedico;
    @FXML private TableColumn<CitaMedica, String> colFechaHora;
    @FXML private TableColumn<CitaMedica, String> colEstado;
    @FXML private ComboBox<String> cmbSalas;

    private Hospital hospital;
    private CitaController citaController;
    private final ObservableList<CitaMedica> listaCitas = FXCollections.observableArrayList();

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        this.citaController = new CitaController(hospital);
        cargarDatos();
    }

    private void cargarDatos() {
        cmbPacientes.setItems(FXCollections.observableArrayList(hospital.getPacientes()));
        cmbMedicos.setItems(FXCollections.observableArrayList(hospital.getMedicos()));
        cmbSalas.setItems(FXCollections.observableArrayList(
                hospital.obtenerListaSalas().stream().map(Sala::getId).toList()
        ));

        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colPaciente.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCedulaPaciente()));
        colMedico.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCedulaMedico()));
        colFechaHora.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFechaHora().toString()));
        colEstado.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstado().toString()));
        colSala.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIdSala()));

        tblCitas.setItems(listaCitas);
        listaCitas.setAll(hospital.getCitas());
    }

    @FXML
    private void onAgendar() {
        Paciente paciente = cmbPacientes.getValue();
        Medico medico = cmbMedicos.getValue();
        LocalDate fecha = dpFecha.getValue();
        String hora = txtHora.getText();


        if (paciente == null || medico == null || fecha == null || hora.isBlank()) {
            mostrarAlerta("Completa todos los campos.");
            return;
        }

        try {
            LocalTime horaParsed = LocalTime.parse(hora);
            LocalDateTime fechaHora = LocalDateTime.of(fecha, horaParsed);

            String id = UUID.randomUUID().toString();
            String idSala = cmbSalas.getValue();

            if (idSala == null || idSala.isBlank()) {
                mostrarAlerta("Selecciona una sala para la cita.");
                return;
            }

            CitaMedica cita = new CitaMedica(id, paciente.getCedula(), medico.getCedula(), fechaHora, idSala);

            if (citaController.agendarCita(
                    id,
                    paciente.getCedula(),
                    medico.getCedula(),
                    fechaHora,
                    idSala
            )) {
                listaCitas.add(cita);
                limpiar();

            } else {
                mostrarAlerta("No se pudo agendar la cita.");
            }
        } catch (Exception e) {
            mostrarAlerta("Hora inválida. Usa el formato HH:MM.");
        }
    }

    @FXML
    private void onCancelar() {
        CitaMedica seleccionada = tblCitas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            citaController.cancelarCita(seleccionada.getId());
            listaCitas.setAll(hospital.getCitas());
        } else {
            mostrarAlerta("Selecciona una cita para cancelar.");
        }
    }

    private void limpiar() {
        dpFecha.setValue(null);
        txtHora.clear();
        cmbPacientes.setValue(null);
        cmbMedicos.setValue(null);
        cmbSalas.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}