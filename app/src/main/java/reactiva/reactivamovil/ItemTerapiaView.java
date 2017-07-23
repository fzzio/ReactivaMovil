package reactiva.reactivamovil;

/**
 * Created by edgardan on 18/07/2017.
 */

public class ItemTerapiaView {
    private int profile_pic;
    private String nombre;
    private String temporizador;
    private String hora;
    private String nombre_detalle;
    private String edad_detalle;
    private String sala_detalle;
    private boolean estado;//True cuando esta activo, False cuando esta inactivo

    public ItemTerapiaView(int profile_pic, String nombre, String temporizador, String hora, String nombre_detalle, String edad_detalle, String sala_detalle) {
        this.profile_pic = profile_pic;
        this.nombre = nombre;
        this.temporizador = temporizador;
        this.hora = hora;
        this.nombre_detalle = nombre_detalle;
        this.edad_detalle = edad_detalle;
        this.sala_detalle = sala_detalle;
        this.estado = false;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporizador() {
        return temporizador;
    }

    public void setTemporizador(String temporizador) {
        this.temporizador = temporizador;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombre_detalle() {
        return nombre_detalle;
    }

    public void setNombre_detalle(String nombre_detalle) {
        this.nombre_detalle = nombre_detalle;
    }

    public String getEdad_detalle() {
        return edad_detalle;
    }

    public void setEdad_detalle(String edad_detalle) {
        this.edad_detalle = edad_detalle;
    }

    public String getSala_detalle() {
        return sala_detalle;
    }

    public void setSala_detalle(String sala_detalle) {
        this.sala_detalle = sala_detalle;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
