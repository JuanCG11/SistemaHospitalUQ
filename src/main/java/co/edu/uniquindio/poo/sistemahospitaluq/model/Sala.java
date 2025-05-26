package co.edu.uniquindio.poo.sistemahospitaluq.model;

public class Sala {
    private String id;
    private String nombre;
    private boolean disponible;

    public Sala(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.disponible = true; // por defecto disponible
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return nombre + " (ID: " + id + ") - " + (disponible ? "Disponible" : "Ocupada");
    }
}
