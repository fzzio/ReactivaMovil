package reactiva.reactivamovil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edgardan on 18/08/2017.
 */

public class UsuarioSQLiteHelper extends SQLiteOpenHelper {

    //Variable que contendrá la sentencia SQL de creación de la tabla
    String sql = "CREATE TABLE Pacientes (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre VARCHAR (20) NOT NULL," +
            "edad TINYINT," +
            "cronometro DATETIME," +
            "proximacita DATE," +
            "observacion TINYINT," +//necesita amcompanante = 2, medio = 1, no necesita = 0
            "ult_obs_medica VARCHAR (255));";

    public UsuarioSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Este método se ejecuta automáticamente cuando la base de datos no existe
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Este método se ejecuta cuando se detecta que la versión de la base de datos cambia
        //en este ejemplo se elimina para crear otra
        db.execSQL("DROP TABLE IF EXISTS Paciente");
        db.execSQL(sql);
    }
}
