package reactiva.reactivamovil.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;

import reactiva.reactivamovil.R;
import reactiva.reactivamovil.classes.ObservacionTerapia;

/**
 * Created by Nancy on 2017-07-15.
 */

public class ObservacionTerapiaAdaptador extends RecyclerView.Adapter<ObservacionTerapiaAdaptador.ObservacionTerapiaViewHolder> {

    ArrayList<ObservacionTerapia> observacionMedicasDurantelLaTerapia;

    public ObservacionTerapiaAdaptador(ArrayList<ObservacionTerapia> observacionMedicas) {
        this.observacionMedicasDurantelLaTerapia = observacionMedicas;
    }


    @Override
    public ObservacionTerapiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                            //ASOCIO MI LAYOUT DE UN ITEM AL RECYCLER VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_observa_medicas,parent,false);
                                            //LUEGO LO PASO AL MANEJADOR PARA QUE OBTENGA CADA UNA DE LOS VIEWS O ELEMENTOS DE LA VISTA
        return new ObservacionTerapiaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ObservacionTerapiaViewHolder observacionMedicaViewHolder, int position) {
                                            ///MEDIANTE EL MANEJADOR ASOCIO MIS VALORES CONTENIDOS EN EL ARRAYLIST A CADA
                                            //ELEMENTO DE MI LAYAOUT ITEMT(UN VIEW)
                                            //PARA ESO CREO UN ITEM DE MI LISTA Y LE ASOCIO LOS CRORRESPONDIENTES VALORES
        ObservacionTerapia observacionMed = observacionMedicasDurantelLaTerapia.get(position);
        observacionMedicaViewHolder.tvHoraObsMed.setText(observacionMed.getObsHoraComentario());
        observacionMedicaViewHolder.tvDetalleObsMed.setText('"' + observacionMed.getObsComentario() + '"');
    }

    @Override
    public int getItemCount() {  ///CANTIDAD DE ELEMENTOS QUE CONTIENE LA LISTA DE OBSERVACIONES MEDICAS
        return observacionMedicasDurantelLaTerapia.size();
    }

    public static class ObservacionTerapiaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHoraObsMed;
        private TextView tvDetalleObsMed;

        public ObservacionTerapiaViewHolder(View itemView) {
            super(itemView);

            tvHoraObsMed    = (TextView) itemView.findViewById(R.id.tvHoraObsMedica);
            tvDetalleObsMed  = (TextView) itemView.findViewById(R.id.tvDetalleObsMedica);

        }
    }
}
