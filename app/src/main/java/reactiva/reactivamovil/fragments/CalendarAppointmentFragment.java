package reactiva.reactivamovil.fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

import reactiva.reactivamovil.CalendarActivity;
import reactiva.reactivamovil.R;
import reactiva.reactivamovil.Utils;
import reactiva.reactivamovil.VerTerapiaRecyclerActivity;
import reactiva.reactivamovil.adapters.CalendarAdapter;
import reactiva.reactivamovil.classes.Appointment;

/**
 * Created by Fernando on 10/08/2017.
 */

public class CalendarAppointmentFragment extends Fragment {

    private List<Appointment> appointmentList = new ArrayList<>();
    private RecyclerView appointmentRecyclerView;
    private CalendarAdapter calendarAdapter = null;
    private String selected_date;
    String url = Utils.URL+"/ReactivaWeb/index.php/services/getCalendar";

    public static CalendarAppointmentFragment newInstance(String selected_date) {
        CalendarAppointmentFragment fr = new CalendarAppointmentFragment();
        Bundle args = new Bundle();
        args.putString("selected_date", selected_date);
        fr.setArguments(args);
        return fr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se define el XML para CalendarAppointmentFragment
        View v = inflater.inflate(R.layout.calendar_fragment, container, false);

        //Remove all elements from list
        appointmentList.clear();
        //Se define el RecyclerView
        appointmentRecyclerView = (RecyclerView) v.findViewById(R.id.calendar_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        appointmentRecyclerView.setLayoutManager(llm);

        selected_date = getArguments().getString("selected_date", "");

        //Se define el TextView
        TextView selected_date_txv = (TextView) v.findViewById(R.id.selected_date_txv);
        selected_date_txv.setText(selected_date);
        //Se define fonts
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Montserrat-Regular.ttf");
        selected_date_txv.setTypeface(type);


        //FOR DEMO
        appointmentList.add(new Appointment("1", "1", "Juan Sebastian Perez Inivito", "", "07:00", "AM"));
        appointmentList.add(new Appointment("1", "1", "Ernesto Fua Suarez Pele", "", "07:30", "AM"));
        appointmentList.add(new Appointment("1", "1", "Fabricio Daniel Moreno Ruz", "", "09:00", "AM"));
        appointmentList.add(new Appointment("1", "1", "Mario Ivan Vanilla Zama", "", "10:00", "AM"));
        appointmentList.add(new Appointment("1", "1", "Denisse Andrea Universo Chiquito", "", "12:00", "PM"));
        appointmentList.add(new Appointment("1", "1", "Pedro Marto Lota Tixi", "", "02:00", "PM"));
        appointmentList.add(new Appointment("1", "1", "Maria Gracia Ruiz Xoom", "", "04:00", "PM"));
        appointmentAdapterBuilder();

        return v;
    }

    public void appointmentAdapterBuilder(){
        this.calendarAdapter = new CalendarAdapter(this.appointmentList);
        this.appointmentRecyclerView.setAdapter(calendarAdapter);
        calendarAdapter.notifyDataSetChanged();
    }

    public void getInfo(){

        //Create the Volley request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Se define las variables para JSONResponse
        String words [] = selected_date.split(" ");
        String mes = words[2];
        final String mes_mcv = monthFormatter(mes);
        final String año_mcv = words[3];
        final String dia_mcv = words[1];

        //Add parameters to URL from arguments
        url = url + "?month=" + mes_mcv + "&year=" + año_mcv;

        //JSON Thread START
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response: ",response.toString());
                for(int i=0;i<response.length();i++) {
                    try {
                        JSONObject day = response.getJSONObject(i).getJSONObject("day");
                        String dia_ws = day.getString("day");
                        //Verify selected_date
                        if (dia_mcv.equals(dia_ws)){
                            JSONArray therapies = response.getJSONObject(i).getJSONArray("therapies");
                            //Iterate JSONArray
                            for(int j =0;j<therapies.length();j++) {
                                Log.d("Therapies.Details: ",therapies.getJSONObject(j).get("id_therapy").toString());
                                //Add to list elements with the adapter class
                                String id_therapy = therapies.getJSONObject(j).get("id_therapy").toString();
                                String id_patient = therapies.getJSONObject(j).get("id_patient").toString();
                                String name = therapies.getJSONObject(j).get("name").toString() + " ";
                                String last_name = therapies.getJSONObject(j).get("last_name").toString();
                                //Time parsing
                                String time = therapies.getJSONObject(j).get("time").toString();
                                String date_mcv = dia_mcv + "-" + mes_mcv + "-" + año_mcv + " " + time;
                                String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
                                System.out.println(date_mcv);
                                System.out.println(timeStamp);
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
                                    appointmentList.add(new Appointment(id_therapy, id_patient, name, last_name, reformattedStr, time));
                                }
                            }
                            Collections.sort(appointmentList, new Comparator<Appointment>() {
                                @Override
                                public int compare(Appointment o1, Appointment o2) {
                                    return o1.getFormat().compareTo(o2.getFormat());
                                }
                            });
                            appointmentAdapterBuilder();
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

    public String monthFormatter(String mes) {
        switch(mes) {
            case "ene.":    mes="01";
                            break;
            case "feb.":    mes="02";
                            break;
            case "mar.":    mes="03";
                            break;
            case "abr.":    mes="04";
                            break;
            case "may.":    mes="05";
                            break;
            case "jun.":    mes="06";
                            break;
            case "jul.":    mes="07";
                            break;
            case "ago.":    mes="08";
                            break;
            case "sep.":    mes="09";
                            break;
            case "oct.":    mes="10";
                            break;
            case "nov.":    mes="11";
                            break;
            case "dic.":    mes="12";
                            break;
        }
        return mes;
    }

}
