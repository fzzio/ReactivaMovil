package reactiva.reactivamovil;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

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








}
