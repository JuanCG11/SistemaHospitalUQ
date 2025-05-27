package co.edu.uniquindio.poo.sistemahospitaluq.controller;

import co.edu.uniquindio.poo.sistemahospitaluq.model.*;
import co.edu.uniquindio.poo.sistemahospitaluq.utils.Notificador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CitaController {
    private final Hospital hospital;

    public CitaController(Hospital hospital) {
        this.hospital = hospital;
    }

    // ------------------------
    // AGENDAR CITA
    // ------------------------

    public boolean agendarCita(String idCita, String cedulaPaciente, String cedulaMedico, LocalDateTime fechaHora) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedulaPaciente);
        Medico medico = hospital.buscarMedicoPorCedula(cedulaMedico);

        if (paciente == null || medico == null || fechaHora == null) {
            return false;
        }

        CitaMedica cita = new CitaMedica(idCita, cedulaPaciente, cedulaMedico, fechaHora);
        hospital.registrarCita(cita);
        paciente.agendarCita(cita);

        Notificador.enviarNotificacion(paciente.getNombre(), "Tu cita fue agendada para " + fechaHora);
        Notificador.enviarNotificacion(medico.getNombre(), "Nueva cita con " + paciente.getNombre() + " el " + fechaHora);

        return true;
    }

    // ------------------------
    // CANCELAR CITA
    // ------------------------

    public boolean cancelarCita(String idCita) {
        CitaMedica cita = buscarCitaPorId(idCita);
        if (cita == null) return false;

        cita.cancelar();

        Paciente paciente = hospital.buscarPacientePorCedula(cita.getCedulaPaciente());
        Medico medico = hospital.buscarMedicoPorCedula(cita.getCedulaMedico());

        if (paciente != null) paciente.cancelarCita(cita);

        assert paciente != null;
        Notificador.enviarNotificacion(paciente.getNombre(), "Tu cita con el Dr. " + medico.getNombre() + " fue cancelada.");
        Notificador.enviarNotificacion(medico.getNombre(), "Se ha cancelado una cita con " + paciente.getNombre());

        return true;
    }

    // ------------------------
    // COMPLETAR CITA
    // ------------------------

    public boolean completarCita(String idCita) {
        CitaMedica cita = buscarCitaPorId(idCita);
        if (cita == null) return false;

        cita.completar();
        return true;
    }

    // ------------------------
    // CONSULTAS
    // ------------------------

    public List<CitaMedica> obtenerCitasPorPaciente(String cedulaPaciente) {
        List<CitaMedica> resultado = new ArrayList<>();
        for (CitaMedica cita : hospital.getCitas()) {
            if (cita.getCedulaPaciente().equals(cedulaPaciente)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    public List<CitaMedica> obtenerCitasPorMedico(String cedulaMedico) {
        List<CitaMedica> resultado = new ArrayList<>();
        for (CitaMedica cita : hospital.getCitas()) {
            if (cita.getCedulaMedico().equals(cedulaMedico)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    public CitaMedica buscarCitaPorId(String idCita) {
        for (CitaMedica cita : hospital.getCitas()) {
            if (cita.getId().equals(idCita)) {
                return cita;
            }
        }
        return null;
    }

    public List<CitaMedica> obtenerTodasLasCitas() {
        return new ArrayList<>(hospital.getCitas());
    }
}
