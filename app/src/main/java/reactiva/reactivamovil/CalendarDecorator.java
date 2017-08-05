package reactiva.reactivamovil;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;

/**
 * Created by Fernando on 03/08/2017.
 */

public class CalendarDecorator implements DayViewDecorator {
    private static final int color = Color.parseColor("#228BC34A");
    private final Calendar calendar = Calendar.getInstance();
    //private final Drawable drawable;

    public CalendarDecorator() {
        //drawable = context.getResources().getDrawable(R.drawable.calendar_selector);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == Calendar.MONDAY || weekDay == Calendar.WEDNESDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        //view.setSelectionDrawable(drawable);
        view.addSpan(new DotSpan(5, color));
    }
}
