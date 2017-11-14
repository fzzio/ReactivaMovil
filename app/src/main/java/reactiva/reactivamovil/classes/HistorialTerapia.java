package reactiva.reactivamovil.classes;

/**
 * Created by daniel on 13/11/2017.
 */

public class HistorialTerapia {

    String fechaHistorial;
    int view_dia;


    public HistorialTerapia(String fechaHistorial, int view_dia) {
        this.fechaHistorial = fechaHistorial;
        this.view_dia = view_dia;
    }

    public String getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(String fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public int getView_dia() {
        return view_dia;
    }

    public void setView_dia(int view_dia) {
        this.view_dia = view_dia;
    }


}
