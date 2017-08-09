package reactiva.reactivamovil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.transition.TransitionManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class recyclerTerapiaAdaptador extends RecyclerView.Adapter<recyclerTerapiaAdaptador.TerapiaViewHolder> {

    List<ItemTerapiaView> listaTerapias;
    private int expandedPosition = -1;
    Context context;
    Activity activity;

    //final Context context = button.getContext();

    public recyclerTerapiaAdaptador(List<ItemTerapiaView> listaTerapias, Context context, Activity activity){
        this.listaTerapias = listaTerapias;
        this.context = context;
        this.activity = activity;
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

        final ItemTerapiaView terapiaView = listaTerapias.get(position);

        holder.profile_pic.setImageResource(listaTerapias.get(position).getProfile_pic());
        holder.txtNombre.setText(listaTerapias.get(position).getNombre());
        //holder.txtTemporizador.setText(listaTerapias.get(position).getTemporizador());
        holder.profile_pic_detalle.setImageResource(listaTerapias.get(position).getProfile_pic());
        holder.txtHora.setText(listaTerapias.get(position).getHora());
        holder.txtNombreDetalle.setText(listaTerapias.get(position).getNombre_detalle());
        holder.txtEdadDetalle.setText(listaTerapias.get(position).getEdad_detalle());

        //holder.btnPausa.setImageResource(listaTerapias.get(position).getBtn_pausa());//poner imagen que es

        holder.imgVComentario.setImageResource(listaTerapias.get(position).getBtn_comentario());
        holder.imgVStop.setImageResource(listaTerapias.get(position).getBtn_stop());
        holder.imgVPause.setImageResource(listaTerapias.get(position).getBtn_pause_detail());
        holder.imgVCamara.setImageResource(listaTerapias.get(position).getBtn_camara());

        holder.imgVVerPerfil.setImageResource(listaTerapias.get(position).getVer_perfil());

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

                boolean isExpanded = position==expandedPosition;
                expandedPosition = isExpanded ? -1:position;
                if (position == expandedPosition) {
                    v.findViewById(R.id.header).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.section).setVisibility(View.GONE);
                } else {
                    v.findViewById(R.id.header).setVisibility(View.GONE);//check v
                    v.findViewById(R.id.section).setVisibility(View.VISIBLE);
                }
                //Log.d("Title", "Value: " + Integer.toString(position));
            }
        });

        /*holder.btnPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Pausa", Toast.LENGTH_SHORT).show();
            }
        });*/

        holder.imgVComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder comentBuilder = new AlertDialog.Builder(v.getContext());
                LayoutInflater li = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vistaComent = li.inflate(R.layout.terapia_comentario, null);

                //TextView titulo = (TextView) vistaComent.findViewById(R.id.txt_descripcion_coment);
                final EditText comentario = (EditText) vistaComent.findViewById(R.id.editTxtComentario);
                //Button btnGuardar = (Button) vistaComent.findViewById(R.id.btnGuardar);
                //Button btnCancelar = (Button) vistaComent.findViewById(R.id.btnCancelar);

                /*btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!comentario.getText().toString().isEmpty()){
                            Toast.makeText(v.getContext(), "Comentario exitoso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(v.getContext(), "Por Favor llene el campo de comentario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/


                TextView titulo = new TextView(context);
                titulo.setText(R.string.terapia_comentario_title);
                titulo.setGravity(Gravity.TOP);
                titulo.setPadding(50, 70, 30, 30);
                titulo.setTextSize(30);
                //titulo.setBackgroundColor(Color.WHITE);
                titulo.setTextColor(Color.parseColor("#163550"));

                comentBuilder.setCustomTitle(titulo);
                comentBuilder.setMessage(R.string.terapia_comentario_descripcion);
                comentBuilder.setNegativeButton("Cancelar",null);
                comentBuilder.setPositiveButton("Guardar", null);
                comentBuilder.setView(vistaComent);
                final AlertDialog dialog = comentBuilder.create();

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        Button boton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                        boton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!comentario.getText().toString().isEmpty()){
                                    Toast.makeText(v.getContext(), "Comentario exitoso", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(v.getContext(), "Por Favor llene el campo de comentario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });


                dialog.show();

                TextView msmTxt = (TextView) dialog.findViewById(android.R.id.message);
                Button btnOk = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button btnCancel = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);


                btnOk.setPadding(20,0,20,50);
                btnCancel.setPadding(20,0,20,50);

                msmTxt.setTextSize(24);
                btnOk.setTextSize(28);
                btnCancel.setTextSize(28);

                /*btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/
            }
        });

        holder.imgVVerPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,terapiaView.getNombre_detalle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity,BitacoraTerapia.class);
                activity.startActivity(intent);
            }
        });

        holder.imgVStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ContextThemeWrapper ctw = new ContextThemeWrapper(v.getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert);
                final AlertDialog.Builder comentBuilder = new AlertDialog.Builder(v.getContext());

                LayoutInflater li = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vistaEvaluacion = li.inflate(R.layout.terapia_evaluacion, null);

                //TextView titulo = (TextView) vistaComent.findViewById(R.id.titulo);
                final EditText comentario = (EditText) vistaEvaluacion.findViewById(R.id.edit_text_obs);
                //TextView btnGuardar = (TextView) vistaEvaluacion.findViewById(R.id.txt_guardar);
                //TextView btnCancelar = (TextView) vistaEvaluacion.findViewById(R.id.txt_cancelar);

                /*btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!comentario.getText().toString().isEmpty()){
                            Toast.makeText(v.getContext(), "Comentario exitoso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(v.getContext(), "Por Favor llene el campo de comentario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

                //comentBuilder.setTitle(R.string.terapia_evaluacion_title);
                TextView titulo = new TextView(context);
                titulo.setText(R.string.terapia_evaluacion_title);
                titulo.setGravity(Gravity.TOP);
                titulo.setPadding(50, 70, 30, 30);
                titulo.setTextSize(30);
                //titulo.setBackgroundColor(Color.WHITE);
                titulo.setTextColor(Color.parseColor("#163550"));

                comentBuilder.setCustomTitle(titulo);
                comentBuilder.setMessage(R.string.terapia_evaluacion_descripcion);
                comentBuilder.setNegativeButton("Cancelar",null);
                comentBuilder.setPositiveButton("Guardar", null);//no se setea el listener aqui para poder cerrar el dialogo unicamente cuando ya se hayan validado todos los campos
                comentBuilder.setView(vistaEvaluacion);


                final AlertDialog dialog = comentBuilder.create();
                //TextView titulo = (TextView) dialog.findViewById(android.R.id.title);
                //titulo.setTextSize(40);
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        Button boton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                        boton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!comentario.getText().toString().isEmpty()){
                                    Toast.makeText(v.getContext(), "Comentario exitoso", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(v.getContext(), "Por Favor llene el campo de comentario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialog.show();

                TextView msmTxt = (TextView) dialog.findViewById(android.R.id.message);
                Button btnOk = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button btnCancel = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                btnOk.setPadding(20,0,20,50);
                btnCancel.setPadding(20,0,20,50);

                btnOk.setTextSize(28);
                btnCancel.setTextSize(28);
                msmTxt.setTextSize(24);

                /*btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/
            }
        });

        holder.imgVCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaTerapias.size();
    }

    /*@Override
    public void onClick(View v) {
        if (v.getId() == boton_test.getId()){
         //   Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        } else {
          //  Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }*/

    public static class TerapiaViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_pic;
        ImageView profile_pic_detalle;
        TextView txtNombre;
        //TextView txtTemporizador;
        TextView txtHora;
        TextView txtNombreDetalle;
        TextView txtEdadDetalle;
        ImageButton btnPausa;
        ImageButton imgVComentario;
        ImageButton imgVStop;
        ImageButton imgVPause;
        ImageButton imgVCamara;
        ImageButton imgVVerPerfil;
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
            imgVComentario = (ImageButton) itemView.findViewById(R.id.imageViewComment);
            imgVStop = (ImageButton) itemView.findViewById(R.id.imageViewStop);
            imgVPause = (ImageButton) itemView.findViewById(R.id.imageViewPause);
            imgVCamara = (ImageButton) itemView.findViewById(R.id.imageViewGallery);
            imgVVerPerfil = (ImageButton) itemView.findViewById(R.id.imageViewViewTherapy);
        }
    }

}
