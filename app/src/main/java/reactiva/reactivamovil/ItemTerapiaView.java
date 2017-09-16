package reactiva.reactivamovil;

import android.widget.ImageButton;

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
    private int btn_stop;
    private int btn_pausa;
    private int btn_comentario;
    private int btn_camara;
    private int btn_pause_detail;
    private int ver_perfil;
    private String id_therapy;
    private String id_consulta;
    private String id_patient;
    private boolean estado;//True cuando esta activo, False cuando esta inactivo

    public ItemTerapiaView(int profile_pic, String nombre, String temporizador, String hora, String nombre_detalle, String edad_detalle, int btn_comentario, int btn_stop, int btn_pausa, int btn_camara, int ver_perfil, ArrayList<Integer> extremidades_pic, String id_therapy, String id_consulta, String id_patient) {
        this.profile_pic = profile_pic;
        this.nombre = nombre;
        this.temporizador = temporizador;
        this.hora = hora;
        this.nombre_detalle = nombre_detalle;
        this.edad_detalle = edad_detalle;
        this.profile_pic_detalle = profile_pic;
        this.btn_comentario = btn_comentario;
        this.btn_pausa = btn_pausa;
        this.btn_stop = btn_stop;
        this.btn_camara = btn_camara;
        this.btn_pause_detail = btn_pausa;
        this.ver_perfil = ver_perfil;
        this.extremidades_pic = extremidades_pic;
        this.estado = false;
        this.id_therapy = id_therapy;
        this.id_consulta = id_consulta;
        this.id_patient = id_patient;
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


    public int getProfile_pic_detalle() {
        return profile_pic_detalle;
    }

    public void setProfile_pic_detalle(int profile_pic_detalle) {
        this.profile_pic_detalle = profile_pic_detalle;
    }

    public int getBtn_stop() {
        return btn_stop;
    }

    public void setBtn_stop(int btn_stop) {
        this.btn_stop = btn_stop;
    }

    public int getBtn_pausa() {
        return btn_pausa;
    }

    public void setBtn_pausa(int btn_pausa) {
        this.btn_pausa = btn_pausa;
    }

    public int getBtn_comentario() {
        return btn_comentario;
    }

    public void setBtn_comentario(int btn_comentario) {
        this.btn_comentario = btn_comentario;
    }

    public int getBtn_camara() {
        return btn_camara;
    }

    public void setBtn_camara(int btn_camara) {
        this.btn_camara = btn_camara;
    }

    public int getBtn_pause_detail() {
        return btn_pause_detail;
    }

    public void setBtn_pause_detail(int btn_pause_detail) {
        this.btn_pause_detail = btn_pause_detail;
    }

    public int getVer_perfil() {
        return ver_perfil;
    }

    public void setVer_perfil(int ver_perfil) {
        this.ver_perfil = ver_perfil;
    }

    public String getId_therapy() {
        return id_therapy;
    }

    public String getId_consulta() {
        return id_consulta;
    }

    public String getId_patient() {
        return id_patient;
    }
}
