package reactiva.reactivamovil;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.view.View.GONE;
import static com.android.volley.Request.Method.POST;

public class VerPerfilActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnHistorialTerapias;
    private Button btnIniciarTerapia;
    String textEdad;

    // Web service Ejemplo: http://107.170.105.224:6522/ReactivaWeb/index.php/services/therapyStartInfo?id=1

    String url ="http://107.170.105.224:6522/ReactivaWeb/index.php/services/therapyStartInfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        //Recibo los datos del paciente para crear el perfil del paciente indicado

        Bundle bundle = getIntent().getExtras();
        final String id_patientP = bundle.getString("id_patient");
        final String namePacienteP = bundle.getString("patient");
        String idTerapiapaciente = bundle.getString("id_therapy");
        Log.d("id perfil", id_patientP);
        Log.d("id perfil", namePacienteP);

        TextView nombresPacienteVP          = (TextView) findViewById(R.id.tvVPPacienteNombres);
        TextView apellidosPacienteVP        = (TextView) findViewById(R.id.tvVPPacienteApellidos);
        final TextView edadPacienteVP             = (TextView) findViewById(R.id.tvVPedadpaciente);
        TextView encabezadoProxCitaVP       = (TextView) findViewById(R.id.tvVPproxiCita);
        TextView fechaProximaCitaVP         = (TextView) findViewById(R.id.tvVPproxCitaData);
        TextView encabezadoZonasEjercitarVP = (TextView) findViewById(R.id.tvVPzonasEjercitar);
        TextView encabezadoInformacionLinkInfo = (TextView) findViewById(R.id.tvVPlinkInfoPaciente);

        String[] nameFull = namePacienteP.split(" ");
        String namesPacienteF = nameFull[0] + " " + nameFull[1];
        String lastNamepaciente = nameFull[2] + " " + nameFull[3];

        nombresPacienteVP.setText(namesPacienteF);
        apellidosPacienteVP.setText(lastNamepaciente);

        url = url + "?id="+ idTerapiapaciente;
        Log.d("Ruta al web service: ", url);
        ////Uso del web service para traer la edad y las extremidasdes del paciente a ejercitar

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        HashMap<String,String> parametros = new HashMap<>();
        parametros.put("id",id_patientP);

        JsonObjectRequest jsonObjectRequestX = new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("respuesta", String.valueOf(response));
                JSONObject edadP = null;
                try {
                    edadP = response.getJSONObject("patient");
                    String edadF = edadP.getString("age");
                    Log.d("respuesta2", edadP.toString());
                    Log.d("respuesta3", edadF);
                    textEdad = edadF + " años";
                    edadPacienteVP.setText(textEdad);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getMessage());
            }
        }

        );


               /* try {
                    JSONObject jsonPatient = response.getJSONObject("patient");
                    String edadPaciente = jsonPatient.getString("age");
                    Log.d("La edad es ",edadPaciente);
                    Log.d("respuesta", response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

        requestQueue.add(jsonObjectRequestX);

        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        Typeface fontSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Bold.ttf");
        Typeface fontBlack = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Black.ttf");

        nombresPacienteVP.setTypeface(fontBold);
        apellidosPacienteVP.setTypeface(fontBold);
        edadPacienteVP.setTypeface(fontMedium);

        encabezadoProxCitaVP.setTypeface(fontBold);
        fechaProximaCitaVP.setTypeface(fontMedium);
        encabezadoZonasEjercitarVP.setTypeface(fontBold);

        encabezadoInformacionLinkInfo.setTypeface(fontMedium);

        //Boton azul que lleva a la ventana historial de terapias
        btnHistorialTerapias = (Button) findViewById(R.id.btnHistorialTerapias);

        btnIniciarTerapia    = (Button) findViewById(R.id.btnIniciarTerapia);

        btnHistorialTerapias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),VerHistorialTerapias.class);
                intent.putExtra("IdPaciente", id_patientP);
                Log.d("idxxxx",id_patientP);
                intent.putExtra("fullName",namePacienteP);
                startActivity(intent);
            }
        });

        btnIniciarTerapia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JuegosTerapias.class);
                intent.putExtra("nombre",getIntent().getExtras().getString("nombre"));
                startActivity(intent);
            }
        });

        ImageButton btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
                intent.putExtra("nombre",getIntent().getExtras().getString("nombre"));
                startActivityForResult(intent,0);
            }
        });

        //Underline ~ver info del paciente
        final TextView tvVPlinkInfoPaciente = (TextView) findViewById(R.id.tvVPlinkInfoPaciente);
        tvVPlinkInfoPaciente.setPaintFlags(tvVPlinkInfoPaciente.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        Menu.funciones_del_menu(VerPerfilActivity.this,getIntent().getExtras().getString("nombre"),"INICIAR TERAPIA");


       /* LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);


        //ASOCIO MIS FRAMES A LOS FRAMES DE LA VISTA
        frameObservMed = (FrameLayout) findViewById(R.id.frameObMed);

        frameTerapiasAnteriores = (FrameLayout) findViewById(R.id.frameTerapAnteriores);


        //ASOCIO MIS RYCLERVIEWS A LOS DE LA VISTA PRINCIPAL
        //OBSERVACIONES MEDICAS
        listaDeObservacionesMedicas = (RecyclerView) findViewById(R.id.rvObsMedicas);
        //TERAPIAS MEDICAS ANTERIORES
        listaDeTerapiasAnteriores = (RecyclerView) findViewById(R.id.rvTerapiasAnteriores);





        LinearLayoutManager llmterapante = new LinearLayoutManager(this);
        llmterapante.setOrientation(LinearLayoutManager.VERTICAL);
        listaDeTerapiasAnteriores.setLayoutManager(llmterapante);

        //INICIALIZO EL ADAPTADOR DE OBSERVACIONES MEDICAS
        incializarListaDeObservacionesMedicas();

        inicializarListaDeTerapiasAnteriores();

        inicializarAdaptadorObservacionesMedicas();


        //INICIALIZO EL ADAPTADOR DE TERAPIAS ANTERIORES


        inicializarAdaptadorTerapiasAnteriores();


        ((ScrollView)listaDeObservacionesMedicas.getParent()).removeView(listaDeObservacionesMedicas);
        frameObservMed.addView(listaDeObservacionesMedicas);


        ((ScrollView)listaDeTerapiasAnteriores.getParent()).removeView(listaDeTerapiasAnteriores);
        frameTerapiasAnteriores.addView(listaDeTerapiasAnteriores);


*/
    }
