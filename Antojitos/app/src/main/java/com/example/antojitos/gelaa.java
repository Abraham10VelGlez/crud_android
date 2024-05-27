package com.example.antojitos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class gelaa extends AppCompatActivity {
    private Spinner spinner_data;
    private EditText editTextc;
    TextView a,b;

    String vl,selecion;
    int variable = 0,cantidad = 0,tipodeguisado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelaa);
        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("Gelatinas");
        }
        //para ocultar titulo de la parte superior


        spinner_data = (Spinner) findViewById(R.id.spinnerg);
        editTextc = (EditText) findViewById(R.id.cantid);

        //a = (TextView) findViewById(R.id.textView5);
        //b = (TextView) findViewById(R.id.textView7);


        //declaramos el array

        String [] arraydatag = {"SELECCIONA UN SABOR","FLAN","QUESO","CHOCOLATE","ROMPOPE","MOSAICO","OTRA"};

        //ArrayAdapter<String> objetoadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraydatag );

        ArrayAdapter<String> objetoadapter = new ArrayAdapter<String>(this, R.layout.layouttotamaniospinnger, arraydatag );
        spinner_data.setAdapter(objetoadapter);


        Button btn1 = (Button) findViewById(R.id.saveingred);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vl = editTextc.getText().toString().trim();
                selecion = spinner_data.getSelectedItem().toString();
        /*
        if(selecion.equals("PAPAS CON CHORIZO")){
            Toast.makeText(kekap.this,"es 1", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(kekap.this,"no es 1", Toast.LENGTH_LONG).show();

        }

         */
                variable = 0;

                switch ( selecion ){
                    case "FLAN":
                        variable = 31;
                        //Toast.makeText(this,"opcion 1",Toast.LENGTH_LONG).show();

                        break;
                    case "QUESO":
                        variable = 32;
                        break;
                    case "CHOCOLATE":
                        variable = 33;
                        break;
                    case "ROMPOPE":
                        variable = 34;
                        break;
                    case "MOSAICO":
                        variable = 35;
                        break;

                    case "OTRA":
                        variable = 36;


                        break;
                    case "PICADILLO":
                        variable = 5;
                        break;
                    case "SESOS":
                        variable = 6;
                        break;

                    case "CHAMPIÑONES":
                        variable = 7;


                        break;
                    case "SELECCIONA UN SABOR":
                        variable = 0;

                        break;

                    default:
                        Toast.makeText(gelaa.this,"error",Toast.LENGTH_LONG).show();

                        break;


                }


                tipodeguisado = Integer.parseInt(String.valueOf(variable));


                if(   vl.isEmpty()  || tipodeguisado == 0){

                    //Toast.makeText(this,"faltan campos" + cantidad,Toast.LENGTH_LONG).show();
                    Toast.makeText(gelaa.this,"faltan campos",Toast.LENGTH_SHORT).show();

                }else{
                    cantidad = Integer.parseInt(vl) ;

                    //antes e guardaban estos parametro en un sharepreference pero ahora van para una base de datos
                    //Preferens_SAve_solds();

                    final BDA basedatoantoji = new BDA(getApplicationContext());
                    int meni = 5 ; // menu 5 GELATINA





                    // activamos las variables guardadas con anticipacion al  este metodo es para devolverlas solo para pruebas

                    // preferencias datavg usuarios registrados en el sistema. Antojitoss lolis
                    // preferencias  datavgfacebook
                    // preferencias  datavgGooGle
                    /*
                     * LAS PREFERENCIAS SE UNIFICAN EN UN SOLO OBJETO CON 5 CATEGORIAS
                     * SharedPreferences preferences = getSharedPreferences("datavg", Context.MODE_PRIVATE);
                     * editor.putInt("clv",);   1 propios del sistema   2 facebook       3 google
                     * editor.putString("usernom",);
                     * editor.putString("useremail",);
                     * editor.putString("pasword",
                     * editor.putBoolean("sesdsion",TRUE);
                     * */


                    SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
                    String aa;
                    String bb;
                    String clvv;
                    clvv = preferences.getString("clv", "");
                    aa = preferences.getString("useremail","");
                    bb = preferences.getString("pasword","");

                    //Toast.makeText(kekap.this,"tipo de usuario: " + clvv + "correo: " + aa + " contraseña: " + bb,Toast.LENGTH_LONG).show();


                    //agrega un registro de un pedido de kekas

                    basedatoantoji.agregarregis2(clvv,meni,0, variable,0, cantidad,"20.00",1);



                    //mensaje de pedido

                    Toast.makeText(gelaa.this,"Pedido agregado guisado:" + variable + " cantidad:" + cantidad,Toast.LENGTH_LONG).show();

                    //Redirectsavecdata("https://antojitoslolis.000webhostapp.com/user_ped1.php");



                    Retornoviu();


                }


            }
        });

    }

    private void Retornoviu(){
        Intent intent = new Intent(this,activitylogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}