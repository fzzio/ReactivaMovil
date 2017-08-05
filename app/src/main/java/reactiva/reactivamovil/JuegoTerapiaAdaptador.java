package reactiva.reactivamovil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Erasmo Zurita on 2/8/2017.
 */

public class JuegoTerapiaAdaptador extends RecyclerView.Adapter<JuegoTerapiaAdaptador.JuegoTerapiaViewHolder>{

    ArrayList<JuegoTerapia> juegosDisponibles;

    public JuegoTerapiaAdaptador(ArrayList<JuegoTerapia> juegosDisponibles) {
        this.juegosDisponibles = juegosDisponibles;
    }

    @Override
    public JuegoTerapiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game,parent,false);
        return new JuegoTerapiaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JuegoTerapiaViewHolder juegoTerapiaViewHolder, int position) {

        JuegoTerapia juegosTerapias = juegosDisponibles.get(position);
        juegoTerapiaViewHolder.tvNombreGame.setText(juegosTerapias.getNameGame());
        juegoTerapiaViewHolder.imgvGame.setImageResource(R.drawable.juego1);
    }

    @Override
    public int getItemCount() {
        return juegosDisponibles.size();
    }


    public static class JuegoTerapiaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombreGame;
        private ImageView imgvGame;

        public JuegoTerapiaViewHolder(View itemView) {
            super(itemView);

            tvNombreGame = (TextView) itemView.findViewById(R.id.tvNameGame);
            imgvGame  = (ImageView) itemView.findViewById(R.id.imgVGame);

        }
    }



}
