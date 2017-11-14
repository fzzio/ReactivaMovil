package reactiva.reactivamovil;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import reactiva.reactivamovil.adapters.ObservacionMedicaAdaptador;
import reactiva.reactivamovil.adapters.ObservacionTerapiaAdaptador;
import reactiva.reactivamovil.classes.ObservacionMedica;
import reactiva.reactivamovil.classes.ObservacionTerapia;
import reactiva.reactivamovil.db.ConstructorObservacionTerapia;
import reactiva.reactivamovil.presentadores.IRecyclerBitacora;

public class BitacoraTerapia extends AppCompatActivity implements IRecyclerBitacora{


    //ELEMENTOS NECESARIOS PARA LA LISTA DE OBSERVACIONES MEDICAS
    ArrayList<ObservacionTerapia> observacionMedicasData;
    private RecyclerView listaDeObservacionesMedicas;

    ConstructorObservacionTerapia constructorObservacionTerapia;

    int idprueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitacora_terapia);

        Bundle bundle = getIntent().getExtras();
        String nombre = bundle.getString("full_name");
        Log.d("Nombrexxx", nombre);
        String IdPaciente = bundle.getString("id_therapy");
        int idFinal = Integer.parseInt(IdPaciente);
        Log.d("id final paciente", IdPaciente);

        TextView nombrePacienteBitacora   = (TextView) findViewById(R.id.tvBpaciente);
        TextView fechaBitacora    = (TextView) findViewById(R.id.tvBfecha);
       // TextView doctorObservacion   = (TextView) findViewById(R.id.tvDoctorUltimaObsMedica);
       // TextView nombrePaciente      = (TextView) findViewById(R.id.tvHTNombreCompletoPaciente);
        //TextView encabezadoUltimaObs = (TextView) findViewById(R.id.tvHTultimaObservacion);

        nombrePacienteBitacora.setText(nombre);

        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        Typeface fontSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Bold.ttf");
        Typeface fontBlack = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Black.ttf");

        nombrePacienteBitacora.setTypeface(fontBold);
        fechaBitacora.setTypeface(fontMedium);

        listaDeObservacionesMedicas = (RecyclerView) findViewById(R.id.rvObsMedicasBitacora);

        generarLinearLayoutVertical();
        //LEEO LOS DATOS DE LA BASE Y LOS TRAIGO
        //idprueba = 5;
        obtenerObservacionesByIdTerapia(idFinal);
        mostrarDatosEnRVobsernacionesTerapia();

        Menu.funciones_del_menu(BitacoraTerapia.this,getIntent().getExtras().getString("nombre"),"BITÁCORA DE TERAPIA");

        inicializarListaDeObservacionesMedicas();
        inicializarAdaptadorObservacionesMedicas();
    }

    public void inicializarAdaptadorObservacionesMedicas() {
        ObservacionTerapiaAdaptador adaptadorObservacionesBitacora = new ObservacionTerapiaAdaptador(observacionMedicasData);
        listaDeObservacionesMedicas.setAdapter(adaptadorObservacionesBitacora);

    }

    public void inicializarListaDeObservacionesMedicas() {
        observacionMedicasData = new ArrayList<ObservacionTerapia>();
        //int idObservacionTerapia, int idTerapia, String obsComentario, String obsHoraComentario, String obsEstado
        observacionMedicasData.add(new ObservacionTerapia(1, 1, "Inicio","9:00", "1"));
        observacionMedicasData.add(new ObservacionTerapia(1, 1, "INICIO juego -Subir Escaleras-","9:08","INICIO juego -Subir Escaleras-"));
        observacionMedicasData.add(new ObservacionTerapia(1, 1, "Imagen","9:12","Imagen"));
        observacionMedicasData.add(new ObservacionTerapia(1, 1, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","9:16",""));
    }

    @Override
    public void generarLinearLayoutVertical() {
        ///DEFINO EL LAYOUT DE OBSERVACIONES MEDDICAS
        LinearLayoutManager llBitacora = new LinearLayoutManager(this);
        llBitacora.setOrientation(LinearLayoutManager.VERTICAL);
        listaDeObservacionesMedicas.setLayoutManager(llBitacora);
    }

    @Override
    public ObservacionTerapiaAdaptador crearAdaptadorObservacionTerapia(ArrayList<ObservacionTerapia> observacionTerapias) {
        ObservacionTerapiaAdaptador adaptadorObservacionesBitacora = new ObservacionTerapiaAdaptador(observacionMedicasData);
        return adaptadorObservacionesBitacora;
    }

    @Override
    public void inicializarAdaptadorRV(ObservacionTerapiaAdaptador observacionTerapiaAdaptador) {
        listaDeObservacionesMedicas.setAdapter(observacionTerapiaAdaptador);
    }

    /**
     * Obtengo todas las observaciones por el ID de la terpaia
     * @param idTerapia
     */
    public void  obtenerObservacionesByIdTerapia(int idTerapia){
        constructorObservacionTerapia = new ConstructorObservacionTerapia(getApplicationContext());
        observacionMedicasData = constructorObservacionTerapia.obtenerObservacionesTerapiasXID(idTerapia);
    }

    public void mostrarDatosEnRVobsernacionesTerapia(){
        ObservacionTerapiaAdaptador adaptadorObservacionesBitacora = new ObservacionTerapiaAdaptador(observacionMedicasData);
        listaDeObservacionesMedicas.setAdapter(adaptadorObservacionesBitacora);
    }
}
