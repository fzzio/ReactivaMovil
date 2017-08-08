package reactiva.reactivamovil.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import reactiva.reactivamovil.BitacoraTerapia;
import reactiva.reactivamovil.R;
import reactiva.reactivamovil.clases.TerapiaAnterior;

/**
 * Created by Erasmo on 2017-07-16.
 */

/*
* 2017-08-04
* AGREGO LA PROPIEDAD ONCLIKCLISTENER PARA QUE CADA ELEMENTO DE MI LISTA
 * ME DIRECCIONE A LA BITACORA CORRESPONDIENTE
*
* */

public class TerapiaAnteriorAdaptador extends RecyclerView.Adapter<TerapiaAnteriorAdaptador.TerapiaAnteriorViewHolder>{

    ArrayList<TerapiaAnterior> terapiasAnteriores;
    ///creo una activiada para utlilizarla como contexto
    Activity activity;


    public TerapiaAnteriorAdaptador(ArrayList<TerapiaAnterior> terapiasAnteriores, Activity activity) {
        this.terapiasAnteriores = terapiasAnteriores;
        this.activity = activity;
    }



    @Override
    public TerapiaAnteriorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_terapia_anterior,parent,false);
        return new TerapiaAnteriorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TerapiaAnteriorViewHolder terapiaAnteriorViewHolder, final int position) {

        final TerapiaAnterior terapiaAnt = terapiasAnteriores.get(position);
        terapiaAnteriorViewHolder.tvFechaTerapiaAnterior.setText(terapiaAnt.getFechaTerapiaAnterior());
        terapiaAnteriorViewHolder.tvTerapistaTerapiaAnterior.setText(terapiaAnt.getTerapistaTerapiaAnterior());
        terapiaAnteriorViewHolder.imgTerapy.setImageResource(R.drawable.view_therapy);

        terapiaAnteriorViewHolder.imgTerapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///MUESTRO EL DOCTOR QUE HIZO LA TERAPIA
                Toast.makeText(activity,terapiaAnt.getTerapistaTerapiaAnterior(),Toast.LENGTH_SHORT).show();
                ///LLAMO A LA ACTIVIDAD BITACORA
                Intent intent = new Intent(activity,BitacoraTerapia.class);
                activity.startActivity(intent);

            }
        });
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
