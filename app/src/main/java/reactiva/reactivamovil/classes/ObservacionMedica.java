package reactiva.reactivamovil.classes;

/**
 * Created by Nancy on 2017-07-15.
 */

public class ObservacionMedica {

    String horaObservacionMedica;
    String detalleSucesoTerapia;


    public ObservacionMedica(String horaObservacionMedica, String detalleSucesoTerapia) {
        this.horaObservacionMedica = horaObservacionMedica;
        this.detalleSucesoTerapia = detalleSucesoTerapia;
    }

    public String getHoraObservacionMedica() {
        return horaObservacionMedica;
    }

    public void setHoraObservacionMedica(String horaObservacionMedica) {
        this.horaObservacionMedica = horaObservacionMedica;
    }

    public String getDetalleSucesoTerapia() {
        return detalleSucesoTerapia;
    }

    public void setDetalleSucesoTerapia(String detalleSucesoTerapia) {
        this.detalleSucesoTerapia = detalleSucesoTerapia;
    }
}
