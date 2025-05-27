package co.edu.uniquindio.poo.sistemahospitaluq.controller;

import co.edu.uniquindio.poo.sistemahospitaluq.model.*;

import java.util.ArrayList;
import java.util.List;


  //Controlador de lógica para manejar operaciones relacionadas con usuarios del hospital.
  //Aplica sobre pacientes, médicos y administradores.

public class UsuarioController {
    private final Hospital hospital;

    public UsuarioController(Hospital hospital) {
        this.hospital = hospital;
    }

    // ---------------------
    // PACIENTES
    // ---------------------

    public boolean registrarPaciente(Paciente paciente) {
        if (hospital.buscarPacientePorCedula(paciente.getCedula()) != null) {
            return false; // Ya existe
        }
        hospital.registrarPaciente(paciente);
        return true;
    }

    public boolean eliminarPaciente(String cedula) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedula);
        if (paciente != null) {
            return hospital.getPacientes().remove(paciente);
        }
        return false;
    }

    public boolean modificarPaciente(String cedula, String nombre, String correo, String telefono) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedula);
        if (paciente == null) return false;

        paciente.actualizarDatos(nombre, correo, telefono);
        return true;
    }

    public Paciente buscarPaciente(String cedula) {
        return hospital.buscarPacientePorCedula(cedula);
    }

    public List<Paciente> obtenerTodosLosPacientes() {
        return new ArrayList<>(hospital.getPacientes());
    }

    // ---------------------
    // MÉDICOS
    // ---------------------

    public boolean registrarMedico(Medico medico) {
        if (hospital.buscarMedicoPorCedula(medico.getCedula()) != null) {
            return false; // Ya existe
        }
        hospital.registrarMedico(medico);
        return true;
    }

    public boolean eliminarMedico(String cedula) {
        Medico medico = hospital.buscarMedicoPorCedula(cedula);
        if (medico != null) {
            return hospital.getMedicos().remove(medico);
        }
        return false;
    }

    public boolean modificarMedico(String cedula, String nombre, String correo, String telefono) {
        Medico medico = hospital.buscarMedicoPorCedula(cedula);
        if (medico == null) return false;

        medico.actualizarDatos(nombre, correo, telefono);
        return true;
    }

    public Medico buscarMedico(String cedula) {
        return hospital.buscarMedicoPorCedula(cedula);
    }

    public List<Medico> obtenerTodosLosMedicos() {
        return new ArrayList<>(hospital.getMedicos());
    }

    // ---------------------
    // ADMINISTRADORES
    // ---------------------

    public List<Administrador> obtenerAdministradores() {
        return new ArrayList<>(hospital.getAdministradores());
    }
}
