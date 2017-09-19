package reactiva.reactivamovil.classes;

import reactiva.reactivamovil.R;

/**
 * Created by Erasmo Zurita on 2017-07-16.
 */

public class TerapiaAnterior {

    String fechaTerapiaAnterior;
    String terapistaTerapiaAnterior;
    String idTerapiaAnterior;
    int view_terapy;

    public TerapiaAnterior(String fechaTerapiaAnterior, String terapistaTerapiaAnterior, String idTerapiaAnterior, int view_terapy) {
        this.fechaTerapiaAnterior = fechaTerapiaAnterior;
        this.terapistaTerapiaAnterior = terapistaTerapiaAnterior;
        this.idTerapiaAnterior = idTerapiaAnterior;
        this.view_terapy = view_terapy;
    }

    public TerapiaAnterior(String fechaTerapiaAnterior, String terapistaTerapiaAnterior, String idTerapiaAnterior) {
        this.fechaTerapiaAnterior = fechaTerapiaAnterior;
        this.terapistaTerapiaAnterior = terapistaTerapiaAnterior;
        this.idTerapiaAnterior = idTerapiaAnterior;
    }

    public String getFechaTerapiaAnterior() {
        return fechaTerapiaAnterior;
    }

    public void setFechaTerapiaAnterior(String fechaTerapiaAnterior) {
        this.fechaTerapiaAnterior = fechaTerapiaAnterior;
    }

    public String getTerapistaTerapiaAnterior() {
        return terapistaTerapiaAnterior;
    }

    public void setTerapistaTerapiaAnterior(String terapistaTerapiaAnterior) {
        this.terapistaTerapiaAnterior = terapistaTerapiaAnterior;
    }

    public String getIdTerapiaAnterior() {
        return idTerapiaAnterior;
    }

    public void setIdTerapiaAnterior(String idTerapiaAnterior) {
        this.idTerapiaAnterior = idTerapiaAnterior;
    }

    public int getView_terapy() {
        return view_terapy;
    }

    public void setView_terapy(int view_terapy) {
        this.view_terapy = view_terapy;
    }
}

