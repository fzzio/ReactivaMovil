package reactiva.reactivamovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ImageView;

import android.widget.ScrollView;

import java.util.ArrayList;

public class VerHistorialTerapias extends AppCompatActivity {

    //ELEMENTOS NECESARIO PARA LA LISTA DE TERAPIAS ANTERIORES
    ArrayList<TerapiaAnterior> terapiasAnterioresData;
    private FrameLayout frameRegistroTerapiasAnteriores;
    private RecyclerView registroDeTerapiasAnteriores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_historial_terapias);


        ImageView imagenOjoHumano = (ImageView) findViewById(R.id.imgVerTerapia);



        frameRegistroTerapiasAnteriores = (FrameLayout) findViewById(R.id.frameTerapAnteriores);
        registroDeTerapiasAnteriores    = (RecyclerView) findViewById(R.id.rvTerapiasAnteriores);


        LinearLayoutManager llRegistroTera = new LinearLayoutManager(this);
        llRegistroTera.setOrientation(LinearLayoutManager.VERTICAL);
        registroDeTerapiasAnteriores.setLayoutManager(llRegistroTera);


        inicializadorTerapiasRegistrosData();
        inicilaizarAdaptadorRegistroTerapiasAnteriores();


        ((ScrollView)registroDeTerapiasAnteriores.getParent()).removeView(registroDeTerapiasAnteriores);
        frameRegistroTerapiasAnteriores.addView(registroDeTerapiasAnteriores);
        funciones_del_menu();

    }

    public void inicilaizarAdaptadorRegistroTerapiasAnteriores(){
        TerapiaAnteriorAdaptador adaptadorRegistroTerapiasAnte = new TerapiaAnteriorAdaptador(terapiasAnterioresData);
        registroDeTerapiasAnteriores.setAdapter(adaptadorRegistroTerapiasAnte);

    }

    public void inicializadorTerapiasRegistrosData () {
        terapiasAnterioresData = new ArrayList<TerapiaAnterior>();
        terapiasAnterioresData.add(new TerapiaAnterior("15 Jul. 2017","Daniel Garcia Arreaga"));
        terapiasAnterioresData.add(new TerapiaAnterior("10 Jul. 2017","Fernando Sanchez Garcia"));
        terapiasAnterioresData.add(new TerapiaAnterior("5 Jul. 2017","Edgar Moreira Arreaga"));
        terapiasAnterioresData.add(new TerapiaAnterior("1 Jul. 2017","Israel Zurita Garcia"));
        terapiasAnterioresData.add(new TerapiaAnterior("25 Jun. 2017","Manuel Zurita Arreaga"));
        terapiasAnterioresData.add(new TerapiaAnterior("20 Jun. 2017","Kevin Sanchez Arreaga"));
        terapiasAnterioresData.add(new TerapiaAnterior("15 Jun. 2017","Daniel Garcia Arreaga"));
        terapiasAnterioresData.add(new TerapiaAnterior("10 Jul. 2017","Dolores Garcia Arreaga"));
    }
    private void funciones_del_menu(){
        clicks_del_menu();
        activar_menu();
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        lyt_menu.setVisibility(LinearLayout.GONE);
    }
    private void clicks_del_menu(){
        final ImageButton btn_historial=(ImageButton)findViewById(R.id.btn_historial);
        btn_historial.setImageDrawable(getDrawable(R.drawable.historial_activo));

        final ImageButton btn_terapias=(ImageButton)findViewById(R.id.btn_terapias);
        btn_terapias.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerHistorialTerapias.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_calendario=(ImageButton)findViewById(R.id.btn_calendario);
        btn_calendario.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerHistorialTerapias.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_paciente=(ImageButton)findViewById(R.id.btn_paciente);
        btn_paciente.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerHistorialTerapias.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });

        btn_historial.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerHistorialTerapias.this, VerHistorialTerapias.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_perfil=(ImageButton)findViewById(R.id.btn_perfil);
        btn_perfil.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerHistorialTerapias.this, VerPerfilActivity.class);
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








}
