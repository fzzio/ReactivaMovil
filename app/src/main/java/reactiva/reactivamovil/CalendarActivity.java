package reactiva.reactivamovil;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import reactiva.reactivamovil.classes.Appointment;
import reactiva.reactivamovil.classes.OnSwipeTouchListener;
import reactiva.reactivamovil.decorators.MonserratDecorator;
import reactiva.reactivamovil.decorators.TodayDecorator;
import reactiva.reactivamovil.fragments.CalendarAppointmentFragment;
import reactiva.reactivamovil.fragments.CalendarEmptyAppointmentFragment;

/**
 * Created by Fernando on 09/07/2017.
 */

public class CalendarActivity extends AppCompatActivity {
    FragmentTransaction ft, fte;
    private boolean closed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String nombre = getIntent().getExtras().getString("nombre");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        final MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        final TodayDecorator todayDecorator = new TodayDecorator();
        final MonserratDecorator monserratDecorator = new MonserratDecorator(this);
        final TextView calendar_today = (TextView) findViewById(R.id.calendar_today_txv);
        final TextView calendar_month = (TextView) findViewById(R.id.calendar_month_txv);
        final ImageView calendar_closed = (ImageView) findViewById(R.id.calendar_closed);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.appointmentContainer);

        final Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        calendar_month.setTypeface(type);
        calendar_today.setTypeface(type);

        //Initialize Calendar DisplayMode in MONTHS
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.WEEKS)
                .commit();

        //Initialize CalendarDecorator
        materialCalendarView.addDecorators(todayDecorator, monserratDecorator);
        //Initialize Top barVisible
        materialCalendarView.setTopbarVisible(false);
        //Initialize Dynamic Month Label
        CalendarDay day = materialCalendarView.getCurrentDate();
        int month_label = day.getMonth();
        calendar_month.setText(current_month(month_label));

        //Initialize MaterialCalendarView
        initializeMaterialCalendarView(materialCalendarView, calendar_today, calendar_month, calendar_closed);

        //Every time that the materialCalendarView isSwipe do the following piece of code
        materialCalendarView.setOnTouchListener(new OnSwipeTouchListener(CalendarActivity.this) {
            public void onSwipeRight() {
                //Toast.makeText(CalendarActivity.this, "right", Toast.LENGTH_SHORT).show();
                Calendar this_date = materialCalendarView.getSelectedDate().getCalendar();
                this_date.add(Calendar.DATE, -1);
                materialCalendarView.setSelectedDate(this_date);
                if (isToday(materialCalendarView.getSelectedDate())){
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
                isAppointment(selected_date, calendar_closed);
            }

            public void onSwipeLeft() {
                //Toast.makeText(CalendarActivity.this, "left", Toast.LENGTH_SHORT).show();
                Calendar this_date = materialCalendarView.getSelectedDate().getCalendar();
                this_date.add(Calendar.DATE, 1);
                materialCalendarView.setSelectedDate(this_date);
                if (isToday(materialCalendarView.getSelectedDate())){
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
                isAppointment(selected_date, calendar_closed);
            }
        });

        //Every time when OnClickListener hear something on ImageView
        calendar_closed.setOnClickListener(new View.OnClickListener() {
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
                //Change visibility
                calendar_closed.setVisibility(View.GONE);
            }
        });


        //Every time when OnDateChangedListener hear something DisplayMode is changed to WEEKS
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                if (isToday(materialCalendarView.getSelectedDate())){
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
                isAppointment(selected_date, calendar_closed);
            }
        });

        //Every time when OnClickListener hear something DisplayMode is changed to WEEKS
        calendar_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSelectedDateNull(materialCalendarView.getSelectedDate()) && !isToday(materialCalendarView.getSelectedDate())) {
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
                    isAppointment(selected_date, calendar_closed);
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
                //Change visibility
                calendar_closed.setVisibility(View.GONE);
            }
        });

        Menu.funciones_del_menu(CalendarActivity.this,nombre,"Agenda");
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
     * Use this method to initialize MaterialCalendarView
     * @return void
     */
    public void initializeMaterialCalendarView(MaterialCalendarView materialCalendarView, TextView calendar_today, TextView calendar_month, ImageView calendar_closed){
        if(isSelectedDateNull(materialCalendarView.getSelectedDate()) && !isToday(materialCalendarView.getSelectedDate())) {
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
            isAppointment(selected_date, calendar_closed);
        }
    }

    /**
     * Use this method to display the CalendarAppointmentFragment
     * @return void
     */
    public void displayCalendarAppointmentFragment(String selected_date, ImageView calendar_closed) {
        //Change Visibility
        calendar_closed.setVisibility(View.VISIBLE);
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
    public void displayCalendarEmptyAppointmentFragment(String selected_date, ImageView calendar_closed) {
        //Change Visibility
        calendar_closed.setVisibility(View.VISIBLE);
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
    public void isAppointment(final CalendarDay selected_date, final ImageView calendar_closed) {
        String url ="http://107.170.105.224:6522/ReactivaWeb/index.php/services/getCalendar";
        int day = selected_date.getDay();
        int mes = selected_date.getMonth();
        int año = selected_date.getYear();
        final String dia_mcv = Integer.toString(day);
        final String mes_mcv = monthFormatter(mes);
        final String año_mcv = Integer.toString(año);
        //Create the Volley request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Add parameters to URL from arguments
        url = url + "?month=" + mes_mcv + "&year=" + año_mcv;
        System.out.println(url);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response: ",response.toString());
                if (response.toString().equals("[]")) {
                    displayCalendarEmptyAppointmentFragment(dateFormatter(selected_date), calendar_closed);
                    removeCalendarAppointmentFragment();
                }
                for(int i=0;i<response.length();i++) {
                    try {
                        JSONObject day = response.getJSONObject(i).getJSONObject("day");
                        String dia_ws = day.getString("day");
                        //Verify selected_date
                        if (dia_mcv.equals(dia_ws)){
                            int counter = 0;
                            JSONArray therapies = response.getJSONObject(i).getJSONArray("therapies");
                            //Iterate JSONArray
                            for(int j =0;j<therapies.length();j++) {
                                Log.d("Therapies.Details: ",therapies.getJSONObject(j).get("id_therapy").toString());
                                //Add to list elements with the adapter class
                                String fullname = therapies.getJSONObject(j).get("fullname").toString();
                                String words_fullname [] = fullname.split(" ");
                                String name = words_fullname[0] + " " + words_fullname[1] ;
                                String last_names = " " + words_fullname[2] + " " + words_fullname[3];
                                //Time parsing
                                String time = therapies.getJSONObject(j).get("time").toString();
                                String date_mcv = dia_mcv + "-" + mes_mcv + "-" + año_mcv + " " + time;
                                String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
                                System.out.println(date_mcv);
                                System.out.println(timeStamp);
                                SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
                                SimpleDateFormat fromService = new SimpleDateFormat("hh:mm:ss");
                                SimpleDateFormat formatReactiva = new SimpleDateFormat("hh:mm a");
                                String reformattedStr = "";
                                try {
                                    reformattedStr = formatReactiva.format(fromService.parse(time));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                int result = date_mcv.compareTo(timeStamp);
                                if (result > 0) {   //   > 0
                                    counter = counter + 1;
                                }
                            }
                            if (counter == 0) {
                                displayCalendarEmptyAppointmentFragment(dateFormatter(selected_date), calendar_closed);
                                removeCalendarAppointmentFragment();
                                break;
                            }
                            displayCalendarAppointmentFragment(dateFormatter(selected_date), calendar_closed);
                            removeCalendarEmptyAppointmentFragment();
                            break;
                        } else if (i == response.length() - 1) {
                            displayCalendarEmptyAppointmentFragment(dateFormatter(selected_date), calendar_closed);
                            removeCalendarAppointmentFragment();
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Error.Super", e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response: ",error.toString());
                error.printStackTrace();
            }
        });
        //JSON Thread ENDS
        requestQueue.add(jsonArrayRequest);
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

    /** Use this method to get is today value
     *  @return true if is today
     *          false otherwise
     */
    public boolean isToday(CalendarDay selected_date) {
        CalendarDay current_date = CalendarDay.today();
        if (current_date.equals(selected_date))
            return true;
        return false;
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

    public String monthFormatter(int value) {
        String mes="";
        switch(value) {
            case 0:    mes="01";
                break;
            case 1:    mes="02";
                break;
            case 2:    mes="03";
                break;
            case 3:    mes="04";
                break;
            case 4:    mes="05";
                break;
            case 5:    mes="06";
                break;
            case 6:    mes="07";
                break;
            case 7:    mes="08";
                break;
            case 8:    mes="09";
                break;
            case 9:    mes="10";
                break;
            case 10:    mes="11";
                break;
            case 11:    mes="12";
                break;
        }
        return mes;
    }

    /** Use this method to get closed value
     *  @return true if closed have been clicked
     *          false otherwise
     */
    public boolean isClosed() {
        return closed;
    }

    /** Use this method to set closed value
     *  @param  closed the boolean value
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /** Use this set of methods for menu management
     *  @return void
     */
}