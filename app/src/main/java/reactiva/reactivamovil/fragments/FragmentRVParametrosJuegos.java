package reactiva.reactivamovil.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import reactiva.reactivamovil.LoginActivity;
import reactiva.reactivamovil.R;
import reactiva.reactivamovil.VerTerapiaRecyclerActivity;
import reactiva.reactivamovil.adapters.ParametrosJuegoAdaptador;
import reactiva.reactivamovil.classes.ParametrosJuego;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRVParametrosJuegos extends Fragment {


    public ArrayList<ParametrosJuego> parametrosJuegoDataList;
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
        //inicializarAdaptadorParametrosJuego();

        return v;
    }


    public void inicializarAdaptadorParametrosJuego(ArrayList<ParametrosJuego> a){
        ParametrosJuegoAdaptador adaptadorParametrosJuego = new ParametrosJuegoAdaptador(a);
        recyclerViewParametrosJuego.setAdapter(adaptadorParametrosJuego);
    }

    public void inicializadorParametrosJuegoData () {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://107.170.105.224:6522/reactiva-api/index.php/web_api/get_game_parameters?game=1";
        final ArrayList<ParametrosJuego> params = new ArrayList<ParametrosJuego>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Response: ",response);
                        try {
                            JSONObject g = new JSONObject(response);
                            JSONArray nombres = g.names();
                            if(g.has("error")){
                                Toast.makeText(getActivity(),g.getString("mensaje"),Toast.LENGTH_LONG).show();
                                return;
                            }else{//si no muestra ningun error estoy recibiendo datos
                                for (int i =0; i<nombres.length();i++){
                                    JSONObject elementoLista = g.getJSONObject(nombres.getString(i));
                                    if(elementoLista.has("error")){
                                        Toast.makeText(getActivity(),elementoLista.getString("mensaje"),Toast.LENGTH_LONG).show();
                                        return;
                                    }else{

                                        activarBotonInicioTerapia();

                                        DecimalFormat dc = new DecimalFormat("00.00");
                                        String x = dc.format(elementoLista.getDouble("x"));
                                        String y = dc.format(elementoLista.getDouble("y"));
                                        String z = dc.format(elementoLista.getDouble("z"));
                                        ParametrosJuego pj = new ParametrosJuego(nombres.getString(i),x,y,z);
                                        //parametrosJuegoDataList.add(new ParametrosJuego("eje1","1","2","3"));
                                        params.add(pj);
                                        Toast.makeText(getActivity(),"Si hubo datos",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }finally {
                            inicializarAdaptadorParametrosJuego(params);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response: ", error.toString());
                error.printStackTrace();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        if(params.size()>0){
            inicializarAdaptadorParametrosJuego(params);
        }
    }
    public void activarBotonInicioTerapia(){
        getActivity().findViewById(R.id.btnIniciarTerapia).setVisibility(Button.VISIBLE);
        //aqui deberia pasar el id de la terapia (que coincide con el cliente que muestro)
        // y en el onclick enviar un update a la BD para cambiar el estado de la terapia a activo
        //lo demas ya debe estar manejado por TerapiasActivas
        //getActivity().getIntent().getExtras().getString("id_paciente");
        getActivity().findViewById(R.id.btnIniciarTerapia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui deberia actualizar el valor de la terapia que le pasan a este activity
                Intent i = new Intent(getActivity(),VerTerapiaRecyclerActivity.class);
                i.putExtra("nombre", getActivity().getIntent().getExtras().getString("nombre"));
                startActivity(i);
            }
        });
    }
}
