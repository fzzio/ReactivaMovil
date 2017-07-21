package reactiva.reactivamovil;

/**
 * Created by edgardan on 18/07/2017.
 */

public class ItemTerapiaView {
    private int profile_pic;
    private String nombre;
    private String temporizador;
    //private boolean estado;//True cuando esta activo, False cuando esta inactivo

    public ItemTerapiaView(int profile_pic, String nombre, String temporizador) {
        this.profile_pic = profile_pic;
        this.nombre = nombre;
        this.temporizador = temporizador;
        //this.estado = false;
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

    /*public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }*/
}
