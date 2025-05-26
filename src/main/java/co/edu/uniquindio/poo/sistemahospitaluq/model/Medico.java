package co.edu.uniquindio.poo.sistemahospitaluq.model;

import java.util.ArrayList;

public class Medico extends Usuario implements IGestionHistorial {
    private Especialidad especialidad;
    private ArrayList<String> horarios;
    private ArrayList<String> pacientesAsignados;
    private ArrayList<HistorialMedico> registros;

    public Medico(String id, String nombre, String correo, String telefono, Especialidad especialidad) {
        super(id, nombre, correo, telefono);
        this.especialidad = especialidad;
        this.horarios = new ArrayList<>();
        this.pacientesAsignados = new ArrayList<>();
        this.registros = new ArrayList<>();
    }

    @Override
    public String getTipoUsuario() {
        return "Médico";
    }
    public void agregarHorario(String horario) {
        horarios.add(horario);
    }

    public void asignarPaciente(String pacienteId) {
        pacientesAsignados.add(pacienteId);
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<String> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<String> getPacientesAsignados() {
        return pacientesAsignados;
    }

    public void setPacientesAsignados(ArrayList<String> pacientesAsignados) {
        this.pacientesAsignados = pacientesAsignados;
    }

    public ArrayList<HistorialMedico> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<HistorialMedico> registros) {
        this.registros = registros;
    }

    @Override
    public void agregarEntradaHistorial(HistorialMedico entrada) {

    }
}
