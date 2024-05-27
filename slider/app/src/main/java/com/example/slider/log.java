package com.example.slider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class log extends AppCompatActivity {
    EditText o,o1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);


       // o.findViewById(R.id.ii);
       // o1.findViewById(R.id.editTextTextPassword);

    }

    public void login(View view){

        String url = "https://antojitoslolis.000webhostapp.com/user_login.php";



        if( o.getText().toString().equals("") ){
            Toast.makeText(this, "ingresa un correo", Toast.LENGTH_LONG).show();


        }else if( o1.getText().toString().equals("") ){
            Toast.makeText(this, "ingresa una contraseña", Toast.LENGTH_LONG).show();


        }else{

            Toast.makeText(this, "redirecciona", Toast.LENGTH_LONG).show();

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Espere, Por favor");

            progressDialog.show();
/*
            emailu.getText().toString().trim();
            passw.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   // progressDialog.dismiss();
                    if (response.equalsIgnoreCase("10")) {
                        emailu.setText("");
                        passw.setText("");
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(log.this, response, Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(log.this, response, Toast.LENGTH_LONG).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                   // progressDialog.dismiss();
                    Toast.makeText(log.this, error.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametrosavg = new HashMap<String, String>();
                    parametrosavg.put("uu",emailu.getText().toString());
                    parametrosavg.put("dd",passw.getText().toString());
                    return parametrosavg;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(log.this);
            requestQueue.add(request);
            */


        }

    }
}