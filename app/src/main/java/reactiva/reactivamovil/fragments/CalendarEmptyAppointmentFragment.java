package reactiva.reactivamovil.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import reactiva.reactivamovil.CalendarActivity;
import reactiva.reactivamovil.R;

/**
 * Created by Fernando on 10/08/2017.
 */

public class CalendarEmptyAppointmentFragment extends Fragment{

    public static CalendarEmptyAppointmentFragment newInstance(String selected_date) {
        CalendarEmptyAppointmentFragment fr = new CalendarEmptyAppointmentFragment();
        Bundle args = new Bundle();
        args.putString("selected_date", selected_date);
        fr.setArguments(args);
        return fr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se define el XML para CalendarAppointmentFragment
        View v = inflater.inflate(R.layout.calendar_empty_fragment, container, false);
        //Se define el TextView
        TextView empty_selected_date_txv = (TextView) v.findViewById(R.id.empty_selected_date_txv);
        String selected_date = getArguments().getString("selected_date");
        empty_selected_date_txv.setText(selected_date);
        //Se define el ImageView
        ImageView calendar_empty_close = (ImageView) v.findViewById(R.id.calendar_empty_close_imv);
        calendar_empty_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarActivity calendarActivity = (CalendarActivity) getActivity();
                calendarActivity.removeCalendarEmptyAppointmentFragment();
                calendarActivity.removeCalendarAppointmentFragment();
            }
        });
        return v;
    }
}
