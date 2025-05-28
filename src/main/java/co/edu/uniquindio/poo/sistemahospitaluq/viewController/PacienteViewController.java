package co.edu.uniquindio.poo.sistemahospitaluq.viewController;

import co.edu.uniquindio.poo.sistemahospitaluq.controller.UsuarioController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class PacienteViewController {
    @FXML private TextField txtCedula;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;

    @FXML private TableView<Paciente> tblPacientes;
    @FXML private TableColumn<Paciente, String> colCedula;
    @FXML private TableColumn<Paciente, String> colNombre;
    @FXML private TableColumn<Paciente, String> colCorreo;
    @FXML private TableColumn<Paciente, String> colTelefono;

    private final ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();
    private UsuarioController usuarioController;
    private Paciente pacienteSeleccionado;

    public void setHospital(Hospital hospital) {
        this.usuarioController = new UsuarioController(hospital);
        cargarPacientes();
    }

    @FXML
    public void initialize() {
        colCedula.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCedula()));
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colCorreo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCorreo()));
        colTelefono.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTelefono()));

        tblPacientes.setItems(listaPacientes);

        tblPacientes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            pacienteSeleccionado = newVal;
            mostrarDatos(pacienteSeleccionado);
        });
    }

    private void mostrarDatos(Paciente p) {
        if (p != null) {
            txtCedula.setText(p.getCedula());
            txtNombre.setText(p.getNombre());
            txtCorreo.setText(p.getCorreo());
            txtTelefono.setText(p.getTelefono());
        }
    }

    private void cargarPacientes() {
        listaPacientes.setAll(usuarioController.obtenerTodosLosPacientes());
    }

    private Paciente crearDesdeFormulario() {
        return new Paciente(
                txtCedula.getText(),
                txtNombre.getText(),
                txtCorreo.getText(),
                txtTelefono.getText()
        );
    }

    @FXML
    private void onAgregarPaciente() {
        Paciente nuevo = crearDesdeFormulario();
        if (usuarioController.registrarPaciente(nuevo)) {
            listaPacientes.add(nuevo);
            limpiar();
        }
    }

    @FXML
    private void onActualizarPaciente() {
        if (pacienteSeleccionado != null) {
            usuarioController.modificarPaciente(
                    pacienteSeleccionado.getCedula(),
                    txtNombre.getText(),
                    txtCorreo.getText(),
                    txtTelefono.getText()

            );
            cargarPacientes();
            limpiar();
        }
    }

    @FXML
    private void onEliminarPaciente() {
        if (pacienteSeleccionado != null) {
            usuarioController.eliminarPaciente(pacienteSeleccionado.getCedula());
            listaPacientes.remove(pacienteSeleccionado);
            limpiar();
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
        tblPacientes.getSelectionModel().clearSelection();
        pacienteSeleccionado = null;
    }
}
