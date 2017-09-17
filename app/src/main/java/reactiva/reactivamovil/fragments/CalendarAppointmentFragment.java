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
    String url ="http://107.170.105.224:6522/ReactivaWeb/index.php/services/getCalendar";

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
        //Se define las variables para JSONResponse
        String selected_date = getArguments().getString("selected_date", "");
        String words [] = selected_date.split(" ");
        String mes = words[2];
        final String mes_mcv = monthFormatter(mes);
        final String año_mcv = words[3];
        final String dia_mcv = words[1];
        //Create the Volley request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //Remove all elements from list
        appointmentList.clear();
        //Se define el RecyclerView
        appointmentRecyclerView = (RecyclerView) v.findViewById(R.id.calendar_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        appointmentRecyclerView.setLayoutManager(llm);
        //Add parameters to URL from arguments
        url = url + "?month=" + mes_mcv + "&year=" + año_mcv;
        System.out.println(url);
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
                                String id_patient = therapies.getJSONObject(j).get("id_patient").toString();
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
                                    appointmentList.add(new Appointment(id_patient, name, last_names, reformattedStr, time));
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
        //Se define el TextView
        TextView selected_date_txv = (TextView) v.findViewById(R.id.selected_date_txv);
        //String selected_date = getArguments().getString("selected_date", "");
        selected_date_txv.setText(selected_date);
        //Se define fonts
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Montserrat-Regular.ttf");
        selected_date_txv.setTypeface(type);
        return v;
    }

    public void appointmentAdapterBuilder(){
        this.calendarAdapter = new CalendarAdapter(this.appointmentList);
        this.appointmentRecyclerView.setAdapter(calendarAdapter);
        calendarAdapter.notifyDataSetChanged();
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
