package reactiva.reactivamovil;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import reactiva.reactivamovil.adapters.ObservacionMedicaAdaptador;
import reactiva.reactivamovil.adapters.ObservacionTerapiaAdaptador;
import reactiva.reactivamovil.classes.ObservacionMedica;
import reactiva.reactivamovil.classes.ObservacionTerapia;
import reactiva.reactivamovil.classes.TerapiaAnterior;
import reactiva.reactivamovil.db.ConstructorObservacionTerapia;
import reactiva.reactivamovil.presentadores.IRecyclerBitacora;

public class BitacoraTerapiaAnterior extends AppCompatActivity{


    //ELEMENTOS NECESARIOS PARA LA LISTA DE OBSERVACIONES MEDICAS
    ArrayList<ObservacionMedica> observacionMedicasData;
    private FrameLayout frameSucesosBitacoraTerapia;
    private RecyclerView listaDeObservacionesMedicas;

    ConstructorObservacionTerapia constructorObservacionTerapia;

    int idprueba;
    String url ="http://107.170.105.224:6522/ReactivaWeb/index.php/services/getTherapyHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitacora_terapia_anterior);


        /// recibo datos de la aplicacion anterior el id de la terapia el historial
        Bundle bundle = getIntent().getExtras();
        final String IdTerapiaAnterior = bundle.getString("IDTerapia");
        //int idFinal = Integer.parseInt(IdPaciente);
        Log.d(" id final paciente", IdTerapiaAnterior);

        final TextView nombrePacienteBitacora   = (TextView) findViewById(R.id.tvBpaciente);
        final TextView fechaBitacora    = (TextView) findViewById(R.id.tvBfecha);

        final TextView nombreTerapista = (TextView) findViewById(R.id.tvHTterapista);
        final TextView fechaTerapia = (TextView) findViewById(R.id.tvHTfecha);

        //nombrePacienteBitacora.setText(nombre);

        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        Typeface fontSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Bold.ttf");
        Typeface fontBlack = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Black.ttf");

        nombrePacienteBitacora.setTypeface(fontBold);
        //fechaBitacora.setTypeface(fontMedium);
        nombreTerapista.setTypeface(fontMedium);
        fechaTerapia.setTypeface(fontMedium);


        frameSucesosBitacoraTerapia = (FrameLayout) findViewById(R.id.frameObsMedicasBitacora);

        listaDeObservacionesMedicas = (RecyclerView) findViewById(R.id.rvObsMedicasBitacora);


        /// Lectura de datos del web services
        url = "http://107.170.105.224:6522/ReactivaWeb/index.php/services/getTherapyHistory";
        Log.d("Ruta al web service: ", url);

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        StringRequest stringRequestX = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("respuesta", String.valueOf(response));
                try {
                    JSONObject dataFull = new JSONObject(response);
                    JSONObject dataTerapia = dataFull.getJSONObject("therapy");

                    String namePaciente = dataTerapia.getString("fullname");
                    String nameTerapista = dataTerapia.getString("therapist");
                    String dateTerapiaAnte = dataTerapia.getString("date");

                    Log.d(" Historial Paciente: ", namePaciente);

                    nombrePacienteBitacora.setText(namePaciente);
                    //fechaBitacora.setText(dateTerapiaAnte);
                    nombreTerapista.setText(nameTerapista);
                    fechaTerapia.setText(dateTerapiaAnte);


                    if (dataFull.getJSONArray("history").length()== 0) {
                        Log.d("No llegaron los datos: ", "No terapias anteriorres y comentarios");
                        Toast.makeText(getApplicationContext(),"No Tiene Comentarios",Toast.LENGTH_LONG).show();
                        //Si no existe ningun elemento muestro un mensahj que no hay terapias
                        //inicializadorTerapiasRegistrosData();
                        //inicilaizarAdaptadorRegistroTerapiasAnteriores();
                        //frameRegistroTerapiasAnteriores.addView(noTerapiasAnteriores);

                    } else {
                        JSONArray comentariosTerapia = dataFull.getJSONArray("history");
                        Log.d("Historial Paciente H: ", String.valueOf(comentariosTerapia));
                        //Array list para agregar terapias anteriores
                        //terapiasAnterioresData = new ArrayList<TerapiaAnterior>();

                        observacionMedicasData = new ArrayList<ObservacionMedica>();
                        for (int i = 0; i < comentariosTerapia.length(); i++){
                            String horaComentario = comentariosTerapia.getJSONObject(i).getString("hour");
                            String detalleComentario = comentariosTerapia.getJSONObject(i).getString("msg");

                            Log.d("Hora del Comentario: ", horaComentario);
                            //String terapista = terapiasHistorial.getJSONObject(i).getString("therapist");
                            //String idTerapiaAnterior = terapiasHistorial.getJSONObject(i).getString("id_therapy");
                            //terapiasAnterioresData.add(new TerapiaAnterior(date,terapista,idTerapiaAnterior));


                            observacionMedicasData.add(new ObservacionMedica(horaComentario,detalleComentario));


                        }

                        ObservacionMedicaAdaptador adaptadorObservacionesBitacora = new ObservacionMedicaAdaptador(observacionMedicasData);
                        listaDeObservacionesMedicas.setAdapter(adaptadorObservacionesBitacora);

                        ///agrego los elmentos del web service al arraylist de terapias anteriores
                        ///inicializadorTerapiasRegistrosData();
                        //inicilaizarAdaptadorRegistroTerapiasAnteriores();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("id",IdTerapiaAnterior);
                return params;
            }
        };

