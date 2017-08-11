package reactiva.reactivamovil;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.util.Calendar;

import reactiva.reactivamovil.fragments.CalendarAppointmentFragment;
import reactiva.reactivamovil.fragments.CalendarEmptyAppointmentFragment;

/**
 * Created by Fernando on 09/07/2017.
 */

public class CalendarActivity extends AppCompatActivity {
    FragmentTransaction ft, fte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        final MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        final CalendarDecorator calendarDecorator = new CalendarDecorator();
        final TextView calendar_today = (TextView) findViewById(R.id.calendar_today_txv);
        final TextView calendar_month = (TextView) findViewById(R.id.calendar_month_txv);

        final Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        calendar_month.setTypeface(type);
        calendar_today.setTypeface(type);

        //Initialize Calendar DisplayMode in MONTHS
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        //initialize CalendarDecorator
        materialCalendarView.addDecorator(calendarDecorator);
        materialCalendarView.setTopbarVisible(false);
        //Initialize Dynamic Month Label
        CalendarDay day = materialCalendarView.getCurrentDate();
        int month_label = day.getMonth();
        calendar_month.setText(current_month(month_label));

        //Every time when OnDateChangedListener hear something DisplayMode is changed to WEEKS
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                if (isAppointment(materialCalendarView.getSelectedDate())){
                    //Update Today's Style
                    calendar_today.setTextColor(getResources().getColor(R.color.colorMoradoOpaco));
                } else {
                    //Update Today's Style
                    calendar_today.setTextColor(getResources().getColor(R.color.colorCeleste));
                }
                //Update Calendar DisplayMode to MONTHS
                materialCalendarView.state().edit()
                        .setCalendarDisplayMode(CalendarMode.WEEKS)
                        .commit();
                //Update Dynamic Month Label
                CalendarDay day = materialCalendarView.getSelectedDate();
                int month_label = day.getMonth();
                calendar_month.setText(current_month(month_label));
                //Appointments Verify
                CalendarDay selected_date = materialCalendarView.getSelectedDate();
                if (isAppointment(selected_date)) {
                    //Toast.makeText(CalendarActivity.this, "HAY CITAS PROGRAMADAS", Toast.LENGTH_SHORT).show();
                    displayCalendarAppointmentFragment(dateFormatter(selected_date));
                    removeCalendarEmptyAppointmentFragment();
                } else {
                   // Toast.makeText(CalendarActivity.this, "NO HAY CITAS PROGRAMADAS", Toast.LENGTH_SHORT).show();
                    displayCalendarEmptyAppointmentFragment(dateFormatter(selected_date));
                    removeCalendarAppointmentFragment();
                }
            }
        });

        //Every time when OnClickListener hear something DisplayMode is changed to WEEKS
        calendar_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSelectedDateNull(materialCalendarView.getSelectedDate()) && !isAppointment(materialCalendarView.getSelectedDate())) {
                    //Update Today's Style
                    calendar_today.setTextColor(getResources().getColor(R.color.colorMoradoOpaco));
                    //SetSelectedDate to CurrentDate
                    materialCalendarView.setSelectedDate(CalendarDay.today());
                    //Update Calendar DisplayMode to WEEKS
                    materialCalendarView.state().edit()
                            .setCalendarDisplayMode(CalendarMode.WEEKS)
                            .commit();
                    //Update Dynamic Month Label
                    CalendarDay day = materialCalendarView.getSelectedDate();
                    int month_label = day.getMonth();
                    calendar_month.setText(current_month(month_label));
                    //Appointments Verify
                    CalendarDay selected_date = materialCalendarView.getSelectedDate();
                    if (isAppointment(selected_date)) {
                        //Toast.makeText(CalendarActivity.this, "HAY CITAS PROGRAMADAS", Toast.LENGTH_SHORT).show();
                        displayCalendarAppointmentFragment(dateFormatter(selected_date));
                        removeCalendarEmptyAppointmentFragment();
                    }
                }

            }
        });

        /**Every time when OnClickListener hear something DisplayMode is changed to MONTHS
         * and the SelectedDate is changed to null
         */
        calendar_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update Today's Style
                calendar_today.setTextColor(getResources().getColor(R.color.colorMoradoOpaco));
                //Update Calendar DisplayMode to WEEKS
                materialCalendarView.state().edit()
                        .setCalendarDisplayMode(CalendarMode.MONTHS)
                        .commit();
                //SelectedDate is changed to null
                CalendarDay empty_date = null;
                materialCalendarView.setSelectedDate(empty_date);
                //Remove fragments
                removeCalendarEmptyAppointmentFragment();
                removeCalendarAppointmentFragment();
            }
        });

        funciones_del_menu();
    }

    /**
     * Use this method to convert date with format
     * @return String
     */
    public String dateFormatter(CalendarDay selected_date) {
        Calendar week_date = selected_date.getCalendar();

        int day = selected_date.getDay();
        int month = selected_date.getMonth();
        int year = selected_date.getYear();
        int week = week_date.get(Calendar.DAY_OF_WEEK);

        String output_day = Integer.toString(day);
        String output_month = get_month(month);
        String output_year = Integer.toString(year);
        String output_week = get_week(week);

        String output = output_week + ", " + output_day + " " + output_month + " " + output_year;
        return output;
    }

    /**
     * Use this method to know if selected_date is null
     * @return true if selected_date is null
     *         false if selected_date isn't null
     */
    public boolean isSelectedDateNull(CalendarDay selected_date) {
        if (selected_date == null)
            return true;
        return false;
    }

    /**
     * Use this method to display the CalendarAppointmentFragment
     * @return void
     */
    public void displayCalendarAppointmentFragment(String selected_date) {
        //Fragment Instance
        CalendarAppointmentFragment calendarAppointmentFragment = CalendarAppointmentFragment.newInstance(selected_date);
        //Begin of the transaction
        ft = getSupportFragmentManager().beginTransaction();
        //Replace the contents of the container with the new fragment
        ft.replace(R.id.appointmentContainer, calendarAppointmentFragment);
        //Complete the changes added above
        ft.commit();
    }

    /**
     * Use this method to remove the CalendarAppointmentFragment
     * @return void
     */
    public void removeCalendarAppointmentFragment() {
        //Fragment Instance
        CalendarAppointmentFragment calendarAppointmentFragment = new CalendarAppointmentFragment();
        //Begin of the transaction
        ft = getSupportFragmentManager().beginTransaction();
        //Replace the contents of the container with the new fragment
        ft.replace(R.id.appointmentContainer, calendarAppointmentFragment);
        //Complete the changes added above
        ft.remove(calendarAppointmentFragment).commit();
    }

    /**
     * Use this method to display the CalendarEmptyAppointmentFragment
     * @return void
     */
    public void displayCalendarEmptyAppointmentFragment(String selected_date) {
        //Fragment Instance
        CalendarEmptyAppointmentFragment calendarEmptyAppointmentFragment = CalendarEmptyAppointmentFragment.newInstance(selected_date);
        //Begin of the transaction
        fte = getSupportFragmentManager().beginTransaction();
        //Replace the contents of the container with the new fragment
        fte.replace(R.id.appointmentEmptyContainer, calendarEmptyAppointmentFragment);
        //Complete the changes added above
        fte.commit();
    }

    /**
     * Use this method to remove the CalendarEmptyAppointmentFragment
     * @return void
     */
    public void removeCalendarEmptyAppointmentFragment() {
        //Fragment Instance
        CalendarEmptyAppointmentFragment calendarEmptyAppointmentFragment = new CalendarEmptyAppointmentFragment();
        //Begin of the transaction
        fte = getSupportFragmentManager().beginTransaction();
        //Replace the contents of the container with the new fragment
        fte.replace(R.id.appointmentEmptyContainer, calendarEmptyAppointmentFragment);
        //Complete the changes added above
        fte.remove(calendarEmptyAppointmentFragment).commit();
    }

    /**
     * Use this method to verify is there is an existing appointments
     * @param  selected_date to be checked
     * @return true if there is an existing appointment
     *         false if there isn't an existing appointment
     */
    public boolean isAppointment(CalendarDay selected_date) {
        CalendarDay current_date = CalendarDay.today();
        if (current_date.equals(selected_date))
            return true;
        return false;
    }

    /** Use this method to get current month
     *  @param  month the numeric value
     *  @return name of a month
     */
    public String current_month(int month) {
        String[] month_array = {"ENERO", "FEBRERO",
                                "MARZO", "ABRIL",
                                "MAYO", "JUNIO",
                                "JULIO", "AGOSTO",
                                "SEPTIEMBRE", "OCTUBRE",
                                "NOVIEMBRE", "DICIEMBRE"};
        return month_array[month];
    }

    /** Use this method to get month
     *  @param  month the numeric value
     *  @return name of a month
     */
    public String get_month(int month) {
        String[] month_array = {"ene.", "feb.",
                "mar.", "abr.",
                "may.", "jun.",
                "jul.", "ago.",
                "sep.", "oct.",
                "nov.", "dic."};
        return month_array[month];
    }

    /** Use this method to get week
     *  @param  week the numeric value
     *  @return name of a week
     */
    public String get_week(int week) {
        String[] week_array = {"Domingo", "Lunes",
                "Martes", "Miércoles",
                "Jueves", "Viernes",
                "Sábado"};
        return week_array[week-1];
    }

    /** Use this set of methods for menu management
     *  @return void
     */
    private void funciones_del_menu(){
        clicks_del_menu();
        activar_menu();
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        lyt_menu.setVisibility(LinearLayout.GONE);
    }

    /** Use this set of methods for menu management
     *  @return void
     */
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

    /** Use this set of methods for menu management
     *  @return void
     */
    public boolean menu_activo(){
        LinearLayout lyt_menu=(LinearLayout)findViewById(R.id.lyt_menu);
        int dato= lyt_menu.getVisibility();
        if(dato==LinearLayout.VISIBLE){
            return true;
        }else {
            return false;
        }
    }

    /** Use this set of methods for menu management
     *  @return void
     */
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
