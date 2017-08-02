package reactiva.reactivamovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class JuegosTerapia extends AppCompatActivity {



    //ELEMENTOS NECESARIO PARA LA TABLA DE PARAMETROS
    ArrayList<ParametrosJuego> parametrosJuegoDataList;
    private FrameLayout frameParametrosJuego;
    private RecyclerView recyclerViewParametrosJuego;


    //ELEMENTOS NECESARIO PARA LAS IMAGENES DE LOS JUEGOS DISPONIBLES
    ArrayList<JuegoTerapia> JuegoDataList;
    private FrameLayout frameJuegos;
    private RecyclerView recyclerViewJuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juegos_terapia);

        //TABLA DE PARAMETROS
        frameParametrosJuego = (FrameLayout) findViewById(R.id.frameParametrosJuego);
        recyclerViewParametrosJuego    = (RecyclerView) findViewById(R.id.rvParametrosJuego);

        //lISTA DE IMAGENES DE JUEGOS DISPONIBLES
        frameJuegos = (FrameLayout) findViewById(R.id.frameJuegos);
        recyclerViewJuegos    = (RecyclerView) findViewById(R.id.rvJuegos);

        //LAYOUT TABLA PARAMETROS
        LinearLayoutManager llParametrosJuego = new LinearLayoutManager(this);
        llParametrosJuego.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewParametrosJuego.setLayoutManager(llParametrosJuego);

        //LAYOUT LISTA DE JUEGOS
        LinearLayoutManager llJuegosDisponibles = new LinearLayoutManager(this);
        llJuegosDisponibles.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewJuegos.setLayoutManager(llJuegosDisponibles);

        //INICIALIZAR DATOS
        inicializadorParametrosJuegoData();
        inicializadorDataJuegosDisponibles();


        //INICALIZAR ADAPTADORES
        inicilaizarAdaptadorParametrosJuego();
        inicilaizarAdaptadorJuegosDisponibles();


        ((FrameLayout)recyclerViewParametrosJuego.getParent()).removeView(recyclerViewParametrosJuego);
        frameParametrosJuego.addView(recyclerViewParametrosJuego);

        ((FrameLayout)recyclerViewJuegos.getParent()).removeView(recyclerViewJuegos);
        frameJuegos.addView(recyclerViewJuegos);

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
        JuegoDataList.add(new JuegoTerapia("CORRER",R.drawable.juego1));
        JuegoDataList.add(new JuegoTerapia("CORRER",R.drawable.juego3));
        JuegoDataList.add(new JuegoTerapia("CORRER",R.drawable.juego1));
        JuegoDataList.add(new JuegoTerapia("CORRER",R.drawable.juego3));
        JuegoDataList.add(new JuegoTerapia("CORRER",R.drawable.juego1));
        JuegoDataList.add(new JuegoTerapia("CORRER",R.drawable.juego3));
    }
}
