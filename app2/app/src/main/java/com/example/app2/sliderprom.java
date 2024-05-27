package com.example.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

public class sliderprom extends AppCompatActivity {
    ImageSwitcher afondo;

    int[] gallery = {R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};
    int position;
    int duracion = 3000;
    Timer timer = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliderprom);

        afondo =  findViewById(R.id.fondo);

        Animation in = AnimationUtils.loadAnimation(this,R.anim.in);
        Animation out = AnimationUtils.loadAnimation(this,R.anim.out);

        afondo.setInAnimation(in);
        afondo.setOutAnimation(out);

        afondo.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView =  new ImageView(sliderprom.this);
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
}