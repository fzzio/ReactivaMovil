package reactiva.reactivamovil.classes;

/**
 * Created by Fernando on 27/07/2017.
 */

public class Appointment {
    private String nombres;
    private String apellidos;
    private String hora;

    public Appointment(String nombres, String apellidos, String hora) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.hora = hora;
    }

    public String getNombres() { return nombres; }

    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getHora() { return hora; }

    public void setHora(String hora) { this.hora = hora; }
}