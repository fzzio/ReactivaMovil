package reactiva.reactivamovil;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reactiva.reactivamovil.db.BaseDeDatosPT;
import reactiva.reactivamovil.db.ConstructorObservacionTerapia;

import static android.view.View.GONE;

/**
 * Created by edgardan on 18/07/2017.
 */

public class VerTerapiaRecyclerActivity extends AppCompatActivity {

    ConstructorObservacionTerapia constructorObservacionTerapia;

    String url =Utils.URL+"/ReactivaWeb/index.php/services/therapyGet";
    private RecyclerView rv;

    List<ItemTerapiaView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_terapia_recycler);
        //terapias = cargaTerapias();

        rv = (RecyclerView) findViewById(R.id.recycler_view);

        ///////////////////////////////////////////////////////////////////
        // inicializo la base de datos, grabo datos de prueba
        BaseDeDatosPT db = new BaseDeDatosPT(getApplicationContext());
        constructorObservacionTerapia = new ConstructorObservacionTerapia(getApplicationContext());
        //constructorObservacionTerapia.insertaPacientesPruebas(db);
        constructorObservacionTerapia.insertarObservacionTerapiaspruebas(db);
        ////////////////////////////////////////////////////////////////////

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        list = new ArrayList<ItemTerapiaView>();
        ItemTerapiaView terapia1 = new ItemTerapiaView(R.drawable.profile_f,"Maria Perez","00:00:00",
                "00:00:00", "Maria Perez", "20 años", R.drawable.comment, R.drawable.finish,
                R.drawable.pause, R.drawable.camera, R.drawable.view_therapy, new ArrayList<String>(),
                "1", "2", "3", R.drawable.play_active);
        ItemTerapiaView terapia2 = new ItemTerapiaView(R.drawable.profile_m,"Ronald Reyes","00:00:00",
                "00:00:00", "Ronald Reyes", "50 años", R.drawable.comment, R.drawable.finish,
                R.drawable.pause, R.drawable.camera, R.drawable.view_therapy, new ArrayList<String>(),
                "1", "2", "3", R.drawable.play_active);
        ItemTerapiaView terapia3 = new ItemTerapiaView(R.drawable.profile_f,"Lolita Franco","00:00:00",
                "00:00:00", "Lolita Franco", "28 años", R.drawable.comment, R.drawable.finish,
                R.drawable.pause, R.drawable.camera, R.drawable.view_therapy, new ArrayList<String>(),
                "1", "2", "3", R.drawable.play_active);
        list.add(terapia1);
        list.add(terapia2);
        list.add(terapia3);

        recyclerTerapiaAdaptador Adaptador = new recyclerTerapiaAdaptador(list, this.getApplication(), this);
        Adaptador.notifyDataSetChanged();
        rv.setAdapter(Adaptador);

        //Set visibility to GONE in main activities
        final ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setVisibility(View.INVISIBLE);
        Menu.funciones_del_menu(VerTerapiaRecyclerActivity.this,getIntent().getExtras().getString("nombre"),"TERAPIAS ACTIVAS");
    }

    public void getTerapias(){

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResponseX: ",response.toString());
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject paciente = response.getJSONObject(i).getJSONObject("info");
                        JSONObject ids = response.getJSONObject(i).getJSONObject("therapy");
                        JSONArray pacienteExtremidades = response.getJSONObject(i).getJSONArray("limbs");
                        String nombre = paciente.getString("fullname");
                        String edad = paciente.getString("age");
                        String genero = paciente.getString("gender");

                        String id_therapy = ids.getString("id_therapy");
                        String id_consulta = ids.getString("id_consulta");
                        String id_patient = ids.getString("id_patient");

                        //ArrayList<Integer> lstExtremidades = new ArrayList<Integer>();
                        ArrayList<String> lstExtremidades = new ArrayList<String>();

                        for(int j =0;j<pacienteExtremidades.length();j++){
                            Log.d("Extremidad detalle: ",pacienteExtremidades.getJSONObject(j).get("icon").toString());
                            String extre = pacienteExtremidades.getJSONObject(j).getString("icon");
                            System.out.print(extre);
                            lstExtremidades.add(extre);
                        }

                        Log.d("NombrePaciente: ",nombre);
                        Log.d("edadPaciente: ",edad);
                        Log.d("generoPaciente: ",genero);
                        Log.d("Extremidades: ",pacienteExtremidades.toString());

                        Log.d("ident terapia: ",id_therapy);
                        Log.d("ident consulta: ",id_consulta);
                        Log.d("ident paciente: ",id_patient);

                        //ItemTerapiaView pctTerap = new ItemTerapiaView(R.drawable.profile_m, nombre, "00:00:00", "00:00:00", nombre, edad+" años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.camera, R.drawable.view_therapy, lstExtremidades);

                        list.add(new ItemTerapiaView(R.drawable.profile_m, nombre, "00:00:00", "00:00:00", nombre, edad+" años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.camera, R.drawable.view_therapy, lstExtremidades, id_therapy, id_consulta, id_patient, R.drawable.play_active));
                        //check tipo de variable de los campos


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Errorsote", e.toString());
                    }
                }

                TextView contador = (TextView) findViewById(R.id.txt_terapias_activas_count);
                contador.setText(list.size() + " activas");
                ConstraintLayout layout_no_terapias = (ConstraintLayout) findViewById(R.id.layout_no_terapias);
                TextView txt_no_terapia = (TextView) findViewById(R.id.txt_terapias_activas_count);
                if(list.size() <= 0)
                {
                    //hacer visible el layout con mensaje en caso de no existir terapias
                    //ConstraintLayout layout_no_terapias = (ConstraintLayout) findViewById(R.id.layout_no_terapias);
                    //TextView txt_no_terapia = (TextView) findViewById(R.id.txtNoTerapias);
                    txt_no_terapia.setGravity(Gravity.CENTER);
                    ViewGroup.LayoutParams params = layout_no_terapias.getLayoutParams();
                    params.height = 1500;
                    params.width = 1200;
                    layout_no_terapias.setLayoutParams(params);
                    rv.setVisibility(View.GONE);
                    layout_no_terapias.setVisibility(View.VISIBLE);
                }else if(list.size() > 0)
                {
                    layout_no_terapias.setVisibility(View.GONE);
                    ViewGroup.LayoutParams params = layout_no_terapias.getLayoutParams();
                    params.height = 0;
                    params.width = 0;
                    layout_no_terapias.setLayoutParams(params);
                    rv.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response: ",error.toString());
                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

}
