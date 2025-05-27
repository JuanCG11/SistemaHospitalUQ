package co.edu.uniquindio.poo.sistemahospitaluq.model;

import java.util.ArrayList;
import java.util.Collection;

public class Hospital {

    private String nombre;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<Administrador> administradores;
    private ArrayList<CitaMedica>citas;
    private ArrayList<Sala> salas;

    public Hospital(String nombre) {
        this.nombre = nombre;
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.citas = new ArrayList<>();

    }
    // Registro de usuarios
    public void registrarPaciente(Paciente paciente) {
        if (paciente == null) throw new IllegalArgumentException("El paciente no puede ser null");
        if (buscarPacientePorCedula(paciente.getCedula()) != null) {
            throw new IllegalArgumentException("Ya existe un paciente con esa cédula");
        }
        pacientes.add(paciente);
    }


    public void registrarMedico(Medico medico) {
        if (medico == null) throw new IllegalArgumentException("El médico no puede ser null");
        if (buscarMedicoPorCedula(medico.getCedula()) != null) {
            throw new IllegalArgumentException("Ya existe un médico con esa cédula");
        }
        medicos.add(medico);
    }

    public void registrarAdministrador(Administrador admin) {
        if (admin == null) throw new IllegalArgumentException("El administrador no puede ser null");
        administradores.add(admin);
    }

    // Gestión de citas
    public void registrarCita(CitaMedica cita) {
        citas.add(cita);
    }
    public Paciente buscarPacientePorCedula(String cedula) {
        return pacientes.stream().filter(p -> p.getCedula().equals(cedula)).findFirst().orElse(null);
    }

    public Medico buscarMedicoPorCedula(String cedula) {
        return medicos.stream().filter(m -> m.getCedula().equals(cedula)).findFirst().orElse(null);
    }

    // Gestión de salas
    public void registrarSala(Sala sala) {
        if (sala == null) throw new IllegalArgumentException("La sala no puede ser null");
        if (buscarSalaPorId(sala.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una sala con ese ID");
        }
        salas.add(sala);
    }

    public Sala buscarSalaPorId(String id) {
        for (Sala sala : salas) {
            if (sala.getId().equals(id)) {
                return sala;
            }
        }
        return null;
    }

    public ArrayList<Sala> getSalasDisponibles() {
        ArrayList<Sala> disponibles = new ArrayList<>();
        for (Sala sala : salas) {
            if (sala.isDisponible()) {
                disponibles.add(sala);
            }
        }
        return disponibles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(ArrayList<Medico> medicos) {
        this.medicos = medicos;
    }

    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(ArrayList<Administrador> administradores) {
        this.administradores = administradores;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }

    public ArrayList<CitaMedica> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<CitaMedica> citas) {
        this.citas = citas;
    }
}
