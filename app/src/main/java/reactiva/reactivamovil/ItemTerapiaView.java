package reactiva.reactivamovil;

import java.util.ArrayList;

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
    private int profile_pic_detalle;
    private ArrayList<Integer> extremidades_pic;
    private boolean estado;//True cuando esta activo, False cuando esta inactivo

    public ItemTerapiaView(int profile_pic, String nombre, String temporizador, String hora, String nombre_detalle, String edad_detalle) {
        this.profile_pic = profile_pic;
        this.nombre = nombre;
        this.temporizador = temporizador;
        this.hora = hora;
        this.nombre_detalle = nombre_detalle;
        this.edad_detalle = edad_detalle;
        this.profile_pic_detalle = profile_pic;
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

    public ArrayList<Integer> getExtremidades_pic() {
        return extremidades_pic;
    }

    public void setExtremidades_pic(ArrayList<Integer> extremidades_pic) {
        this.extremidades_pic = extremidades_pic;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
