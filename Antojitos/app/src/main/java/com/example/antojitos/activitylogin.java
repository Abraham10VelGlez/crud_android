package com.example.antojitos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class activitylogin extends AppCompatActivity {

    //variables para cargar el slider de imagens o banners
    ImageSwitcher afondo;

    int[] gallery = {R.drawable.antojitoslogoslider, R.drawable.img4, R.drawable.img2, R.drawable.img3, R.drawable.img1, R.drawable.img5};
    int position;
    int duracion = 3000;
    Timer timer = null;
    //variables para cargar el slider de imagens o banners


    // para notificaciones
    NotificationBadge notificationBadge1;
    Button success, cleear;
    int count = 1;
    TextView  textCartItemCount;
    int mCartItemCount = 10;
    BDA db;
    // para notificaciones


    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView nameTextView_datosgoogle;
    ImageView fotgoogle;
    TextView text1,text2,text3;
    Button revokke;
    //ImageButton cls;
    ImageView cls;


    private TextView emailTextView;
    private TextView idTextView;
    //TextView vistA;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitylogin);


        //para mostrar el  numero de registros de pedidos pre ordenados
        ActionBar a = getSupportActionBar();
        if( a != null ){
            a.setLogo(R.drawable.com_facebook_close);
            a.setTitle("Antojitos Lolis");
            a.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        }
        //para mostrar el  numero de registros de pedidos pre ordenados

        // para CONTAR LAS  notificaciones
        db = new BDA(activitylogin.this);
        // Para CONTAR LAS  notificaciones

        // BOTONES  para notificaciones
        /*
        notificationBadge1 = (NotificationBadge) findViewById(R.id.badge);
        success = (Button) findViewById(R.id.button6);
        cleear = (Button) findViewById(R.id.button7);

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationBadge1.setNumber(count++);
            }
        });

        cleear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationBadge1.clear();
            }
        });*/
        // BOTONES  para notificaciones





        //emailTextView = (TextView) findViewById(R.id.atributosfacegoogle);

        //nameTextView_datosgoogle = (TextView) findViewById(R.id.textView3);
        //emailTextView = (TextView) findViewById(R.id.textView6);
        //idTextView = (TextView) findViewById(R.id.textView3);

        //vistA = (TextView) findViewById(R.id.textView5);

//  COMPROBAR DE INICIO DE  SESION PARA FACEBOOK CUNADO ES NEUVO
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    displayProfileInfo(currentProfile);
                    //activa el email
                    requestEmail(AccessToken.getCurrentAccessToken());
                    //Toast.makeText(getApplicationContext(), "Inicia nueva sesion con el perfil tal", Toast.LENGTH_SHORT).show();
            }else{
                    //requestEmail(AccessToken.getCurrentAccessToken());
                    //Toast.makeText(getApplicationContext(), "continua con session del perfil tal", Toast.LENGTH_SHORT).show();

            }
            }
        };
        //  COMPROBAR DE INICIO DE  SESION PARA FACEBOOK CUNADO ES NEUVO


