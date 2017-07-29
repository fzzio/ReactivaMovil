package reactiva.reactivamovil;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Erasmo Zurita on 2017-07-16.
 */

public class TerapiaAnterior {

    String fechaTerapiaAnterior;
    String terapistaTerapiaAnterior;
    int view_terapy;

    public TerapiaAnterior(String fechaTerapiaAnterior, String terapistaTerapiaAnterior) {
        this.fechaTerapiaAnterior = fechaTerapiaAnterior;
        this.terapistaTerapiaAnterior = terapistaTerapiaAnterior;
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

    public void setView_terapy() {
        this.view_terapy = R.drawable.view_therapy;
    }
}
