package com.example.crudsql;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class mostrar extends AppCompatActivity {

    RecyclerView recyclerView;

    databasehelper db;
    ArrayList<String> a_id, a_nom, a_nume;
    Customadapter customadapter;
    ImageView imagendato;
    TextView gg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        recyclerView = findViewById(R.id.recycleravg);

        imagendato = findViewById(R.id.imageView);
        gg = findViewById(R.id.textView8);

        db = new databasehelper(mostrar.this);
        a_id = new ArrayList<>();
        a_nom = new ArrayList<>();
        a_nume = new ArrayList<>();

        storeDataInArrays();
        customadapter = new Customadapter(mostrar.this,this,a_id,a_nom,a_nume);
        recyclerView.setAdapter(customadapter);
        recyclerView.setLayoutManager( new LinearLayoutManager( mostrar.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();

        }
    }

    void storeDataInArrays(){
        Cursor cursor = db.Readalldata();
        if( cursor.getCount() == 0){
            //Toast.makeText(this,"no hya datos",Toast.LENGTH_SHORT).show();
            imagendato.setVisibility(View.VISIBLE);
            gg.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()){
                a_id.add(cursor.getString(0));
                a_nom.add(cursor.getString(1));
                a_nume.add(cursor.getString(2));
            }
            imagendato.setVisibility(View.GONE);
            gg.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.elim){
            confirmdialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar todo ?");
        builder.setMessage("estas seguro de eliminar todo ?");
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //elimina todos los registros
                databasehelper mydb = new databasehelper(mostrar.this);
                mydb.deleteall();
                //actualiza la vista de la lista de los datos
                Intent intent = new Intent(mostrar.this,mostrar.class);
                startActivity(intent);
                Toast.makeText(mostrar.this,"eliminados",Toast.LENGTH_SHORT).show();
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