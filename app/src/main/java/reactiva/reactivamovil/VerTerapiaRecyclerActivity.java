package reactiva.reactivamovil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;
import java.util.List;

/**
 * Created by edgardan on 18/07/2017.
 */

public class VerTerapiaRecyclerActivity extends AppCompatActivity {
    View section, header;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ver_terapia_recycler);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        List<ItemTerapiaView> listaTerapias = Arrays.asList(new ItemTerapiaView(R.drawable.profile_f,"Maria Perez","00:12:00", "00:12:00", "Maria Perez", "20", "Sala 2"),
                new ItemTerapiaView(R.drawable.profile_f,"Pedro Lainez","00:24:05", "00:24:05", "Pedro Lainez", "33", "Sala 1"),
                new ItemTerapiaView(R.drawable.profile_f,"Edgar Moreira","00:10:13", "00:10:13", "Edgar Moreira", "24", "Sala 4"),
                new ItemTerapiaView(R.drawable.profile_f,"Maria Chavez","00:17:09", "00:10:13", "Edgar Moreira", "24", "Sala 4"),
                new ItemTerapiaView(R.drawable.profile_f,"Karen Mera","00:06:25", "00:10:13", "Edgar Moreira", "24", "Sala 4"),
                new ItemTerapiaView(R.drawable.profile_f,"Jaime Banchon","00:09:54", "00:10:13", "Edgar Moreira", "24", "Sala 4"),
                new ItemTerapiaView(R.drawable.profile_f,"Susana Carrasco","00:17:13", "00:10:13", "Edgar Moreira", "24", "Sala 4"),
                new ItemTerapiaView(R.drawable.profile_f,"Estefania Loor","00:19:22", "00:10:13", "Edgar Moreira", "24", "Sala 4"));
        recyclerTerapiaAdaptador Adaptador = new recyclerTerapiaAdaptador(listaTerapias);
        rv.setAdapter(Adaptador);

    }

}
