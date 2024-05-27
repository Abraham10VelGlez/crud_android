package com.example.antojitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class metodos extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos);
        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("¿Cómo quieres pagar?");
        }
        //para ocultar titulo de la parte superior

    }



    // metodo  vista de MERCADO PAGO
    public void MERCADOP(View v){
        Intent nuevox = new Intent(this,marketpay.class);
        startActivity(nuevox);
    }
    // metodo  vista de TRANSFERENCIA
    public void TRANSFERENCIA(View v){
        Intent nuevox = new Intent(this,pagtransf.class);
        startActivity(nuevox);
    }
    // metodo  vista de EFECTIVOENCAJA
    public void EFECTIVOENCAJA(View v){
        Intent nuevox = new Intent(this,pagefec.class);
        startActivity(nuevox);
    }
}