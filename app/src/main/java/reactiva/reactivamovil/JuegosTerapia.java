package reactiva.reactivamovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class JuegosTerapia extends AppCompatActivity {



    //ELEMENTOS NECESARIO PARA LA LISTA DE TERAPIAS ANTERIORES
    ArrayList<ParametrosJuego> parametrosJuegoDataList;
    private FrameLayout frameParametrosJuego;
    private RecyclerView recyclerViewParametrosJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juegos_terapia);


        frameParametrosJuego = (FrameLayout) findViewById(R.id.frameParametrosJuego);
        recyclerViewParametrosJuego    = (RecyclerView) findViewById(R.id.rvParametrosJuego);

        LinearLayoutManager llParametrosJuego = new LinearLayoutManager(this);
        llParametrosJuego.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewParametrosJuego.setLayoutManager(llParametrosJuego);


        inicializadorParametrosJuegoData();
        inicilaizarAdaptadorParametrosJuego();


        ((FrameLayout)recyclerViewParametrosJuego.getParent()).removeView(recyclerViewParametrosJuego);
        frameParametrosJuego.addView(recyclerViewParametrosJuego);

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
}
