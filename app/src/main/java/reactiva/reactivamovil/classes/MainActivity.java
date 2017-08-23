package reactiva.reactivamovil.classes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nancy.dbasegson.desarializador.TerapiaDeserializador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btwebservice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarServicio();
            }
        });

    }

    public void llamarServicio() {
       // ArrayList<TerapiaDia> terapiasTerapiaDias = new ArrayList<>();


        RequestQueue queue = Volley.newRequestQueue(this);
       // final String url = "http://107.170.105.224:6522/ReactivaWeb/index.php/services/therapyGet";
        final String url = "http://107.170.105.224:6522/ReactivaWeb/index.php/services/getCalendar?month=6&year=2017";


        JsonArrayRequest getRequest = new JsonArrayRequest(JsonArrayRequest.Method.GET,url,null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", response.toString());

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.registerTypeAdapter(TerapiaDiaResponse.class, new TerapiaDeserializador());

                        Gson miGson = gsonBuilder.create();

                        String jsonp = "[\n" +
                                "    {\n" +
                                "        \"day\": {\n" +
                                "            \"day\": \"19\",\n" +
                                "            \"count\": \"2\"\n" +
                                "        },\n" +
                                "        \"therapies\": [\n" +
                                "            {\n" +
                                "                \"id_therapy\": \"4\",\n" +
                                "                \"id_patient\": \"1\",\n" +
                                "                \"id_consulta\": \"15\",\n" +
                                "                \"fullname\": \"Made Velasco Mite\",\n" +
                                "                \"status\": \"3\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"id_therapy\": \"5\",\n" +
                                "                \"id_patient\": \"1\",\n" +
                                "                \"id_consulta\": \"15\",\n" +
                                "                \"fullname\": \"Made Velasco Mite\",\n" +
                                "                \"status\": \"3\"\n" +
                                "            }\n" +
                                "        ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "        \"day\": {\n" +
                                "            \"day\": \"21\",\n" +
                                "            \"count\": \"6\"\n" +
                                "        },\n" +
                                "        \"therapies\": [\n" +
                                "            {\n" +
                                "                \"id_therapy\": \"6\",\n" +
                                "                \"id_patient\": \"1\",\n" +
                                "                \"id_consulta\": \"19\",\n" +
                                "                \"fullname\": \"Made Velasco Mite\",\n" +
                                "                \"status\": \"1\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"id_therapy\": \"7\",\n" +
                                "                \"id_patient\": \"18\",\n" +
                                "                \"id_consulta\": \"25\",\n" +
                                "                \"fullname\": \"Jose Luis Masson \",\n" +
                                "                \"status\": \"2\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"id_therapy\": \"8\",\n" +
                                "                \"id_patient\": \"13\",\n" +
                                "                \"id_consulta\": \"22\",\n" +
                                "                \"fullname\": \"Rodrigo Castro Reyes\",\n" +
                                "                \"status\": \"2\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"id_therapy\": \"9\",\n" +
                                "                \"id_patient\": \"10\",\n" +
                                "                \"id_consulta\": \"20\",\n" +
                                "                \"fullname\": \"Oswaldo Aguilar \",\n" +
                                "                \"status\": \"2\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"id_therapy\": \"10\",\n" +
                                "                \"id_patient\": \"11\",\n" +
                                "                \"id_consulta\": \"23\",\n" +
                                "                \"fullname\": \"Viviana Laurido Aguirre\",\n" +
                                "                \"status\": \"2\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"id_therapy\": \"11\",\n" +
                                "                \"id_patient\": \"10\",\n" +
                                "                \"id_consulta\": \"22\",\n" +
                                "                \"fullname\": \"Oswaldo Aguilar \",\n" +
                                "                \"status\": \"2\"\n" +
                                "            }\n" +
                                "        ]\n" +
                                "    }\n" +
                                "]";

                        System.out.println(miGson.fromJson(jsonp,TerapiaDia.class));

                    }
                },new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", error.toString());
                        }
                    }
        );
        queue.add(getRequest);
    }
}
