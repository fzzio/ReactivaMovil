package reactiva.reactivamovil;

import android.app.Activity;
import android.app.Dialog;
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

/**
 * Created by edgardan on 18/07/2017.
 */

public class VerTerapiaRecyclerActivity extends AppCompatActivity {

    private Button button;
    Context context = this.getApplication();
    Activity activity = this;

    ConstructorObservacionTerapia constructorObservacionTerapia;

    String url ="http://107.170.105.224:6522/ReactivaWeb/index.php/services/therapyGet";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ver_terapia_recycler);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);

        TextView txtnombre = (TextView) rv.findViewById(R.id.txtName);

        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        Typeface fontSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Bold.ttf");

        //txtnombre.setTypeface(fontMedium);


        ///////////////////////////////////////////////////////////////////
        // inicializo la base de datos inserto datos de prueba
        BaseDeDatosPT db = new BaseDeDatosPT(getApplicationContext());
        constructorObservacionTerapia = new ConstructorObservacionTerapia(getApplicationContext());
        constructorObservacionTerapia.insertaPacientesPruebas(db);
        constructorObservacionTerapia.insertarObservacionTerapiaspruebas(db);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        /*List<ItemTerapiaView> listaTerapias = Arrays.asList(new ItemTerapiaView(R.drawable.profile_f,"Maria Perez","00:12:00", "00:12:00", "Maria Perez", "20 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_h,"Pedro Lainez","00:24:05", "00:24:05", "Pedro Lainez", "33 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_h,"Edgar Moreira","00:10:13", "00:10:13", "Edgar Moreira", "24 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_f,"Maria Chavez","00:17:09", "00:17:09", "Maria Chavez", "22 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_f,"Karen Mera","00:06:25", "00:06:25", "Karen Mera", "16 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_h,"Jaime Banchon","00:09:54", "00:09:54", "Jaime Banchon", "50 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_f,"Susana Carrasco","00:17:13", "00:17:13", "Susana Carrasco", "44 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_f,"Estefania Loor","00:19:22", "00:19:22", "Estefania Loor", "33 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy));*/
        List<ItemTerapiaView> listaTerapias = Arrays.asList();
        ArrayList<Integer> listaExtremidades= new ArrayList<Integer>();
        listaExtremidades.add(12);
        listaExtremidades.add(0);
        listaExtremidades.add(2);
        ItemTerapiaView terapia = new ItemTerapiaView(R.drawable.profile_f,"Maria Perez","00:12:00", "00:12:00", "Maria Perez", "20 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.camera, R.drawable.view_therapy, listaExtremidades);
        final List<ItemTerapiaView> list = new ArrayList<ItemTerapiaView>(Arrays.asList(terapia));
        //list.add(terapia);
        //listaTerapias.add(terapia);

        //loadTerapiaJson(list);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response: ",response.toString());
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject paciente = response.getJSONObject(i).getJSONObject("info");
                        //JSONObject pacienteExtremidades = response.getJSONObject(i).getJSONObject("limbs");
                        JSONArray pacienteExtremidades = response.getJSONObject(i).getJSONArray("limbs");
                        String nombre = paciente.getString("fullname");
                        String edad = paciente.getString("age");
                        String genero = paciente.getString("gender");

                        ArrayList<Integer> lstExtremidades = new ArrayList<Integer>();

                        for(int j =0;j<pacienteExtremidades.length();j++){
                            Log.d("Extremidad detalle: ",pacienteExtremidades.getJSONObject(j).get("id_limb").toString());
                            int extre = pacienteExtremidades.getJSONObject(j).getInt("id_limb");
                            System.out.print(extre);
                            lstExtremidades.add(extre);
                        }
                        //String info = paciente.getString("info");

                        Log.d("NombrePaciente: ",nombre);
                        Log.d("edadPaciente: ",edad);
                        Log.d("generoPaciente: ",genero);
                        Log.d("Extremidades: ",pacienteExtremidades.toString());




                        ItemTerapiaView pctTerap = new ItemTerapiaView(R.drawable.profile_m, nombre, "00:00:00", "00:00:00", nombre, edad+" años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.camera, R.drawable.view_therapy, lstExtremidades);

                        //listaTerapias.add(i, pctTerap);
                        list.add(pctTerap);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Errorsote", e.toString());
                    }
                }
        /*        recyclerTerapiaAdaptador Adaptador = new recyclerTerapiaAdaptador(list, context, activity);
                rv.setAdapter(Adaptador);*/

                TextView contador = (TextView) findViewById(R.id.txt_terapias_activas_count);
                contador.setText(list.size() + " activas");
                ConstraintLayout layout_no_terapias = (ConstraintLayout) findViewById(R.id.layout_no_terapias);
                TextView txt_no_terapia = (TextView) findViewById(R.id.txtNoTerapias);
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
                Toast.makeText(VerTerapiaRecyclerActivity.this,error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        recyclerTerapiaAdaptador Adaptador = new recyclerTerapiaAdaptador(list, this.getApplication(), this);
        rv.setAdapter(Adaptador);

        /*TextView contador = (TextView) findViewById(R.id.txt_terapias_activas_count);
        contador.setText(list.size() + " terapias activas");
        ConstraintLayout layout_no_terapias = (ConstraintLayout) findViewById(R.id.layout_no_terapias);
        TextView txt_no_terapia = (TextView) findViewById(R.id.txtNoTerapias);
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
        }*/
        Menu.funciones_del_menu(VerTerapiaRecyclerActivity.this,getIntent().getExtras().getString("nombre"),"Terapias Activas");
    }

    /*public void loadTerapiaJson(final List<ItemTerapiaView> listaTerapias){
        RequestQueue queue = Volley.newRequestQueue(VerTerapiaRecyclerActivity.this);
        String url ="http://107.170.105.224:6522/ReactivaWeb/index.php/services/therapyGet";
        //final List<ItemTerapiaView> listaTerapias = Arrays.asList();
        //final List<ItemTerapiaView> listaTerapias = new ArrayList<ItemTerapiaView>();
        //cambiar : ver que es lo que recibo: recibe jsonarray

        //UsuarioSQLiteHelper usuario = new UsuarioSQLiteHelper(this, "DBPaciente", null, 1);
        //SQLiteDatabase db = usuario.getWritableDatabase();
        //SQLiteDatabase db = usuario.getReadableDatabase();
        //db.execSQL("INSERT INTO Cliente (Identificacion, Nombre, Edades) VALUES" +info);
        //db.execSQL("SELECT * FROM Cliente");
        //db.close();
        //Toast.makeText(this, "Registros cargados!", Toast.LENGTH_SHORT).show();

        //txtNombreDetalle.setText

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response: ",response.toString());
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject paciente = response.getJSONObject(i).getJSONObject("info");
                        //JSONObject pacienteExtremidades = response.getJSONObject(i).getJSONObject("limbs");
                        String nombre = paciente.getString("fullname");
                        String edad = paciente.getString("age");
                        //String info = paciente.getString("info");

                        Log.d("NombrePaciente: ",nombre);
                        Log.d("edadPaciente: ",edad);

                        ItemTerapiaView pctTerap = new ItemTerapiaView(R.drawable.profile_m, nombre, "", "", nombre, edad, R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy, new ArrayList<Integer>());

                        //listaTerapias.add(i, pctTerap);
                        listaTerapias.add(pctTerap);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Errorsote", e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response: ",error.toString());
                error.printStackTrace();
                Toast.makeText(VerTerapiaRecyclerActivity.this,error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        //return listaTerapias;
    }*/

}
