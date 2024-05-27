package com.example.antojitos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class regis extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    EditText view;

    // Obtener referencia al EditText
    private EditText etFecha;

    // Guardar el último año, mes y día del mes
    private int ultimoAnio, ultimoMes, ultimoDiaDelMes;

    /////////////////////////////////////////variables de los edittext///////////////////////////////////////////////////
    protected  EditText a,b,c,d,e,f,g,h;
    String aa,bb,cc,dd,ee,ff,gg,hh,valid;

    ////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        // Instanciar objetos
        etFecha = findViewById(R.id.fewcha);

        // Poner último año, mes y día a la fecha de hoy
        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);

        /////////////////////////////////////////variables de los edittext///////////////////////////////////////////////////
        a = (EditText) findViewById(R.id.fewcha);
        b = (EditText) findViewById(R.id.nom);
        c = (EditText) findViewById(R.id.app);
        d = (EditText) findViewById(R.id.apm);
        e = (EditText) findViewById(R.id.email);
        f = (EditText) findViewById(R.id.cantid);
        g = (EditText) findViewById(R.id.password1);
        h = (EditText) findViewById(R.id.password2);

        ////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////// ESTO RECOLECTA LA INFORMACION DETODO EL FORMULARIO /////////////////////////////////////////////////////////////
        Button btn = (Button) findViewById(R.id.saveingred);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iniciamos una ventana de ejecucion

                aa = etFecha.getText().toString().trim();
                bb = b.getText().toString().trim();
                cc = c.getText().toString().trim();
                dd = d.getText().toString().trim();
                ee = e.getText().toString().trim();
                ff = f.getText().toString().trim();
                gg = g.getText().toString().trim();
                hh = h.getText().toString().trim();

                if( !aa.isEmpty() && !bb.isEmpty() && !cc.isEmpty() && !dd.isEmpty() && !ee.isEmpty() && !ff.isEmpty() && !gg.isEmpty() && !hh.isEmpty()  ){
                    if(gg.equals(hh)){
                        Redirectdata("https://antojitoslolis.000webhostapp.com/user_regis.php");
                        //Toast.makeText(regis.this,"Todos los campos son Obligatorios" +aa +hh + dd, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(regis.this,"Las contraseñas no coinciden" + gg + " " + hh, Toast.LENGTH_LONG).show();

                    }

                }else{
                    Toast.makeText(regis.this,"Todos los campos son Obligatorios", Toast.LENGTH_LONG).show();


                }
            }
        });
        /////////////////////////////// ESTO RECOLECTA LA INFORMACION DETODO EL FORMULARIO /////////////////////////////////////////////////////////////




        // Refrescar la fecha en el EditText
        //refrescarFechaEnEditText();

        // Hacer que el datepicker se muestre cuando toquen el EditText; recuerda
        // que se podría invocar en el click de cualquier otro botón, o en cualquier
        // otro evento
        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí es cuando dan click así que mostramos el DatePicker

                // Le pasamos lo que haya en las globales
                DatePickerDialog dialogoFecha = new DatePickerDialog(regis.this, listenerDeDatePicker, ultimoAnio, ultimoMes, ultimoDiaDelMes);
                //Mostrar
                dialogoFecha.show();
            }
        });

        //loginButton.setReadPermissions("email");

      /*  callbackManager = CallbackManager.Factory.create();
/*
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Error de logeo",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),R.string.error_login ,Toast.LENGTH_LONG).show();

            }
        });*/
    }

    private void Redirectdata(String url){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if( response.equalsIgnoreCase("10")){
                    Toast.makeText(regis.this,"Registro Guardado", Toast.LENGTH_LONG).show();
                    Logred();




                }else {

                    Toast.makeText(regis.this,"usuario incorrecto", Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(regis.this,error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros_avg = new HashMap<String, String>();

                parametros_avg.put("fec",a.getText().toString());
                parametros_avg.put("nmm",b.getText().toString());
                parametros_avg.put("apl",c.getText().toString());
                parametros_avg.put("apt",d.getText().toString());
                parametros_avg.put("coe",e.getText().toString());
                parametros_avg.put("ono",f.getText().toString());
                parametros_avg.put("paw",g.getText().toString());

                return parametros_avg;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    private void Logred(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



    // Crear un listener del datepicker;
    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int anio, int mes, int diaDelMes) {
            // Esto se llama cuando seleccionan una fecha. Nos pasa la vista, pero más importante, nos pasa:
            // El año, el mes y el día del mes. Es lo que necesitamos para saber la fecha completa


            // Refrescamos las globales
            ultimoAnio = anio;
            ultimoMes = mes;
            ultimoDiaDelMes = diaDelMes;

            // Y refrescamos la fecha
            refrescarFechaEnEditText();

        }
    };

    public void refrescarFechaEnEditText() {
        // Formateamos la fecha pero podríamos hacer cualquier otra cosa ;)
        String fecha = String.format(Locale.getDefault(), "%02d-%02d-%02d", ultimoAnio, ultimoMes+1, ultimoDiaDelMes);

        // La ponemos en el editText
        etFecha.setText(fecha);
    }

/*
    private void goMainScreen(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult( requestCode, resultCode, data);
    }
*/
    public void continiusReg(View v){
        Intent nuevox = new Intent(this,Regis2.class);
        startActivity(nuevox);

    }
}