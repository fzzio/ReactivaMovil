package reactiva.reactivamovil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.transition.TransitionManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import reactiva.reactivamovil.db.ConstructorObservacionTerapia;

/**
 * Created by edgardan on 18/07/2017.
 */

public class recyclerTerapiaAdaptador extends RecyclerView.Adapter<recyclerTerapiaAdaptador.TerapiaViewHolder> {

    List<ItemTerapiaView> listaTerapias;
    private int expandedPosition = -1;
    public String response;
    public EditText comentario;
    int estadoPaciente = -1;
    boolean flag = false;
    String tiempoTotalTerapia;
    int tiempoTotalInt;

    Handler customHandler = new Handler();
    long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime = 0L;

    Context context;
    Activity activity;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    private Bitmap bitmap;
    ImageView urlpic;

    ConstructorObservacionTerapia constructorObservacionTerapia;

    private static final int CAMERA_PIC_REQUEST = 1337;

    //Typeface type = Typeface.createFromAsset(activity.getAssets(),"fonts/Montserrat-Regular.ttf");

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
    public void onBindViewHolder(final TerapiaViewHolder holder, final int position) {

        final ItemTerapiaView terapiaView = listaTerapias.get(position);

        holder.profile_pic.setImageResource(listaTerapias.get(position).getProfile_pic());
        //bitmap = getBitmapFromURL(listaTerapias.get(position).getProfile_pic());
        //holder.profile_pic.setImageBitmap(bitmap);
        //holder.profile_pic.
        //loadImageFromURL(listaTerapias.get(position).getProfile_pic(), holder.profile_pic);

        holder.txtNombre.setText(listaTerapias.get(position).getNombre());
        //holder.txtTemporizador.setText(listaTerapias.get(position).getTemporizador());
        holder.profile_pic_detalle.setImageResource(listaTerapias.get(position).getProfile_pic());
        holder.txtHora.setText(listaTerapias.get(position).getHora());
        holder.txtNombreDetalle.setText(listaTerapias.get(position).getNombre_detalle());
        holder.txtEdadDetalle.setText(listaTerapias.get(position).getEdad_detalle());

        //holder.btnPausa.setImageResource(listaTerapias.get(position).getBtn_pausa());//poner imagen que es

        holder.imgVComentario.setBackgroundResource(listaTerapias.get(position).getBtn_comentario());
        //////////////////////////////////
        holder.imgVStart.setBackgroundResource(listaTerapias.get(position).getBtn_start());
        //////////////////////////
        holder.imgVStop.setBackgroundResource(listaTerapias.get(position).getBtn_stop());
        holder.imgVPause.setBackgroundResource(listaTerapias.get(position).getBtn_pause_detail());
        holder.imgVCamara.setBackgroundResource(listaTerapias.get(position).getBtn_camara());

        //holder.imgExtremidad1.setImageResource(listaTerapias.get(position).getExtremidades_pic().get(0));
        //holder.imgExtremidadDetalle1.setImageResource(listaTerapias.get(position).getExtremidades_pic().get(0));

        ///////////////////////////////////////////////////////////////////////////////////
        //cargarExtremidades(listaTerapias.get(position).getExtremidades_pic(), holder);
        //////////////////////////////////////////////////////////////////////////////////

        holder.imgVVerPerfil.setBackgroundResource(listaTerapias.get(position).getVer_perfil());

        //holder.txtNombre.setTypeface(type);

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

        final Runnable updateTimerThread = new Runnable() {
            @Override
            public void run() {

                if(flag){
                    return;
                }

                timeInMilliseconds = 0+(SystemClock.uptimeMillis()-startTime);
                //se le suma la cantidad de tiempo transucrrido en millisegundos, setearlo como variable global y cargar
                updateTime = timeSwapBuff+timeInMilliseconds;
                int secs= (int) (updateTime/1000);
                int mins= secs/60;
                secs%=60;
                int milliseconds= (int) (updateTime%1000);
                holder.txtHora.setText(new DecimalFormat("00").format(mins)
                        +":"+new DecimalFormat("00").format(secs)
                        +":"+new DecimalFormat("000").format(milliseconds));
                customHandler.postDelayed(this,0);
                //Log.d("mins", ""+mins);
                Log.d("secs", ""+secs);

            }
        };

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for (Iterator<ItemTerapiaView> i = listaTerapias.iterator(); i.hasNext();) {
                    ItemTerapiaView item = i.next();
                    item.setEstado(false);
                }
                listaTerapias.get(position).setEstado(true);*/

                //obtengo todos los registros de terapias para poder cerrar todos menos el que se ha clickeado
                //ViewGroup recyclerv = (ViewGroup) v.getParent();
                RecyclerView recyclerv = (RecyclerView) v.getParent();
                //Log.d("hijos", "Value: " + Integer.toString(recyclerv.getChildCount()));

                for(int i=0; i<recyclerv.getChildCount();i++){
                    //if(i!=position){
                    recyclerv.getChildAt(i).findViewById(R.id.section).setVisibility(View.GONE);
                    recyclerv.getChildAt(i).findViewById(R.id.header).setVisibility(View.VISIBLE);
                    //Log.d("Title", "Value: " + Integer.toString(i));
                    //timeSwapBuff+=timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                    //}
                }

                boolean isExpanded = position==expandedPosition;
                expandedPosition = isExpanded ? -1:position;
                if (position == expandedPosition) {
                    v.findViewById(R.id.header).setVisibility(View.GONE);
                    v.findViewById(R.id.section).setVisibility(View.VISIBLE);
                } else {
                    v.findViewById(R.id.header).setVisibility(View.VISIBLE);//check v
                    v.findViewById(R.id.section).setVisibility(View.GONE);
                }
                //Log.d("Posicion row", "Value: " + Integer.toString(position));
                //Log.d("posicion expandida", "Value: " + Integer.toString(expandedPosition));
            }
        });


        holder.imgVComentario.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    holder.imgVComentario.setBackgroundResource(R.drawable.comment_active);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    holder.imgVComentario.setBackgroundResource(R.drawable.comment);

                    AlertDialog.Builder comentBuilder = new AlertDialog.Builder(v.getContext());
                    LayoutInflater li = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View vistaComent = li.inflate(R.layout.terapia_comentario, null);

                    comentario = (EditText) vistaComent.findViewById(R.id.editTxtComentario);
                    comentBuilder.setNegativeButton("Cancelar",null);
                    comentBuilder.setPositiveButton("Guardar", null);
                    comentBuilder.setView(vistaComent);
                    final AlertDialog dialog = comentBuilder.create();

                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(final DialogInterface dialog) {
                            holder.imgVComentario.setBackgroundResource(R.drawable.comment_active);
                            holder.imgVComentario.setBackgroundResource(R.drawable.comment);
                            Button boton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                            boton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(!comentario.getText().toString().isEmpty()){
                                        Toast.makeText(v.getContext(), "Comentario exitoso", Toast.LENGTH_SHORT).show();
                                        enviar_comentario(listaTerapias.get(position).getId_therapy());
                                        dialog.dismiss();
                                    }else{
                                        Toast.makeText(v.getContext(), "Por Favor llene el campo de comentario", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });

                    dialog.show();
                }

                return true;
            }
        });

        holder.imgVVerPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity,terapiaView.getNombre_detalle(),Toast.LENGTH_SHORT).show();
                //String id = listaTerapias.get(position).getId_therapy();
                //Toast.makeText(activity,listaTerapias.get(position).getExtremidades_pic().get(1).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity,BitacoraTerapia.class);
                //intent.putExtra("nombre",activity.getIntent().getExtras().getString("nombre"));
                intent.putExtra("id_therapy",listaTerapias.get(position).getId_therapy());
                intent.putExtra("id_consulta",listaTerapias.get(position).getId_consulta());
                intent.putExtra("id_patient",listaTerapias.get(position).getId_patient());
                intent.putExtra("full_name",listaTerapias.get(position).getNombre());

                /*for(int i = 0; i< listaTerapias.get(position).getExtremidades_pic().size();i++){
                    intent.putExtra("extremidad"+i, listaTerapias.get(position).getExtremidades_pic().get(i));
                }*/

                //Log.d("intent0", listaTerapias.get(position).getId_therapy());
                //intent.putExtra("id_therapy",activity.getIntent().getExtras().getString("id_therapy"));
