package co.edu.uniquindio.poo.sistemahospitaluq.test;

import co.edu.uniquindio.poo.sistemahospitaluq.model.Especialidad;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Medico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicoTest {

    private Medico medico;

    @BeforeEach
    public void setUp() {
        medico = new Medico("M001", "Dr. House", "house@hospital.com", "3210000000", Especialidad.MEDICINA_GENERAL);
        medico.agregarHorario("lunes 08:00-12:00");
        medico.agregarHorario("martes 14:00-18:00");
    }

    @Test
    public void puedeAtenderEnHorarioValido() {
        LocalDateTime cita = LocalDateTime.of(2025, 6, 2, 9, 30); // lunes 9:30
        assertTrue(medico.puedeAtender(cita));
    }

    @Test
    public void noPuedeAtenderFueraDeHorario() {
        LocalDateTime cita = LocalDateTime.of(2025, 6, 2, 13, 0); // lunes 13:00
        assertFalse(medico.puedeAtender(cita));
    }

    @Test
    public void noPuedeAtenderEnDiaNoRegistrado() {
        LocalDateTime cita = LocalDateTime.of(2025, 6, 5, 10, 0); // jueves
        assertFalse(medico.puedeAtender(cita));
    }
}
