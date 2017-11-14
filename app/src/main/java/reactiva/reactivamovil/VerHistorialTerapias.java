package reactiva.reactivamovil;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ImageView;

import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import reactiva.reactivamovil.adapters.TerapiaAnteriorAdaptador;
import reactiva.reactivamovil.classes.TerapiaAnterior;

public class VerHistorialTerapias extends AppCompatActivity {

    //ELEMENTOS NECESARIO PARA LA LISTA DE TERAPIAS ANTERIORES
    ArrayList<TerapiaAnterior> terapiasAnterioresData;
    private RecyclerView registroDeTerapiasAnteriores;

    String url =Utils.URL+"/ReactivaWeb/index.php/services/patientHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_historial_terapias);

        //Recibo los datos del paciente
        Bundle bundle = getIntent().getExtras();
        final String IdPacienteF = bundle.getString("IdPaciente");
        String nombrePacienteF = bundle.getString("fullName");

        TextView observacionMedica   = (TextView) findViewById(R.id.tvObservacionMedicaPrincipal);
        TextView fechaObservacion    = (TextView) findViewById(R.id.tvFechaUltimaObsMedica);
        TextView doctorObservacion   = (TextView) findViewById(R.id.tvDoctorUltimaObsMedica);
        final TextView nombrePaciente      = (TextView) findViewById(R.id.tvHTNombreCompletoPaciente);
        TextView encabezadoUltimaObs = (TextView) findViewById(R.id.tvHTultimaObservacion);
        TextView encabezadoRegistro  = (TextView) findViewById(R.id.tvHTregistro);



        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        Typeface fontSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Bold.ttf");
        Typeface fontBlack = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Black.ttf");


        //Seteo los datos del paciente en la vista
        nombrePaciente.setText(nombrePacienteF);

        fechaObservacion.setTypeface(fontBold);
        doctorObservacion.setTypeface(fontBold);
        observacionMedica.setTypeface(fontMedium);

        nombrePaciente.setTypeface(fontBold);
        encabezadoUltimaObs.setTypeface(fontMedium);
        encabezadoRegistro.setTypeface(fontMedium);

        final TextView noTerapiasAnteriores = new TextView(this);
        noTerapiasAnteriores.setText("No Existen Terapias Anteriores");
        noTerapiasAnteriores.setTextSize(30);
        noTerapiasAnteriores.setGravity(1);
        noTerapiasAnteriores.setTypeface(fontBold);

        registroDeTerapiasAnteriores    = (RecyclerView) findViewById(R.id.rvTerapiasAnteriores);

        /// llamo al web services para ver el histroial del terapias

        url = Utils.URL+"/ReactivaWeb/index.php/services/patientHistory";
        url = url + "?id=" + IdPacienteF;
        Log.d("Ruta al web service: ", url);
        ////Uso del web service para traer la edad y las extremidasdes del paciente a ejercitar

        LinearLayoutManager llRegistroTera = new LinearLayoutManager(this);
        llRegistroTera.setOrientation(LinearLayoutManager.VERTICAL);
        registroDeTerapiasAnteriores.setLayoutManager(llRegistroTera);

        inicializadorTerapiasRegistrosData();

        Menu.funciones_del_menu(VerHistorialTerapias.this,getIntent().getExtras().getString("nombre"),"HISTORIAL DE TERAPIAS");

    }

    public void inicilaizarAdaptadorRegistroTerapiasAnteriores(){
        TerapiaAnteriorAdaptador adaptadorRegistroTerapiasAnte = new TerapiaAnteriorAdaptador(terapiasAnterioresData,this);
        registroDeTerapiasAnteriores.setAdapter(adaptadorRegistroTerapiasAnte);

    }

    public void inicializadorTerapiasRegistrosData () {

        terapiasAnterioresData = new ArrayList<TerapiaAnterior>();
        terapiasAnterioresData.add(new TerapiaAnterior("15 Jul. 2017","Daniel Garcia Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("10 Jul. 2017","Fernando Sanchez Garcia",""));
        terapiasAnterioresData.add(new TerapiaAnterior("5 Jul. 2017","Edgar Moreira Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("1 Jul. 2017","Israel Zurita Garcia",""));
        terapiasAnterioresData.add(new TerapiaAnterior("25 Jun. 2017","Manuel Zurita Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("20 Jun. 2017","Kevin Sanchez Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("15 Jun. 2017","Daniel Garcia Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("10 Jul. 2017","Dolores Garcia Arreaga",""));

        inicilaizarAdaptadorRegistroTerapiasAnteriores();
    }

    public void getHistorial(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequestX = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("respuesta", String.valueOf(response));
                try {
                    JSONObject dataHistory = new JSONObject(response);
                    JSONObject dataBasic = dataHistory.getJSONObject("patient");
                    String namePaciente = dataBasic.getString("fullname");
                    Log.d(" Historial Paciente: ", namePaciente);

                    if (dataHistory.getJSONArray("therapies").length()== 0) {
                        Log.d("Historial Paciente H: ", "No tiene terapias anteriores");
                        Toast.makeText(getApplicationContext(),"No tiene Terapias Anteriores",Toast.LENGTH_LONG).show();
                        //Si no existe ningun elemento muestro un mensahj que no hay terapias
                        //inicializadorTerapiasRegistrosData();
                        //inicilaizarAdaptadorRegistroTerapiasAnteriores();

                    } else {
                        JSONArray terapiasHistorial = dataHistory.getJSONArray("therapies");
                        Log.d("Historial Paciente H: ", String.valueOf(terapiasHistorial));
                        //Array list para agregar terapias anteriores
                        terapiasAnterioresData = new ArrayList<TerapiaAnterior>();

                        for (int i = 0; i < terapiasHistorial.length(); i++){
                            String date = terapiasHistorial.getJSONObject(i).getString("date");
                            Log.d("Historial Fecha: ", date);
                            String terapista = terapiasHistorial.getJSONObject(i).getString("therapist");
                            String idTerapiaAnterior = terapiasHistorial.getJSONObject(i).getString("id_therapy");
                            terapiasAnterioresData.add(new TerapiaAnterior(date,terapista,idTerapiaAnterior));

                        }

                        ///agrego los elmentos del web service al arraylist de terapias anteriores
                        ///inicializadorTerapiasRegistrosData();
                        inicilaizarAdaptadorRegistroTerapiasAnteriores();
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
        })/*{
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("id",IdPacienteF);
                return params;
            }
        }*/;

        requestQueue.add(stringRequestX);
    }

}