//                Log.d("intentX", activity.getIntent().getExtras().getString("id_therapy"));
                activity.startActivityForResult(intent, 0); //edgar
                //activity.startActivity(intent);
            }
        });


        holder.imgVStop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                String tiempoTotalTerapia;

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    holder.imgVStop.setBackgroundResource(R.drawable.finish_active);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    holder.imgVStop.setBackgroundResource(R.drawable.finish);

                    flag = true;

                    //ContextThemeWrapper ctw = new ContextThemeWrapper(v.getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert);
                    //timeSwapBuff+=timeInMilliseconds;
                    //esta linea de codigo me suma lo anterior
                    customHandler.removeCallbacks(updateTimerThread);
                    tiempoTotalTerapia = holder.txtHora.getText().toString();
                    Log.d("cronometro", tiempoTotalTerapia);
                    Log.d("cronometro", tiempoTotalTerapia);


                    final AlertDialog.Builder comentBuilder = new AlertDialog.Builder(v.getContext());

                    LayoutInflater li = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View vistaEvaluacion = li.inflate(R.layout.terapia_evaluacion, null);

                    //*****************************************************************************************//
                    //final EditText comentario = (EditText) vistaEvaluacion.findViewById(R.id.edit_text_obs);
                    comentario = (EditText) vistaEvaluacion.findViewById(R.id.edit_text_obs);
                    //****************************************************************************************//

                    comentBuilder.setNegativeButton("Cancelar",null);
                    comentBuilder.setPositiveButton("Guardar", null);//no se setea el listener aqui para poder cerrar el dialogo unicamente cuando ya se hayan validado todos los campos
                    comentBuilder.setView(vistaEvaluacion);

                    final AlertDialog dialog = comentBuilder.create();
                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(final DialogInterface dialog) {
                            Button boton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);

                            ((AlertDialog) dialog).findViewById(R.id.imgv_alto).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImageView reaccion_osa_triste = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_alto);
                                    ImageView reaccion_osa_normal = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_medio);
                                    ImageView reaccion_osa_feliz = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_bajo);
                                    reaccion_osa_triste.setImageResource(R.drawable.reaccion3_activa);
                                    reaccion_osa_normal.setImageResource(R.drawable.reaccion2_inactiva);
                                    reaccion_osa_feliz.setImageResource(R.drawable.reaccion1_inactiva);

                                    estadoPaciente = 0;
                                }
                            });

                            ((AlertDialog) dialog).findViewById(R.id.imgv_medio).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImageView reaccion_osa_triste = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_alto);
                                    ImageView reaccion_osa_normal = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_medio);
                                    ImageView reaccion_osa_feliz = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_bajo);
                                    reaccion_osa_triste.setImageResource(R.drawable.reaccion3_inactiva);
                                    reaccion_osa_normal.setImageResource(R.drawable.reaccion2_activa);
                                    reaccion_osa_feliz.setImageResource(R.drawable.reaccion1_inactiva);

                                    estadoPaciente = 1;
                                }
                            });

                            ((AlertDialog) dialog).findViewById(R.id.imgv_bajo).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImageView reaccion_osa_triste = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_alto);
                                    ImageView reaccion_osa_normal = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_medio);
                                    ImageView reaccion_osa_feliz = (ImageView) ((AlertDialog) dialog).findViewById(R.id.imgv_bajo);
                                    reaccion_osa_triste.setImageResource(R.drawable.reaccion3_inactiva);
                                    reaccion_osa_normal.setImageResource(R.drawable.reaccion2_inactiva);
                                    reaccion_osa_feliz.setImageResource(R.drawable.reaccion1_activa);

                                    estadoPaciente = 2;
                                }
                            });
                            boton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(!comentario.getText().toString().isEmpty() && estadoPaciente!=-1){
                                        Toast.makeText(v.getContext(), "Comentario exitoso", Toast.LENGTH_SHORT).show();
                                        finalizar_terapia(listaTerapias.get(position).getId_therapy(), estadoPaciente);
                                        dialog.dismiss();
                                    }else{
                                        Toast.makeText(v.getContext(), "Por Favor llene el campo de comentario", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                    dialog.show();

                }

                return true;
            }
        });

        /*holder.imgVCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activity.startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });*/

        holder.imgVCamara.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    holder.imgVCamara.setBackgroundResource(R.drawable.camera_active);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    holder.imgVCamara.setBackgroundResource(R.drawable.camera);
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activity.startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                }
                return true;
            }
        });




        holder.imgVStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    holder.imgVStart.setBackgroundResource(R.drawable.play_active);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    holder.imgVStart.setBackgroundResource(R.drawable.play_active);
                    holder.imgVStart.setVisibility(View.GONE);
                    holder.imgVPause.setVisibility(View.VISIBLE);

                    startTime = SystemClock.uptimeMillis();
                    //starttime empieza el cronometro

                    customHandler.postDelayed(updateTimerThread, 0);
                    //aqui pasar los valores

                    flag = false;

                }
                return true;
            }
        });

        holder.imgVPause.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    holder.imgVPause.setBackgroundResource(R.drawable.pause);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    holder.imgVPause.setBackgroundResource(R.drawable.pause_active);
                    holder.imgVPause.setVisibility(View.GONE);
                    holder.imgVStart.setVisibility(View.VISIBLE);
                    customHandler.removeCallbacks(updateTimerThread);
                }
                return false;
            }
        });

        holder.imgExtremidadDetalle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Extremidad 1", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void enviar_comentario(final String id_therapy){
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date = simpleDateFormat.format(calendar.getTime());

        int id_therapyint = Integer.parseInt(id_therapy);
        Log.d("Entrea ", id_therapy);
        Log.d("comentariotext ", comentario.getText().toString().trim());
        Log.d("fechaa ", Date);
        //Log.d("estadoPacLog", ""+estadoPaciente);


        ConstructorObservacionTerapia constructorObservacionTerapia = new ConstructorObservacionTerapia(context);
        constructorObservacionTerapia.insertarNuevoComentarioByIdTerapia(id_therapyint,Date,comentario.getText().toString().trim());
        //Toast.makeText(this.context, id_therapy, Toast.LENGTH_SHORT).show();

        String url = Utils.URL+"/ReactivaWeb/index.php/requests/savecomments";//falta url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", response);
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_therapy", id_therapy);
                params.put("comentario", comentario.getText().toString().trim());
                params.put("tiempo", (""+Date));
                //params.put("estado_paciente", (""+estadoPaciente));
                //falta enviar eleccion de estado del paciente
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void finalizar_terapia(final String id_therapy, final int estado_paciente){
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date = simpleDateFormat.format(calendar.getTime());

        int id_therapyint = Integer.parseInt(id_therapy);
        Log.d("Entrea ", id_therapy);
        Log.d("comentariotext ", comentario.getText().toString().trim());
        Log.d("fechaa ", Date);
        Log.d("estadoPacLog", ""+estadoPaciente);


        ConstructorObservacionTerapia constructorObservacionTerapia = new ConstructorObservacionTerapia(context);
        constructorObservacionTerapia.insertarNuevoComentarioByIdTerapia(id_therapyint,Date,comentario.getText().toString().trim());
        //Toast.makeText(this.context, id_therapy, Toast.LENGTH_SHORT).show();

        String url = Utils.URL+"/ReactivaWeb/index.php/requests/endTherapy";//falta url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_therapy", id_therapy);
                params.put("estado_paciente", (""+estadoPaciente));
                params.put("tiempo_tr", (""));
                params.put("comentario", comentario.getText().toString().trim());
                params.put("tiempo", (""+Date));

                //falta enviar eleccion de estado del paciente
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void enviar_foto(){

    }

    public void cargarExtremidades(ArrayList<Integer> lstExtremidades, TerapiaViewHolder holder){

        for(int i=0;i<lstExtremidades.size();i++){
            if(lstExtremidades.get(i)==0){
                //cargar brazo der
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_brazo_der);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_der);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_brazo_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_der);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_brazo_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_der);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_brazo_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_der);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_brazo_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_der);
                }
            }else if(lstExtremidades.get(i)==1){
                //brazo izq
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_brazo_izq);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_izq);

                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_brazo_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_izq);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_brazo_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_izq);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_brazo_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_izq);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_brazo_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_brazo_izq);
                }
            }else if(lstExtremidades.get(i)==2){
                //cadera
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_cadera);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cadera);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_cadera);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cadera);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_cadera);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cadera);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_cadera);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cadera);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_cadera);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cadera);
                }
            }else if(lstExtremidades.get(i)==3){
                //codo der
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_codo_der);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_der);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_codo_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_der);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_codo_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_der);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_codo_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_der);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_codo_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_der);
                }
            }else if(lstExtremidades.get(i)==4){
                //codo izq
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_codo_izq);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_izq);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_codo_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_izq);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_codo_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_izq);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_codo_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_izq);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_codo_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_codo_izq);
                }
            }else if(lstExtremidades.get(i)==5){
                //columna
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_columna);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_columna);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_columna);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_columna);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_columna);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_columna);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_columna);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_columna);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_columna);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_columna);
                }
            }else if(lstExtremidades.get(i)==6){
                //cuello
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_cuello_cabeza);
                }
            }else if(lstExtremidades.get(i)==7){
                //espalda
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_espalda);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_espalda);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_espalda);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_espalda);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_espalda);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_espalda);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_espalda);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_espalda);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_espalda);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_espalda);
                }
            }else if(lstExtremidades.get(i)==8){
                //mano der
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_mano_der);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_der);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_mano_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_der);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_mano_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_der);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_mano_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_der);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_mano_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_der);
                }
            }else if(lstExtremidades.get(i)==9){
                //mano izq
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_mano_izq);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_izq);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_mano_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_izq);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_mano_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_izq);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_mano_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_izq);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_mano_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_mano_izq);
                }
            }else if(lstExtremidades.get(i)==10){
                //pie der
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_pie_der);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_der);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_pie_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_der);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_pie_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_der);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_pie_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_der);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_pie_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_der);
                }
            }else if(lstExtremidades.get(i)==11){
                //pie izq
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_pie_izq);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_izq);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_pie_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_izq);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_pie_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_izq);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_pie_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_izq);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_pie_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pie_izq);
                }
            }else if(lstExtremidades.get(i)==12){
                //pierna der
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_pierna_der);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_der);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_pierna_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_der);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_pierna_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_der);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_pierna_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_der);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_pierna_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_der);
                }
            }else if(lstExtremidades.get(i)==13){
                //pierna izq
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_pierna_izq);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_izq);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_pierna_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_izq);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_pierna_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_izq);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_pierna_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_izq);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_pierna_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_pierna_izq);
                }
            }else if(lstExtremidades.get(i)==14){
                //talon der
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_talon_der);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_der);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_talon_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_der);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_talon_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_der);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_talon_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_der);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_talon_der);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_der);
                }
            }else if(lstExtremidades.get(i)==15) {
                //talon izq
                if(i==0){
                    holder.imgExtremidad1.setVisibility(View.VISIBLE);
                    holder.imgExtremidad1.setImageResource(R.drawable.cuerpo_talon_izq);
                    holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_izq);
                }else if(i==1){
                    holder.imgExtremidad2.setVisibility(View.VISIBLE);
                    holder.imgExtremidad2.setImageResource(R.drawable.cuerpo_talon_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_izq);
                }else if(i==2){
                    holder.imgExtremidad3.setVisibility(View.VISIBLE);
                    holder.imgExtremidad3.setImageResource(R.drawable.cuerpo_talon_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_izq);
                }else if(i==3){
                    holder.imgExtremidad4.setVisibility(View.VISIBLE);
                    holder.imgExtremidad4.setImageResource(R.drawable.cuerpo_talon_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_izq);
                }else if(i==4){
                    holder.imgExtremidad5.setVisibility(View.VISIBLE);
                    holder.imgExtremidad5.setImageResource(R.drawable.cuerpo_talon_izq);
                    //holder.imgExtremidadDetalle1.setImageResource(R.drawable.cuerpo_talon_izq);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return listaTerapias.size();
    }

    public Bitmap getBitmapFromURL (String src){
        try{
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        }catch (Exception e){
            //TODO handle exception
            e.printStackTrace();
            return null;
        }
    }

    /*private void loadImageFromURL (String url, ImageView imagen) {
        Picasso.with(context).load(url).placeholder(R.drawable.profile_m)
                .error(R.drawable.profile_m)
                .into(imagen, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }*/

    /*@Override
    public void onClick(View v) {
        if (v.getId() == boton_test.getId()){
         //   Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        } else {
          //  Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }*/

    public static class TerapiaViewHolder extends RecyclerView.ViewHolder{
        //TextView txt_terapias_activas;
        //TextView txt_titulo_terapias;

        ImageView profile_pic;
        ImageView profile_pic_detalle;
        TextView txtNombre;
        //TextView txtTemporizador;
        TextView txtHora;
        TextView txtNombreDetalle;
        TextView txtEdadDetalle;
        ImageButton btnPausa;
        Button imgVComentario;
        Button imgVStop;
        Button imgVPause;
        Button imgVCamara;
        Button imgVVerPerfil;
        Button imgVStart;

        ImageView imgExtremidad1;
        ImageView imgExtremidad2;
        ImageView imgExtremidad3;
        ImageView imgExtremidad4;
        ImageView imgExtremidad5;
        ImageView imgExtremidadDetalle1;

        //TextView txtSalaDetalle;

        public TerapiaViewHolder(View itemView) {
            super(itemView);

            //txt_terapias_activas = (TextView) itemView.findViewById(R.id.txt_terapias_activas_count);
            //txt_titulo_terapias = (TextView) itemView.findViewById(R.id.txt_titulo_terapias);
            profile_pic = (ImageView) itemView.findViewById(R.id.image_profile_pic);
            txtNombre = (TextView) itemView.findViewById(R.id.txtName);
            //txtTemporizador = (TextView) itemView.findViewById(R.id.txt_temporizador);
            profile_pic_detalle = (ImageView) itemView.findViewById(R.id.imageViewProfile);
            txtHora = (TextView) itemView.findViewById(R.id.txt_hora);
            txtNombreDetalle = (TextView) itemView.findViewById(R.id.txtNameDetalle);
            txtEdadDetalle = (TextView) itemView.findViewById(R.id.txtEdadDetalle);
            //txtSalaDetalle = (TextView) itemView.findViewById(R.id.txtSalaDetalle);
            //btnPausa = (ImageButton) itemView.findViewById(R.id.btnIniciarTerapia);
            imgVComentario = (Button) itemView.findViewById(R.id.imageViewComment);
            imgVStop = (Button) itemView.findViewById(R.id.imageViewStop);
            imgVPause = (Button) itemView.findViewById(R.id.imageViewPause);
            imgVCamara = (Button) itemView.findViewById(R.id.imageViewGallery);
            imgVVerPerfil = (Button) itemView.findViewById(R.id.imageViewViewTherapy);
            imgVStart = (Button) itemView.findViewById(R.id.imageViewStart);

            imgExtremidad1 = (ImageView) itemView.findViewById(R.id.imageViewExtremidad1);
            imgExtremidad2 = (ImageView) itemView.findViewById(R.id.imageViewExtremidad2);
            imgExtremidad3 = (ImageView) itemView.findViewById(R.id.imageViewExtremidad3);
            imgExtremidad4 = (ImageView) itemView.findViewById(R.id.imageViewExtremidad4);
            imgExtremidad5 = (ImageView) itemView.findViewById(R.id.imageViewExtremidad5);

            imgExtremidadDetalle1 = (ImageView) itemView.findViewById(R.id.imgViewExtDetalle1);

            Typeface type = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Montserrat-Regular.ttf");
            txtNombre.setTypeface(type);
            txtHora.setTypeface(type);
            txtNombreDetalle.setTypeface(type);
            txtEdadDetalle.setTypeface(type);

            //txt_titulo_terapias.setTypeface(type);
            //txt_terapias_activas.setTypeface(type);
        }
    }

}
