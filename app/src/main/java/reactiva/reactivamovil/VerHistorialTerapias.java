package reactiva.reactivamovil;

import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;

import java.util.ArrayList;

import reactiva.reactivamovil.adapters.TerapiaAnteriorAdaptador;
import reactiva.reactivamovil.classes.TerapiaAnterior;

public class VerHistorialTerapias extends AppCompatActivity {

    //ELEMENTOS NECESARIO PARA LA LISTA DE TERAPIAS ANTERIORES
    ArrayList<TerapiaAnterior> terapiasAnterioresData;
    private FrameLayout frameRegistroTerapiasAnteriores;
    private RecyclerView registroDeTerapiasAnteriores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_historial_terapias);


        TextView observacionMedica   = (TextView) findViewById(R.id.tvObservacionMedicaPrincipal);
        TextView fechaObservacion    = (TextView) findViewById(R.id.tvFechaUltimaObsMedica);
        TextView doctorObservacion   = (TextView) findViewById(R.id.tvDoctorUltimaObsMedica);
        TextView nombrePaciente      = (TextView) findViewById(R.id.tvHTNombreCompletoPaciente);
        TextView encabezadoUltimaObs = (TextView) findViewById(R.id.tvHTultimaObservacion);
        TextView encabezadoRegistro  = (TextView) findViewById(R.id.tvHTregistro);


        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        Typeface fontSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Bold.ttf");
        Typeface fontBlack = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Black.ttf");

        fechaObservacion.setTypeface(fontBold);
        doctorObservacion.setTypeface(fontBold);
        observacionMedica.setTypeface(fontMedium);

        nombrePaciente.setTypeface(fontBold);
        encabezadoUltimaObs.setTypeface(fontMedium);
        encabezadoRegistro.setTypeface(fontMedium);


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
        Menu.funciones_del_menu(VerHistorialTerapias.this,getIntent().getExtras().getString("nombre"),"verHistorial");

    }


    public void inicilaizarAdaptadorRegistroTerapiasAnteriores(){
        TerapiaAnteriorAdaptador adaptadorRegistroTerapiasAnte = new TerapiaAnteriorAdaptador(terapiasAnterioresData,this);
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
