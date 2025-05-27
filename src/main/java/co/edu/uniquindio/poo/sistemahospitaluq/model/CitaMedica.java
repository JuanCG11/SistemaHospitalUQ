package co.edu.uniquindio.poo.sistemahospitaluq.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class CitaMedica {
    private String id;
    private String cedulaPaciente;
    private String cedulaMedico;
    private LocalDateTime fechaHora;
    private EstadoCita estado;

    public CitaMedica(String id, String cedulaPaciente, String cedulaMedico, LocalDateTime fechaHora, EstadoCita estado) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID de cita inválido");
        if (cedulaPaciente == null || cedulaPaciente.isBlank()) throw new IllegalArgumentException("Cédula de paciente inválida");
        if (cedulaMedico == null || cedulaMedico.isBlank()) throw new IllegalArgumentException("Cédula de médico inválida");
        if (fechaHora == null) throw new IllegalArgumentException("La fecha no puede ser null");

        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitaMedica cita = (CitaMedica) o;
        return id.equals(cita.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
