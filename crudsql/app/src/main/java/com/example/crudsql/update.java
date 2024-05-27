package com.example.crudsql;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    EditText j,i;
    Button k , ELIMIN;

    String ide,nomr,nmme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        j = findViewById(R.id.EDIT1);
        i = findViewById(R.id.EDIT2);
        k = findViewById(R.id.button4);
        ELIMIN = findViewById(R.id.button5);

        regresando_datos_y_actualiza_getandsetintentdate();

        //para mostrar el registro a actualizar en la parte superior del baneer usamos el siguiente metodo
        ActionBar a = getSupportActionBar();
        if( a != null ){
            a.setTitle(nomr);
        }


        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databasehelper mydb =  new databasehelper(update.this);
                mydb.updateData(ide,j.getText().toString().trim(),i.getText().toString().trim());

            }
        });

        ELIMIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmdialog();
            }
        });


    }

    void regresando_datos_y_actualiza_getandsetintentdate(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nmbre") &&
        getIntent().hasExtra("nump")){
            ///devuelve
            ide = getIntent().getStringExtra("id");
            nomr = getIntent().getStringExtra("nmbre");
            nmme = getIntent().getStringExtra("nump");

            //actualiza
            j.setText(nomr);
            i.setText(nmme);


        }else{
            Toast.makeText(this,"no hay datos", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar a " + nomr + "?");
        builder.setMessage("estas seguro de eliminar a" + nomr + "?");
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databasehelper mydb = new databasehelper(update.this);
                mydb.deleteOnRow(ide);
                finish();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();

    }
}