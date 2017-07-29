package reactiva.reactivamovil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nancy on 2017-07-15.
 */

public class ObservacionMedicaAdaptador extends RecyclerView.Adapter<ObservacionMedicaAdaptador.ObservacionMedicaViewHolder> {

    ArrayList<ObservacionMedica> observacionMedicasDurantelLaTerapia;

    public ObservacionMedicaAdaptador(ArrayList<ObservacionMedica> observacionMedicas) {
        this.observacionMedicasDurantelLaTerapia = observacionMedicas;
    }


    @Override
    public ObservacionMedicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                            //ASOCIO MI LAYOUT DE UN ITEM AL RECYCLER VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_observa_medicas,parent,false);
                                            //LUEGO LO PASO AL MANEJADOR PARA QUE OBTENGA CADA UNA DE LOS VIEWS O ELEMENTOS DE LA VISTA
        return new ObservacionMedicaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ObservacionMedicaViewHolder observacionMedicaViewHolder, int position) {
                                            ///MEDIANTE EL MANEJADOR ASOCIO MIS VALORES CONTENIDOS EN EL ARRAYLIST A CADA
                                            //ELEMENTO DE MI LAYAOUT ITEMT(UN VIEW)
                                            //PARA ESO CREO UN ITEM DE MI LISTA Y LE ASOCIO LOS CRORRESPONDIENTES VALORES
        ObservacionMedica observacionMed = observacionMedicasDurantelLaTerapia.get(position);
        observacionMedicaViewHolder.tvHoraObsMed.setText(observacionMed.getHoraObservacionMedica());
        observacionMedicaViewHolder.tvDetalleObsMed.setText(observacionMed.getDetalleSucesoTerapia());
    }

    @Override
    public int getItemCount() {  ///CANTIDAD DE ELEMENTOS QUE CONTIENE LA LISTA DE OBSERVACIONES MEDICAS
        return observacionMedicasDurantelLaTerapia.size();
    }

    public static class ObservacionMedicaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHoraObsMed;
        private TextView tvDetalleObsMed;

        public ObservacionMedicaViewHolder(View itemView) {
            super(itemView);

            tvHoraObsMed    = (TextView) itemView.findViewById(R.id.tvHoraObsMedica);
            tvDetalleObsMed  = (TextView) itemView.findViewById(R.id.tvDetalleObsMedica);
        }
    }
}
