package com.example.antojitos;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class pedidosadd extends AppCompatActivity {
    RecyclerView recyclerView;

    // RecyclerViewAdaptor recyclerViewAdaptor;

    BDA db;
    //ArrayList<String> id_user,id_menu,id_guisado,cantidad,precio,id_estatus;
    ArrayList<String> id_menu,id_guisado,id_guisado2,cantidad,precio,id_estatus;


    CustomadapterPedidos customadapterPedidos;
    ImageView imagendato;
    TextView gg;
    Button okk,droop;

    // variable para la extraer datos de mysql del web service de la base de datos online en hostinger.mx
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidosadd);

        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("PEDIDOS EN CURSO");
        }
        //para ocultar titulo de la parte superior

        recyclerView = (RecyclerView) findViewById(R.id.recycleravg);

        imagendato = (ImageView) findViewById(R.id.imageView);
        gg =(TextView) findViewById(R.id.textView8);


        db = new BDA(pedidosadd.this);

        id_menu = new ArrayList<>();
        id_guisado = new ArrayList<>();
        id_guisado2 = new ArrayList<>();
        cantidad = new ArrayList<>();
        precio = new ArrayList<>();
        id_estatus = new ArrayList<>();
        //id_user = new ArrayList<>();
        //id = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        String clvv;

        clvv = preferences.getString("clv", "");

        //storeDataInArraysPedidos();
        //RenderDataJSOn( "https://antojitoslolis.000webhostapp.com/gsonp.php");
        String URL = "https://antojitoslolis.000webhostapp.com/gsonp.php?avgysv=" + clvv ;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                //Toast.makeText(getApplicationContext(), "cargando datos mysql web service", Toast.LENGTH_SHORT).show();
                imagendato.setVisibility(View.GONE);
                gg.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(),  , Toast.LENGTH_SHORT).show();
                if( 0 != response.length()  ){
                    for (int y = 0; y < response.length(); y++) {
                        try {
                            jsonObject = response.getJSONObject(y);
                        /*folio_ord.setText(jsonObject.getString("folio_ord"));
                        id_estatus.setText(jsonObject.getString("id_estatus"));
                        fechax.setText(jsonObject.getString("fechax"));
                        cantidadx.setText(jsonObject.getString("cantidadx"));
                        preciovv.setText(jsonObject.getString("preciovv"));*/

                            id_menu.add(jsonObject.getString("folio_ord"));
                            id_guisado.add(jsonObject.getString("id_estatus"));
                            cantidad.add(jsonObject.getString("fechax"));
                            precio.add(jsonObject.getString("cantidadx"));
                            id_estatus.add(jsonObject.getString("preciovv"));



                            //Toast.makeText(getApplicationContext(), "cargando datos mysql web service: " + id_menu, Toast.LENGTH_LONG).show();
                            customadapterPedidos = new CustomadapterPedidos (pedidosadd.this,id_menu,id_guisado,cantidad,precio,id_estatus);
                            recyclerView.setAdapter(customadapterPedidos);
                            recyclerView.setLayoutManager( new LinearLayoutManager( pedidosadd.this));




                        } catch (JSONException x) {
                            Toast.makeText(getApplicationContext(), x.getMessage(), Toast.LENGTH_SHORT).show();
                            imagendato.setVisibility(View.VISIBLE);
                            gg.setVisibility(View.VISIBLE);

                        }
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "cero registros en bd Central", Toast.LENGTH_SHORT).show();
                    imagendato.setVisibility(View.VISIBLE);
                    gg.setVisibility(View.VISIBLE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error de conexión", Toast.LENGTH_SHORT).show();
                imagendato.setVisibility(View.VISIBLE);
                gg.setVisibility(View.VISIBLE);
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);



    }


    private void storeDataInArraysPedidos() {
        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        String clvv;
        clvv = preferences.getString("clv", "");
        Cursor cursor = db.readallPEDNEXXT(clvv);
        if( cursor.getCount() == 0){
            //Toast.makeText(this,"no hay datos",Toast.LENGTH_SHORT).show();
            imagendato.setVisibility(View.VISIBLE);
            gg.setVisibility(View.VISIBLE);

        }else {
            while (cursor.moveToNext()){
                //id_user.add(cursor.getString(1));
                id_menu.add(cursor.getString(0));
                id_guisado.add(cursor.getString(1));
                cantidad.add(cursor.getString(2));
                precio.add(cursor.getString(3));
                id_estatus.add(cursor.getString(4));

            }
            imagendato.setVisibility(View.GONE);
            gg.setVisibility(View.GONE);

        }

    }

    public void RenderDataJSOn( String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                //Toast.makeText(getApplicationContext(), "cargando datos mysql web service", Toast.LENGTH_SHORT).show();
                for (int y = 0; y < response.length(); y++) {
                    try {
                        jsonObject = response.getJSONObject(y);
                        /*folio_ord.setText(jsonObject.getString("folio_ord"));
                        id_estatus.setText(jsonObject.getString("id_estatus"));
                        fechax.setText(jsonObject.getString("fechax"));
                        cantidadx.setText(jsonObject.getString("cantidadx"));
                        preciovv.setText(jsonObject.getString("preciovv"));*/

                        id_menu.add(jsonObject.getString("folio_ord"));
                        id_guisado.add(jsonObject.getString("id_estatus"));
                        cantidad.add(jsonObject.getString("fechax"));
                        precio.add(jsonObject.getString("cantidadx"));
                        id_estatus.add(jsonObject.getString("preciovv"));

                        //Toast.makeText(getApplicationContext(), "cargando datos mysql web service" + id_menu, Toast.LENGTH_LONG).show();


                    } catch (JSONException x) {
                        Toast.makeText(getApplicationContext(), x.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
}