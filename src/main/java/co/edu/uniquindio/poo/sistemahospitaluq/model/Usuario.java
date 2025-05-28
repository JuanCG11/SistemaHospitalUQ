package co.edu.uniquindio.poo.sistemahospitaluq.model;

import java.util.Objects;

public abstract  class Usuario {

    protected String cedula;
    protected String nombre;
    protected String correo;
    protected String telefono;

    public Usuario(String cedula, String nombre, String correo, String telefono){
        if (cedula == null || cedula.isBlank()) throw new IllegalArgumentException("La cédula no puede estar vacía");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("El correo no puede estar vacío");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("El teléfono no puede estar vacío");

        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    // getters y setters


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void actualizarDatos(String nombre, String correo, String telefono) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre inválido");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("Correo inválido");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("Teléfono inválido");

        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
    // Igualdad basada en la cédula
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // misma referencia
        if (o == null || getClass() != o.getClass()) return false; // clases distintas
        Usuario usuario = (Usuario) o;
        return cedula.equals(usuario.cedula); // igualdad lógica por cédula
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }

    public abstract String getTipoUsuario();
}
