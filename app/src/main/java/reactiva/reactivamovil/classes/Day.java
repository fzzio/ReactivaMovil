package reactiva.reactivamovil.classes;

/**
 * Created by Nancy on 2017-08-21.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("count")
    @Expose
    private String count;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}