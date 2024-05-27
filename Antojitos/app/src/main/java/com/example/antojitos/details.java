package com.example.antojitos;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class details extends AppCompatActivity {

    TableLayout tableLayout;
    ArrayList<String> sumadorcantidades;
    TextView titulodeturno,ahcaf,total;
    // variable para la extraer datos de mysql del web service de la base de datos online en hostinger.mx
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("Datos Generales");
        }
        //para ocultar titulo de la parte superior

        titulodeturno = (TextView) findViewById(R.id.textView13);
        ahcaf = (TextView) findViewById(R.id.textView14);
        total = (TextView) findViewById(R.id.textView17);
        // CARGA DE LA TABLA DE ORDEN FINAL POR DETALLES
        tableLayout = (TableLayout) findViewById(R.id.tablad);

        Tabladetalles_bdAntojitos();
    }

    void Tabladetalles_bdAntojitos(){

        /*
        * Intent intent = new Intent(context, details.class);
                intent.putExtra("folio",String.valueOf(id_menu.get(position)));
                intent.putExtra("fecha",String.valueOf(cantidad.get(position)));
                intent.putExtra("precio",String.valueOf(id_estatus.get(position)));

                intent.putExtra("estatus",String.valueOf(id_guisado.get(position)));
                intent.putExtra("cantidad",String.valueOf(precio.get(position)));
                intent.putExtra("precio",String.valueOf(id_estatus.get(position)));
        * */

        titulodeturno.setText("Numero de Turno de Pedido " + getIntent().getStringExtra("folio"));
        ahcaf.setText("Ingreso del pedido " + getIntent().getStringExtra("fecha"));



       // private void  tablee(){
            //Cursor cursor = db.Countefect();
            //if( cursor.getCount() == 0){
                //Toast.makeText(pagefec.this,"no hay datos",Toast.LENGTH_SHORT).show();

            //}else {
                //id_menu,  id_guisado,id_guisado2,cantidad, precio,  id_estatus
                //titulo de la tabla
                String [] titulo = {"MENU","GUISADO","CANTIDAD","SUBTOTAL"};
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
               // while (cursor.moveToNext()){
        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        String clvv;
        clvv = preferences.getString("clv", "");

        String URL = "https://antojitoslolis.000webhostapp.com/gsonp_details.php?x_token=" + getIntent().getStringExtra("folio") + "&vlc=" + clvv;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
        for (int y = 0; y < response.length(); y++) {
                    try {
                        jsonObject = response.getJSONObject(y);
                        /*
                        id_menu.add(jsonObject.getString("folio_ord"));
                        id_guisado.add(jsonObject.getString("id_estatus"));
                        cantidad.add(jsonObject.getString("fechax"));
                        precio.add(jsonObject.getString("cantidadx"));
                        id_estatus.add(jsonObject.getString("preciovv"));
                         */

                        String naa = "";
                        if(jsonObject.getString("id_guisado2").equals("na")){
                            naa = jsonObject.getString("id_guisado");
                        }else {
                            naa = jsonObject.getString("id_guisado") + "/\n" + jsonObject.getString("id_guisado2");

                        }

                        //SELECT id_user, id_menu, id_guiso, id_guiso2, cantidad, precio, id_estatus, idtipouser, fechax, folio_ord FROM `entradas` where folio_ord = 2 order by folio_ord
                        //String [] vectordetalles = {jsonObject.getString("id_menu"),jsonObject.getString("id_guisado")  + "/" +  jsonObject.getString("id_guisado2"),jsonObject.getString("cantidad"),jsonObject.getString("precio")};
                        String [] vectordetalles = {jsonObject.getString("id_menu"),naa,jsonObject.getString("cantidad"),jsonObject.getString("precio")};

                        //String []  vector = {cursor.getString(0),naa, cursor.getString(3), cursor.getString(4)};
                        String [] vector = {"MENU","GUISADO","CANTIDAD","SUBTOTAL"};

                        TableRow row2 = new TableRow(getBaseContext());
                        TextView textView;

                        for (int a=0 ; a < vectordetalles.length ; a ++){
                            textView = new TextView(getBaseContext());


                            // datos
                            textView.setGravity(Gravity.CENTER_HORIZONTAL);
                            textView.setPadding(20,20,20,20);
                            textView.setBackgroundResource(R.color.design_default_color_surface);
                            textView.setText( vectordetalles[a] );
                            textView.setTextColor(Color.BLACK);
                            textView.setTextSize(13);

                            row2.addView(textView);




                        }

                        tableLayout.addView(row2);
                        //Toast.makeText(details.this,vectordetalles.toString(), Toast.LENGTH_LONG).show();



                    } catch (JSONException x) {
                        Toast.makeText(getApplicationContext(), x.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }

         /// suma de total de los antojitos pedidos.
        total.setText("Total a pagar: $" + getIntent().getStringExtra("precio"));
        /// suma de total de los antojitos pedidos.

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
                    textView4.setTextSize(13);
                    row4.addView(textView4);
                }
                tableLayout.addView(row4);
                //total de la orden
                //total de la orden
                String [] Total = {"TOTAL"," "," ","$" + String.valueOf(getIntent().getStringExtra("precio"))};
                TableRow row3 = new TableRow(getBaseContext());
                TextView textView3;
                for (int b=0 ; b < Total.length ; b ++) {
                    textView3 = new TextView(getBaseContext());
                    textView3.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView3.setPadding(20, 20, 20, 20);
                    textView3.setBackgroundResource(R.color.design_default_color_surface);
                    textView3.setTextColor(Color.BLACK);
                    textView3.setText(Total[b]);
                    textView3.setTextSize(13);
                    row3.addView(textView3);
                }
                tableLayout.addView(row3);
                //total de la orden

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error de conexión", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);





                    //iniciamos un vector
                    //"Menu","Guisado","Piezas","Subtutal"
                    // id_menu,  id_guisado,id_guisado2,cantidad, precio,  id_estatus

                    //String []contador = {cursor.getString(4)};
                    //sumadorcantidades.add(cursor.getString(4));

               // }





                //Toast.makeText(pagefec.this,"datos"+ vector ,Toast.LENGTH_SHORT).show();



                //se pueden ver datos
            //}
        //}
    }
}