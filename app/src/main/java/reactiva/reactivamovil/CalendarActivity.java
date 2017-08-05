package reactiva.reactivamovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Fernando on 09/07/2017.
 */

public class CalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        //Declarando variables finales
        final MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        final CalendarDecorator calendarDecorator = new CalendarDecorator();
        final TextView calendar_today = (TextView) findViewById(R.id.calendar_today_txv);
        final TextView calendar_month = (TextView) findViewById(R.id.calendar_month_txv);
        final TextView empty = (TextView) findViewById(R.id.empty_cita_txv);

        //Recycler View
        /*setContentView(R.layout.calendar_activity);
        RecyclerView rv = (RecyclerView) findViewById(R.id.calendar_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        List<ItemCalendarView> listaCitas = Arrays.asList(new ItemCalendarView("María", " Velasco Ibarra", "7:00 AM     "),
                new ItemCalendarView("Antonio", " Zambrano Villalba", "8:00 AM     "),
                new ItemCalendarView("Kevin", " Sanchez Arreaga", "9:00 AM     "),
                new ItemCalendarView("Rafael", " Martinez Mata", "10:00 AM   "),
                new ItemCalendarView("Keyla", " Pacheco Tovar", "11:00 AM   "));
        CalendarAdapter Adaptador = new CalendarAdapter(listaCitas);
        rv.setAdapter(Adaptador);*/

        //Inicializando calendario
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        //Inicializando calendario
        materialCalendarView.addDecorator(calendarDecorator);
        materialCalendarView.setTopbarVisible(false);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        //Se cambia el txt view por el mes actual
        CalendarDay day = materialCalendarView.getSelectedDate();
        int month_label = day.getMonth();
        calendar_month.setText(current_month(month_label));

        //Se cambia el selector de fecha y se modifica el mes
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                //Se cambia los paramtetros del calendario
                materialCalendarView.state().edit()
                        .setFirstDayOfWeek(Calendar.MONDAY)
                        .setMinimumDate(CalendarDay.from(1900, 1, 1))
                        .setMaximumDate(CalendarDay.from(2100, 12, 31))
                        .setCalendarDisplayMode(CalendarMode.WEEKS)
                        .commit();
                //Se cambia el txt view por el mes actual
                CalendarDay day = materialCalendarView.getSelectedDate();
                int month_label = day.getMonth();
                calendar_month.setText(current_month(month_label));
                //Se muestra la lista de citas
                empty.setVisibility(View.VISIBLE);
            }
        });

        //Se cambia el selector a la fecha actual
        calendar_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialCalendarView.setSelectedDate(CalendarDay.today());
                materialCalendarView.state().edit()
                        .setFirstDayOfWeek(Calendar.MONDAY)
                        .setCalendarDisplayMode(CalendarMode.WEEKS)
                        .commit();
                //Se cambia el txt view por el mes actual
                CalendarDay day = materialCalendarView.getSelectedDate();
                int month_label = day.getMonth();
                calendar_month.setText(current_month(month_label));
                //Se muestra la lista de citas
                empty.setVisibility(View.VISIBLE);
            }
        });

        //Se cambia el display mode a MONTHS
        calendar_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialCalendarView.state().edit()
                        .setCalendarDisplayMode(CalendarMode.MONTHS)
                        .commit();
                //Se muestra la lista de citas
                empty.setVisibility(View.INVISIBLE);
            }
        });

        funciones_del_menu();
    }

    private String current_month(int mes) {
        String[] month_array = {"ENERO", "FEBRERO",
                                "MARZO", "ABRIL",
                                "MAYO", "JUNIO",
                                "JULIO", "AGOSTO",
                                "SEPTIEMBRE", "OCTUBRE",
                                "NOVIEMBRE", "DICIEMBRE"};
        return month_array[mes];
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
