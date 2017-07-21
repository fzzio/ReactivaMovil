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

        /*final int mExpandedPosition = -1;
        final boolean isExpanded = position==mExpandedPosition;
        holder.row_item.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                TransitionManager.beginDelayedTransition(recyclerView);//recyclerView es un groupView
                notifyDataSetChanged();
            }
        });*/

        /*if (position == expandedPosition) {
            holder.itemView.findViewById(R.id.header).setVisibility(View.VISIBLE);
        } else {
            holder.itemView.findViewById(R.id.header).setVisibility(View.GONE);
        }*/

        /*holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TerapiaViewHolder holder = (TerapiaViewHolder) view.getTag();
                String theString = listaTerapias.get(holder.get(position));

                // Check for an expanded view, collapse if you find one
                if (expandedPosition >= 0) {
                    int prev = expandedPosition;
                    notifyItemChanged(prev);
                }
                // Set the current position to "expanded"
                expandedPosition = holder.get(position);
                notifyItemChanged(expandedPosition);

                Toast.makeText(mContext, "Clicked: "+theString, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    /*@Override
    public void onClick(View view) {
        TerapiaViewHolder holder = (TerapiaViewHolder) view.getTag();
        String theString = mDataset.get(holder.getPosition());

        // Check for an expanded view, collapse if you find one
        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            notifyItemChanged(prev);
        }
        // Set the current position to "expanded"
        expandedPosition = holder.getPosition();
        notifyItemChanged(expandedPosition);

        Toast.makeText(mContext, "Clicked: "+theString, Toast.LENGTH_SHORT).show();
    }*/

    /**
     * Create a ViewHolder to represent your cell layout
     * and data element structure
     */
    /*public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvSubTitle;
        RelativeLayout llExpandArea;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tvSubTitle);
            llExpandArea = (RelativeLayout) itemView.findViewById(R.id.header);
        }
    }*/

    @Override
    public int getItemCount() {
        return listaTerapias.size();
    }

    public static class TerapiaViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_pic;
        TextView txtNombre;
        TextView txtTemporizador;

        public TerapiaViewHolder(View itemView) {
            super(itemView);
            profile_pic = (ImageView) itemView.findViewById(R.id.image_profile_pic);
            txtNombre = (TextView) itemView.findViewById(R.id.txtName);
            txtTemporizador = (TextView) itemView.findViewById(R.id.txt_temporizador);
        }
    }

}
