package co.edu.uniquindio.poo.sistemahospitaluq.model;


import co.edu.uniquindio.poo.sistemahospitaluq.utils.Notificador;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Paciente extends Usuario implements IGestionCitas, IGestionHistorial{
    private ArrayList<HistorialMedico> historial;
    private ArrayList<CitaMedica> citas;

    public Paciente(String cedula, String nombre, String correo, String telefono) {
        super(cedula, nombre, correo, telefono);
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
        if (cita == null) throw new IllegalArgumentException("La cita no puede ser null");
        citas.add(cita);

        // Formatear la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaFormateada = cita.getFechaHora().format(formatter);

        Notificador.enviarNotificacion(getNombre(), "Cita agendada para el " + fechaFormateada);
    }

    @Override
    public void cancelarCita(CitaMedica cita) {
        if (cita == null) throw new IllegalArgumentException("La cita no puede ser null");
        citas.remove(cita);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaFormateada = cita.getFechaHora().format(formatter);

        Notificador.enviarNotificacion(getNombre(), "Has cancelado la cita del " + fechaFormateada);
    }

    @Override
    public void agregarEntradaHistorial(HistorialMedico entrada) {
        if (entrada == null) throw new IllegalArgumentException("El historial no puede ser null");
        historial.add(entrada);
        Notificador.enviarNotificacion(getNombre(), "Se agregó una nueva entrada a tu historial médico.");
    }

}