package co.edu.uniquindio.poo.sistemahospitaluq.model;

import co.edu.uniquindio.poo.sistemahospitaluq.utils.Notificador;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario{

    public Administrador(String cedula, String nombre, String correo, String telefono) {
        super(cedula, nombre, correo, telefono);
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }

    // ----------------------------
    // GESTIÓN DE PACIENTES Y MÉDICOS
    // ----------------------------

    public void eliminarPaciente(Hospital hospital, String cedula) {
        if (hospital == null || cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para eliminar paciente");
        }
        Paciente paciente = hospital.buscarPacientePorCedula(cedula);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
        hospital.getPacientes().remove(paciente);
        Notificador.enviarNotificacion(getNombre(), "Eliminaste al paciente con cédula: " + cedula);
    }

    public void eliminarMedico(Hospital hospital, String cedula) {
        if (hospital == null || cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para eliminar médico");
        }
        Medico medico = hospital.buscarMedicoPorCedula(cedula);
        if (medico == null) {
            throw new IllegalArgumentException("Médico no encontrado");
        }
        hospital.getMedicos().remove(medico);
        Notificador.enviarNotificacion(getNombre(), "Eliminaste al médico con cédula: " + cedula);
    }
    public void modificarPaciente(Hospital hospital, String cedula, String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        if (hospital == null || cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para modificar paciente");
        }
        Paciente paciente = hospital.buscarPacientePorCedula(cedula);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
        paciente.actualizarDatos(nuevoNombre,nuevoCorreo, nuevoTelefono);
    }
    public void modificarMedico(Hospital hospital, String cedula, String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        if (hospital == null || cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para modificar médico");
        }
        Medico medico = hospital.buscarMedicoPorCedula(cedula);
        if (medico == null) {
            throw new IllegalArgumentException("Médico no encontrado");
        }
        medico.actualizarDatos(nuevoNombre, nuevoCorreo, nuevoTelefono);
    }


    // ----------------------------
    // GESTIÓN DE SALAS
    // ----------------------------

    public void agregarSala(Hospital hospital, Sala sala) {
        if (hospital == null || sala == null) {
            throw new IllegalArgumentException("El hospital o la sala no pueden ser null");
        }
        hospital.registrarSala(sala);
        Notificador.enviarNotificacion(getNombre(), "Agregaste una nueva sala: " + sala.getId());
    }

    public void eliminarSala(Hospital hospital, String idSala) {
        if (hospital == null || idSala == null || idSala.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para eliminar sala");
        }
        Sala sala = hospital.buscarSalaPorId(idSala);
        if (sala == null) {
            throw new IllegalArgumentException("Sala no encontrada");
        }
        hospital.getSalas().remove(sala);
        Notificador.enviarNotificacion(getNombre(), "Eliminaste la sala con ID: " + idSala);
    }

    public void actualizarSala(Sala sala, int nuevaCapacidad, boolean disponible) {
        if (sala == null) {
            throw new IllegalArgumentException("Sala inválida");
        }
        sala.setCapacidad(nuevaCapacidad);
        sala.setDisponible(disponible);
        Notificador.enviarNotificacion(getNombre(), "Actualizaste la sala " + sala.getId() +
                " con capacidad: " + nuevaCapacidad + " y disponibilidad: " + (disponible ? "Sí" : "No"));
    }

    // ----------------------------
    // MONITOREO Y REPORTES
    // ----------------------------

    public List<Medico> obtenerMedicosDisponibles(Hospital hospital, String horarioDeseado) {
        if (hospital == null || horarioDeseado == null || horarioDeseado.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para buscar disponibilidad");
        }

        List<Medico> disponibles = new ArrayList<>();
        for (Medico medico : hospital.getMedicos()) {
            if (medico.getHorarios().contains(horarioDeseado)) {
                disponibles.add(medico);
            }
        }
        return disponibles;
    }

    public void asignarPacienteAMedico(Medico medico, String cedulaPaciente) {
        if (medico == null || cedulaPaciente == null || cedulaPaciente.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para asignar paciente");
        }
        medico.asignarPaciente(cedulaPaciente);
        Notificador.enviarNotificacion(getNombre(), "Asignaste al paciente con cédula " +
                cedulaPaciente + " al médico " + medico.getNombre());
    }

    public int contarCitasPorMedico(Hospital hospital, String cedulaMedico) {
        if (hospital == null || cedulaMedico == null || cedulaMedico.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para contar citas");
        }

        int count = 0;
        for (CitaMedica cita : hospital.getCitas()) {
            if (cedulaMedico.equals(cita.getCedulaMedico())) {
                count++;
            }
        }
        return count;
    }

    public List<Sala> obtenerSalasOcupadas(Hospital hospital) {
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital inválido");
        }

        List<Sala> ocupadas = new ArrayList<>();
        for (Sala sala : hospital.getSalas()) {
            if (!sala.isDisponible()) {
                ocupadas.add(sala);
            }
        }
        return ocupadas;
    }

    public String generarResumen(Hospital hospital) {
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital inválido");
        }

        return "Resumen del Hospital '" + hospital.getNombre() + "':\n" +
                "Pacientes registrados: " + hospital.getPacientes().size() + "\n" +
                "Médicos registrados: " + hospital.getMedicos().size() + "\n" +
                "Administradores: " + hospital.getAdministradores().size() + "\n" +
                "Citas registradas: " + hospital.getCitas().size() + "\n" +
                "Salas activas: " + hospital.getSalas().size();
    }
}
