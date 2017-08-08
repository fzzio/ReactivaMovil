package reactiva.reactivamovil.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import reactiva.reactivamovil.R;
import reactiva.reactivamovil.adapters.ParametrosJuegoAdaptador;
import reactiva.reactivamovil.clases.ParametrosJuego;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRVParametrosJuegos extends Fragment {


    ArrayList<ParametrosJuego> parametrosJuegoDataList;
    private FrameLayout frameParametrosJuego;
    private RecyclerView recyclerViewParametrosJuego;

    public FragmentRVParametrosJuegos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rvparametros_juegos, container, false);

        recyclerViewParametrosJuego    = (RecyclerView) v.findViewById(R.id.rvParametrosJuego);

        LinearLayoutManager llParametrosJuego = new LinearLayoutManager(getActivity());
        llParametrosJuego.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewParametrosJuego.setLayoutManager(llParametrosJuego);

        inicializadorParametrosJuegoData();
        inicilaizarAdaptadorParametrosJuego();

        return v;
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
