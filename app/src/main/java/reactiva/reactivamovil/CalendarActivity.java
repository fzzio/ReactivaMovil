package reactiva.reactivamovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

/**
 * Created by Fernando on 09/07/2017.
 */

public class CalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);


        final MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {

                materialCalendarView.state().edit()
                        .setFirstDayOfWeek(Calendar.MONDAY)
                        .setMinimumDate(CalendarDay.from(1900, 1, 1))
                        .setMaximumDate(CalendarDay.from(2100, 12, 31))
                        .setCalendarDisplayMode(CalendarMode.WEEKS)
                        .commit();
            }
        });
        funciones_del_menu();

    }
    private void funciones_del_menu(){
        clicks_del_menu();
        activar_menu();
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        lyt_menu.setVisibility(LinearLayout.GONE);
    }
    private void clicks_del_menu(){
        final ImageButton btn_calendario=(ImageButton)findViewById(R.id.btn_calendario);
        btn_calendario.setImageDrawable(getDrawable(R.drawable.agenda_activo));

        final ImageButton btn_terapias=(ImageButton)findViewById(R.id.btn_terapias);
        btn_terapias.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CalendarActivity.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });


        btn_calendario.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CalendarActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_paciente=(ImageButton)findViewById(R.id.btn_paciente);
        btn_paciente.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CalendarActivity.this, VerTerapiaRecyclerActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_historial=(ImageButton)findViewById(R.id.btn_historial);
        btn_historial.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CalendarActivity.this, VerHistorialTerapias.class);
                startActivity(intent);
            }
        });
        final ImageButton btn_perfil=(ImageButton)findViewById(R.id.btn_perfil);

        btn_perfil.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CalendarActivity.this, VerPerfilActivity.class);
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
                if(menu_activo()){
                    lyt_menu.setVisibility(LinearLayout.GONE);
                    btn_oc.setImageDrawable(getDrawable(R.drawable.menu));
                }else {
                    lyt_menu.setVisibility(LinearLayout.VISIBLE);
                    btn_oc.setImageDrawable(getDrawable(R.drawable.menu_close));
                }
            }
        });
    }
}
