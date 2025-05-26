package co.edu.uniquindio.poo.sistemahospitaluq.model;

import java.time.LocalDateTime;

public class CitaMedica {
    private String cedulaPaciente;
    private String cedulaMedico;
    private LocalDateTime fechaHora;
    private EstadoCita estado;

    public CitaMedica(String cedulaPaciente, String cedulaMedico, LocalDateTime fechaHora, EstadoCita estado) {
        this.cedulaPaciente = cedulaPaciente;
        this.cedulaMedico = cedulaMedico;
        this.fechaHora = fechaHora;
        this.estado = EstadoCita.AGENDADA;
    }

    public void cancelar() {
        this.estado = EstadoCita.CANCELADA;
    }

    public void completar() {
        this.estado = EstadoCita.COMPLETADA;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public String getCedulaMedico() {
        return cedulaMedico;
    }

    public void setCedulaMedico(String cedulaMedico) {
        this.cedulaMedico = cedulaMedico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
}
