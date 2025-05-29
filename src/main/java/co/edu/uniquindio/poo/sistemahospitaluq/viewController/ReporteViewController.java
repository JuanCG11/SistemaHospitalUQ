package co.edu.uniquindio.poo.sistemahospitaluq.viewController;

import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Sala;
import co.edu.uniquindio.poo.sistemahospitaluq.model.EstadoCita;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ReporteViewController {

    @FXML private TextArea txtReporte;

    private Hospital hospital;

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        generarReporte();
    }

    @FXML
    private void onActualizarReporte() {
        generarReporte();
    }

    private void generarReporte() {
        long totalCitas = hospital.getCitas().size();
        long citasActivas = hospital.getCitas().stream()
                .filter(c -> c.getEstado() == EstadoCita.AGENDADA)
                .count();

        long totalPacientes = hospital.getPacientes().size();
        long totalMedicos = hospital.getMedicos().size();

        long salasDisponibles = hospital.getSalas().stream().filter(Sala::isDisponible).count();
        long salasOcupadas = hospital.getSalas().size() - salasDisponibles;

        String reporte = "📊 Reporte General del Hospital:\n\n" +
                "🧑‍⚕️ Total de médicos registrados: " + totalMedicos + "\n" +
                "👥 Total de pacientes registrados: " + totalPacientes + "\n\n" +
                "📅 Citas médicas:\n" +
                "   - Total agendadas: " + totalCitas + "\n" +
                "   - Activas: " + citasActivas + "\n" +
                "   - Canceladas: " + (totalCitas - citasActivas) + "\n\n" +
                "🏥 Salas:\n" +
                "   - Total: " + hospital.getSalas().size() + "\n" +
                "   - Disponibles: " + salasDisponibles + "\n" +
                "   - Ocupadas: " + salasOcupadas + "\n";

        txtReporte.setText(reporte);
    }
}
