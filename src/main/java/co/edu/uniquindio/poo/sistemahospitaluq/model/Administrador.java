package co.edu.uniquindio.poo.sistemahospitaluq.model;

public class Administrador extends Usuario{

    public Administrador(String id, String nombre, String correo, String telefono) {
        super(id, nombre, correo, telefono);
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }

    public void registrarSala(Hospital hospital, Sala sala) {

        System.out.println("Sala registrada: " + sala.getNombre());
    }

    public void mostrarResumen(Hospital hospital) {
        System.out.println("Resumen del hospital: ");
        System.out.println("Pacientes: " + hospital.getPacientes().size());
        System.out.println("Médicos: " + hospital.getMedicos().size());
        System.out.println("Citas: " + hospital.getCitas().size());
    }
}
