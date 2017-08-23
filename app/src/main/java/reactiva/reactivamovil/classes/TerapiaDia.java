package reactiva.reactivamovil.classes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nancy on 2017-08-19.
 */

public class TerapiaDia {

    @SerializedName("day")
    @Expose
    private Day day;
    @SerializedName("therapies")
    @Expose
    private List<Therapy> therapies = null;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public List<Therapy> getTherapies() {
        return therapies;
    }

    public void setTherapies(List<Therapy> therapies) {
        this.therapies = therapies;
    }
}
