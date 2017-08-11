package reactiva.reactivamovil.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import reactiva.reactivamovil.CalendarActivity;
import reactiva.reactivamovil.R;

/**
 * Created by Fernando on 10/08/2017.
 */

public class CalendarEmptyAppointmentFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Se define el XML para CalendarAppointmentFragment
        View v = inflater.inflate(R.layout.calendar_empty_fragment, container, false);
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
