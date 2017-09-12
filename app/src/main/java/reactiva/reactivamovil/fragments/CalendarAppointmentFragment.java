package reactiva.reactivamovil.fragments;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import reactiva.reactivamovil.CalendarActivity;
import reactiva.reactivamovil.R;
import reactiva.reactivamovil.adapters.CalendarAdapter;
import reactiva.reactivamovil.classes.Appointment;

/**
 * Created by Fernando on 10/08/2017.
 */

public class CalendarAppointmentFragment extends Fragment{
    List<Appointment> appointmentList = new ArrayList<>();
    private RecyclerView appointmentRecyclerView;

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
        //Se define el RecyclerView
        appointmentRecyclerView = (RecyclerView) v.findViewById(R.id.calendar_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        appointmentRecyclerView.setLayoutManager(llm);
        appointmentListBuilder();
        appointmentAdapterBuilder();
        //Se define el TextView
        TextView selected_date_txv = (TextView) v.findViewById(R.id.selected_date_txv);
        String selected_date = getArguments().getString("selected_date", "");
        selected_date_txv.setText(selected_date);
        //Se define la fuente
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Montserrat-Regular.ttf");
        selected_date_txv.setTypeface(type);
        return v;
    }

    /**
     * Usa este método para inicializar appointmentList.
     * @return void
     */
    public void appointmentListBuilder() {
        appointmentList.removeAll(appointmentList);
        appointmentList.add(new Appointment("Nadia", " Pezantes Hermenjildo", "7:00 AM"));
        appointmentList.add(new Appointment("Ángel", " Peña García", "8:15 AM"));
        appointmentList.add(new Appointment("Pablo", " Peñafiel Guerrero", "8:30 AM"));
        appointmentList.add(new Appointment("Erasmo", " Pesantes De Los Monteros", "9:45 AM"));
        appointmentList.add(new Appointment("Fabricio", " Perero Aguirre", "10:00 AM"));
        appointmentList.add(new Appointment("María", " Pesantes Maduro", "11:15 AM"));
        appointmentList.add(new Appointment("Luz", " Piedrahita Reyes", "12:00 PM"));
        appointmentList.add(new Appointment("Mario", " Peréz Contreras", "12:50 PM"));
        appointmentList.add(new Appointment("Michelle", " Espinoza Martinez", "1:00 PM"));
        appointmentList.add(new Appointment("Henry", " Ascensio Lomas", "2:00 PM"));
    }

    public void appointmentAdapterBuilder(){
        CalendarAdapter calendarAdapter = new CalendarAdapter(appointmentList);
        appointmentRecyclerView.setAdapter(calendarAdapter);
    }

}
