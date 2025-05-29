package co.edu.uniquindio.poo.sistemahospitaluq.test;

import co.edu.uniquindio.poo.sistemahospitaluq.controller.HistorialController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.HistorialMedico;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistorialControllerTest {

    private Hospital hospital;
    private HistorialController historialController;

    @BeforeEach
    public void setUp() {
        hospital = new Hospital("Hospital UQ");
        historialController = new HistorialController(hospital);
        hospital.registrarPaciente(new Paciente("123", "Paciente", "correo@correo.com", "123"));
    }

    @Test
    public void crearHistorialSiNoExiste() {
        assertFalse(hospital.existeHistorial("123"), "Debe iniciar sin historial");
        historialController.crearHistorialSiNoExiste("123");
        assertTrue(hospital.existeHistorial("123"), "Después de crear, debe existir el historial");
    }

    @Test
    public void agregarEntradaAlHistorial() {
        HistorialMedico entrada = new HistorialMedico(LocalDate.now(), "Fiebre alta", "M001");

        historialController.crearHistorialSiNoExiste("123");
        historialController.agregarEntrada("123", entrada);

        List<HistorialMedico> historial = historialController.obtenerHistorialDePaciente("123");

        assertEquals(1, historial.size(), "Debe haber una entrada");
        assertEquals("Fiebre alta", historial.get(0).descripcion(), "La descripción debe coincidir");
    }
}
