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

    //Obtiene el historial médico de un paciente.

    public List<HistorialMedico> obtenerHistorialDePaciente(String cedulaPaciente) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedulaPaciente);
        if (paciente != null) {
            return new ArrayList<>(paciente.getHistorial());
        }
        return new ArrayList<>();
    }

    //Obtiene el historial médico registrado por un médico.

    public List<HistorialMedico> obtenerHistorialDeMedico(String cedulaMedico) {
        Medico medico = hospital.buscarMedicoPorCedula(cedulaMedico);
        if (medico != null) {
            return new ArrayList<>(medico.getRegistros());
        }
        return new ArrayList<>();
    }
}