        requestQueue.add(stringRequestX);



        generarLinearLayoutVertical();
        //LEEO LOS DATOS DE LA BASE Y LOS TRAIGO

        //idprueba = 5;
        //obtenerObservacionesByIdTerapia(idFinal);

        //mostrarDatosEnRVobsernacionesTerapia();



        ((ScrollView)listaDeObservacionesMedicas.getParent()).removeView(listaDeObservacionesMedicas);
        frameSucesosBitacoraTerapia.addView(listaDeObservacionesMedicas);

        ImageButton btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),VerHistorialTerapias.class);
                intent.putExtra("nombre",getIntent().getExtras().getString("nombre"));
                startActivityForResult(intent,0);
            }
        });
        //Menu.funciones_del_menu(BitacoraTerapia.this,getIntent().getExtras().getString("nombre"),"BITÁCORA DE TERAPIA");
    }

    public void inicializarAdaptadorObservacionesMedicas() {
        ObservacionMedicaAdaptador adaptadorObservacionesBitacora = new ObservacionMedicaAdaptador(observacionMedicasData);
        listaDeObservacionesMedicas.setAdapter(adaptadorObservacionesBitacora);

    }


    public void generarLinearLayoutVertical() {
        ///DEFINO EL LAYOUT DE OBSERVACIONES MEDDICAS
        LinearLayoutManager llBitacora = new LinearLayoutManager(this);
        llBitacora.setOrientation(LinearLayoutManager.VERTICAL);
        listaDeObservacionesMedicas.setLayoutManager(llBitacora);
    }
   /* public void inicializarListaDeObservacionesMedicas() {
        observacionMedicasData = new ArrayList<ObservacionMedica>();
        observacionMedicasData.add(new ObservacionMedica("9:00","Inicio"));
        observacionMedicasData.add(new ObservacionMedica("9:08","INICIO juego -Subir Escaleras-"));
        observacionMedicasData.add(new ObservacionMedica("9:12","Imagen"));
        observacionMedicasData.add(new ObservacionMedica("9:16","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
        observacionMedicasData.add(new ObservacionMedica("9:23","FIN juego -Subir Escaleras-"));
        observacionMedicasData.add(new ObservacionMedica("9:29","INICIO juego -Nombre De Juego-"));
        observacionMedicasData.add(new ObservacionMedica("9:36","PAUSA juego -Nombre De Juego-"));
        observacionMedicasData.add(new ObservacionMedica("9:36","El paciente tropezo y callo"));
        observacionMedicasData.add(new ObservacionMedica("9:45","REANUDA juego -Nombre de Juego-"));
        observacionMedicasData.add(new ObservacionMedica("9:50","PAUSA juego -Nombre De Juego-"));
        observacionMedicasData.add(new ObservacionMedica("10:00","El paciente se fue de oreja"));
        observacionMedicasData.add(new ObservacionMedica("10:05","REANUDA juego -Nombre de Juego-"));
        observacionMedicasData.add(new ObservacionMedica("10:15","PAUSA juego -Nombre De Juego-"));
        observacionMedicasData.add(new ObservacionMedica("10:25","El paciente otra vez se fue de oreja"));
        observacionMedicasData.add(new ObservacionMedica("10:35","REANUDA juego -Nombre de Juego-"));

    }*/





   /* *//**
     * Obtengo todas las observaciones por el ID de la terpaia
     * @param idTerapia
     *//*
    public void  obtenerObservacionesByIdTerapia(int idTerapia){
        constructorObservacionTerapia = new ConstructorObservacionTerapia(getApplicationContext());
        observacionMedicasData = constructorObservacionTerapia.obtenerObservacionesTerapiasXID(idTerapia);
    }

    public void mostrarDatosEnRVobsernacionesTerapia(){
        ObservacionTerapiaAdaptador adaptadorObservacionesBitacora = new ObservacionTerapiaAdaptador(observacionMedicasData);
        listaDeObservacionesMedicas.setAdapter(adaptadorObservacionesBitacora);
    }*/
}
