package reactiva.reactivamovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class VerPerfilActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnHistorialTerapias;

    //ELEMENTOS NECESARIOS PARA LA LISTA DE OBSERVACIONES MEDICAS
    ArrayList<ObservacionMedica> observacionMedicas;
    private FrameLayout frameObservMed;
    private RecyclerView listaDeObservacionesMedicas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        btnHistorialTerapias = (Button) findViewById(R.id.btnHistorialTerapias);

        btnHistorialTerapias.setOnClickListener(this);
        funciones_del_menu();


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


        ///DEFINO EL LAYOUT DE OBSERVACIONES MEDDICAS
        LinearLayoutManager llmobsmed = new LinearLayoutManager(this);
        llmobsmed.setOrientation(LinearLayoutManager.VERTICAL);
        listaDeObservacionesMedicas.setLayoutManager(llmobsmed);


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
    private void funciones_del_menu(){
        clicks_del_menu();
        activar_menu();
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        lyt_menu.setVisibility(LinearLayout.GONE);

    }
    private void clicks_del_menu(){
        final ImageButton btn_perfil=(ImageButton)findViewById(R.id.btn_perfil);
        btn_perfil.setImageDrawable(getDrawable(R.drawable.perfil_activo));

        final ImageButton btn_terapias=(ImageButton)findViewById(R.id.btn_terapias);
        btn_terapias.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerPerfilActivity.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_calendario=(ImageButton)findViewById(R.id.btn_calendario);
        btn_calendario.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerPerfilActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_paciente=(ImageButton)findViewById(R.id.btn_paciente);
        btn_paciente.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerPerfilActivity.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_historial=(ImageButton)findViewById(R.id.btn_historial);
        btn_historial.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerPerfilActivity.this, VerHistorialTerapias.class);
                startActivity(intent);
            }
        });

        btn_perfil.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerPerfilActivity.this, VerPerfilActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean menu_activo(){
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        int dato= lyt_menu.getVisibility();
        if(dato==LinearLayout.VISIBLE){
            return true;
        }else {
            return false;
        }
    }

    private void activar_menu() {
        final ImageButton btn_oc=(ImageButton)findViewById(R.id.btn_oc);
        btn_oc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
                if(menu_activo()){
                    lyt_menu.setVisibility(LinearLayout.GONE);
                    btn_oc.setImageDrawable(getDrawable(R.drawable.menu));
                }else {
                    lyt_menu.setVisibility(LinearLayout.VISIBLE);
                    btn_oc.setImageDrawable(getDrawable(R.drawable.menu_close));
                }
            }
        });
    }






/*
    ///INICIALIZO MI ADAPTADOR CON EL ARRAYLITS DE MIS OBSERVACIONES MEDICAS
    public void inicializarAdaptadorObservacionesMedicas() {
        ObservacionMedicaAdaptador adaptador = new ObservacionMedicaAdaptador(observacionMedicas);
        listaDeObservacionesMedicas.setAdapter(adaptador);
    }


    public void inicializarAdaptadorTerapiasAnteriores() {
        TerapiaAnteriorAdaptador adaptador2 = new TerapiaAnteriorAdaptador(terapiasAnterioresData);
        listaDeTerapiasAnteriores.setAdapter(adaptador2);
    }


    public void incializarListaDeObservacionesMedicas() {

        observacionMedicas = new ArrayList<ObservacionMedica>();
        observacionMedicas.add(new ObservacionMedica("29 Julio 2017","Dr. Jose Morales","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("20 Julio 2017","Dr. Juan Zurita","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("18 Julio 2017","Dr. Fernando Sanchez","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("15 Julio 2017","Dr. Edgar Moreira","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("12 Julio 2017","Dr. Made Velasco","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("10 Julio 2017","Dr. Carla Garcia","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("5 Julio 2017","Dr. Jose Morales","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));

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
