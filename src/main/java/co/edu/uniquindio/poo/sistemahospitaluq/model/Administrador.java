package co.edu.uniquindio.poo.sistemahospitaluq.model;

public class Administrador extends Usuario{

    public Administrador(String cedula, String nombre, String correo, String telefono) {
        super(cedula, nombre, correo, telefono);
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }

    public void agregarSala(Hospital hospital, Sala sala) {
        if (hospital == null) throw new IllegalArgumentException("El hospital no puede ser null");
        if (sala == null) throw new IllegalArgumentException("La sala no puede ser null");
        hospital.registrarSala(sala);
    }

    public void eliminarSala(Hospital hospital, String idSala) {
        Sala sala = hospital.buscarSalaPorId(idSala);
        if (sala != null) {
            hospital.getSalas().remove(sala);
        } else {
            throw new IllegalArgumentException("No se encontró una sala con ese ID");
        }
    }

    public String generarResumen(Hospital hospital) {
        return "Resumen del Hospital '" + hospital.getNombre() + "':\n" +
                "Pacientes registrados: " + hospital.getPacientes().size() + "\n" +
                "Médicos registrados: " + hospital.getMedicos().size() + "\n" +
                "Administradores: " + hospital.getAdministradores().size() + "\n" +
                "Citas registradas: " + hospital.getCitas().size() + "\n" +
                "Salas activas: " + hospital.getSalas().size();
    }
}
