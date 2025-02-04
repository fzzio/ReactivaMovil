package reactiva.reactivamovil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import static reactiva.reactivamovil.R.drawable.menu_close;

/**
 * Created by erick on 29/7/2017.
 */

public class Menu extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_menu);

    }
    public static void funciones_del_menu(Activity act, String nombre, String clase){
        final Typeface montR= Typeface.createFromAsset(act.getAssets(),"fonts/Montserrat-Regular.ttf");
        ((TextView)act.findViewById(R.id.lbl_welcome)).setTypeface(montR);
        ((TextView)act.findViewById(R.id.lbl_ubicacion)).setTypeface(montR);
        TextView lbl_welcome=(TextView)act.findViewById(R.id.lbl_welcome);
        lbl_welcome.setText("¡Hola, "+nombre+"!");
        Menu.clicks_del_menu(act,nombre,clase);
        activar_menu(act);
        LinearLayout lyt_menu=(LinearLayout)act.findViewById(R.id.lyt_menu);
        lyt_menu.setVisibility(LinearLayout.GONE);
        LinearLayout lyt_ubicacion = (LinearLayout)act.findViewById(R.id.lyt_ubicacion);
        lyt_ubicacion.setVisibility(LinearLayout.VISIBLE);
    }

    /** Use this set of methods for menu management
     *  @return void
     */
    private static void clicks_del_menu(final Activity act,final String nombre,String clase){
        final ImageButton btn_calendario=(ImageButton)act.findViewById(R.id.btn_calendario);
        final ImageButton btn_terapias = (ImageButton)act.findViewById(R.id.btn_terapias);
        final ImageButton btn_paciente=(ImageButton)act.findViewById(R.id.btn_paciente);
        final ImageButton btn_historial=(ImageButton)act.findViewById(R.id.btn_historial);
        final ImageButton btn_perfil=(ImageButton)act.findViewById(R.id.btn_perfil);
        final ImageButton btn_back = (ImageButton)act.findViewById(R.id.btn_back);
        TextView ubicacion = (TextView)act.findViewById(R.id.lbl_ubicacion);
        ubicacion.setText(clase);
        //verPerfil, verCalendario,verTerapia,verHistorial
        if(clase == "AGENDA"){
            btn_calendario.setImageDrawable(act.getDrawable(R.drawable.agenda_activo));
            btn_terapias.setImageDrawable(act.getDrawable(R.drawable.terapia));
            btn_paciente.setImageDrawable(act.getDrawable(R.drawable.paciente));
            btn_historial.setImageDrawable(act.getDrawable(R.drawable.historial));
            btn_perfil.setImageDrawable(act.getDrawable(R.drawable.cerrar_sesion));
        }else if(clase == "TERAPIAS ACTIVAS"){
            //act.findViewById(R.id.txt_terapias_activas_count).setVisibility(LinearLayout.VISIBLE);
            btn_calendario.setImageDrawable(act.getDrawable(R.drawable.agenda));
            btn_terapias.setImageDrawable(act.getDrawable(R.drawable.terapia_activo));
            btn_paciente.setImageDrawable(act.getDrawable(R.drawable.paciente));
            btn_historial.setImageDrawable(act.getDrawable(R.drawable.historial));
            btn_perfil.setImageDrawable(act.getDrawable(R.drawable.cerrar_sesion));
        }else if(clase == "INICIAR TERAPIA"){
            btn_calendario.setImageDrawable(act.getDrawable(R.drawable.agenda));
            btn_terapias.setImageDrawable(act.getDrawable(R.drawable.terapia));
            btn_paciente.setImageDrawable(act.getDrawable(R.drawable.paciente));
            btn_historial.setImageDrawable(act.getDrawable(R.drawable.historial));
            btn_perfil.setImageDrawable(act.getDrawable(R.drawable.cerrar_sesion));
        }else if(clase == "HISTORIAL"){
            btn_calendario.setImageDrawable(act.getDrawable(R.drawable.agenda));
            btn_terapias.setImageDrawable(act.getDrawable(R.drawable.terapia));
            btn_paciente.setImageDrawable(act.getDrawable(R.drawable.paciente));
            btn_historial.setImageDrawable(act.getDrawable(R.drawable.historial_activo));
            btn_perfil.setImageDrawable(act.getDrawable(R.drawable.cerrar_sesion));
        }
        btn_terapias.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(act, VerTerapiaRecyclerActivity.class);
                intent.putExtra("nombre",nombre);
                act.startActivity(intent);
                act.finish();
            }
        });

        btn_calendario.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(act, CalendarActivity.class);
                intent.putExtra("nombre",nombre);
                act.startActivity(intent);
                act.finish();
            }
        });

        /*DISPONIBLE DESDE LA VERSION 2
        btn_paciente.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(act, VerTerapiaRecyclerActivity.class);
                intent.putExtra("nombre",nombre);
                act.startActivity(intent);
            }
        });*/

        btn_historial.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(act, HistorialActivity.class);
                intent.putExtra("nombre",nombre);
                act.startActivity(intent);
                act.finish();
            }
        });

        btn_perfil.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(act,"Cerrando sesión...",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(act, LoginActivity.class);
                act.startActivity(intent);
                act.finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.finish();
            }
        });
    }

    /** Use this set of methods for menu management
     *  @return void
     */
    public static boolean menu_activo(Activity act){
        LinearLayout lyt_menu=(LinearLayout)act.findViewById(R.id.lyt_menu);
        int dato= lyt_menu.getVisibility();
        if(dato==LinearLayout.VISIBLE){
            return true;
        }else {
            return false;
        }
    }

    /** Use this set of methods for menu management
     *  @return void
     */
    private static void activar_menu(final Activity act) {
        final ImageButton btn_oc=(ImageButton)act.findViewById(R.id.btn_oc);
        btn_oc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout lyt_menu=(LinearLayout)act.findViewById(R.id.lyt_menu);
                LinearLayout lyt_ubicacion = (LinearLayout)act.findViewById(R.id.lyt_ubicacion);
                if(menu_activo(act)){
                    lyt_menu.setVisibility(LinearLayout.GONE);
                    btn_oc.setImageDrawable(act.getDrawable(R.drawable.menu));
                    lyt_ubicacion.setVisibility(LinearLayout.VISIBLE);
                }else {
                    lyt_menu.setVisibility(LinearLayout.VISIBLE);
                    btn_oc.setImageDrawable(act.getDrawable(R.drawable.menu_close));
                    lyt_ubicacion.setVisibility(LinearLayout.GONE);
                }
            }
        });
    }
}