package reactiva.reactivamovil;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.GONE;

public class VerPerfilActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnHistorialTerapias;
    private Button btnIniciarTerapia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        TextView nombresPacienteVP          = (TextView) findViewById(R.id.tvVPPacienteNombres);
        TextView apellidosPacienteVP        = (TextView) findViewById(R.id.tvVPPacienteApellidos);
        TextView edadPacienteVP             = (TextView) findViewById(R.id.tvVPedadpaciente);
        TextView encabezadoProxCitaVP       = (TextView) findViewById(R.id.tvVPproxiCita);
        TextView fechaProximaCitaVP         = (TextView) findViewById(R.id.tvVPproxCitaData);
        TextView encabezadoZonasEjercitarVP = (TextView) findViewById(R.id.tvVPzonasEjercitar);
        TextView encabezadoInformacionLinkInfo = (TextView) findViewById(R.id.tvVPlinkInfoPaciente);


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

        btnHistorialTerapias = (Button) findViewById(R.id.btnHistorialTerapias);
        btnIniciarTerapia    = (Button) findViewById(R.id.btnIniciarTerapia);

        btnHistorialTerapias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),VerHistorialTerapias.class);
                intent.putExtra("nombre",getIntent().getExtras().getString("nombre"));
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
