package co.edu.uniquindio.poo.sistemahospitaluq.test;

import co.edu.uniquindio.poo.sistemahospitaluq.controller.UsuarioController;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Paciente;
import co.edu.uniquindio.poo.sistemahospitaluq.utils.Notificador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioControllerTest {
    private Hospital hospital;
    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        // Sobrescribe el notificador para evitar uso de JavaFX
        Notificador.setManejadorNotificacion((destinatario, mensaje) -> {
            System.out.printf("Mock Notificación -> %s: %s%n", destinatario, mensaje);
        });

        hospital = new Hospital("Hospital UQ");
        usuarioController = new UsuarioController(hospital);
    }

    @AfterEach
    public void tearDown() {
        // Restaura el comportamiento original para la app real
        Notificador.restaurarManejadorOriginal();
    }

    @Test
    public void registrarPacienteExitoso() {
        Paciente paciente = new Paciente("123", "Juan", "juan@correo.com", "1234567");
        boolean resultado = usuarioController.registrarPaciente(paciente);
        assertTrue(resultado);
        assertEquals(1, hospital.getPacientes().size());
    }

    @Test
    public void modificarPacienteCorrectamente() {
        Paciente paciente = new Paciente("123", "Juan", "juan@correo.com", "1234567");
        usuarioController.registrarPaciente(paciente);

        usuarioController.modificarPaciente("123", "Pedro", "pedro@gmail.com", "7654321");
        Paciente modificado = hospital.buscarPacientePorCedula("123");

        assertEquals("Pedro", modificado.getNombre());
    }

    @Test
    public void eliminarPacienteCorrectamente() {
        Paciente paciente = new Paciente("123", "Juan", "juan@correo.com", "1234567");
        usuarioController.registrarPaciente(paciente);

        boolean resultado = usuarioController.eliminarPaciente("123");
        assertTrue(resultado);
        assertNull(hospital.buscarPacientePorCedula("123"));
    }
}


