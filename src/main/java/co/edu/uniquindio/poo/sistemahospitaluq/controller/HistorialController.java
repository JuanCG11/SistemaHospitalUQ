package co.edu.uniquindio.poo.sistemahospitaluq.controller;

import co.edu.uniquindio.poo.sistemahospitaluq.model.HistorialMedico;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Medico;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Paciente;


import java.util.ArrayList;
import java.util.List;

public class HistorialController {

    private final Hospital hospital;

    public HistorialController(Hospital hospital) {
        this.hospital = hospital;
    }

    public void crearHistorialSiNoExiste(String cedulaPaciente) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedulaPaciente);
        if (paciente != null && paciente.getHistorial().isEmpty()) {
            // Ya está inicializado en el paciente, no se necesita más
        }
    }

    public void agregarEntrada(String cedulaPaciente, HistorialMedico entrada) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedulaPaciente);
        if (paciente != null) {
            paciente.getHistorial().add(entrada);
        }
    }

    public List<HistorialMedico> obtenerHistorialDePaciente(String cedulaPaciente) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedulaPaciente);
        if (paciente != null) {
            return new ArrayList<>(paciente.getHistorial());
        }
        return new ArrayList<>();
    }

    public List<HistorialMedico> obtenerHistorialDeMedico(String cedulaMedico) {
        Medico medico = hospital.buscarMedicoPorCedula(cedulaMedico);
        if (medico != null) {
            return new ArrayList<>(medico.getRegistros());
        }
        return new ArrayList<>();
    }
}
