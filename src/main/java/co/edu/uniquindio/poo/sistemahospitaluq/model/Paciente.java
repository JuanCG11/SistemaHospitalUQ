package co.edu.uniquindio.poo.sistemahospitaluq.model;

import java.util.ArrayList;

public class Paciente extends Usuario implements IGestionCitas, IGestionHistorial{
    private ArrayList<HistorialMedico> historial;
    private ArrayList<CitaMedica> citas;

    public Paciente(String id, String nombre, String correo, String telefono) {
        super(id, nombre, correo, telefono);
        this.historial = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    @Override
    public String getTipoUsuario() {
        return "Paciente";
    }

    public ArrayList<CitaMedica> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<CitaMedica> citas) {
        this.citas = citas;
    }

    public ArrayList<HistorialMedico> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<HistorialMedico> historial) {
        this.historial = historial;
    }

    @Override
    public void agendarCita(CitaMedica cita) {
        citas.add(cita);
    }

    @Override
    public void cancelarCita(CitaMedica cita) {
        citas.remove(cita);
    }

    @Override
    public void agregarEntradaHistorial(HistorialMedico entrada) {
        historial.add(entrada);
    }

}