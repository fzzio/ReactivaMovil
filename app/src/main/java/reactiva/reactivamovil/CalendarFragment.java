package reactiva.reactivamovil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

/**
 * Created by Fernando on 09/07/2017.
 */

public class CalendarFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState) {

        final MaterialCalendarView materialCalendarView = (MaterialCalendarView) getView().findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2010, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                materialCalendarView.state().edit()
                        .setFirstDayOfWeek(Calendar.MONDAY)
                        .setMinimumDate(CalendarDay.from(2010, 1, 1))
                        .setMaximumDate(CalendarDay.from(2100, 12, 31))
                        .setCalendarDisplayMode(CalendarMode.WEEKS)
                        .commit();

            }
        });
        return inflater.inflate(R.layout.fragment_calendar,container,false);
    }
}
