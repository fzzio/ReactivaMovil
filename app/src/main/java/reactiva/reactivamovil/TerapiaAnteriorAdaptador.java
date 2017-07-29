package reactiva.reactivamovil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static reactiva.reactivamovil.R.drawable.view_therapy;

/**
 * Created by Nancy on 2017-07-16.
 */

public class TerapiaAnteriorAdaptador extends RecyclerView.Adapter<TerapiaAnteriorAdaptador.TerapiaAnteriorViewHolder>{

    ArrayList<TerapiaAnterior> terapiasAnteriores;

    public TerapiaAnteriorAdaptador(ArrayList<TerapiaAnterior> terapiasAnteriores) {
        this.terapiasAnteriores = terapiasAnteriores;
    }



    @Override
    public TerapiaAnteriorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_terapia_anterior,parent,false);
        return new TerapiaAnteriorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TerapiaAnteriorViewHolder terapiaAnteriorViewHolder, int position) {

        TerapiaAnterior terapiaAnt = terapiasAnteriores.get(position);
        terapiaAnteriorViewHolder.tvFechaTerapiaAnterior.setText(terapiaAnt.getFechaTerapiaAnterior());
        terapiaAnteriorViewHolder.tvTerapistaTerapiaAnterior.setText(terapiaAnt.getTerapistaTerapiaAnterior());
        terapiaAnteriorViewHolder.imgTerapy.setImageResource(R.drawable.view_therapy);
    }

    @Override
    public int getItemCount() {
        return terapiasAnteriores.size();
    }



    public static class TerapiaAnteriorViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFechaTerapiaAnterior;
        private TextView tvTerapistaTerapiaAnterior;
        private ImageView imgTerapy;

        public TerapiaAnteriorViewHolder(View itemView) {
            super(itemView);

            tvFechaTerapiaAnterior      = (TextView) itemView.findViewById(R.id.tvfechaTeraAnte);
            tvTerapistaTerapiaAnterior  = (TextView) itemView.findViewById(R.id.tvTerapistaTerapiaAnte);
            imgTerapy                   = (ImageView) itemView.findViewById(R.id.imgVerTerapia);

        }
    }



}
