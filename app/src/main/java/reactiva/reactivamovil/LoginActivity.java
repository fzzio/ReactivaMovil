package reactiva.reactivamovil;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    EditText txt_email;
    EditText txt_password;
    TextView forgot_pass;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final Typeface montR= Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgot_pass = (TextView)findViewById(R.id.lbl_forgot_pass);
        txt_email = (EditText) findViewById(R.id.email);
        txt_password = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.email_sign_in_button);

        //formato a elementos
        //labels
        forgot_pass.setTypeface(montR);
        ((TextView)findViewById(R.id.lbl_user)).setTypeface(montR);
        ((TextView)findViewById(R.id.lbl_pass)).setTypeface(montR);
        //textedits
        txt_email.setTypeface(montR);
        txt_password.setTypeface(montR);
        //boton
        btn_login.setTypeface(montR);

        txt_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarLogin();
            }
        });
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(i);
            }
        });



    }
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
    public void verificarLogin(){
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        //String url ="http://192.168.0.5:8081/ws/login.php";
        String url ="http://107.170.105.224:6522/ReactivaWeb/index.php/services/checklogin";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Response: ",response);
                        try {
                            JSONObject g = new JSONObject(response);
                            if(g.get("event").toString().equals("1")){
                                Intent i = new Intent(LoginActivity.this,VerPerfilActivity.class);
                                startActivity(i);
                            }else if(g.get("data").toString().equals("0")) {
                                Toast.makeText(LoginActivity.this,"Usuario y/o Constraseña erróneos",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(LoginActivity.this,"Error FATAL",Toast.LENGTH_LONG).show();
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
                Toast.makeText(LoginActivity.this,error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("user",txt_email.getText().toString().trim());
                String aConvertir=md5(txt_password.getText().toString().trim());
                if (!aConvertir.isEmpty()){
                    params.put("pass",aConvertir);
                }
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
