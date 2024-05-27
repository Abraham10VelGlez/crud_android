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

public class mixx extends AppCompatActivity {
    private Spinner spinner_data,spinner_data2,spinner_data3;
    private EditText editTextc;
    TextView a,b;

    String vl,selecion,selecion2,selecion1;
    int variable = 0, variable11 = 0,variable22 = 0,cantidad = 0,tipodeguisado = 0,tipodeguisado2 = 0,tipodeguisado11 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixx);
        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle(" Que vas a ordenar ");
        }
        //para ocultar titulo de la parte superior


        spinner_data = (Spinner) findViewById(R.id.spinnerg);
        spinner_data2 = (Spinner) findViewById(R.id.spinnerg2);
        spinner_data3 = (Spinner) findViewById(R.id.spinnerg3);

        editTextc = (EditText) findViewById(R.id.cantid);

        //a = (TextView) findViewById(R.id.textView5);
        //b = (TextView) findViewById(R.id.textView7);


        //declaramos el array1

        String [] arraydatag = {"SELECCIONA UN ANTOJITO",
                "DOBLADITA",
                "TOSTADA",
                "PAMBAZO",
                "SOPE"};
        // ES UN SPINNER SIN COLOR ES UN SPINNER SIMPLE
        //ArrayAdapter<String> objetoadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraydatag );

        // ES UN SPINNER CON COLOR Y ATRIBUTOS PARA MEJOR VISTA
        ArrayAdapter<String> objetoadapter = new ArrayAdapter<String>(this, R.layout.layouttotamaniospinnger, arraydatag );
        spinner_data.setAdapter(objetoadapter);

        //declaramos el array2

        String [] arraydatag2 = {"SELECCIONA UN GUISADO",
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
        //ArrayAdapter<String> objetoadapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraydatag2 );
        ArrayAdapter<String> objetoadapter2 = new ArrayAdapter<String>(this,  R.layout.layouttotamaniospinnger , arraydatag2 );
        spinner_data2.setAdapter(objetoadapter2);


        String [] arraydatag3 = {"SELECCIONA UN GUISADO",
                "QUESO",
                "MOLE VERDE",
                "CARNE",
                "PICADILLO",
                "SESOS",
                "CHAMPIÑONES",
                "POLLO",
                "PAPAS CON CHORIZO",
                "PATA",
                "TINGA DE POLLO",
                "TINGA DE RES"};
        //ArrayAdapter<String> objetoadapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraydatag3 );
        ArrayAdapter<String> objetoadapter3 = new ArrayAdapter<String>(this,  R.layout.layouttotamaniospinnger , arraydatag3 );
        spinner_data3.setAdapter(objetoadapter3);


        Button btn1 = (Button) findViewById(R.id.saveingred);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vl = editTextc.getText().toString().trim();
                selecion1 = spinner_data.getSelectedItem().toString();
                selecion = spinner_data2.getSelectedItem().toString();
                selecion2 = spinner_data3.getSelectedItem().toString();

                variable11 = 0;

                switch ( selecion1 ){
                    case "DOBLADITA":
                        variable11 = 1;
                        break;
                    case "TOSTADA":
                        variable11 = 2;
                        break;
                    case "PAMBAZO":
                        variable11 = 3;
                        break;

                    case "SOPE":
                        variable11 = 4;

                        break;

                    case "SELECCIONA UN ANTOJITO":
                        variable11 = 0;

                        break;
                    default:
                        Toast.makeText(mixx.this,"error1",Toast.LENGTH_LONG).show();

                        break;


                }

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
                        Toast.makeText(mixx.this,"error2",Toast.LENGTH_LONG).show();

                        break;


                }

                variable22 = 0;

                switch ( selecion2 ){
                    case "QUESO":
                        variable22 = 6;
                        break;
                    case "MOLE VERDE":
                        variable22 = 1;
                        break;
                    case "CARNE":
                        variable22 = 2;
                        break;
                    case "PICADILLO":
                        variable22 = 3;
                        break;

                    case "SESOS":
                        variable22 = 4;

                        break;
                    case "CHAMPIÑONES":
                        variable22 = 5;
                        break;

                    case "POLLO":
                        variable22 = 7;

                        break;
                    case "PAPAS CON CHORIZO":
                        variable22 = 8;

                        break;
                    case "PATA":
                        variable22 = 9;
                        break;

                    case "TINGA DE POLLO":
                        variable22 = 10;

                        break;
                    case "TINGA DE RES":
                        variable22 = 11;

                        break;
                    case "SELECCIONA UN GUISADO":
                        variable22 = 0;

                        break;
                    default:
                        Toast.makeText(mixx.this,"error3",Toast.LENGTH_LONG).show();

                        break;


                }

                // menu tipo de antojito
                tipodeguisado11 = Integer.parseInt(String.valueOf(variable11));
                // guiso uno
                tipodeguisado = Integer.parseInt(String.valueOf(variable));
                // guiso dos
                tipodeguisado2 = Integer.parseInt(String.valueOf(variable22));


                // esto funciono cuando eran mas menus para ordenar, pero con la actualizacion de la app los datos se ven de manera mas simples y
                // obligan a acortar los menus y este sera el menu principal, y puede ir de un guiso o de 2
                //if(   vl.isEmpty()  || tipodeguisado11 == 0 || tipodeguisado == 0 || tipodeguisado2 == 0){

                if(   vl.isEmpty()  || tipodeguisado11 == 0 || tipodeguisado == 0){

                    //Toast.makeText(this,"faltan campos" + cantidad,Toast.LENGTH_LONG).show();
                    Toast.makeText(mixx.this,"faltan campos",Toast.LENGTH_SHORT).show();

                }else{
                    cantidad = Integer.parseInt(vl) ;

                    //antes e guardaban estos parametro en un sharepreference pero ahora van para una base de datos
                    //Preferens_SAve_solds();

                    final BDA basedatoantoji = new BDA(getApplicationContext());






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


                    //agrega un registro de un pedido de kekas determinando si es de un guiso o de 2 guisos
                    if( tipodeguisado != 0 && tipodeguisado2 == 0) {
                        int meni = 6 ; // menu 6 normales
                        Toast.makeText(mixx.this,"keka normal",Toast.LENGTH_LONG).show();
                        basedatoantoji.agregarregis2(clvv,meni,variable11, variable,variable22, cantidad,"25.00",1);

                    }else if(  tipodeguisado != 0 || tipodeguisado2 != 0){
                        int meni = 7 ; // menu 6 combinadas
                        Toast.makeText(mixx.this,"keka con combinadas " + tipodeguisado,Toast.LENGTH_LONG).show();

                        basedatoantoji.agregarregis2(clvv,meni,variable11, variable,variable22, cantidad,"28.00",1);

                    }else {
                        Toast.makeText(mixx.this,"error de registro",Toast.LENGTH_LONG).show();

                    }





                    //mensaje de pedido

                    //Toast.makeText(mixx.this,"Pedido agregado guisado:" + variable + " cantidad:" + cantidad,Toast.LENGTH_LONG).show();
                    Toast.makeText(mixx.this,"PEDIDO AGREGADO",Toast.LENGTH_LONG).show();

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