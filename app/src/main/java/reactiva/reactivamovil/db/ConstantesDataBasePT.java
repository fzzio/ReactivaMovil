package reactiva.reactivamovil.db;

/**
 * Created by Usuario on 5/9/2017.
 */

public final class ConstantesDataBasePT {

    public static final String DATABASE_NAME = "pacienteDetallETerapia";
    public static final int DATABASE_VERSION = 1;

    //Constantes tabla Paciente
    public static final String TABLE_PACIENTE = "paciente";

    public static final String TABLE_PACIENTE_ID = "idPaciente";
    public static final String TABLE_PACIENTE_TERAPIA_ID = "idTerapia";
    public static final String TABLE_PACIENTE_NOMBRE = "nombrep";
    public static final String TABLE_PACIENTE_EDAD = "edadp";
    public static final String TABLE_PACIENTE_HORA_TERAPIA = "horaTerapia";
    public static final String TABLE_PACIENTE_PROXIMA_CITA = "proximaCita";


    //Constantes tabla Observaciones Terapia
    public static final String TABLE_OBS_TERAPIA = "observacionTerapia";

    public static final String TABLE_OBS_TERAPIA_ID = "idObservacion";
    public static final String TABLE_OBS_TERAPIA_ID_TERAPIA = "idTerapia";
    public static final String TABLE_OBS_TERAPIA_COMENTARIO = "comentario";
    public static final String TABLE_OBS_TERAPIA_HORA_COMENTARIO = "horaComentario";
    public static final String TABLE_OBS_TERAPIA_ESTADO = "estadoSessionTer";
    //imagen detalle de la session de terapia
    //public static final String TABLE_OBS_TERAPIA_IMAGEN = "imagenObser";



}
