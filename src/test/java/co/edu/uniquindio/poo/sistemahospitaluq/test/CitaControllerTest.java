package co.edu.uniquindio.poo.sistemahospitaluq.test;

import co.edu.uniquindio.poo.sistemahospitaluq.controller.CitaController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Especialidad;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Medico;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Paciente;
import co.edu.uniquindio.poo.sistemahospitaluq.utils.Notificador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CitaControllerTest {

    private Hospital hospital;
    private CitaController citaController;
    private Medico medico;

    @BeforeEach
    public void setUp() {
        // Desactivar notificaciones gráficas para pruebas
        Notificador.setManejadorNotificacion((destinatario, mensaje) -> {
            System.out.printf("Mock Notificación -> %s: %s%n", destinatario, mensaje);
        });

        hospital = new Hospital("UQ");
        citaController = new CitaController(hospital);

        medico = new Medico("M001", "Dr. Strange", "strange@correo.com", "123", Especialidad.MEDICINA_GENERAL);
        medico.agregarHorario("lunes 08:00-12:00");
        hospital.registrarMedico(medico);
        hospital.registrarPaciente(new Paciente("P001", "Paciente 1", "p1@mail.com", "555"));
    }

    @AfterEach
    public void tearDown() {
        // Restaurar notificador original (JavaFX) para ejecución normal de la app
        Notificador.restaurarManejadorOriginal();
    }

    @Test
    public void registrarCitaDentroDelHorario() {
        LocalDateTime fecha = LocalDateTime.of(2025, 6, 2, 9, 0); // lunes
        boolean resultado = citaController.agendarCita("C001", "P001", "M001", fecha);
        assertTrue(resultado);
    }

    @Test
    public void registrarCitaFueraDelHorario() {
        LocalDateTime fecha = LocalDateTime.of(2025, 6, 2, 13, 0); // lunes 1pm
        boolean resultado = citaController.agendarCita("C001", "P001", "M001", fecha);
        assertFalse(resultado);
    }

    @Test
    public void cancelarCitaCorrectamente() {
        LocalDateTime fecha = LocalDateTime.of(2025, 6, 2, 9, 0);
        citaController.agendarCita("C001", "P001", "M001", fecha);
        boolean cancelada = citaController.cancelarCita("C001");
        assertTrue(cancelada);
    }
}

