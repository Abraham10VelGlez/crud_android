package com.example.antojitos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class pagefec extends AppCompatActivity {
    BDA db;
    TableLayout tableLayout;
    TextView f,g,h;
    ArrayList<String> sumadorcantidades;
    Button eventoefectivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagefec);
        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("CONFIRMA TU COMPRA");
        }
        //para ocultar titulo de la parte superior
        //OBJETO PARA LA BASE DE DATOS SQLITE
        db = new BDA(pagefec.this);
        //OBJETO PARA LA BASE DE DATOS SQLITE

        // CARGA DE LA TABLA DE ORDEN FINAL
        tableLayout = (TableLayout) findViewById(R.id.table);
        sumadorcantidades = new ArrayList<>();
        tablee();// CARGA DE LA TABLA DE ORDEN FINAL
        // CARGA DE LA TABLA DE ORDEN FINAL

        eventoefectivo = (Button) findViewById(R.id.btnevent);
        eventoefectivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodo_para_enviar_pedir_BASEDATOSCENTRAL();
            }
        });

    }

    // metodo para evniar pedido final a la base de datos central de ANTOJITOS LOLIS
    private void metodo_para_enviar_pedir_BASEDATOSCENTRAL(){

        // crear un objeto
        JSONObject dataas = new JSONObject();
        // crear un aarreglo de objetos
        JSONArray jsonArray = new JSONArray();
        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        String aa;
        String bb;
        //int clvv;
        String clvv;
        //clvv = preferences.getInt("clv", -1);
        clvv = preferences.getString("clv", "");
        aa = preferences.getString("useremail","");
        bb = preferences.getString("pasword","");
        try{

            Cursor cursor = db.readallpedidosendd(clvv);
            if( cursor.getCount() == 0){

                Toast.makeText(pagefec.this,"no hay datos", Toast.LENGTH_LONG).show();

            }else {
                int a = 0;




                while (cursor.moveToNext()){

                    //dataas.put("id"+a, );
                    dataas.put("avg", a );
                    dataas.put("m"+a,cursor.getString(0) );
                    dataas.put("g"+a,cursor.getString(1) );
                    dataas.put("g2"+a,cursor.getString(2) );
                    dataas.put("c"+a,cursor.getString(3) );
                    dataas.put("p"+a,cursor.getString(4) );
                    dataas.put("etts"+a,cursor.getString(5) );
                    //validamos el tipop de usuario para recabar informacion para su pedido...
                    dataas.put("uss"+a,cursor.getString(6) );
                    if( cursor.getString(6).equals("1")   ){
                        dataas.put("id"+a,aa );

                    }else if( cursor.getString(6).equals("2") ){
                        dataas.put("id"+a,bb);

                    }else if( cursor.getString(6).equals("3") ){
                        dataas.put("id"+a,bb );

                    }else{
                        dataas.put("id"+a, cursor.getString(6) );
                    }

                    db.updata_tbl_tmp_ord_estatus(cursor.getString(7).toString().trim());

                            /*
                            id_menu.add(cursor.getString(0));
                            id_guisado.add(cursor.getString(1));
                            cantidad.add(cursor.getString(2));
                            precio.add(cursor.getString(3));
                            id_estatus.add(cursor.getString(4));
                            id_user.add(cursor.getString(5));
                            */

                    a++;
                    //constants



                }

            }


            //Toast.makeText(pagefec.this,dataas.toString(), Toast.LENGTH_LONG).show();

            //esta forma tambien sirve de enviar datos 1
//                    renderdatafinish_antojitos("https://antojitoslolis.000webhostapp.com/pedydoorg.php?us=" + dataas);
            //esta forma tambien sirve de enviar datos 2
            renderdatafinish_antojitos22("https://antojitoslolis.000webhostapp.com/pedydoorg.php" ,dataas);
            Retornoviu();
        }catch ( JSONException i){
            i.printStackTrace();

        }

    }



    // tabla final del pedido
    private void  tablee(){
        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        String clvv;
        clvv = preferences.getString("clv", "");
        Cursor cursor = db.Countefect(clvv);
        if( cursor.getCount() == 0){
            //Toast.makeText(pagefec.this,"no hay datos",Toast.LENGTH_SHORT).show();

        }else {
            //id_menu,  id_guisado,id_guisado2,cantidad, precio,  id_estatus
            //titulo de la tabla
            String [] titulo = {"MENU","GUISADO","PIEZAS","$ SUBTOTAL"};
            TableRow row1 = new TableRow(getBaseContext());
            TextView textView2;
            for (int v=0 ; v < titulo.length ; v ++) {
                textView2 = new TextView(getBaseContext());
                textView2.setGravity(Gravity.CENTER_HORIZONTAL);
                textView2.setPadding(20, 20, 20, 20);
                textView2.setBackgroundResource(R.color.design_default_color_surface);
                textView2.setTextColor(Color.BLACK);
                textView2.setText(titulo[v]);
                textView2.setTextSize(15);
                row1.addView(textView2);
            }
            tableLayout.addView(row1);
            //titulo de la tabla
            while (cursor.moveToNext()){
                //iniciamos un vector
                //"Menu","Guisado","Piezas","Subtutal"
                // id_menu,  id_guisado,id_guisado2,cantidad, precio,  id_estatus
                String naa = "";
                if(cursor.getString(2).equals("na")){
                    naa = cursor.getString(1);
                }else {
                    naa =cursor.getString(1) + "/\n" + cursor.getString(2);

                }
                //String []contador = {cursor.getString(4)};
                sumadorcantidades.add(cursor.getString(4));




                String []  vector = {cursor.getString(0),naa, cursor.getString(3), cursor.getString(4)};


                TableRow row2 = new TableRow(getBaseContext());
                TextView textView;

                for (int a=0 ; a < vector.length ; a ++){
                    textView = new TextView(getBaseContext());


                    // datos
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setPadding(20,20,20,20);
                    textView.setBackgroundResource(R.color.design_default_color_surface);
                    textView.setText( vector[a] );
                    textView.setTextColor(Color.BLACK);
                    textView.setTextSize(14);

                    row2.addView(textView);




                }

                tableLayout.addView(row2);

            }

            /// suma de total de los antojitos pedidos.
             Integer hh = 0;
            for (int e = 0; e <sumadorcantidades.size(); e++){/// suma de total de los antojitos pedidos.
                /// suma de total de los antojitos pedidos.
                 hh += Integer.parseInt(sumadorcantidades.get(e));
            }/// suma de total de los antojitos pedidos.
            //Toast.makeText(pagefec.this,"datos"+ hh ,Toast.LENGTH_SHORT).show();
            /// suma de total de los antojitos pedidos.
            //total de la orden

            String [] division = {"           ","           ","           ","           "};
            TableRow row4 = new TableRow(getBaseContext());
            TextView textView4;
            for (int b=0 ; b < division.length ; b ++) {
                textView4 = new TextView(getBaseContext());
                textView4.setGravity(Gravity.CENTER_HORIZONTAL);
                textView4.setPadding(20, 20, 20, 20);
                textView4.setBackgroundResource(R.color.design_default_color_surface);
                textView4.setTextColor(Color.BLACK);
                textView4.setText(division[b]);
                textView4.setTextSize(14);
                row4.addView(textView4);
            }
            tableLayout.addView(row4);
            //total de la orden
            //total de la orden
            String [] Total = {"TOTAL $"," "," ", String.valueOf(hh)};
            TableRow row3 = new TableRow(getBaseContext());
            TextView textView3;
            for (int b=0 ; b < Total.length ; b ++) {
                textView3 = new TextView(getBaseContext());
                textView3.setGravity(Gravity.CENTER_HORIZONTAL);
                textView3.setPadding(20, 20, 20, 20);
                textView3.setBackgroundResource(R.color.design_default_color_surface);
                textView3.setTextColor(Color.BLACK);
                textView3.setText(Total[b]);
                textView3.setTextSize(14);
                row3.addView(textView3);
            }
            tableLayout.addView(row3);
            //total de la orden



            //Toast.makeText(pagefec.this,"datos"+ vector ,Toast.LENGTH_SHORT).show();



           //se pueden ver datos
        }
    }

    // metodo para regresar a la vista de sabores de antojitos
    private void Retornoviu(){
        Intent intent = new Intent(this,activitylogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void renderdatafinish_antojitos22(String URLAVG, JSONObject kk){


        new Thread(new Runnable() {

            @Override
            public void run() {
                OutputStream os = null;
                InputStream is = null;
                HttpURLConnection conn = null;
                try {
                    //constants
                    URL url = new URL(URLAVG);

                    String message = kk.toString();

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout( 10000 /*milliseconds*/ );
                    conn.setConnectTimeout( 15000 /* milliseconds */ );
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setFixedLengthStreamingMode(message.getBytes().length);

                    //make some HTTP header nicety
                    conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

                    //open
                    conn.connect();

                    //setup send
                    os = new BufferedOutputStream(conn.getOutputStream());
                    os.write(message.getBytes());
                    //clean up
                    os.flush();

                    //do somehting with response
                    is = conn.getInputStream();
                    //String contentAsString = readIt(is,len);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    //clean up
                    try {
                        os.close();
                        is.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    conn.disconnect();
                }
            }
        }).start();
        Toast.makeText(pagefec.this,"PEDIDO ENVIADO", Toast.LENGTH_LONG).show();






    }
}
