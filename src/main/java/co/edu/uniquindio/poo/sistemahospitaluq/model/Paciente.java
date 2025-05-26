package co.edu.uniquindio.poo.sistemahospitaluq.model;

import java.util.ArrayList;

public class Paciente extends Usuario {
    private ArrayList<HistorialMedico> historialMedico;
    private ArrayList<CitaMedica> citasProgramadas;

    public Paciente(String id, String nombre, String correo, String telefono) {
        super(id, nombre, correo, telefono);
        this.historialMedico = new ArrayList<>();
        this.citasProgramadas = new ArrayList<>();
    }

    @Override
    public String getTipoUsuario() {
        return "Paciente";
    }

    public ArrayList<CitaMedica> getCitasProgramadas() {
        return citasProgramadas;
    }

    public void setCitasProgramadas(ArrayList<CitaMedica> citasProgramadas) {
        this.citasProgramadas = citasProgramadas;
    }

    public ArrayList<HistorialMedico> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(ArrayList<HistorialMedico> historialMedico) {
        this.historialMedico = historialMedico;
    }

    public void agregarDiagnostico(String diagnostico) {
        historialMedico.add(diagnostico);
    }

    public void agendarCita(String cita) {
        citasProgramadas.add(cita);
    }

    public void cancelarCita(String cita) {
        citasProgramadas.remove(cita);
    }

}