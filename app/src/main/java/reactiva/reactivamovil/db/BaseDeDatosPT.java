package reactiva.reactivamovil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

import reactiva.reactivamovil.classes.ObservacionTerapia;

/**
 * Created by Usuario on 5/9/2017.
 * Base de datos paciente detalle terapia
 */

public class BaseDeDatosPT extends SQLiteOpenHelper{

    private Context context;

    public BaseDeDatosPT(Context context) {
        super(context, ConstantesDataBasePT.DATABASE_NAME, null, ConstantesDataBasePT.DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Creacion de las tablas de la base de datos
     * **/
    @Override
    public void onCreate(SQLiteDatabase db) {
        //tabla paciente
        String queryCrearTablePaciente = "CREATE TABLE " + ConstantesDataBasePT.TABLE_PACIENTE + "(" +
                ConstantesDataBasePT.TABLE_PACIENTE_ID           + " INTEGER PRIMARY KEY, " +
                ConstantesDataBasePT.TABLE_PACIENTE_TERAPIA_ID   + " INTEGER, " +
                ConstantesDataBasePT.TABLE_PACIENTE_NOMBRE       + " TEXT, " +
                ConstantesDataBasePT.TABLE_PACIENTE_EDAD         + " TEXT, " +
                ConstantesDataBasePT.TABLE_PACIENTE_HORA_TERAPIA + " TEXT, " +
                ConstantesDataBasePT.TABLE_PACIENTE_PROXIMA_CITA + " TEXT" +
                ")";
        //tabla observacionTerapia
        String queryCrearTableTerapia = "CREATE TABLE " + ConstantesDataBasePT.TABLE_OBS_TERAPIA + "(" +
                ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID                + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID_TERAPIA        + " INTEGER, " +  // clave foranea, el id de la terapia del paciente
                ConstantesDataBasePT.TABLE_OBS_TERAPIA_COMENTARIO        + " TEXT, " +
                ConstantesDataBasePT.TABLE_OBS_TERAPIA_HORA_COMENTARIO   + " TEXT, " +
                ConstantesDataBasePT.TABLE_OBS_TERAPIA_ESTADO            + " TEXT, " +
                "FOREIGN KEY (" + ConstantesDataBasePT.TABLE_OBS_TERAPIA_ID_TERAPIA + ") " +
                "REFERENCES " + ConstantesDataBasePT.TABLE_PACIENTE + "("+ConstantesDataBasePT.TABLE_PACIENTE_ID + ")" +
                ")" ;


        db.execSQL(queryCrearTablePaciente);
        db.execSQL(queryCrearTableTerapia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesDataBasePT.TABLE_PACIENTE);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesDataBasePT.TABLE_OBS_TERAPIA);
        onCreate(db);
    }

    /*
    * Metodo que devuelve todas las observaciones de una terapia
    * */
    public ArrayList<ObservacionTerapia> obtenerTodasLasObservacionesDeLaTerapia(){
        ArrayList<ObservacionTerapia> observacionTerapias = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesDataBasePT.TABLE_OBS_TERAPIA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            ObservacionTerapia observacionActual = new ObservacionTerapia();
            observacionActual.setIdObservacionTerapia(registros.getInt(0));
            observacionActual.setIdTerapia(registros.getInt(1));
            observacionActual.setObsComentario(registros.getString(2));
            observacionActual.setObsHoraComentario(registros.getString(3));
            observacionActual.setObsEstado(registros.getString(4));

            observacionTerapias.add(observacionActual);
        }

        db.close();
        return observacionTerapias;
    }

    /*
    * Metodo que inserta una observacion de terapia
    * */
    public void insertarObservacionTerapia(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDataBasePT.TABLE_OBS_TERAPIA, null, contentValues);
        db.close();
    }

    public void insertarPacientes(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDataBasePT.TABLE_PACIENTE, null, contentValues);
        db.close();
    }

    /// insertar comentario

    public void insertarComentario(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDataBasePT.TABLE_OBS_TERAPIA, null, contentValues);
        db.close();
    }
}
