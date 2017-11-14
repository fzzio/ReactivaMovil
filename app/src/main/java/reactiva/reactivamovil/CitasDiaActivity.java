package reactiva.reactivamovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import reactiva.reactivamovil.adapters.TerapiaAnteriorAdaptador;
import reactiva.reactivamovil.classes.TerapiaAnterior;

public class CitasDiaActivity extends AppCompatActivity {

    ArrayList<TerapiaAnterior> terapiasAnterioresData;
    private RecyclerView registroDeTerapiasAnteriores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_dia);

        registroDeTerapiasAnteriores    = (RecyclerView) findViewById(R.id.rvTerapiasAnteriores);

        LinearLayoutManager llRegistroTera = new LinearLayoutManager(this);
        llRegistroTera.setOrientation(LinearLayoutManager.VERTICAL);
        registroDeTerapiasAnteriores.setLayoutManager(llRegistroTera);

        inicializadorTerapiasRegistrosData();

        Menu.funciones_del_menu(this,getIntent().getExtras().getString("nombre"),"CITAS DEL DÍA");
    }

    public void inicilaizarAdaptadorRegistroTerapiasAnteriores(){
        TerapiaAnteriorAdaptador adaptadorRegistroTerapiasAnte = new TerapiaAnteriorAdaptador(terapiasAnterioresData,this);
        registroDeTerapiasAnteriores.setAdapter(adaptadorRegistroTerapiasAnte);

    }

    public void inicializadorTerapiasRegistrosData () {

        terapiasAnterioresData = new ArrayList<TerapiaAnterior>();
        terapiasAnterioresData.add(new TerapiaAnterior("7:00 AM","Daniel Garcia Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("8:00 AM","Fernando Sanchez Garcia",""));
        terapiasAnterioresData.add(new TerapiaAnterior("9:15 AM","Edgar Moreira Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("10:30 AM","Israel Zurita Garcia",""));
        terapiasAnterioresData.add(new TerapiaAnterior("11:00 AM","Manuel Zurita Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("1:00 PM","Kevin Sanchez Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("2:00 PM","Daniel Garcia Arreaga",""));
        terapiasAnterioresData.add(new TerapiaAnterior("5:30 PM","Dolores Garcia Arreaga",""));

        inicilaizarAdaptadorRegistroTerapiasAnteriores();
    }

}

