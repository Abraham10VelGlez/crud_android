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

public class pambz extends AppCompatActivity {
    private Spinner spinner_data;
    private EditText editTextc;
    TextView a,b;

    String vl,selecion;
    int variable = 0,cantidad = 0,tipodeguisado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pambz);

        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("Pambazos");
        }
        //para ocultar titulo de la parte superior

        spinner_data = (Spinner) findViewById(R.id.spinnerg);
        editTextc = (EditText) findViewById(R.id.cantid);

        //a = (TextView) findViewById(R.id.textView5);
        //b = (TextView) findViewById(R.id.textView7);


        //declaramos el array

        String [] arraydatag = {"SELECCIONA UN GUISADO",
                "MOLE VERDE",
                "CARNE",
                "PICADILLO",
                "SESOS",
                "CHAMPIÑONES",
                "QUESO",
                "POLLO",
                "PAPAS CON CHORIZO",
                "PATA",
                "TINGA DE POLLO",
                "TINGA DE RES"};
        ArrayAdapter<String> objetoadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraydatag );
        spinner_data.setAdapter(objetoadapter);


        Button btn1 = (Button) findViewById(R.id.saveingred);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vl = editTextc.getText().toString().trim();
                selecion = spinner_data.getSelectedItem().toString();

                variable = 0;

                switch ( selecion ){
                    case "MOLE VERDE":
                        variable = 1;
                        break;
                    case "CARNE":
                        variable = 2;
                        break;
                    case "PICADILLO":
                        variable = 3;
                        break;

                    case "SESOS":
                        variable = 4;

                        break;
                    case "CHAMPIÑONES":
                        variable = 5;
                        break;
                    case "QUESO":
                        variable = 6;
                        break;

                    case "POLLO":
                        variable = 7;

                        break;
                    case "PAPAS CON CHORIZO":
                        variable = 8;

                        break;
                    case "PATA":
                        variable = 9;
                        break;

                    case "TINGA DE POLLO":
                        variable = 10;

                        break;
                    case "TINGA DE RES":
                        variable = 11;

                        break;
                    case "SELECCIONA UN GUISADO":
                        variable = 0;

                        break;

                    default:
                        Toast.makeText(pambz.this,"error",Toast.LENGTH_LONG).show();

                        break;


                }


                tipodeguisado = Integer.parseInt(String.valueOf(variable));


                if(   vl.isEmpty()  || tipodeguisado == 0){

                    //Toast.makeText(this,"faltan campos" + cantidad,Toast.LENGTH_LONG).show();
                    Toast.makeText(pambz.this,"faltan campos",Toast.LENGTH_SHORT).show();

                }else{
                    cantidad = Integer.parseInt(vl) ;

                    //antes e guardaban estos parametro en un sharepreference pero ahora van para una base de datos
                    //Preferens_SAve_solds();

                    final BDA basedatoantoji = new BDA(getApplicationContext());
                    int meni = 3 ; // menu 3 PAMBAZO





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
                    //int clvv;
                    //clvv = preferences.getString("clv", -1);
                    clvv = preferences.getString("clv", "");
                    aa = preferences.getString("useremail","");
                    bb = preferences.getString("pasword","");

                    //Toast.makeText(kekap.this,"tipo de usuario: " + clvv + "correo: " + aa + " contraseña: " + bb,Toast.LENGTH_LONG).show();


                    //agrega un registro de un pedido de kekas

                    basedatoantoji.agregarregis(clvv,meni, variable,0, cantidad,"25.00",1);



                    //mensaje de pedido

                    Toast.makeText(pambz.this,"Pedido agregado guisado:" + variable + " cantidad:" + cantidad,Toast.LENGTH_LONG).show();

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