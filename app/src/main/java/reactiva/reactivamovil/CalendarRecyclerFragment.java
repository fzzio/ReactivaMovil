package reactiva.reactivamovil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Fernando on 25/07/2017.
 */

public class CalendarRecyclerFragment extends AppCompatActivity {

    View section, header;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_calendar_recycler);

        RecyclerView rv = (RecyclerView) findViewById(R.id.calendar_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        List<ItemCalendarView> listaCitas = Arrays.asList(new ItemCalendarView("José Marìa", "Velasco Ibarra", "7:00 AM"),
                new ItemCalendarView("Antonio Jerónimo", "Zambrano Villalba", "8:00 AM"),
                new ItemCalendarView("Kevin Ocran", "Sanchez Arreaga", "9:00 AM"),
                new ItemCalendarView("Rafael Alfonso", "Martinez Mata", "10:00 AM"),
                new ItemCalendarView("Keyla Lindsay", "Pacheco Tovar", "11:00 AM"));
        CalendarAdapter Adaptador = new CalendarAdapter(listaCitas);
        rv.setAdapter(Adaptador);

    }
}
