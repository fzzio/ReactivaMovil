package reactiva.reactivamovil;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class VerPerfilFragment extends Fragment {


    //ELEMENTOS NECESARIOS PARA LA LISTA DE OBSERVACIONES MEDICAS
    ArrayList<ObservacionMedica> observacionMedicas;
    private FrameLayout frameObservMed;
    private RecyclerView listaDeObservacionesMedicas;

    //ELEMENTOS NECESARIO PARA LA LISTA DE TERAPIAS ANTERIORES
    ArrayList<TerapiaAnterior> terapiasAnterioresData;
    private FrameLayout frameTerapiasAnteriores;
    private RecyclerView listaDeTerapiasAnteriores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedinstanceState){

        //ASOCIO MIS FRAMES A LOS FRAMES DE LA VISTA
        frameObservMed = (FrameLayout) getView().findViewById(R.id.frameObMed);
        // frameTerapiasAnteriores = (FrameLayout) findViewById(R.id.frameTerapAnteriores);

        //ASOCIO MIS RYCLERVIEWS A LOS DE LA VISTA PRINCIPAL
        //OBSERVACIONES MEDICAS
        listaDeObservacionesMedicas = (RecyclerView) getView().findViewById(R.id.rvObsMedicas);
        //TERAPIAS MEDICAS ANTERIORES
        //listaDeTerapiasAnteriores = (RecyclerView) findViewById(R.id.rvTerapiasAnteriores);
        frameObservMed = (FrameLayout) getView().findViewById(R.id.frameObMed);
        ///DEFINO EL LAYOUT DE OBSERVACIONES MEDDICAS
        LinearLayoutManager llmobsmed = new LinearLayoutManager(this.getContext());
        llmobsmed.setOrientation(LinearLayoutManager.VERTICAL);
        listaDeObservacionesMedicas.setLayoutManager(llmobsmed);

        incializarListaDeObservacionesMedicas();
        inicializarAdaptadorObservacionesMedicas();

        //INICIALIZO EL ADAPTADOR DE TERAPIAS ANTERIORES
        //inicializarAdaptadorTerapiasAnteriores();
        ((ScrollView)listaDeObservacionesMedicas.getParent()).removeView(listaDeObservacionesMedicas);
        frameObservMed.addView(listaDeObservacionesMedicas);

        return inflater.inflate(R.layout.fragment_ver_perfil,container,false);
    }

    ///INICIALIZO MI ADAPTADOR CON EL ARRAYLITS DE MIS OBSERVACIONES MEDICAS
    public void inicializarAdaptadorObservacionesMedicas() {
        ObservacionMedicaAdaptador adaptador = new ObservacionMedicaAdaptador(observacionMedicas);
        listaDeObservacionesMedicas.setAdapter(adaptador);
    }


    public void inicializarAdaptadorTerapiasAnteriores() {
        TerapiaAnteriorAdaptador adaptador2 = new TerapiaAnteriorAdaptador(terapiasAnterioresData);
        listaDeTerapiasAnteriores.setAdapter(adaptador2);
    }


    public void incializarListaDeObservacionesMedicas() {

        observacionMedicas = new ArrayList<ObservacionMedica>();
        observacionMedicas.add(new ObservacionMedica("29 Julio 2017","Dr. Jose Morales","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("20 Julio 2017","Dr. Juan Zurita","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("18 Julio 2017","Dr. Fernando Sanchez","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("15 Julio 2017","Dr. Edgar Moreira","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("12 Julio 2017","Dr. Made Velasco","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("10 Julio 2017","Dr. Carla Garcia","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        observacionMedicas.add(new ObservacionMedica("5 Julio 2017","Dr. Jose Morales","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));

    }

    public void inicializarListaDeTerapiasAnteriores() {

        terapiasAnterioresData = new ArrayList<TerapiaAnterior>();
        terapiasAnterioresData.add(new TerapiaAnterior("15 jul. 2017","Daniel Garcia","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1));
        terapiasAnterioresData.add(new TerapiaAnterior("10 jul. 2017","Ariel Cedenio","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1));
        terapiasAnterioresData.add(new TerapiaAnterior("5 jul. 2017","Ariel Cedenio","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1));
        terapiasAnterioresData.add(new TerapiaAnterior("1 jul. 2017","Rosa Maria","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1));
        terapiasAnterioresData.add(new TerapiaAnterior("25 jun. 2017","Juan Piguave","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1));
        terapiasAnterioresData.add(new TerapiaAnterior("20 jun. 2017","Luis Ernesto","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1));
        terapiasAnterioresData.add(new TerapiaAnterior("15 jun. 2017","Sara Poveda","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1));
        terapiasAnterioresData.add(new TerapiaAnterior("10 jun. 2017","Silvia Paredes","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1));

    }


}
