package com.example.antojitos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class marketpay extends AppCompatActivity {
    Button bbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketpay);
        //para ocultar titulo de la parte superior
        ActionBar az = getSupportActionBar();
        if( az != null ){
            az.setTitle("FORMA DE PAGO SELECCIONADA MERCADOP");
        }

        bbtn = (Button) findViewById(R.id.button21);

        //para ocultar titulo de la parte superior
        //
        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
                    //MercadoPago.SDK.configure("ENV_ACCESS_TOKEN");
            public void onClick(View v) {
                //MercadoPago.SDK.configure("ENV_ACCESS_TOKEN");
              /*  MercadoPago.setAccessToken("YOUR_ACCESS_TOKEN");

                Payment payment = new Payment()
                        .setTransactionAmount(100f)
                        .setToken("your_cardtoken")
                        .setDescription("description")
                        .setInstallments(1)
                        .setPaymentMethodId("visa")
                        .setPayer(new Payer()
                                .setEmail("dummy_email"));

                payment.save();*/

            }
        });


    }



    public void submit(View view) {

    }









}