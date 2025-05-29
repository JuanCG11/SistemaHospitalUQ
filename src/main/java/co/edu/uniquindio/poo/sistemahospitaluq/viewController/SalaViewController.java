package co.edu.uniquindio.poo.sistemahospitaluq.viewController;

import co.edu.uniquindio.poo.sistemahospitaluq.controller.SalaController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Especialidad;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Sala;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SalaViewController {

    @FXML private TextField txtIdSala;
    @FXML private TableView<Sala> tblSalas;
    @FXML private TableColumn<Sala, String> colIdSala;
    @FXML private TableColumn<Sala, String> colDisponible;

    private Hospital hospital;
    private SalaController salaController;
    private final ObservableList<Sala> listaSalas = FXCollections.observableArrayList();

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        this.salaController = new SalaController(hospital);
        cargarSalas();
    }

    private void cargarSalas() {
        listaSalas.setAll(hospital.getSalas());

        colIdSala.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colDisponible.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().isDisponible() ? "Sí" : "No"));

        tblSalas.setItems(listaSalas);
    }

    @FXML
    private void onAgregarSala() {
        String id = txtIdSala.getText().trim();

        if (id.isEmpty()) {
            mostrarAlerta("Ingrese el ID de la sala.");
            return;
        }

        Sala nuevaSala = new Sala(
                id,
                "Sala " + id,
                10,
                Especialidad.MEDICINA_GENERAL,
                "Piso 1"
        );

        if (salaController.registrarSala(nuevaSala)) {
            cargarSalas();
            txtIdSala.clear();

        } else {
            mostrarAlerta("No se pudo registrar la sala. Puede que ya exista.");
        }
    }

    @FXML
    private void onEliminarSala() {
        Sala seleccionada = tblSalas.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            salaController.eliminarSala(seleccionada.getId());
            cargarSalas();
        } else {
            mostrarAlerta("Seleccione una sala para eliminar.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
