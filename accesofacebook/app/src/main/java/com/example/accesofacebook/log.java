package com.example.accesofacebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class log extends AppCompatActivity {


    private LoginButton loginButton;
    private Button Button;
    private CallbackManager callbackManager;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("");
        }
        //para ocultar titulo de la parte superior

        Button = (android.widget.Button) findViewById(R.id.logind);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"entrar",Toast.LENGTH_LONG).show();
            }
        });

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
                Toast.makeText(getApplicationContext(),R.string.error_login ,Toast.LENGTH_LONG).show();

            }
        });
        // metodo de inicio sesion facebook

    }


    /// esto es para la sesion de faceebook
    private void goMainScreen(){
        Intent intent = new Intent(this,bienvenido.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}