/*
    ///INICIALIZO MI ADAPTADOR CON EL ARRAYLITS DE MIS OBSERVACIONES MEDICAS
    public void inicializarAdaptadorObservacionesMedicas() {
        ObservacionMedicaAdaptador adaptador = new ObservacionMedicaAdaptador(observacionMedicas);
        listaDeObservacionesMedicas.setAdapter(adaptador);
    }

    public void inicializarListaDeTerapiasAnteriores() {

        terapiasAnterioresData = new ArrayList<TerapiaAnterior>();
        terapiasAnterioresData.add(new TerapiaAnterior("15 jul. 2017","Daniel Garcia","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1"));
        terapiasAnterioresData.add(new TerapiaAnterior("10 jul. 2017","Ariel Cedenio","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1"));
        terapiasAnterioresData.add(new TerapiaAnterior("5 jul. 2017","Ariel Cedenio","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1"));
        terapiasAnterioresData.add(new TerapiaAnterior("1 jul. 2017","Rosa Maria","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1"));
        terapiasAnterioresData.add(new TerapiaAnterior("25 jun. 2017","Juan Piguave","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1"));
        terapiasAnterioresData.add(new TerapiaAnterior("20 jun. 2017","Luis Ernesto","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1"));
        terapiasAnterioresData.add(new TerapiaAnterior("15 jun. 2017","Sara Poveda","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1"));
        terapiasAnterioresData.add(new TerapiaAnterior("10 jun. 2017","Silvia Paredes","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1"));

    }*/


    @Override
    public void onClick(View v) {
        Intent mostrarHistorialterapias = new Intent(this,VerHistorialTerapias.class);
        startActivity(mostrarHistorialterapias);
    }
}
