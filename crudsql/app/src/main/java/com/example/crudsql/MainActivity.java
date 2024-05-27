package com.example.crudsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button a,b,c;
    EditText g,h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        g = findViewById(R.id.text1);
        h = findViewById(R.id.text22);
        a = findViewById(R.id.button);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasehelper mydb = new databasehelper(MainActivity.this);
                mydb.addPed(g.getText().toString().trim(),
                        Integer.valueOf(h.getText().toString().trim()));

            }
        });

    }

    public void m(View v){
        Intent nuevox = new Intent(this,mostrar.class);
        startActivity(nuevox);
    }
}
