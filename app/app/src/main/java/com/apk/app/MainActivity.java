package com.apk.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {


    /* esto es para facebook*/
    //ImageView facebookbtn;
    ImageView imgface;
    Button loginButton;

    CallbackManager callbackManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);



        callbackManager = CallbackManager.Factory.create();




        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent( MainActivity.this,welcome.class));
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

        imgface = findViewById(R.id.imageView);
        imgface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}