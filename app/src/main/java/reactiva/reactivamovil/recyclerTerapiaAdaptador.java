package reactiva.reactivamovil;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.List;
/**
 * Created by edgardan on 18/07/2017.
 */

public class recyclerTerapiaAdaptador extends RecyclerView.Adapter<recyclerTerapiaAdaptador.TerapiaViewHolder> implements View.OnClickListener{

    List<ItemTerapiaView> listaTerapias;
    private int expandedPosition = -1;
    Context context;

    private Button button;
    private Dialog dialog;

    public Button boton_test;
    //final Context context = button.getContext();

    public recyclerTerapiaAdaptador(List<ItemTerapiaView> listaTerapias, Context context){
        this.listaTerapias = listaTerapias;
        this.context = context;
    }

    @Override
    public TerapiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.terapia_row_item,parent,false);
        TerapiaViewHolder holder = new TerapiaViewHolder(v);
        return holder;
    }

    @Override
    //aqui se establecen los valores de la vista
    //Bind Data
    public void onBindViewHolder(TerapiaViewHolder holder, final int position) {

        //ImageButton botonPause= (ImageButton) holder.itemView.findViewById(R.id.btnIniciarTerapia);

        holder.profile_pic.setImageResource(listaTerapias.get(position).getProfile_pic());
        holder.txtNombre.setText(listaTerapias.get(position).getNombre());
        //holder.txtTemporizador.setText(listaTerapias.get(position).getTemporizador());
        holder.profile_pic_detalle.setImageResource(listaTerapias.get(position).getProfile_pic());
        holder.txtHora.setText(listaTerapias.get(position).getHora());
        holder.txtNombreDetalle.setText(listaTerapias.get(position).getNombre_detalle());
        holder.txtEdadDetalle.setText(listaTerapias.get(position).getEdad_detalle());
        //holder.txtSalaDetalle.setText(listaTerapias.get(position).getSala_detalle());

        //holder.itemView.findViewById(R.id.btnIniciarTerapia).setOnClickListener();

        //holder.btnPausa.
        if(position%4 == 0)
        {
            holder.itemView.findViewById(R.id.header).setBackgroundColor(Color.parseColor("#cb7cb3"));
            holder.itemView.findViewById(R.id.section).setBackgroundColor(Color.parseColor("#cb7cb3"));
        }else if(position%4 == 1)
        {
            holder.itemView.findViewById(R.id.header).setBackgroundColor(Color.parseColor("#8E7CB8"));
            holder.itemView.findViewById(R.id.section).setBackgroundColor(Color.parseColor("#8E7CB8"));
        }else if(position%4 == 2)
        {
            holder.itemView.findViewById(R.id.header).setBackgroundColor(Color.parseColor("#664C8E"));
            holder.itemView.findViewById(R.id.section).setBackgroundColor(Color.parseColor("#664C8E"));
        }else if(position%4 == 3)
        {
            holder.itemView.findViewById(R.id.header).setBackgroundColor(Color.parseColor("#3C2C5E"));
            holder.itemView.findViewById(R.id.section).setBackgroundColor(Color.parseColor("#3C2C5E"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for (Iterator<ItemTerapiaView> i = listaTerapias.iterator(); i.hasNext();) {
                    ItemTerapiaView item = i.next();
                    item.setEstado(false);
                }
                listaTerapias.get(position).setEstado(true);*/

                /*button = (Button) v.findViewById(R.id.buttonShowCustomDialog);

                // custom dialog
                //Dialog dialog;
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.terapia_comentario);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                //image.setImageResource(R.drawable.ic_launcher);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();*/

                boolean isExpanded = position==expandedPosition;
                expandedPosition = isExpanded ? -1:position;
                if (position == expandedPosition) {
                    v.findViewById(R.id.header).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.section).setVisibility(View.GONE);
                } else {
                    v.findViewById(R.id.header).setVisibility(View.GONE);//check v
                    v.findViewById(R.id.section).setVisibility(View.VISIBLE);
                }

                //v.findViewById(R.id.btnIniciarTerapia).setOnClickListener();
                //Log.d("Title", "Value: " + Integer.toString(position));
            }
        });

        //final ImageButton botonPause= (ImageButton) v.findViewById(R.id.btnIniciarTerapia);

        //esto da error
        /*holder.itemView.findViewById(R.id.btnIniciarTerapia).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                //v.findViewById(R.id.btnIniciarTerapia);
            }
        });*/


        /*button = (Button) findViewById(R.id.buttonShowCustomDialog);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.terapia_comentario);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                //image.setImageResource(R.drawable.ic_launcher);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listaTerapias.size();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == boton_test.getId()){
            Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }

    public static class TerapiaViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_pic;
        ImageView profile_pic_detalle;
        TextView txtNombre;
        //TextView txtTemporizador;
        TextView txtHora;
        TextView txtNombreDetalle;
        TextView txtEdadDetalle;
        ImageButton btnPausa;
        //TextView txtSalaDetalle;

        public TerapiaViewHolder(View itemView) {
            super(itemView);
            profile_pic = (ImageView) itemView.findViewById(R.id.image_profile_pic);
            txtNombre = (TextView) itemView.findViewById(R.id.txtName);
            //txtTemporizador = (TextView) itemView.findViewById(R.id.txt_temporizador);
            profile_pic_detalle = (ImageView) itemView.findViewById(R.id.imageViewProfile);
            txtHora = (TextView) itemView.findViewById(R.id.txt_hora);
            txtNombreDetalle = (TextView) itemView.findViewById(R.id.txtNameDetalle);
            txtEdadDetalle = (TextView) itemView.findViewById(R.id.txtEdadDetalle);
            //txtSalaDetalle = (TextView) itemView.findViewById(R.id.txtSalaDetalle);
            //btnPausa = (ImageButton) itemView.findViewById(R.id.btnIniciarTerapia);
        }
    }

}
