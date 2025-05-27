package co.edu.uniquindio.poo.sistemahospitaluq.controller;

import co.edu.uniquindio.poo.sistemahospitaluq.model.Especialidad;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Hospital;
import co.edu.uniquindio.poo.sistemahospitaluq.model.Sala;

import java.util.ArrayList;
import java.util.List;

public class SalaController {
    private final Hospital hospital;

    public SalaController(Hospital hospital) {
        this.hospital = hospital;
    }

    // --------------------------
    // REGISTRAR SALA
    // --------------------------

    public boolean registrarSala(Sala sala) {
        if (hospital.buscarSalaPorId(sala.getId()) != null) {
            return false; // Ya existe una sala con ese ID
        }
        hospital.registrarSala(sala);
        return true;
    }

    // --------------------------
    // BUSCAR SALA
    // --------------------------

    public Sala buscarSalaPorId(String id) {
        return hospital.buscarSalaPorId(id);
    }

    // --------------------------
    // ELIMINAR SALA
    // --------------------------

    public boolean eliminarSala(String id) {
        Sala sala = hospital.buscarSalaPorId(id);
        if (sala != null) {
            return hospital.getSalas().remove(sala);
        }
        return false;
    }

    // --------------------------
    // ACTUALIZAR SALA
    // --------------------------

    public boolean actualizarSala(String id, int nuevaCapacidad, boolean disponible) {
        Sala sala = hospital.buscarSalaPorId(id);
        if (sala == null) return false;

        sala.setCapacidad(nuevaCapacidad);
        sala.setDisponible(disponible);
        return true;
    }

    // --------------------------
    // CONSULTAS
    // --------------------------

    public List<Sala> obtenerTodasLasSalas() {
        return new ArrayList<>(hospital.getSalas());
    }

    public List<Sala> obtenerSalasDisponibles() {
        List<Sala> disponibles = new ArrayList<>();
        for (Sala sala : hospital.getSalas()) {
            if (sala.isDisponible()) {
                disponibles.add(sala);
            }
        }
        return disponibles;
    }

    public List<Sala> obtenerSalasPorEspecialidad(Especialidad especialidad) {
        List<Sala> filtradas = new ArrayList<>();
        for (Sala sala : hospital.getSalas()) {
            if (sala.getEspecialidad().equals(especialidad)) {
                filtradas.add(sala);
            }
        }
        return filtradas;
    }
}
