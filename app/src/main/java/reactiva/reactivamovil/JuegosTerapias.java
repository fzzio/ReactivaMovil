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
import android.widget.TextView;

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
        ((TextView)findViewById(R.id.lbl_welcome)).setText(getIntent().getExtras().getString("nombre"));
        //((TextView)findViewById(R.id.lbl_ubicacion)).setText("HISTORIAL DE TERAPIAS");

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

        Menu.funciones_del_menu(JuegosTerapias.this,getIntent().getExtras().getString("nombre"),"SELECCION DE JUEGOS");

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
}
