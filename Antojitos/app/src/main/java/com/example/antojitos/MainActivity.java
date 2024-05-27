 package com.example.antojitos;

 import android.content.Context;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
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
 import com.facebook.AccessTokenTracker;
 import com.facebook.CallbackManager;
 import com.facebook.FacebookCallback;
 import com.facebook.FacebookException;
 import com.facebook.login.LoginResult;
 import com.facebook.login.widget.LoginButton;
 import com.google.android.gms.auth.api.Auth;
 import com.google.android.gms.auth.api.signin.GoogleSignIn;
 import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
 import com.google.android.gms.auth.api.signin.GoogleSignInClient;
 import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
 import com.google.android.gms.auth.api.signin.GoogleSignInResult;
 import com.google.android.gms.common.ConnectionResult;
 import com.google.android.gms.common.SignInButton;
 import com.google.android.gms.common.api.GoogleApiClient;

 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Objects;

 import static com.google.android.gms.common.api.GoogleApiClient.Builder;
 import static com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

 public class MainActivity extends AppCompatActivity implements OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_IN_CODE = 777 ;

    EditText a,b ;
    TextView textView5;
    session sessions;

    //para almacenar datos
    String dato1,dato2;
    //Button btn ;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("");
        }
        //para ocultar titulo de la parte superior


        a = findViewById(R.id.username);

        b = findViewById(R.id.password);

        //textView5 = findViewById(R.id.textView5);

        Button mButton = (Button) findViewById(R.id.saveingred);
        Resave();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                //iniciamos una ventana de ejecucion
                dato1 = a.getText().toString().trim();
                dato2 = b.getText().toString().trim();
                if( !dato1.isEmpty() && !dato2.isEmpty() ){
                    ValidationUser("https://antojitoslolis.000webhostapp.com/user_login.php");
                }else{
                    Toast.makeText(MainActivity.this,"No se permite campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });



        // metodo de inicio sesion facebook
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override//con exito
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
                Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();

            }

            @Override // proceso cancelado
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"proceso de logeo cancelado",Toast.LENGTH_LONG).show();

            }

            @Override // hubo un error
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),R.string.error_login ,Toast.LENGTH_LONG).show();

            }
        });
        // metodo de inicio sesion facebook

        //iniciamos un objeto de tipo token para obtener los datos de perfil de faceebook

        AccessTokenTracker tokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if( currentAccessToken == null ){
                    //Toast.makeText(MainActivity.this,"fuera de session " ,Toast.LENGTH_LONG).show();
                }else {
                    // renderizacion a menu principal con FACEBOOK
                    //Toast.makeText(MainActivity.this, "Cargar  session ", Toast.LENGTH_LONG).show();
                    goMainScreen();
               }

            }
        };
        // metodo de inicio sesion facebook

        //  COMPROBAR SI EXISTE UN USUARIO QUE HAYA INICIADO SESION PARA FACEBOOK

        if(AccessToken.getCurrentAccessToken() != null) {
            goMainScreen();

        }
        //  COMPROBAR SI EXISTE UN USUARIO QUE HAYA INICIADO SESION PARA FACEBOOK



        // PARA UASR EL APPI DE GOOGLE SIEMPRE SE NECESITA USAR GOOGLEAPICLIENT

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // CREAR UN OBJETO  GoogleSignInClient CON OPCIONES ESPECIFICAS  DE LA VARIABLE ANTERIOR gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        googleApiClient = new Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        // COMPROBAR SI EXISTE UN USUARIO QUE HAYA INICIADO SESION
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

        //llamamos al metodo del booton de GOOGLE
        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });

        // PARA UASR EL APPI DE GOOGLE SIEMPRE SE NECESITA USAR GOOGLEAPICLIENT





    }


    // renderizacion a menu principal con GOOGLE
     private void updateUI(GoogleSignInAccount account) {
        if(Objects.equals(null,account)){
            // en Main
            //Toast.makeText(MainActivity.this,"aún no ha iniciado en su aplicación con Google.", Toast.LENGTH_LONG).show();
         }else{
            //Toast.makeText(MainActivity.this,"inicie su actividad principal o lo que sea apropiado para su aplicación.", Toast.LENGTH_LONG).show();
            goMainScreen();
        }
     }


     /// esto es para la sesion de faceebook
    private void goMainScreen(){
        Intent intent = new Intent(this,activitylogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    /// esto es para la sesion de googgle
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode ==  SIGN_IN_CODE ){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handlSingInResult(result);


        }else{
            callbackManager.onActivityResult( requestCode, resultCode, data);
        }


    }
    /// esto es para la sesion de googgle


    //evento de salto en activity a activyty
    public void loginhome(View view){


        Intent nuevox = new Intent(this,activitylogin.class);
        startActivity(nuevox);




    }

    //evento para mostrar y ocultar el menu
   /* public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.overflow,menu);
        return true;

    }*/
    /*
    //opciones y movimiento para el menu
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

       // if( id == R.id.item1 ){
          //  Toast.makeText(this,"opcion 1",Toast.LENGTH_LONG).show();
       // }else if( id == R.id.item2 ){
       //     Toast.makeText(this,"opcion 2",Toast.LENGTH_LONG).show();
       // } else  if ( id == R.id.item3 ){
        //    Toast.makeText(this,"opcion 3",Toast.LENGTH_LONG).show();
        //}

        switch ( id ){
            case R.id.item1:


                Toast.makeText(this,"opcion 1",Toast.LENGTH_LONG).show();

                break;
            case R.id.item2:
                Toast.makeText(this,"opcion 2",Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(this,"opcion 3",Toast.LENGTH_LONG).show();
                break;

            default:
                Toast.makeText(this,"error",Toast.LENGTH_LONG).show();

                break;


        }
        // super es una palabra reservadda de android se deebe investigar
        return  super.onOptionsItemSelected(item);


    }*/



    // evento del boton de enviar datos
    public void event(View view){

        String var11 = a.getText().toString();
        String var22 = b.getText().toString();

        if( !var11.isEmpty() && !var22.isEmpty() ) {
            String var1 = a.getText().toString();
            String var2 = b.getText().toString();

            /*
            // definicion de la clase session/
            sessions = new session("abraham", "12345", 3.16);
            // definicion de la clase session/

            String var1 = a.getText().toString();
            String var2 = b.getText().toString();

            int resultado = Integer.parseInt(var2);


//        xx.setText("mira el resultado:" + var1 + " " + resultado  );
            textView5.setText("mira el resultado:" + sessions.nom);


            Toast.makeText(this, "se guardo el registro" + var1 + "__" + resultado, Toast.LENGTH_SHORT).show();

*/

            /*
            if (!var1.isEmpty() && !var2.isEmpty()) {



            } else {
                Toast.makeText(this, "No se permiten campos vacios", Toast.LENGTH_SHORT).show();
            }*/
        }else {
            Toast.makeText(this, "No se permiten campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private void ValidationUser(String URL){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if( response.equalsIgnoreCase("10")){
                    PreferensDESAve();
                    Intent intent = new Intent(getApplicationContext(),activitylogin.class);
                    startActivity(intent);


                }else {

                    Toast.makeText(MainActivity.this,"usuario incorrecto", Toast.LENGTH_LONG).show();

                }
                /*
                if(!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(),activitylogin.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"usuario incorrecto", Toast.LENGTH_LONG).show();

                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametrosavg = new  HashMap<String, String>();
                parametrosavg.put("uu",dato1);
                parametrosavg.put("dd",dato2);
                return parametrosavg;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }


    private void PreferensDESAve(){
        SharedPreferences  preferences = getSharedPreferences("datavgMultivers",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clv",1);
        editor.putString("usernom","");
        editor.putString("useremail",dato1);
        editor.putString("pasword",dato2);
        editor.putBoolean("sesdsion",true);
        editor.commit();

    }


    private void  Resave(){
        SharedPreferences preferences = getSharedPreferences("datavgMultivers",Context.MODE_PRIVATE);
        //a.setText(preferences.getString("user","usbrifa@gmail.com"));
        a.setText(preferences.getString("user",""));
        //b.setText(preferences.getString("pasword","12345"));
        b.setText(preferences.getString("pasword",""));

    }

    public void Regis(View v) {
        Intent nuevox = new Intent(this,regis.class);
        startActivity(nuevox);
    }

    //metodo cuando algo sale mal en la validacion de datos del usuario de GOOGLE
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if( requestCode ==  SIGN_IN_CODE ){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handlSingInResult(result);

        }
    }*/

    /// metodo para saber si la operacion fue exitosa si si o sii no
    private void handlSingInResult(GoogleSignInResult result) {
        if( result.isSuccess() ){
            goMainScreen2();
        }else{
            Toast.makeText(MainActivity.this,"No se puede iniciar sesión", Toast.LENGTH_LONG).show();
        }
    }

    /// esto es para la sesion de GOOGLE
    private void goMainScreen2(){
        Intent intent = new Intent(this,activitylogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }






}

/*
antololi
rooto
* mQC)rXs7KL&m/EI9
* */