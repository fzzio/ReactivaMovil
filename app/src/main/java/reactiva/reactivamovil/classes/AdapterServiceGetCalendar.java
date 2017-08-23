package reactiva.reactivamovil.classes;

import com.example.nancy.dbasegson.desarializador.TerapiaDeserializador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Nancy on 2017-08-21.
 */

public class AdapterServiceGetCalendar {




    private Gson construyendoGsonDeserializador() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TerapiaDiaResponse.class, new TerapiaDeserializador());

        Gson miGson = gsonBuilder.create();
        return miGson;
    }
}
