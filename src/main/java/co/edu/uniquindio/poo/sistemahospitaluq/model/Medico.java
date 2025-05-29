package co.edu.uniquindio.poo.sistemahospitaluq.model;

import co.edu.uniquindio.poo.sistemahospitaluq.utils.Notificador;

import java.time.LocalDateTime;
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
    public String toString() {
        return nombre + " (" + cedula + ")";
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
    public boolean puedeAtender(LocalDateTime fechaCita) {
        String diaSemana = fechaCita.getDayOfWeek().toString().toLowerCase(); // ej: monday
        int hora = fechaCita.getHour();
        int minuto = fechaCita.getMinute();
        int minutosTotales = hora * 60 + minuto;

        for (String horario : horarios) {
            // Ejemplo de formato: "lunes 08:00-12:00"
            String[] partes = horario.toLowerCase().split(" ");
            if (partes.length != 2) continue;

            String diaHorario = partes[0]; // lunes
            String rango = partes[1];      // 08:00-12:00

            if (!diaHorario.equals(diaSemana)) continue;

            String[] horas = rango.split("-");
            if (horas.length != 2) continue;

            int inicio = convertirHoraAMinutos(horas[0]);
            int fin = convertirHoraAMinutos(horas[1]);

            if (minutosTotales >= inicio && minutosTotales <= fin) {
                return true; // Está dentro del horario
            }
        }

        return false; // No coincide con ningún horario
    }

    private int convertirHoraAMinutos(String horaStr) {
        String[] partes = horaStr.split(":");
        int h = Integer.parseInt(partes[0]);
        int m = Integer.parseInt(partes[1]);
        return h * 60 + m;
    }

    @Override
    public void agregarEntradaHistorial(HistorialMedico entrada, Hospital hospital, String cedulaPaciente) {
        if (!hospital.existeHistorial(cedulaPaciente)) {
            hospital.crearHistorial(cedulaPaciente);
        }
        hospital.agregarEntradaHistorial(cedulaPaciente, entrada);
    }
}
