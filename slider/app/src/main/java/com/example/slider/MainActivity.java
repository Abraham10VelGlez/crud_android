package com.example.slider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageSwitcher afondo;

    int[] gallery = {R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};
    int position;
    int duracion = 3000;
    Timer timer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        afondo =  findViewById(R.id.fondo);

        Animation in = AnimationUtils.loadAnimation(this,R.anim.in);
        Animation out = AnimationUtils.loadAnimation(this,R.anim.out);

        afondo.setInAnimation(in);
        afondo.setOutAnimation(out);

        afondo.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView =  new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return imageView;
            }
        });

        iniciar_Slider();

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

    //evento para mostrar y ocultar el menu
    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.menuinicio,menu);
        return true;

    }
    //opciones y movimiento para el menu
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch ( id ){
            case R.id.item1:


                Toast.makeText(this,"Inicio de Sesión",Toast.LENGTH_LONG).show();
                /*
                ESTE TIPO DE ALERTA ES PARA HACER UN PROCESO DE ALGUN MENU O DE LLENAR UN FOMULARIO DE TIPO VALIDAR
                final ProgressDialog  progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Espere, Por favor");

                progressDialog.show();
                */


                Intent inisess = new Intent(this,log.class);
                startActivity(inisess);

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


    }



}