//  COMPROBAR SI EXISTE UN USUARIO QUE HAYA INICIADO SESION PARA FACEBOOK

        if(AccessToken.getCurrentAccessToken() == null) {
            //Toast.makeText(this,"es null y la session esta vacia",Toast.LENGTH_LONG).show();
        }else{
            requestEmail(AccessToken.getCurrentAccessToken());
            //Toast.makeText(this,"no es null y activa el Preferens_SAved_facebbok",Toast.LENGTH_LONG).show();
        }
        //  COMPROBAR SI EXISTE UN USUARIO QUE HAYA INICIADO SESION PARA FACEBOOK


        // para ver los datos guardados del pedido n
        //Redirect_preferensmenu1();

        // PARA USAR EL APPI DE GOOGLE SIEMPRE SE NECESITA USAR GOOGLEAPICLIENT PARA CARGAR DATOS DE GOOGLE DEL USUARIO
        //text1 = (TextView) findViewById(R.id.aa);
        //text2 = (TextView) findViewById(R.id.bb);
        //text3 = (TextView) findViewById(R.id.cc);
        /////////////////////////// cls = (ImageButton) findViewById(R.id.button55);
        cls = (ImageView) findViewById(R.id.button55);

        //revokke = (Button) findViewById(R.id.button44);
        //fotgoogle = (ImageView) findViewById(R.id.img1);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


          googleApiClient = new GoogleApiClient.Builder(this)
                  .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                  .build();

          cls.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //revokeAccess();
                  signOutgoogle();

              }
          });
        // PARA USAR EL APPI DE GOOGLE SIEMPRE SE NECESITA USAR GOOGLEAPICLIENT PARA CARGAR DATOS DE GOOGLE DEL USUARIO






          /// metodos y funciones para las imagenes del slider o baners
        afondo =  findViewById(R.id.fondo);

        Animation in = AnimationUtils.loadAnimation(this,R.anim.in);
        Animation out = AnimationUtils.loadAnimation(this,R.anim.out);

        afondo.setInAnimation(in);/// metodos y funciones para las imagenes del slider o baners
        afondo.setOutAnimation(out);

        afondo.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {/// metodos y funciones para las imagenes del slider o baners
                ImageView imageView =  new ImageView(activitylogin.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                /// metodos y funciones para las imagenes del slider o baners
                return imageView;
            }
        });

        iniciar_Slider();
        /// metodos y funciones para las imagenes del slider o baners


    }



    // cierra la sesion del FACEBOOOK con exito
    public void logoutfacebook(View view){
        LoginManager.getInstance().logOut();
        logout2();

    }


    // cierra la sesion del google con exito y  NOOOOOOOOO Borra  la vinculacion del sistema y google cuenta
    private void signOutgoogle() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // este es facebook
                        LoginManager.getInstance().logOut();
                        // este es google
                        Toast.makeText(getApplicationContext(), "Sesión finalizada", Toast.LENGTH_SHORT).show();
                        logout2();
                    }
                });
    }
    // cierra la sesion del google con exito y borra  la vinculacion del sistema y google cuenta
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "borrar sesion de aqui", Toast.LENGTH_SHORT).show();
                        logout2();
                        //goLoginScreen();
                    }
                });
    }

    public  void  iniciar_Slider(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        afondo.setImageResource(gallery[position]);
                        position++;
                        if (position == gallery.length){
                            position = 0;

                        }
                    }
                });
            }
        },0,duracion );


    }

    protected void onStart() {
        super.onStart();

        OptionalPendingResult <GoogleSignInResult> pr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if( pr.isDone() ){
            GoogleSignInResult result = pr.get();
            handleSignInResult(result);

        }else{
            pr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });

        }

    }



    private void handleSignInResult(GoogleSignInResult result) {
        if( result.isSuccess() ){
            GoogleSignInAccount account = result.getSignInAccount();
            // llamada par adevolver los datos DE GOOGLE.GMAIL a la vista
            //text1.setText(account.getEmail());
            //text2.setText(account.getDisplayName());
            //text3.setText(account.getId());

            //nameTextView_datosgoogle.setText(account.getEmail() + " ___ " + account.getId() + " ___ " +  account.getDisplayName());



            // GUARDAMOS VALORES DE GOOGLE EN NUETRAS BASE DE DATOS
            // extrae la fecha
            Date fecha = new Date();
            DateFormat df = new SimpleDateFormat("yy/MM/dd");
            String fechasalida = df.format(fecha);
            //nameTextView_datosgoogle.setText(salida);
            // extrae la fecha

            // extrae division de nombres
            String string = account.getDisplayName();
            String[] parts = string.split(" ",2);
            String part1 = parts[0]; // NOMBRE
            //String part2 = parts[1]; // APPE PATERNO
            //String part3 = parts[2]; // APPE MATERNO
            String part2 = " ";
            String part3 = " ";
            //nameTextView_datosgoogle.setText( " hola "+ hiddeenemail + salida);
            // extrae division de nombres

            String telef = "5500000000";

            //guardamos los datos GOOGLE en  preferends para hacer los movimientos de pedidos en las base de sqllite
            Preferens_SAved_gooGle(account.getId(),account.getDisplayName(),account.getEmail());
            //guardamos los datos GOOGLE en  preferends para hacer los movimientos de pedidos en las base de sqllite

            savedata_google("https://antojitoslolis.000webhostapp.com/user_regis_google.php", fechasalida,account.getDisplayName(),part2,part3,account.getEmail(),telef, account.getId());

            // GUARDAMOS VALORES DE GOOGLE EN NUETRAS BASE DE DATOS


            // este metodo incrusta la imagen del correo dentro de  nuestro controlador  imagenview
            //Log.d("MIAPP",account.getPhotoUrl().toString());
            //Glide.with(this).load(account.getPhotoUrl()).into(fotgoogle);
            // este metodo incrusta la imagen del correo dentro de  nuestro controlador  imagenview


        }else{
            goLoginScreen();
        }
    }

    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    String iid = object.getString("id");
                    String a1 = object.getString("first_name");
                    String a2 = object.getString("last_name");
                    setEmail(iid,a1,a2,email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void setEmail(String iid,String a1,String a2, String email) {
        //nameTextView_datosgoogle.setText(iid + " ___  " + email);

        // GUARDAMOS VALORES DE FACEBOOK EN NUETRAS BASE DE DATOS

        //muestra los valores de los datos capturados en el facebook nombre completo, id, y correo
        //nameTextView.setText(name + " ___ " + id);
        //nameTextView_datosgoogle.setText( id + hiddeenemail);
        //Preferens_usuarios_faceb(id, name, hiddeenemail);

        // extrae la fecha
        Date fecha = new Date();
        DateFormat df = new SimpleDateFormat("yy/MM/dd");
        String fechasalida = df.format(fecha);
        //nameTextView_datosgoogle.setText(salida);
        // extrae la fecha

        // extrae division de nombres
        String string = a2;
        String[] parts = string.split(" ",2);
        String part1 = parts[0]; // NOMBRE
        String part2 = parts[1]; // APPE PATERNO
        //String part3 = parts[2]; // APPE MATERNO
        String part3 = " ";
        //nameTextView_datosgoogle.setText( " hola "+ hiddeenemail + salida);
        // extrae division de nombres

        String telef = "5500000000";

        //guardamos los datos facebook en  preferends para hacer los movimientos de pedidos en las base de sqllite
        Preferens_SAved_facebbok(iid,a1,a2,email);
                //guardamos los datos facebook en  preferends para hacer los movimientos de pedidos en las base de sqllite
        //Toast.makeText(activitylogin.this,"google datos:" + a1.toString().trim() ,Toast.LENGTH_LONG).show();

        savedata_faceebook("https://antojitoslolis.000webhostapp.com/user_regis_faceebook.php", fechasalida,a1,a2,part3,email,telef, iid);

// GUARDAMOS VALORES DE FACEBOOK EN NUETRAS BASE DE DATOS


    }
    private void displayProfileInfo(Profile profile) {
        String id = profile.getId();
        String name = profile.getName();

        //Toast.makeText(activitylogin.this,"DATOS FACEBOOK: " + name.toString().trim() ,Toast.LENGTH_LONG).show();
        //idTextView.setText(id);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        profileTracker.stopTracking();
    }


    private void goLoginScreen(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // metodo  vista de orden de kekas
    public void menuone(View v){
        Intent nuevox = new Intent(this,kekap.class);
        startActivity(nuevox);
    }
    // metodo  vista de orden de Tostadas
    public void menutwoo(View v){
        Intent nuevox = new Intent(this,tostds.class);
        startActivity(nuevox);
    }
    public void menutreee(View v){
        Intent nuevox = new Intent(this,pambz.class);
        startActivity(nuevox);
    }
    public void menufourr(View v){
        Intent nuevox = new Intent(this,sops.class);
        startActivity(nuevox);
    }
    public void menufivve(View v){
        Intent nuevox = new Intent(this,gelaa.class);
        startActivity(nuevox);
    }
    public void menusixx(View v){
        Intent nuevox = new Intent(this,mixx.class);
        startActivity(nuevox);
    }

    public void logout(View v){
        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        LoginManager.getInstance().logOut();
        goLoginScreen();

    }

    public void logout2(){

        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        LoginManager.getInstance().logOut();
        goLoginScreen();

    }

    private void  Redirect_preferensmenu1(){
        int a = 1;
        SharedPreferences preferences = getSharedPreferences("datavgMultivers",Context.MODE_PRIVATE);
        int val = preferences.getInt("tipo_g",0);
        //Toast.makeText(activitylogin.this,"dato:" + val ,Toast.LENGTH_LONG).show();
        //.setText(String.valueOf("guisado tal " + val));

    }

/*
    //opciones y movimiento para el menu
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch ( id ){
            case R.id.item1:


                //ESTE TIPO DE ALERTA ES PARA HACER UN PROCESO DE ALGUN MENU O DE LLENAR UN FOMULARIO DE TIPO VALIDAR
                //final ProgressDialog  progressDialog = new ProgressDialog(this);
                //progressDialog.setMessage("Espere, Por favor");
                //progressDialog.show();




                Intent inisess = new Intent(this,listmenu.class);
                startActivity(inisess);

                break;
            case R.id.item2:
                Toast.makeText(this,"opcion 2",Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(this,"opcion 3",Toast.LENGTH_LONG).show();
                break;
            case R.id.item4:
                logout2();
                //logOut_to_google_com();
                break;
            case R.id.item5:
                Intent p = new Intent(this,pedidosadd.class);
                startActivity(p);

                break;

            default:
                Toast.makeText(this,"error",Toast.LENGTH_LONG).show();

                break;


        }
        // super es una palabra reservadda de android se deebe investigar
        return  super.onOptionsItemSelected(item);


    }

    //evento para mostrar y ocultar el menu POR ACTIVIDAD SOLO ESTA DADA DE ALTA EN ESA ACTIVIDAD
    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.overflow,menu);
        return true;

    }*/

    private void savedata_faceebook(String url,String a,String b, String c, String d, String e, String f, String g){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if( response.equalsIgnoreCase("11")) {

                    //Toast.makeText(activitylogin.this,"Listo para hacer pedidos con facebook", Toast.LENGTH_LONG).show();

                }else if( response.equalsIgnoreCase("10")){

                    Toast.makeText(activitylogin.this,"Bienvenido", Toast.LENGTH_LONG).show();
                    //Logred();




                }else {

                    Toast.makeText(activitylogin.this,"error acceso" + response, Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(regis.this,error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros_avg = new HashMap<String, String>();

                parametros_avg.put("fec", a);
                parametros_avg.put("nmm", b.trim());
                parametros_avg.put("apl", c.trim());
                parametros_avg.put("apt", d.trim());
                parametros_avg.put("coe", e.trim());
                parametros_avg.put("ono", f.trim());
                parametros_avg.put("paw", g.trim());

                //datos a enviarse a mysql php
                /*

                parametros_avg.put("fec", "2021-01-01");
                parametros_avg.put("nmm", "aaaa");
                parametros_avg.put("apl", "aaaa1");
                parametros_avg.put("apt", "aaaa2");
                parametros_avg.put("coe", "aaaa@gmail.com");
                parametros_avg.put("ono", "7224076449");
                parametros_avg.put("paw", "1234");

                savedata_faceebook("https://antojitoslolis.000webhostapp.com/user_regis_faceebook.php", salida,part1, part2, part3, hiddeenemail, telef, id);

                $a = $_POST['fec'];
                $b = $_POST['nmm'];
                $c = $_POST['apl'];
                $d = $_POST['apt'];
                $e = $_POST['coe'];
                $f = $_POST['ono'];
                $g = $_POST['paw'];

                 */
                return parametros_avg;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }


    private void savedata_google(String url,String a,String b, String c, String d, String e, String f, String g){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if( response.equalsIgnoreCase("11")) {

                    //Toast.makeText(activitylogin.this,"Listo para hacer pedidos con google", Toast.LENGTH_LONG).show();

                }else if( response.equalsIgnoreCase("10")){

                    Toast.makeText(activitylogin.this,"Bienvenido", Toast.LENGTH_LONG).show();
                    //Logred();




                }else {

                    Toast.makeText(activitylogin.this,"error acceso" + response, Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(regis.this,error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros_avg = new HashMap<String, String>();

                parametros_avg.put("fec", a);
                parametros_avg.put("nmm", b.trim());
                parametros_avg.put("apl", c.trim());
                parametros_avg.put("apt", d.trim());
                parametros_avg.put("coe", e.trim());
                parametros_avg.put("ono", f.trim());
                parametros_avg.put("paw", g.trim());

                //datos a enviarse a mysql php
                /*

                parametros_avg.put("fec", "2021-01-01");
                parametros_avg.put("nmm", "aaaa");
                parametros_avg.put("apl", "aaaa1");
                parametros_avg.put("apt", "aaaa2");
                parametros_avg.put("coe", "aaaa@gmail.com");
                parametros_avg.put("ono", "7224076449");
                parametros_avg.put("paw", "1234");



                $a = $_POST['fec'];
                $b = $_POST['nmm'];
                $c = $_POST['apl'];
                $d = $_POST['apt'];
                $e = $_POST['coe'];
                $f = $_POST['ono'];
                $g = $_POST['paw'];

                 */
                return parametros_avg;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    private void Preferens_SAved_facebbok( String iid, String a1, String a2, String emailz){
        String k = a1 + " " + a2;
        SharedPreferences  preferences = getSharedPreferences("datavgMultivers",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //editor.putInt("clv",2);
        editor.putString("clv",iid);
        editor.putString("usernom",k);
        editor.putString("useremail",emailz);
        editor.putString("pasword",iid);
        editor.putBoolean("sesdsion",true);
        editor.commit();

    }

    private void Preferens_SAved_gooGle(String IID, String nombrecompl, String emailx){
        SharedPreferences  preferences = getSharedPreferences("datavgMultivers",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //editor.putInt("clv",3);
        editor.putString("clv", IID);
        editor.putString("usernom",nombrecompl);
        editor.putString("useremail",emailx);
        editor.putString("pasword",IID);
        editor.putBoolean("sesdsion",true);
        editor.commit();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);

        final MenuItem menuItem = menu.findItem(R.id.item1);

        View actionView = menuItem.getActionView();
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        couunts();
        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            /*
            case R.id.item1: {
                //accion a realizar

                return true;
            }*/
            case R.id.item1:

                //ESTE TIPO DE ALERTA ES PARA HACER UN PROCESO DE ALGUN MENU O DE LLENAR UN FOMULARIO DE TIPO VALIDAR
                //final ProgressDialog  progressDialog = new ProgressDialog(this);
                //progressDialog.setMessage("Espere, Por favor");
                //progressDialog.show();
                Intent inisess = new Intent(this,listmenu.class);
                startActivity(inisess);

                break;
            case R.id.item2:
                Toast.makeText(this,"en desarrollo",Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(this,"en desarrollo",Toast.LENGTH_LONG).show();
                break;
            /*
            <item
             android:id="@+id/item4"
              android:icon="@drawable/ic_launcher_foreground"
                android:title="Cerrar Sesión" >
                </item>
            case R.id.item4:
                logout2();
                //logOut_to_google_com();
                break;*/
            case R.id.item5:
                Intent p = new Intent(this,pedidosadd.class);
                startActivity(p);

                break;

            default:
                Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void couunts(){
        // a cargar numero de registros.
        //textCartItemCount.setText("9");
        SharedPreferences preferences = getSharedPreferences("datavgMultivers", Context.MODE_PRIVATE);
        String clvv;
        clvv = preferences.getString("clv", "");

        Cursor cursor = db.notifitycountyanli(clvv);
        if( cursor.getCount() == 0){
            Toast.makeText(this,"no hay datos",Toast.LENGTH_SHORT).show();
        }else {
            String clouds;
            while (cursor.moveToNext()){


                if( cursor.getString(0) == null){
                    clouds = "avg";
                }else{
                    clouds = cursor.getString(0);
                }
                textCartItemCount.setText(clouds);
                //Toast.makeText(this,"resultado: " + clouds.toString() ,Toast.LENGTH_SHORT).show();

            }
        }
        // a cargar numero de registros.
    }

    private void setupBadge() {
        //Toast.makeText(this,"contador" + textCartItemCount ,Toast.LENGTH_SHORT).show();

        if (textCartItemCount != null) {
            //Toast.makeText(this,"Es diferente a null",Toast.LENGTH_SHORT).show();
            if (mCartItemCount == 0) {
                //Toast.makeText(this,"Es == 0",Toast.LENGTH_SHORT).show();
                if (textCartItemCount.getVisibility() != View.GONE) {
                    //Toast.makeText(this,"Es != 0",Toast.LENGTH_SHORT).show();

                    textCartItemCount.setVisibility(View.GONE);
                }
                //Toast.makeText(this,"Aq va1",Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this,"no es igual  a 0",Toast.LENGTH_SHORT).show();
                textCartItemCount.setVisibility(View.VISIBLE);
                if(textCartItemCount.getText().equals("avg")){
                    //Toast.makeText(this,"ES NULL",Toast.LENGTH_SHORT).show();
                    textCartItemCount.setVisibility(View.GONE);

                }
                /*
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    Toast.makeText(this,"Es diferente  a visible",Toast.LENGTH_SHORT).show();
                    //textCartItemCount.setVisibility(View.VISIBLE);

                }*/
                //Toast.makeText(this,"Aq va2",Toast.LENGTH_SHORT).show();

            }
        }else{
            //Toast.makeText(this,"NO diferente a null",Toast.LENGTH_SHORT).show();
            textCartItemCount.setVisibility(View.GONE);

        }
    }

    // SALIR O CERRAR SESIÓN
    public void cllosed(){
        logout2();
    }

}