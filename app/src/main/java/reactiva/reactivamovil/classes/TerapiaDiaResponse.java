package reactiva.reactivamovil.classes;

import java.util.ArrayList;

/**
 * Created by Erasmo Zurita on 2017-08-19.
 * Clase necesaria para devolver una lista de terapiaDias
 */


public class TerapiaDiaResponse {

    ArrayList<TerapiaDia> terapiaDias;

    public TerapiaDiaResponse(ArrayList<TerapiaDia> terapiaDias) {
        this.terapiaDias = terapiaDias;
    }

    public ArrayList<TerapiaDia> getTerapiaDias() {
        return terapiaDias;
    }

    public void setTerapiaDias(ArrayList<TerapiaDia> terapiaDias) {
        this.terapiaDias = terapiaDias;
    }
}
