package reactiva.reactivamovil.presentadores;



import java.util.ArrayList;

import reactiva.reactivamovil.adapters.ObservacionTerapiaAdaptador;
import reactiva.reactivamovil.classes.ObservacionTerapia;

/**
 * Created by Usuario on 14/9/2017.
 */

public interface IRecyclerBitacora {

    public void generarLinearLayoutVertical();

    public ObservacionTerapiaAdaptador crearAdaptadorObservacionTerapia(ArrayList<ObservacionTerapia> observacionTerapias);

    public void inicializarAdaptadorRV(ObservacionTerapiaAdaptador observacionTerapiaAdaptador);

}
