package reactiva.reactivamovil;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.junit.Test;
import junit.framework.*;

import java.util.Calendar;

/**
 * Created by Fernando on 18/9/2017.
 * Unit Test for Calendar Activity (Black Box Test)
 */

public class CalendarActivityTest {
    @Test
    public void testCurrentMonth() {
        String result = CalendarActivity.current_month(11);
        Assert.assertEquals("DICIEMBRE", result);
    }

    @Test
    public void testIsToday() {
        CalendarDay calendarDay = CalendarDay.from(Calendar.getInstance());
        boolean result = CalendarActivity.isToday(calendarDay);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testGetMonth() {
        CalendarDay calendarDay = CalendarDay.from(Calendar.getInstance());
        String result = CalendarActivity.get_month(0);
        Assert.assertEquals("ene.", result);
    }

    @Test
    public void testGetWeek() {
        CalendarDay calendarDay = CalendarDay.from(Calendar.getInstance());
        String result = CalendarActivity.get_week(1);
        Assert.assertEquals("Domingo", result);
    }

    @Test
    public void testMonthFormatter() {
        CalendarDay calendarDay = CalendarDay.from(Calendar.getInstance());
        String result = CalendarActivity.monthFormatter(11);
        Assert.assertEquals("12", result);
    }

}
