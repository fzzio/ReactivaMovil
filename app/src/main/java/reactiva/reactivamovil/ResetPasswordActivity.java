package reactiva.reactivamovil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ResetPasswordActivity extends AppCompatActivity {
    Button btn_verificar_correo, btn_verificar_codigo,btn_restablecer_pass;
    LinearLayout layout_codigo,layout_new_password,layout_restablecer;
    String nombre;
    TextView txt_email,txt_nuevo_pass,txt_confirmar_pass,txt_digitos;
    String url ="http://192.168.0.5:8081/ws/restablecer.php";
    String url_login ="http://192.168.0.5:8081/ws/login.php";
    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString((messageDigest[i] & 0xFF) | 0x100).substring(1, 3));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void verificarCorreo(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response: ",response);
                        if(!response.equals("")){
                            nombre=response;
                            layout_codigo.setVisibility(View.VISIBLE);
                            btn_verificar_correo.setEnabled(false);
                        }else{
                            Toast.makeText(ResetPasswordActivity.this,"El correo proporcionado no esta en nuestro sistema",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response: ",error.toString());
                error.printStackTrace();
                Toast.makeText(ResetPasswordActivity.this,error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("correo",txt_email.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void restablecerPass(){
        findViewById(R.id.layout_restablecer).setVisibility(LinearLayout.GONE);
        findViewById(R.id.layout_new_password).setVisibility(LinearLayout.VISIBLE);
    }
    public void verificarCodigo(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response: ",response);
                        if(response.equals("1")){
                            restablecerPass();
                        }else{
                            Toast.makeText(ResetPasswordActivity.this,"El código es incorrecto",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response: ",error.toString());
                error.printStackTrace();
                Toast.makeText(ResetPasswordActivity.this,error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("codigo",txt_digitos.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void nuevoPass(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response: ",response);
                        if(response.equals("1")){
                            Intent i = new Intent(ResetPasswordActivity.this,VerPerfilActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(ResetPasswordActivity.this,"Error FATAL",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response: ",error.toString());
                error.printStackTrace();
                Toast.makeText(ResetPasswordActivity.this,error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                String aConvertir=md5(txt_confirmar_pass.getText().toString().trim());
                if (!aConvertir.isEmpty()){
                    params.put("npass",aConvertir);
                }
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void loginNuevoPass(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response: ",response);
                        try {
                            JSONObject g = new JSONObject(response);
                            if(g.get("event").toString().equals("1")){
                                Intent i = new Intent(ResetPasswordActivity.this,VerPerfilActivity.class);
                                startActivity(i);
                            }else if(g.get("data").toString().equals("0")) {
                                Toast.makeText(ResetPasswordActivity.this,"Usuario y/o Constraseña erróneos",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(ResetPasswordActivity.this,"Error FATAL",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response: ",error.toString());
                error.printStackTrace();
                Toast.makeText(ResetPasswordActivity.this,error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("user",nombre);
                String aConvertir=md5(txt_confirmar_pass.getText().toString().trim());
                if (!aConvertir.isEmpty()){
                    params.put("pass",aConvertir);
                }
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Typeface montR=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        btn_verificar_correo=(Button) findViewById(R.id.btn_enviar_correo);
        btn_verificar_codigo=(Button) findViewById(R.id.btn_verificar_codigo);
        btn_restablecer_pass=(Button) findViewById(R.id.btn_restablecer_pass);
        layout_codigo=(LinearLayout) findViewById(R.id.layout_codigo);
        layout_restablecer=(LinearLayout) findViewById(R.id.layout_restablecer);
        txt_nuevo_pass = (TextView) findViewById(R.id.txt_nuevo_pass);
        txt_confirmar_pass = (TextView) findViewById(R.id.txt_confirmar_pass);
        txt_email=(AutoCompleteTextView) findViewById(R.id.email);
        txt_digitos=(TextView)findViewById(R.id.txt_digitos);
        //formato
        //labels
        ((TextView)findViewById(R.id.titulo_pass)).setTypeface(montR);
        ((TextView)findViewById(R.id.descripcion_pass)).setTypeface(montR);
        ((TextView)findViewById(R.id.lbl_user)).setTypeface(montR);
        ((TextView)findViewById(R.id.descripcion_codigo)).setTypeface(montR);
        ((TextView)findViewById(R.id.descripcion_nuevo_pass)).setTypeface(montR);
        ((TextView)findViewById(R.id.lbl_nuevo_pass)).setTypeface(montR);
        txt_nuevo_pass.setTypeface(montR);
        ((TextView)findViewById(R.id.lbl_confirmar_pass)).setTypeface(montR);
        txt_confirmar_pass.setTypeface(montR);

        ((AutoCompleteTextView)findViewById(R.id.email)).setTypeface(montR);
        ((EditText)findViewById(R.id.txt_digitos)).setTypeface(montR);

        ((Button)findViewById(R.id.btn_enviar_correo)).setTypeface(montR);
        ((Button)findViewById(R.id.btn_verificar_codigo)).setTypeface(montR);
        ((Button)findViewById(R.id.btn_restablecer_pass)).setTypeface(montR);


        btn_verificar_correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                verificarCorreo();
            }
        });
        btn_verificar_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                verificarCodigo();
            }
        });
        btn_restablecer_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                if (txt_nuevo_pass.getText().length()==0||!txt_nuevo_pass.getText().toString().equals(txt_confirmar_pass.getText().toString())){
                    Toast.makeText(ResetPasswordActivity.this,"Los datos proporcionados no coinciden", Toast.LENGTH_LONG).show();
                }else {
                    nuevoPass();
                    loginNuevoPass();
                }
            }
        });
    }
}
