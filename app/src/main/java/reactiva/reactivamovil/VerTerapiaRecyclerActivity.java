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

        /*section = findViewById(R.id.section);

        header = findViewById(R.id.header);

        header.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section.getVisibility() == View.GONE)
                {
                    section.setVisibility(View.VISIBLE);
                    v.setVisibility(View.GONE);
                }
                else
                {
                    section.setVisibility(View.GONE);
                }
            }
        });

        section.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (header.getVisibility() == View.GONE)
                {
                    header.setVisibility(View.VISIBLE);
                    v.setVisibility(View.GONE);
                }
                else
                {
                    header.setVisibility(View.GONE);
                }
            }
        });*/
        //Toolbar toolbar = (Toolbar) findViewById(R.id.barra);
        //setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        List<ItemTerapiaView> listaTerapias = Arrays.asList(new ItemTerapiaView(R.drawable.profile_f,"Maria Perez","00:12:00"),
                new ItemTerapiaView(R.drawable.profile_f,"Pedro Lainez","00:24:05"),
                new ItemTerapiaView(R.drawable.profile_f,"Edgar Moreira","00:10:13"),
                new ItemTerapiaView(R.drawable.profile_f,"Maria Chavez","00:17:09"),
                new ItemTerapiaView(R.drawable.profile_f,"Karen Mera","00:06:25"),
                new ItemTerapiaView(R.drawable.profile_f,"Jaime Banchon","00:09:54"),
                new ItemTerapiaView(R.drawable.profile_f,"Susana Carrasco","00:17:13"),
                new ItemTerapiaView(R.drawable.profile_f,"Estefania Loor","00:19:22"));
        recyclerTerapiaAdaptador Adaptador = new recyclerTerapiaAdaptador(listaTerapias);
        rv.setAdapter(Adaptador);

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_recycler, menu);
    }*/
}
