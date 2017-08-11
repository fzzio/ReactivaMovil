package reactiva.reactivamovil.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import reactiva.reactivamovil.classes.ParametrosJuego;
import reactiva.reactivamovil.R;

/**
 * Created by Nancy on 2017-08-02.
 */

public class ParametrosJuegoAdaptador extends RecyclerView.Adapter<ParametrosJuegoAdaptador.ParametrosJuegoViewHolder> {

    ArrayList<ParametrosJuego> parametrosDelJuegoData;

    public ParametrosJuegoAdaptador(ArrayList<ParametrosJuego> parametrosDelJuegoData) {
        this.parametrosDelJuegoData = parametrosDelJuegoData;
    }

    @Override
    public ParametrosJuegoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parametros_juego,parent,false);

        return new ParametrosJuegoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ParametrosJuegoViewHolder parametrosJuegoViewHolder, int position) {

        ParametrosJuego parametrosJuego = parametrosDelJuegoData.get(position);
        parametrosJuegoViewHolder.tvDetalleParametroJuego.setText(parametrosJuego.getDetalleParametro());
        parametrosJuegoViewHolder.tvParametroX.setText(parametrosJuego.getParametroX());
        parametrosJuegoViewHolder.tvParametroY.setText(parametrosJuego.getParametroY());
        parametrosJuegoViewHolder.tvParametroZ.setText(parametrosJuego.getParametroZ());

    }

    @Override
    public int getItemCount() {
        return parametrosDelJuegoData.size();
    }


    public static class ParametrosJuegoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDetalleParametroJuego;
        private TextView tvParametroX;
        private TextView tvParametroY;
        private TextView tvParametroZ;

        public ParametrosJuegoViewHolder(View itemView) {
            super(itemView);

            tvDetalleParametroJuego = (TextView) itemView.findViewById(R.id.tvDetalleParamJ);
            tvParametroX = (TextView) itemView.findViewById(R.id.tvParamX);
            tvParametroY = (TextView) itemView.findViewById(R.id.tvParamY);
            tvParametroZ = (TextView) itemView.findViewById(R.id.tvParamZ);

        }
    }


}
