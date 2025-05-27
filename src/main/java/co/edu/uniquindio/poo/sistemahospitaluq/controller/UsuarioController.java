package co.edu.uniquindio.poo.sistemahospitaluq.controller;

import co.edu.uniquindio.poo.sistemahospitaluq.model.*;

import java.util.ArrayList;


public class UsuarioController {
    private final Hospital hospital;

    public UsuarioController(Hospital hospital) {
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital no puede ser null");
        }
        this.hospital = hospital;
    }

    // -------------------------
    // REGISTRO DE USUARIOS
    // -------------------------

    public void registrarPaciente(String cedula, String nombre, String correo, String telefono) {
        Paciente paciente = new Paciente(cedula, nombre, correo, telefono);
        hospital.registrarPaciente(paciente);
    }

    public void registrarMedico(String cedula, String nombre, String correo, String telefono, Especialidad especialidad) {
        Medico medico = new Medico(cedula, nombre, correo, telefono, especialidad);
        hospital.registrarMedico(medico);
    }

    public void registrarAdministrador(String cedula, String nombre, String correo, String telefono) {
        Administrador admin = new Administrador(cedula, nombre, correo, telefono);
        hospital.registrarAdministrador(admin);
    }

    // -------------------------
    // MODIFICACIÓN DE USUARIOS
    // -------------------------

    public void modificarPaciente(String cedula, String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedula);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
        paciente.actualizarDatos(nuevoNombre, nuevoCorreo, nuevoTelefono);
    }

    public void modificarMedico(String cedula, String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        Medico medico = hospital.buscarMedicoPorCedula(cedula);
        if (medico == null) {
            throw new IllegalArgumentException("Médico no encontrado");
        }
        medico.actualizarDatos(nuevoNombre, nuevoCorreo, nuevoTelefono);
    }

    // -------------------------
    // ELIMINACIÓN DE USUARIOS
    // -------------------------

    public void eliminarPaciente(String cedula) {
        Paciente paciente = hospital.buscarPacientePorCedula(cedula);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
        hospital.getPacientes().remove(paciente);
    }

    public void eliminarMedico(String cedula) {
        Medico medico = hospital.buscarMedicoPorCedula(cedula);
        if (medico == null) {
            throw new IllegalArgumentException("Médico no encontrado");
        }
        hospital.getMedicos().remove(medico);
    }

    // -------------------------
    // CONSULTAS
    // -------------------------

    public ArrayList<Paciente> obtenerPacientes() {
        return hospital.getPacientes();
    }

    public ArrayList<Medico> obtenerMedicos() {
        return hospital.getMedicos();
    }

    public Paciente buscarPacientePorCedula(String cedula) {
        return hospital.buscarPacientePorCedula(cedula);
    }

    public Medico buscarMedicoPorCedula(String cedula) {
        return hospital.buscarMedicoPorCedula(cedula);
    }
}
