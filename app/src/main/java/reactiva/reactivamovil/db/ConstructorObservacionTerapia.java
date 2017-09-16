package reactiva.reactivamovil.db;

import android.content.ContentValues;
import android.content.Context;


import java.util.ArrayList;

import reactiva.reactivamovil.classes.ObservacionTerapia;

/**
 * Created by Erasmo Zurita on 14/9/2017.
 *
 * clase intermediaria entre la base y la vista de presentacion me devuelve los datos como los necesito
 */

public class ConstructorObservacionTerapia {

    private Context context;

    public ConstructorObservacionTerapia(Context context) {
        this.context = context;
    }


    public ArrayList<ObservacionTerapia> obtenerObservacionesDeTerapias(){
        BaseDeDatosPT db = new BaseDeDatosPT(context);
        return db.obtenerTodasLasObservacionesDeLaTerapia();
    }

    public ArrayList<ObservacionTerapia> obtenerObservacionesTerapiasXID(int idTerapia){
        BaseDeDatosPT db = new BaseDeDatosPT(context);
        return db.obtenerTodasLasObservacionesDeUnaTerapiaPorIDTerapia(idTerapia);
    }

    public void insertaPacientesPruebas(BaseDeDatosPT db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDataBasePT.TABLE_PACIENTE_ID,1);
        contentValues.put(ConstantesDataBasePT.TABLE_PACIENTE_TERAPIA_ID, 7);
        contentValues.put(ConstantesDataBasePT.TABLE_PACIENTE_NOMBRE,"israel Zurita");
        contentValues.put(ConstantesDataBasePT.TABLE_PACIENTE_EDAD, "33");
        contentValues.put(ConstantesDataBasePT.TABLE_PACIENTE_HORA_TERAPIA, "14:00");
        contentValues.put(ConstantesDataBasePT.TABLE_PACIENTE_PROXIMA_CITA, "15/09/17");

        db.insertarPacientes(contentValues);
    }

    public void insertarObservacionTerapiaspruebas(BaseDeDatosPT db){
        ContentValues contentValues = new ContentValues();
        // contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID, 1);
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID_TERAPIA, 7);
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_COMENTARIO, "Esto es un comentario de prueba uno");
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_HORA_COMENTARIO, "9:00");
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ESTADO, "PAUSA");
        // contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID, 1);

        db.insertarObservacionTerapia(contentValues);

        contentValues = new ContentValues();
        // contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID, 1);
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID_TERAPIA, 7);
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_COMENTARIO, "Esto es un comentario de prueba dos");
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_HORA_COMENTARIO, "9:05");
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ESTADO, "PAUSA");
        // contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID, 1);

        db.insertarObservacionTerapia(contentValues);

        contentValues = new ContentValues();
        // contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID, 1);
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID_TERAPIA, 7);
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_COMENTARIO, "Esto es un comentario de prueba tres");
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_HORA_COMENTARIO, "9:10");
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ESTADO, "PAUSA");
        // contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID, 1);

        db.insertarObservacionTerapia(contentValues);
    }

    public void insertarNuevoComentario(ObservacionTerapia observacionTerapia){
        BaseDeDatosPT db = new BaseDeDatosPT(context);
        ContentValues contentValues = new ContentValues();
        //contentValues.put(); // la data del comentario
        db.insertarComentario(contentValues);
    }

    public void insertarNuevoComentarioByIdTerapia(int idTerapia,String horaComent, String comentario){
        BaseDeDatosPT db = new BaseDeDatosPT(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID_TERAPIA, idTerapia);
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_COMENTARIO,comentario);
        contentValues.put(ConstantesDataBasePT.TABLE_OBS_TERAPIA_HORA_COMENTARIO,horaComent);

        db.insertarComentario(contentValues);
    }


}
