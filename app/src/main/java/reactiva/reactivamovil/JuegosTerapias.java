package reactiva.reactivamovil;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;

import java.util.ArrayList;

import reactiva.reactivamovil.adapters.JuegoTerapiaAdaptador;
import reactiva.reactivamovil.adapters.ParametrosJuegoAdaptador;
import reactiva.reactivamovil.classes.JuegoTerapia;
import reactiva.reactivamovil.classes.ParametrosJuego;
import reactiva.reactivamovil.fragments.FragmentRVCitasDelDia;
import reactiva.reactivamovil.fragments.FragmentRVParametrosJuegos;

public class JuegosTerapias extends AppCompatActivity {



    //ELEMENTOS NECESARIO PARA LA TABLA DE PARAMETROS
    ArrayList<ParametrosJuego> parametrosJuegoDataList;
    private FrameLayout frameParametrosJuego;
    private RecyclerView recyclerViewParametrosJuego;


    //ELEMENTOS NECESARIO PARA LAS IMAGENES DE LOS JUEGOS DISPONIBLES
    ArrayList<JuegoTerapia> JuegoDataList;
    private FrameLayout frameJuegos;
    private RecyclerView recyclerViewJuegos;
    Fragment fragment,frg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juegos_terapia);



        Button btnGetParatametrosJuegos = (Button) findViewById(R.id.btGetParametrosJuegos);

        btnGetParatametrosJuegos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fragment = new FragmentRVParametrosJuegos();
                frg = new FragmentRVCitasDelDia();
                getSupportFragmentManager().beginTransaction().add(R.id.frameParametrosJuego,fragment).commit();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParametrosJuego,frg).commit();
            }
        });




        //TABLA DE PARAMETROS
        //frameParametrosJuego = (FrameLayout) findViewById(R.id.frameParametrosJuego);
        //recyclerViewParametrosJuego    = (RecyclerView) findViewById(R.id.rvParametrosJuego);

        //lISTA DE IMAGENES DE JUEGOS DISPONIBLES
        frameJuegos = (FrameLayout) findViewById(R.id.frameJuegos);
        //recyclerViewJuegos    = (RecyclerView) findViewById(R.id.rvJuegos);

        //LAYOUT TABLA PARAMETROS
        //LinearLayoutManager llParametrosJuego = new LinearLayoutManager(this);
        //llParametrosJuego.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerViewParametrosJuego.setLayoutManager(llParametrosJuego);

        //LAYOUT LISTA DE JUEGOS
        //LinearLayoutManager llJuegosDisponibles = new LinearLayoutManager(this);
        //llJuegosDisponibles.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recyclerViewJuegos.setLayoutManager(llJuegosDisponibles);

        //INICIALIZAR DATOS
        //inicializadorParametrosJuegoData();
        //inicializadorDataJuegosDisponibles();


        //INICALIZAR ADAPTADORES
        //inicilaizarAdaptadorParametrosJuego();
        //inicilaizarAdaptadorJuegosDisponibles();


        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        final RecyclerView recyclerViewJuegos = (RecyclerView) findViewById(R.id.rvJuegos);
        recyclerViewJuegos.setLayoutManager(layoutManager);

        inicializadorDataJuegosDisponibles();
        recyclerViewJuegos.setHasFixedSize(true);
        recyclerViewJuegos.setAdapter(new JuegoTerapiaAdaptador(JuegoDataList));
        recyclerViewJuegos.addOnScrollListener(new CenterScrollListener());


       // ((FrameLayout)recyclerViewParametrosJuego.getParent()).removeView(recyclerViewParametrosJuego);
        //frameParametrosJuego.addView(recyclerViewParametrosJuego);

        ((FrameLayout)recyclerViewJuegos.getParent()).removeView(recyclerViewJuegos);
        frameJuegos.addView(recyclerViewJuegos);

        funciones_del_menu();

    }


    public void inicilaizarAdaptadorParametrosJuego(){
        ParametrosJuegoAdaptador adaptadorParametrosJuego = new ParametrosJuegoAdaptador(parametrosJuegoDataList);
        recyclerViewParametrosJuego.setAdapter(adaptadorParametrosJuego);
    }

    public void inicializadorParametrosJuegoData () {
        parametrosJuegoDataList = new ArrayList<ParametrosJuego>();
        parametrosJuegoDataList.add(new ParametrosJuego("Lorem ipsum dolor sit amet.","49","12","5.5"));
        parametrosJuegoDataList.add(new ParametrosJuego("Lorem ipsum dolor sit amet.","49","12","5.5"));
        parametrosJuegoDataList.add(new ParametrosJuego("Lorem ipsum dolor sit amet.","49","12","5.5"));
        parametrosJuegoDataList.add(new ParametrosJuego("Lorem ipsum dolor sit amet.","49","12","5.5"));
        parametrosJuegoDataList.add(new ParametrosJuego("Lorem ipsum dolor sit amet.","49","12","5.5"));
        parametrosJuegoDataList.add(new ParametrosJuego("Lorem ipsum dolor sit amet.","49","12","5.5"));
        parametrosJuegoDataList.add(new ParametrosJuego("Lorem ipsum dolor sit amet.","49","12","5.5"));

    }


    public void inicilaizarAdaptadorJuegosDisponibles(){
        JuegoTerapiaAdaptador adaptadorTerapiaAdaptador = new JuegoTerapiaAdaptador(JuegoDataList);
        recyclerViewJuegos.setAdapter(adaptadorTerapiaAdaptador);
    }

    public void inicializadorDataJuegosDisponibles() {
        JuegoDataList = new ArrayList<JuegoTerapia>();
        JuegoDataList.add(new JuegoTerapia("CORRER",R.drawable.juego2));
        JuegoDataList.add(new JuegoTerapia("NADAR",R.drawable.juego3));
        JuegoDataList.add(new JuegoTerapia("VOLAR",R.drawable.juego1));
        JuegoDataList.add(new JuegoTerapia("TROTAR",R.drawable.juego2));
        JuegoDataList.add(new JuegoTerapia("ATERRIZAR",R.drawable.juego3));
        JuegoDataList.add(new JuegoTerapia("COMER",R.drawable.juego1));
        JuegoDataList.add(new JuegoTerapia("CORRER",R.drawable.juego2));
        JuegoDataList.add(new JuegoTerapia("NADAR",R.drawable.juego3));
        JuegoDataList.add(new JuegoTerapia("VOLAR",R.drawable.juego1));
    }

    private void funciones_del_menu(){
        clicks_del_menu();
        activar_menu();
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        lyt_menu.setVisibility(LinearLayout.GONE);
    }
    private void clicks_del_menu(){
        final ImageButton btn_terapia=(ImageButton)findViewById(R.id.btn_terapias);
        btn_terapia.setImageDrawable(getDrawable(R.drawable.terapia_activo));

        final ImageButton btn_terapias=(ImageButton)findViewById(R.id.btn_terapias);
        btn_terapias.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(JuegosTerapias.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_calendario=(ImageButton)findViewById(R.id.btn_calendario);
        btn_calendario.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(JuegosTerapias.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_paciente=(ImageButton)findViewById(R.id.btn_paciente);
        btn_paciente.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(JuegosTerapias.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_historial=(ImageButton)findViewById(R.id.btn_historial);
        btn_historial.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(JuegosTerapias.this, VerHistorialTerapias.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_perfil=(ImageButton)findViewById(R.id.btn_perfil);
        btn_perfil.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(JuegosTerapias.this, VerPerfilActivity.class);
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
                ConstraintLayout cont_terapias = (ConstraintLayout) findViewById(R.id.layout_count_terapias);
                if(menu_activo()){
                    lyt_menu.setVisibility(LinearLayout.GONE);
                    cont_terapias.setVisibility(ConstraintLayout.VISIBLE);
                    btn_oc.setImageDrawable(getDrawable(R.drawable.menu));
                }else {
                    lyt_menu.setVisibility(LinearLayout.VISIBLE);
                    cont_terapias.setVisibility(ConstraintLayout.GONE);
                    btn_oc.setImageDrawable(getDrawable(R.drawable.menu_close));
                }
            }
        });
    }
}
