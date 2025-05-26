package co.edu.uniquindio.poo.sistemahospitaluq.model;

public class Administrador extends Usuario{

    public Administrador(String id, String nombre, String correo, String telefono) {
        super(id, nombre, correo, telefono);
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }
}
