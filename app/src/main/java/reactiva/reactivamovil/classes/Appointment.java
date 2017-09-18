package reactiva.reactivamovil.classes;

/**
 * Created by Fernando on 27/07/2017.
 */

public class Appointment {
    private String id_therapy;
    private String id_patient;
    private String nombres;
    private String apellidos;
    private String hora;
    private String format;

    public Appointment(String id_therapy, String id_patient, String nombres, String apellidos, String hora, String format) {
        this.id_therapy = id_therapy;
        this.id_patient = id_patient;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.hora = hora;
        this.format = format;
    }

    public String getNombres() { return nombres; }

    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getHora() { return hora; }

    public void setHora(String hora) { this.hora = hora; }

    public String getFormat() {
        return format;
    }

    public String getId_patient() {
        return id_patient;
    }

    public void setId_patient(String id_patient) {
        this.id_patient = id_patient;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getId_therapy() {
        return id_therapy;
    }

    public void setId_therapy(String id_therapy) {
        this.id_therapy = id_therapy;
    }
}