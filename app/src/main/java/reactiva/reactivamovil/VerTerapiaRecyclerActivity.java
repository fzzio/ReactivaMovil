package reactiva.reactivamovil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by edgardan on 18/07/2017.
 */

public class VerTerapiaRecyclerActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ver_terapia_recycler);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);

        TextView txtnombre = (TextView) rv.findViewById(R.id.txtName);

        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        Typeface fontSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Bold.ttf");

        //txtnombre.setTypeface(fontMedium);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        List<ItemTerapiaView> listaTerapias = Arrays.asList(new ItemTerapiaView(R.drawable.profile_f,"Maria Perez","00:12:00", "00:12:00", "Maria Perez", "20 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_h,"Pedro Lainez","00:24:05", "00:24:05", "Pedro Lainez", "33 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_h,"Edgar Moreira","00:10:13", "00:10:13", "Edgar Moreira", "24 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_f,"Maria Chavez","00:17:09", "00:17:09", "Maria Chavez", "22 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_f,"Karen Mera","00:06:25", "00:06:25", "Karen Mera", "16 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_h,"Jaime Banchon","00:09:54", "00:09:54", "Jaime Banchon", "50 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_f,"Susana Carrasco","00:17:13", "00:17:13", "Susana Carrasco", "44 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy),
                new ItemTerapiaView(R.drawable.profile_f,"Estefania Loor","00:19:22", "00:19:22", "Estefania Loor", "33 años", R.drawable.comment, R.drawable.finish, R.drawable.pause, R.drawable.picture, R.drawable.view_therapy));
        //List<ItemTerapiaView> listaTerapias = Arrays.asList();
        recyclerTerapiaAdaptador Adaptador = new recyclerTerapiaAdaptador(listaTerapias, this.getApplication(), this);
        rv.setAdapter(Adaptador);

        TextView contador = (TextView) findViewById(R.id.txt_terapias_activas_count);
        contador.setText(listaTerapias.size() + " terapias activas");
        ConstraintLayout layout_no_terapias = (ConstraintLayout) findViewById(R.id.layout_no_terapias);
        TextView txt_no_terapia = (TextView) findViewById(R.id.txtNoTerapias);
        if(listaTerapias.size() <= 0)
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
        }else if(listaTerapias.size() > 0)
        {
            layout_no_terapias.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = layout_no_terapias.getLayoutParams();
            params.height = 0;
            params.width = 0;
            layout_no_terapias.setLayoutParams(params);
            rv.setVisibility(View.VISIBLE);
        }
        funciones_del_menu();
    }
    private void funciones_del_menu(){
        clicks_del_menu();
        activar_menu();
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        lyt_menu.setVisibility(LinearLayout.GONE);
    }
    private void clicks_del_menu(){
        final ImageButton btn_terapia=(ImageButton)findViewById(R.id.btn_terapias);
        btn_terapia.setImageDrawable(getDrawable(R.drawable.terapia_activo));

        final ImageButton btn_terapias=(ImageButton)findViewById(R.id.btn_terapias);
        btn_terapias.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerTerapiaRecyclerActivity.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_calendario=(ImageButton)findViewById(R.id.btn_calendario);
        btn_calendario.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerTerapiaRecyclerActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_paciente=(ImageButton)findViewById(R.id.btn_paciente);
        btn_paciente.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerTerapiaRecyclerActivity.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_historial=(ImageButton)findViewById(R.id.btn_historial);
        btn_historial.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerTerapiaRecyclerActivity.this, VerHistorialTerapias.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_perfil=(ImageButton)findViewById(R.id.btn_perfil);
        btn_perfil.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VerTerapiaRecyclerActivity.this, VerPerfilActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean menu_activo(){
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        int dato= lyt_menu.getVisibility();
        if(dato==LinearLayout.VISIBLE){
            return true;
        }else {
            return false;
        }
    }

    private void activar_menu() {
        final ImageButton btn_oc=(ImageButton)findViewById(R.id.btn_oc);
        btn_oc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
                ConstraintLayout cont_terapias = (ConstraintLayout) findViewById(R.id.layout_count_terapias);
                if(menu_activo()){
                    lyt_menu.setVisibility(LinearLayout.GONE);
                    cont_terapias.setVisibility(ConstraintLayout.VISIBLE);
                    btn_oc.setImageDrawable(getDrawable(R.drawable.menu));
                }else {
                    lyt_menu.setVisibility(LinearLayout.VISIBLE);
                    cont_terapias.setVisibility(ConstraintLayout.GONE);
                    btn_oc.setImageDrawable(getDrawable(R.drawable.menu_close));
                }
            }
        });
    }

}
