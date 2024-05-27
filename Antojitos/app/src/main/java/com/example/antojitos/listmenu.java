package com.example.antojitos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

public class listmenu extends AppCompatActivity {


    RecyclerView recyclerView;

   // RecyclerViewAdaptor recyclerViewAdaptor;

    BDA db;
    //ArrayList<String> id_user,id_menu,id_guisado,cantidad,precio,id_estatus;
    ArrayList<String> id_menu,id_guisado,id_guisado2,cantidad,precio,id_estatus,id_user,id;


    Customadapter customadapter;
    ImageView imagendato;
    TextView gg;
    Button okk,droop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmenu);

        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("PEDIDOS AGREGADOS");
        }
        //para ocultar titulo de la parte superior

        recyclerView = (RecyclerView) findViewById(R.id.recycleravg);

        imagendato = (ImageView) findViewById(R.id.imageView);
        gg =(TextView) findViewById(R.id.textView8);

        okk = (Button) findViewById(R.id.button3);
        droop = (Button) findViewById(R.id.button2);

        db = new BDA(listmenu.this);

        id_menu = new ArrayList<>();
        id_guisado = new ArrayList<>();
        id_guisado2 = new ArrayList<>();
        cantidad = new ArrayList<>();
        precio = new ArrayList<>();
        id_estatus = new ArrayList<>();
        id_user = new ArrayList<>();
        id = new ArrayList<>();

        storeDataInArrays();
        //customadapter = new Customadapter(listmenu.this,id_user,id_menu,id_guisado,cantidad,precio,id_estatus);
        customadapter = new Customadapter(listmenu.this,id_menu,id_guisado,id_guisado2,cantidad,precio,id_estatus,id_user,id);
        recyclerView.setAdapter(customadapter);
        recyclerView.setLayoutManager( new LinearLayoutManager( listmenu.this));

        // METODO PARA ORDENAR A LA BD CENTRAL TU PEDIDO...
        okk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetornoviewMetodos();

            }
        });


        // METODO PARA ELIMINAR LAS ORDENES
        droop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmdialog();
            }
        });


    }

    private void metodo_para_enviar_pedir_BASEDATOSCENTRAL()                                         {

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

                Toast.makeText(listmenu.this,"no hay datos", Toast.LENGTH_LONG).show();

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
                        dataas.put("id"+a,"errortipousuer" );
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


            //Toast.makeText(listmenu.this,dataas.toString(), Toast.LENGTH_LONG).show();

            //esta forma tambien sirve de enviar datos 1
//                    renderdatafinish_antojitos("https://antojitoslolis.000webhostapp.com/pedydoorg.php?us=" + dataas);
            //esta forma tambien sirve de enviar datos 2
            renderdatafinish_antojitos22("https://antojitoslolis.000webhostapp.com/pedydoorg.php" ,dataas);
            Retornoviu();
        }catch ( JSONException i){
            i.printStackTrace();

        }

    }

    private void storeDataInArrays() {

        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        //String aa;
        //String bb;
        //int clvv;
        String clvv;
        //clvv = preferences.getInt("clv", -1);
        clvv = preferences.getString("clv", "");
        //aa = preferences.getString("useremail","");
        //bb = preferences.getString("pasword","");

        Cursor cursor = db.Readalldata(clvv);
        if( cursor.getCount() == 0){
            //Toast.makeText(this,"no hay datos",Toast.LENGTH_SHORT).show();
            imagendato.setVisibility(View.VISIBLE);
            gg.setVisibility(View.VISIBLE);
            okk.setVisibility(View.INVISIBLE);
            droop.setVisibility(View.INVISIBLE);
        }else {
            String naa = "";
            while (cursor.moveToNext()){
                //id_user.add(cursor.getString(1));
                id_menu.add(cursor.getString(0));
                id_guisado.add(cursor.getString(1));
                if(cursor.getString(2).equals("na")){
                    naa ="";
                }else {
                    naa = "/ " + cursor.getString(2);

                }
                id_guisado2.add(naa);
                cantidad.add(cursor.getString(3));
                precio.add(cursor.getString(4));
                id_estatus.add(cursor.getString(5));
            }
            imagendato.setVisibility(View.GONE);
            gg.setVisibility(View.GONE);
            okk.setVisibility(View.VISIBLE);
            droop.setVisibility(View.VISIBLE);
        }

    }


    //metodos para manipulacion de opciones de la parte superior de la app en la vista
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //metodos para manipulacion de opciones de la parte superior de la app en la vista
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.elim){
            confirmdialog();
        }
        return super.onOptionsItemSelected(item);
    }
    //metodos para manipulacion de opciones de la parte superior de la app en la vista

    // ventana de dialogo previa a eliminar
    void confirmdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" ¿ DESEAS ELIMINAR TODA TU ORDEN ?");
        builder.setMessage(" ESTAS SEGURO(A)");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
                String clvv;
                clvv = preferences.getString("clv", "");

                //elimina todos los registros
                BDA mydb = new BDA(listmenu.this);
                mydb.deleteall(clvv);
                //actualiza la vista de la lista de los datos
                //Intent intent = new Intent(listmenu.this,listmenu.class);
                //startActivity(intent);
                //actualiza la vista de la lista de los datos
                Toast.makeText(listmenu.this,"ELIMINADOS",Toast.LENGTH_SHORT).show();
                Retornoviu();
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();

    }

    private void renderdatafinish_antojitos(String URL){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                    Toast.makeText(listmenu.this,"datos listos" + response, Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(listmenu.this,error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

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
        Toast.makeText(listmenu.this,"datos listos", Toast.LENGTH_LONG).show();






    }

    private void Retornoviu(){
        Intent intent = new Intent(this,activitylogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void RetornoviewMetodos(){
        Intent nuevox = new Intent(this,metodos.class);
        startActivity(nuevox);
    }


}