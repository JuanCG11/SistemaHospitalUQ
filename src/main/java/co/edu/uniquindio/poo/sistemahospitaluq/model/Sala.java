package co.edu.uniquindio.poo.sistemahospitaluq.model;


import java.util.Objects;

public class Sala {
    private String id;
    private String nombre;
    private boolean disponible;
    private int capacidad;
    private Especialidad especialidad;
    private String ubicacion;

    public Sala(String id, String nombre, int capacidad, Especialidad especialidad, String ubicacion) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID de sala inválido");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre de sala inválido");
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad debe ser mayor a cero");
        if (especialidad == null) throw new IllegalArgumentException("Especialidad no puede ser null");
        if (ubicacion == null || ubicacion.isBlank()) throw new IllegalArgumentException("Ubicación inválida");

        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.especialidad = especialidad;
        this.ubicacion = ubicacion;
        this.disponible = true;
    }

    //Getters y Setters

    public String getId () {
        return id;
    }

    public void setId (String id){
        this.id = id;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }

    public boolean isDisponible () {
        return disponible;
    }

    public void setDisponible ( boolean disponible){
        this.disponible = disponible;
    }

    public int getCapacidad () {
        return capacidad;
    }

    public void setCapacidad ( int capacidad){
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad inválida");
        this.capacidad = capacidad;
    }

    public Especialidad getEspecialidad () {
        return especialidad;
    }

    public void setEspecialidad (Especialidad especialidad){
        this.especialidad = especialidad;
    }

    public String getUbicacion () {
        return ubicacion;
    }

    public void setUbicacion (String ubicacion){
        if (ubicacion == null || ubicacion.isBlank()) throw new IllegalArgumentException("Ubicación inválida");
        this.ubicacion = ubicacion;
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sala sala)) return false;
        return id.equals(sala.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especialidad=" + especialidad +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidad=" + capacidad +
                ", disponible=" + disponible +
                '}';
    }
}

