package reactiva.reactivamovil.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import reactiva.reactivamovil.R;
import reactiva.reactivamovil.VerPerfilActivity;
import reactiva.reactivamovil.classes.Appointment;

/**
 * Created by Fernando on 25/07/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter <CalendarAdapter.CalendarViewHolder> {

    List<Appointment> appointmentList;

    public CalendarAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_appointment_item, parent, false);
        CalendarViewHolder holder = new CalendarViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        String  hour = appointmentList.get(position).getHora();
        final String id_therapy = appointmentList.get(position).getId_therapy();
        final String patient = appointmentList.get(position).getNombres() + appointmentList.get(position).getApellidos();
        final String id_patient = appointmentList.get(position).getId_patient();
        holder.text_view_hour.setText(hour);
        holder.text_view_patient.setText(patient);

        holder.text_view_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Activity activity = (Activity) v.getContext();
                Intent intent = new Intent(activity,VerPerfilActivity.class);
                intent.putExtra("id_therapy", id_therapy); // Therapy ID
                intent.putExtra("id_patient", id_patient); // Patient ID
                intent.putExtra("patient", patient); // Complete name of the patient
                intent.putExtra("nombre",activity.getIntent().getExtras().getString("nombre"));
                activity.startActivityForResult(intent, 0);
                Log.d("Parse.id_therapy: ",id_therapy);
                Log.d("Parse.id_patient: ",id_patient);
                Log.d("Parse.patient: ",patient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        private TextView text_view_hour;
        private TextView text_view_patient;

        public CalendarViewHolder(View itemView) {
            super(itemView);
            text_view_hour = (TextView) itemView.findViewById(R.id.calendar_hour_txv);
            text_view_patient = (TextView) itemView.findViewById(R.id.calendar_patient_txv);
            //Se define la fuentes
            Typeface type = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Montserrat-Regular.ttf");
            text_view_patient.setTypeface(type);
            text_view_hour.setTypeface(type);
        }
    }
}
