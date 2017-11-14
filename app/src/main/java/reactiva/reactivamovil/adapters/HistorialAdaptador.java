package reactiva.reactivamovil.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import reactiva.reactivamovil.R;
import reactiva.reactivamovil.classes.HistorialTerapia;

/**
 * Created by daniel on 13/11/2017.
 */

public class HistorialAdaptador extends RecyclerView.Adapter<HistorialAdaptador.HistorialViewHolder>{

    ArrayList<HistorialTerapia> historialesDisponibles;
    HistorialClickListener listener;

    public HistorialAdaptador(ArrayList<HistorialTerapia> historialesDisponibles, HistorialClickListener listener) {
        this.historialesDisponibles = historialesDisponibles;
        this.listener = listener;
    }

    @Override
    public HistorialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial,parent,false);
        return new HistorialViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HistorialViewHolder historialViewHolder, int position) {

        final HistorialTerapia historialTerapia = historialesDisponibles.get(position);
        historialViewHolder.tvFecha.setText(historialTerapia.getFechaHistorial());
        historialViewHolder.imgvDia.setImageResource(historialTerapia.getView_dia());

        historialViewHolder.imgvDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickHistorial(historialTerapia);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historialesDisponibles.size();
    }


    public static class HistorialViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFecha;
        private ImageView imgvDia;

        public HistorialViewHolder(View itemView) {
            super(itemView);

            tvFecha = (TextView) itemView.findViewById(R.id.textViewFecha);
            imgvDia = (ImageView) itemView.findViewById(R.id.imageViewDia);

        }

    }

    public interface HistorialClickListener {
        void onClickHistorial(HistorialTerapia historialTerapia);
    }

}
