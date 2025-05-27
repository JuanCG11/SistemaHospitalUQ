package co.edu.uniquindio.poo.sistemahospitaluq.utils;

public class Notificador {
    private Notificador() {
        // Constructor privado para evitar instanciación
    }

    //Simula el envío de una notificación a un usuario.

    public static void enviarNotificacion(String nombreUsuario, String mensaje) {
        System.out.println("🔔 Notificación para " + nombreUsuario + ": " + mensaje);
    }
}
