package co.edu.uniquindio.poo.sistemahospitaluq.model;

import co.edu.uniquindio.poo.sistemahospitaluq.utils.Notificador;

import java.util.ArrayList;

public class Medico extends Usuario implements IGestionHistorial {
    private Especialidad especialidad;
    private ArrayList<String> horarios;
    private ArrayList<String> pacientesAsignados;
    private ArrayList<HistorialMedico> registros;

    public Medico(String cedula, String nombre, String correo, String telefono, Especialidad especialidad) {
        super(cedula, nombre, correo, telefono);
        if (especialidad == null) throw new IllegalArgumentException("La especialidad no puede ser null");

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
        if (horario == null || horario.isBlank()) throw new IllegalArgumentException("El horario no puede estar vacío");
        horarios.add(horario);
    }

    public void asignarPaciente(String pacienteCedula) {
        if (pacienteCedula == null || pacienteCedula.isBlank()) throw new IllegalArgumentException("Cedula de paciente inválido");
        pacientesAsignados.add(pacienteCedula);
        Notificador.enviarNotificacion(getNombre(), "Se le ha asignado un nuevo paciente con cédula: " + pacienteCedula);
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
        if (entrada == null) throw new IllegalArgumentException("El historial no puede ser null");
        registros.add(entrada);
        Notificador.enviarNotificacion(getNombre(), "Registraste una nueva entrada médica.");
    }
}
