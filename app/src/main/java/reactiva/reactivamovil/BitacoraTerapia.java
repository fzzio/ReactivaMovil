package reactiva.reactivamovil;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import reactiva.reactivamovil.adapters.ObservacionMedicaAdaptador;
import reactiva.reactivamovil.clases.ObservacionMedica;

public class BitacoraTerapia extends AppCompatActivity {


    //ELEMENTOS NECESARIOS PARA LA LISTA DE OBSERVACIONES MEDICAS
    ArrayList<ObservacionMedica> observacionMedicasData;
    private FrameLayout frameSucesosBitacoraTerapia;
    private RecyclerView listaDeObservacionesMedicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitacora_terapia);

        TextView nombrePacienteBitacora   = (TextView) findViewById(R.id.tvBpaciente);
        TextView fechaBitacora    = (TextView) findViewById(R.id.tvBfecha);
       // TextView doctorObservacion   = (TextView) findViewById(R.id.tvDoctorUltimaObsMedica);
       // TextView nombrePaciente      = (TextView) findViewById(R.id.tvHTNombreCompletoPaciente);
        //TextView encabezadoUltimaObs = (TextView) findViewById(R.id.tvHTultimaObservacion);



        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        Typeface fontSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Bold.ttf");
        Typeface fontBlack = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Black.ttf");

        nombrePacienteBitacora.setTypeface(fontBold);
        fechaBitacora.setTypeface(fontMedium);



        frameSucesosBitacoraTerapia = (FrameLayout) findViewById(R.id.frameObsMedicasBitacora);

        listaDeObservacionesMedicas = (RecyclerView) findViewById(R.id.rvObsMedicasBitacora);


        ///DEFINO EL LAYOUT DE OBSERVACIONES MEDDICAS
        LinearLayoutManager llBitacora = new LinearLayoutManager(this);
        llBitacora.setOrientation(LinearLayoutManager.VERTICAL);
        listaDeObservacionesMedicas.setLayoutManager(llBitacora);

        inicializarListaDeObservacionesMedicas();

        inicializarAdaptadorObservacionesMedicas();

        ((ScrollView)listaDeObservacionesMedicas.getParent()).removeView(listaDeObservacionesMedicas);
        frameSucesosBitacoraTerapia.addView(listaDeObservacionesMedicas);


    }

    public void inicializarAdaptadorObservacionesMedicas() {
        ObservacionMedicaAdaptador adaptadorObservacionesBitacora = new ObservacionMedicaAdaptador(observacionMedicasData);
        listaDeObservacionesMedicas.setAdapter(adaptadorObservacionesBitacora);

    }

    public void inicializarListaDeObservacionesMedicas() {
        observacionMedicasData = new ArrayList<ObservacionMedica>();
        observacionMedicasData.add(new ObservacionMedica("9:00","Inicio"));
        observacionMedicasData.add(new ObservacionMedica("9:08","INICIO juego -Subir Escaleras-"));
        observacionMedicasData.add(new ObservacionMedica("9:12","Imagen"));
        observacionMedicasData.add(new ObservacionMedica("9:16","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
        observacionMedicasData.add(new ObservacionMedica("9:23","FIN juego -Subir Escaleras-"));
        observacionMedicasData.add(new ObservacionMedica("9:29","INICIO juego -Nombre De Juego-"));
        observacionMedicasData.add(new ObservacionMedica("9:36","PAUSA juego -Nombre De Juego-"));
        observacionMedicasData.add(new ObservacionMedica("9:36","El paciente tropezo y callo"));
        observacionMedicasData.add(new ObservacionMedica("9:45","REANUDA juego -Nombre de Juego-"));
        observacionMedicasData.add(new ObservacionMedica("9:50","PAUSA juego -Nombre De Juego-"));
        observacionMedicasData.add(new ObservacionMedica("10:00","El paciente se fue de oreja"));
        observacionMedicasData.add(new ObservacionMedica("10:05","REANUDA juego -Nombre de Juego-"));
        observacionMedicasData.add(new ObservacionMedica("10:15","PAUSA juego -Nombre De Juego-"));
        observacionMedicasData.add(new ObservacionMedica("10:25","El paciente otra vez se fue de oreja"));
        observacionMedicasData.add(new ObservacionMedica("10:35","REANUDA juego -Nombre de Juego-"));

    }

}
