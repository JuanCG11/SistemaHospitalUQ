package co.edu.uniquindio.poo.sistemahospitaluq.viewController;

import co.edu.uniquindio.poo.sistemahospitaluq.controller.UsuarioController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Especialidad;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Medico;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CrudMedicoViewController {

    @FXML private TextField txtCedula;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;

    @FXML private TableView<Medico> tblMedicos;
    @FXML private TableColumn<Medico, String> colCedula;
    @FXML private TableColumn<Medico, String> colNombre;
    @FXML private TableColumn<Medico, String> colCorreo;
    @FXML private TableColumn<Medico, String> colEspecialidad;

    private Hospital hospital;
    private UsuarioController usuarioController;
    private ObservableList<Medico> listaMedicos = FXCollections.observableArrayList();
    private Medico medicoSeleccionado;

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        this.usuarioController = new UsuarioController(hospital);
        inicializar();
    }

    private void inicializar() {
        cmbEspecialidad.setItems(FXCollections.observableArrayList(Especialidad.values()));

        colCedula.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCedula()));
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colCorreo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCorreo()));
        colEspecialidad.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEspecialidad().toString()));

        tblMedicos.setItems(listaMedicos);
        listaMedicos.setAll(hospital.getMedicos());

        tblMedicos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            medicoSeleccionado = newVal;
            if (newVal != null) mostrarDatos(newVal);
        });
    }

    private void mostrarDatos(Medico m) {
        txtCedula.setText(m.getCedula());
        txtNombre.setText(m.getNombre());
        txtCorreo.setText(m.getCorreo());
        txtTelefono.setText(m.getTelefono());
        cmbEspecialidad.setValue(m.getEspecialidad());
    }

    @FXML
    private void onAgregar() {
        try {
            Medico nuevo = new Medico(
                    txtCedula.getText(),
                    txtNombre.getText(),
                    txtCorreo.getText(),
                    txtTelefono.getText(),
                    cmbEspecialidad.getValue()
            );
            if (usuarioController.registrarMedico(nuevo)) {
                listaMedicos.add(nuevo);
                limpiar();
            } else {
                mostrarAlerta("No se pudo registrar el médico. ¿Ya existe?");
            }
        } catch (Exception e) {
            mostrarAlerta("Datos inválidos: " + e.getMessage());
        }
    }

    @FXML
    private void onEliminar() {
        if (medicoSeleccionado != null) {
            usuarioController.eliminarMedico(medicoSeleccionado.getCedula());
            listaMedicos.remove(medicoSeleccionado);
            limpiar();
        } else {
            mostrarAlerta("Selecciona un médico para eliminar.");
        }
    }

    @FXML
    private void onLimpiar() {
        limpiar();
    }

    private void limpiar() {
        txtCedula.clear();
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        cmbEspecialidad.setValue(null);
        tblMedicos.getSelectionModel().clearSelection();
        medicoSeleccionado = null;
    }

    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
