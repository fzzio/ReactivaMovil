package reactiva.reactivamovil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fernando on 25/07/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter <CalendarAdapter.CalendarViewHolder> {

    List<ItemCalendarView> listaCitas;

    public CalendarAdapter(List<ItemCalendarView> listaCitas) {
        this.listaCitas = listaCitas;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_row_item,parent,false);
        CalendarViewHolder holder = new CalendarViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        String  item = listaCitas.get(position).getHora() +
                "    "  +
                listaCitas.get(position).getNombres() +
                listaCitas.get(position).getApellidos();

        holder.text_cita.setText(item);
    }

    @Override
    public int getItemCount() {
        return listaCitas.size();
    }


    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView text_cita;
        public CalendarViewHolder(View itemView) {
            super(itemView);
            text_cita = (TextView) itemView.findViewById(R.id.cita);
        }
    }
}
