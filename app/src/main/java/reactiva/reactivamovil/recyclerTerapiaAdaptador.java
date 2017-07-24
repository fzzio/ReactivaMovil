package reactiva.reactivamovil;

import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.List;
/**
 * Created by edgardan on 18/07/2017.
 */

public class recyclerTerapiaAdaptador extends RecyclerView.Adapter<recyclerTerapiaAdaptador.TerapiaViewHolder>{

    List<ItemTerapiaView> listaTerapias;
    private int expandedPosition = -1;

    public recyclerTerapiaAdaptador(List<ItemTerapiaView> listaTerapias){
        this.listaTerapias = listaTerapias;
    }

    @Override
    public TerapiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.terapia_row_item,parent,false);
        TerapiaViewHolder holder = new TerapiaViewHolder(v);
        return holder;
    }

    @Override
    //aqui se establecen los valores de la vista
    public void onBindViewHolder(TerapiaViewHolder holder, final int position) {
        holder.profile_pic.setImageResource(listaTerapias.get(position).getProfile_pic());
        holder.txtNombre.setText(listaTerapias.get(position).getNombre());
        holder.txtTemporizador.setText(listaTerapias.get(position).getTemporizador());
        holder.txtHora.setText(listaTerapias.get(position).getHora());
        holder.txtNombreDetalle.setText(listaTerapias.get(position).getNombre_detalle());
        holder.txtEdadDetalle.setText(listaTerapias.get(position).getEdad_detalle());
        holder.txtSalaDetalle.setText(listaTerapias.get(position).getSala_detalle());

        //boolean isExpanded = position==mExpandedPosition;
        //holder.itemView.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        //holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for (Iterator<ItemTerapiaView> i = listaTerapias.iterator(); i.hasNext();) {
                    ItemTerapiaView item = i.next();
                    item.setEstado(false);
                }
                listaTerapias.get(position).setEstado(true);*/
                boolean isExpanded = position==expandedPosition;
                expandedPosition = isExpanded ? -1:position;
                if (position == expandedPosition) {
                    v.findViewById(R.id.header).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.section).setVisibility(View.GONE);
                } else {
                    v.findViewById(R.id.header).setVisibility(View.GONE);//check v
                    v.findViewById(R.id.section).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaTerapias.size();
    }

    public static class TerapiaViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_pic;
        TextView txtNombre;
        TextView txtTemporizador;
        TextView txtHora;
        TextView txtNombreDetalle;
        TextView txtEdadDetalle;
        TextView txtSalaDetalle;

        public TerapiaViewHolder(View itemView) {
            super(itemView);
            profile_pic = (ImageView) itemView.findViewById(R.id.image_profile_pic);
            txtNombre = (TextView) itemView.findViewById(R.id.txtName);
            txtTemporizador = (TextView) itemView.findViewById(R.id.txt_temporizador);
            txtHora = (TextView) itemView.findViewById(R.id.txt_hora);
            txtNombreDetalle = (TextView) itemView.findViewById(R.id.txtNameDetalle);
            txtEdadDetalle = (TextView) itemView.findViewById(R.id.txtEdadDetalle);
            txtSalaDetalle = (TextView) itemView.findViewById(R.id.txtSalaDetalle);
        }
    }

}
