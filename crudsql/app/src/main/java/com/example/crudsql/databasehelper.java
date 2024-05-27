package com.example.crudsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databasehelper extends SQLiteOpenHelper {

    private  Context context;
    private  static  final  String DATABASE_NAME ="sqlavg.db";
    private  static  final  int DATABASE_VERSION =1;

    private  static  final  String TABLE_NAME ="pedidode";
    private  static  final  String COLUMN_ID ="_id";
    private  static  final  String COLUMN_NOMBRE ="nombr";
    private  static  final  String COLUMN_NUMEROPIEZAS ="num";



    databasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " create table " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOMBRE + " TEXT,"
                + COLUMN_NUMEROPIEZAS + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addPed( String n, int numero){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOMBRE,n);
        cv.put(COLUMN_NUMEROPIEZAS,numero);
        long result = db.insert(TABLE_NAME, null,cv);
        if ( result == -1){
            Toast.makeText(context,"registro fallido",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"registro exitoso",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor Readalldata(){
            String query =" select * from "  + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData( String iid, String a_nom, String a_nume){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOMBRE, a_nom);
        cv.put(COLUMN_NUMEROPIEZAS, a_nume);

        long result = db.update(TABLE_NAME, cv,"_id=?", new String[]{iid});
        if( result == -1 ){
            Toast.makeText(context,"fallo actualizacion",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context,"actualización exitosa",Toast.LENGTH_SHORT).show();

        }
    }

    void deleteOnRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?", new String[]{row_id});
        if( result == -1 ){
            Toast.makeText(context,"fallo la eliminacion",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Eliminado",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteall(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME );
    }
}

