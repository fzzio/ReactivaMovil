package reactiva.reactivamovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;

import java.util.ArrayList;

import reactiva.reactivamovil.adapters.HistorialAdaptador;
import reactiva.reactivamovil.classes.HistorialTerapia;

public class HistorialActivity extends AppCompatActivity implements HistorialAdaptador.HistorialClickListener{

    HistorialAdaptador historialAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        //Set visibility to GONE in main activities
        final ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setVisibility(View.INVISIBLE);
        Menu.funciones_del_menu(this,getIntent().getExtras().getString("nombre"),"HISTORIAL");

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        final RecyclerView recyclerViewJuegos = (RecyclerView) findViewById(R.id.reciclerViewHistorial);
        recyclerViewJuegos.setLayoutManager(layoutManager);

        ArrayList<HistorialTerapia> historialTerapias = new ArrayList<>();
        historialTerapias.add(new HistorialTerapia("2 Jun. 2017", R.drawable.lunes));
        historialTerapias.add(new HistorialTerapia("1 Jul. 2017", R.drawable.martes));
        historialTerapias.add(new HistorialTerapia("14 Ago. 2017", R.drawable.miercoles));
        historialTerapias.add(new HistorialTerapia("22 Sep. 2017", R.drawable.jueves));
        historialTerapias.add(new HistorialTerapia("31 Oct. 2017", R.drawable.viernes));

        historialAdaptador = new HistorialAdaptador(historialTerapias, this);
        recyclerViewJuegos.setAdapter(historialAdaptador);
    }

    @Override
    public void onClickHistorial(HistorialTerapia historialTerapia) {
        Intent intent = new Intent(getApplicationContext(),CitasDiaActivity.class);
        intent.putExtra("nombre",getIntent().getExtras().getString("nombre"));
        startActivity(intent);
    }
}
