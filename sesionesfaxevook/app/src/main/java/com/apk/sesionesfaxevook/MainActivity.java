package com.apk.sesionesfaxevook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
/*
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;*/
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

import javax.security.auth.callback.Callback;



public class MainActivity extends AppCompatActivity {

    /*esto es para google*/
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //bootn de iniciar
    ImageView googlebtn;
    /*esto es para google*/

    /* esto es para facebook*/
    ImageView facebookbtn;

    // hask de activacion de mi aplicacion en facebook
    // keytool -exportcert -alias appsesiones -keystore "C:\Users\lenovo\.android\debug.keystore" | openssl sha1 -binary | openssl base64
    /*
    C:\Users\lenovo\Downloads\HASK_KEY_DE_MIS_APK_ANDROID\bin>
    C:\Users\lenovo\Downloads\HASK_KEY_DE_MIS_APK_ANDROID\bin>keytool -exportcert -alias appsesiones -keystore "C:\Users\lenovo\.android\debug.keystore" | openssl sha1 -binary | openssl base64
    Introduzca la contraseña del almacén de claves:

            *****************  WARNING WARNING WARNING  *****************
            * La integridad de la información almacenada en el almacén de claves  *
            * NO se ha comprobado.  Para comprobar dicha integridad, *
            * debe proporcionar la contraseña del almacén de claves.                  *
            *****************  WARNING WARNING WARNING  *****************

    V82XtQp3SSmbVwuBeiFAgeJ26MY=
    */
    CallbackManager callbackManager;




    /* esto es para facebook*/


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("");
        }

        //inicializamos boton
        googlebtn = findViewById(R.id.imageView6);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);


        // cerrar o mantener abiertto la sesion de google y  ahora tambien la de faceook las 2 en 1
        // para google
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        // para google

        // para facebook
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        // para facebook


        if( acct != null ){
            //|| accessToken != null && accessToken.isExpired() ==  false
            navigateToSecondActivity();

        }



        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singInicio();
            }
        });


        callbackManager = CallbackManager.Factory.create();


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        startActivity(new Intent( MainActivity.this,bienvenido.class));
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });






        facebookbtn = findViewById(R.id.imageView7);
        facebookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));


            }
        });











        //para ocultar titulo de la parte superior

       /* Button = (android.widget.Button) findViewById(R.id.logind);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"entrar",Toast.LENGTH_LONG).show();
            }
        });*/
        /*
        // metodo de inicio sesion facebook
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
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
                Toast.makeText(getApplicationContext(),R.string.errorlogin ,Toast.LENGTH_LONG).show();

            }
        });
        // metodo de inicio sesion facebook
        */

    }


    //****************************************************************************************************************************************************** FACEBOOK

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // esto es para facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // esto es para facebook

        //esto es para ambos
        super.onActivityResult(requestCode, resultCode, data);
        //esto es para ambos

        // esto es para google
        if( requestCode == 1000){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            // esto es para google
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e){
                Toast.makeText(getApplicationContext(), "Algun error de ejucucion", Toast.LENGTH_SHORT).show();
            }

        }
        // esto es para google

    }

    //****************************************************************************************************************************************************** FACEBOOK



    private void goMainScreen(){
        Intent intent = new Intent(this,bienvenido.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    void singInicio(){
        Intent singInicioIntent = gsc.getSignInIntent();
        startActivityForResult(singInicioIntent,1000);
    }

    // ************************************************************************************************************************************* GOOGLE ****************************************************************
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == 1000){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);


            try {
            task.getResult(ApiException.class);
            navigateToSecondActivity();
            } catch (ApiException e){
                Toast.makeText(getApplicationContext(), "Algun error de ejucucion", Toast.LENGTH_SHORT).show();
            }

        }
    }*/

    void   navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(MainActivity.this, bienvenido.class);
        startActivity(intent);
    }
// ************************************************************************************************************************************* GOOGLE ****************************************************************


}
