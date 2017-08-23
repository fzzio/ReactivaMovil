package reactiva.reactivamovil.deserializador;

//import com.example.nancy.dbasegson.TerapiaDia;
//import com.example.nancy.dbasegson.TerapiaDiaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import reactiva.reactivamovil.classes.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Nancy on 2017-08-19.
 */

public class TerapiaDeserializador implements JsonDeserializer<TerapiaDiaResponse>{

    @Override
    public TerapiaDiaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        TerapiaDiaResponse terapiaDiaResponse = gson.fromJson(json, TerapiaDiaResponse.class);
        JsonArray terapiaResponseData = json.getAsJsonArray();

        terapiaDiaResponse.setTerapiaDias(deserializadorTerapiaDeJson(terapiaResponseData));
        return terapiaDiaResponse;
    }
    
    public ArrayList<TerapiaDia> deserializadorTerapiaDeJson(JsonArray terapiaResponseDta) {
        ArrayList<TerapiaDia> terapiaDias = new ArrayList<>();
        for (int i = 0; i < terapiaResponseDta.size() ; i++) {

            JsonObject terapiasResponseDataObject = terapiaResponseDta.get(i).getAsJsonObject();

            JsonArray terapiasJson  = terapiasResponseDataObject.getAsJsonArray("therapies");
            JsonObject unaTerapia   = terapiasJson.getAsJsonObject();

            String idTherapy        = unaTerapia.get("id_therapy").getAsString();
            String idPatient        = unaTerapia.get("id_patient").getAsString();
            String idConsulta       = unaTerapia.get("id_consulta").getAsString();
            String nombrePaciente   = unaTerapia.get("fullname").getAsString();
            String status           = unaTerapia.get("status").getAsString();

            TerapiaDia terapiaDia = new TerapiaDia();
            //terapiaDia.setIdTherapy(idTherapy);
            //terapiaDia.setIdPatient(idPatient);
            //terapiaDia.setIdConsulta(idConsulta);
            //terapiaDia.setFullname(nombrePaciente);
            //terapiaDia.setStatus(status);

            terapiaDias.add(terapiaDia);

        }
        return terapiaDias;
    }
}
