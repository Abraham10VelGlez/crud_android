 package com.example.antojitos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BDA extends SQLiteOpenHelper {

    private static final String nombrebd = "antojitoslolis.bd";
    private static final int versionbd = 1;
    private static final String tabla = "create table tmp_ord( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , id_user varchar(255) ,id_menu int, id_guisado int,id_guisado2 int, cantidad int,precio real, id_estatus int, fechaa DATETIME DEFAULT CURRENT_TIMESTAMP,folio int)";
    private static final String tabla2 = "create table tmp_ord2( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , id_user varchar(255) ,id_menu int, id_guisado int,id_guisado2 int, cantidad int,precio real, id_estatus int, fechaa DATETIME DEFAULT CURRENT_TIMESTAMP,folio int)";
    private static final String tabla3 = "create table tmp_ord3( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , id_user varchar(255) ,id_menu int, id_guisado int,id_guisado2 int, cantidad int,precio real, id_estatus int, fechaa DATETIME DEFAULT CURRENT_TIMESTAMP,folio int)";
    private  static  final  String TABLE_NAME ="tmp_ord";
    private  static  final  String TABLE_NAME2 ="tmp_ord2";
    private  static  final  String TABLE_NAME3 ="tmp_ord3";

    public BDA(Context context){
        super(context,nombrebd,null,versionbd);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(tabla);
        sqLiteDatabase.execSQL(tabla2);
        sqLiteDatabase.execSQL(tabla3);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(tabla);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        sqLiteDatabase.execSQL(tabla2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        sqLiteDatabase.execSQL(tabla3);

    }
// metodo para agregar un nueva orden desde 1,2,3,4
    public void agregarregis(String iduser, Integer idmenu, Integer idguisado,Integer idguisado2, Integer cantida, String pres, Integer idestatu ){
        int yaneli = 25;
        int result  = 0;
        SQLiteDatabase bd = getWritableDatabase();
        if( bd != null ){
            //forma 1 de insertar
            //bd.execSQL("INSERT INTO tmp_ord  VALUES ("+iduser+","+idmenu+","+idguisado+","+cantida+","+preci+","+idestatu+")");
            //bd.close();
            //forma 2 de insertar
            //id primary key , id_user int ,id_menu int,
            //id_guisado int, cantidad int,precio real, id_estatus int
            ContentValues nuevoreg = new ContentValues();
            nuevoreg.put("id_user",iduser);
            nuevoreg.put("id_menu",idmenu);
            nuevoreg.put("id_guisado",idguisado);
            nuevoreg.put("id_guisado2",idguisado2);
            nuevoreg.put("cantidad",cantida);
            result = yaneli * cantida;
            nuevoreg.put("precio", String.valueOf(result));
            nuevoreg.put("id_estatus",idestatu);
            nuevoreg.put("fechaa",getDateTime());
            nuevoreg.put("folio",0);
            bd.insert("tmp_ord",null,nuevoreg);
            bd.close();

        }


    }
    // metodo para agregar un nueva orden desde GELATINAS Y COMBINADAS
    public void agregarregis2(String iduser, Integer idmenu, Integer idmenu2, Integer idguisado,Integer idguisado2, Integer cantida, String pres, Integer idestatu ){

        SQLiteDatabase bd = getWritableDatabase();
        if( bd != null ){
            if( idmenu == 5 ){
                int yaneli = 20;
                int result  = 0;
                //forma 2 de insertar
                //id primary key , id_user int ,id_menu int,
                //id_guisado int, cantidad int,precio real, id_estatus int
                ContentValues nuevoreg = new ContentValues();
                nuevoreg.put("id_user",iduser);
                nuevoreg.put("id_menu",idmenu);
                nuevoreg.put("id_guisado",idguisado);
                nuevoreg.put("id_guisado2",idguisado2);
                nuevoreg.put("cantidad",cantida);
                result = yaneli * cantida;
                nuevoreg.put("precio", String.valueOf(result));
                nuevoreg.put("id_estatus",idestatu);
                nuevoreg.put("fechaa",getDateT222());
                nuevoreg.put("folio",0);
                bd.insert("tmp_ord2",null,nuevoreg);
                bd.close();

            }else if( idmenu == 6 ) {
                // antojito normal
                int yaneli = 25;
                int result  = 0;
                //forma 2 de insertar
                //id primary key , id_user int ,id_menu int,
                //id_guisado int, cantidad int,precio real, id_estatus int
                ContentValues nuevoreg = new ContentValues();
                nuevoreg.put("id_user",iduser);
                nuevoreg.put("id_menu",idmenu2);
                nuevoreg.put("id_guisado",idguisado);
                nuevoreg.put("id_guisado2",idguisado2);
                nuevoreg.put("cantidad",cantida);
                result = yaneli * cantida;
                nuevoreg.put("precio", String.valueOf(result));
                nuevoreg.put("id_estatus",idestatu);
                nuevoreg.put("fechaa",getDateT222());
                nuevoreg.put("folio",0);
                bd.insert("tmp_ord3",null,nuevoreg);
                bd.close();

            }else if( idmenu == 7 ) {
                // antojito combinados
                int yaneli = 28;
                int result  = 0;
                //forma 2 de insertar
                //id primary key , id_user int ,id_menu int,
                //id_guisado int, cantidad int,precio real, id_estatus int
                ContentValues nuevoreg = new ContentValues();
                nuevoreg.put("id_user",iduser);
                nuevoreg.put("id_menu",idmenu2);
                nuevoreg.put("id_guisado",idguisado);
                nuevoreg.put("id_guisado2",idguisado2);
                nuevoreg.put("cantidad",cantida);
                result = yaneli * cantida;
                nuevoreg.put("precio", String.valueOf(result));
                nuevoreg.put("id_estatus",idestatu);
                nuevoreg.put("fechaa",getDateT222());
                nuevoreg.put("folio",0);
                bd.insert("tmp_ord3",null,nuevoreg);
                bd.close();

            }else{
                bd.close();

            }

        }
       // bd.close();


    }

    //funcion de tiempo
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    private String getDateT222() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

//mostrar las ordenes registradas
    Cursor Readalldata(String clvuser){
        //IIF(1 < 2, 'Yes', 'No' )
        //String query =" select * from "  + TABLE_NAME;
        /*

        select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS'" +
                "   WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS' " +
                "  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' " +
                "  WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu, " +
                " (CASE   WHEN id_guisado == 1 THEN 'MOLE VERDE'   WHEN id_guisado == 2 THEN 'CARNE'" +
                "   WHEN id_guisado == 3 THEN 'PICADILLO'   WHEN id_guisado == 4 THEN 'SESOS' " +
                "  WHEN id_guisado == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado == 6 THEN 'QUESO' " +
                "  WHEN id_guisado == 7 THEN 'POLLO'   WHEN id_guisado == 8 THEN 'PAPAS CON CHORIZO'" +
                "   WHEN id_guisado == 9 THEN 'PATA'   WHEN id_guisado == 10 THEN 'TINGA DE POLLO' WHEN id_guisado == 11 THEN 'TINGA DE RES'   ELSE 'error' END ) as  id_guisado," +
                " cantidad, precio, " +
                " (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord where id_estatus = 1;


        * select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS' WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS'  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu,   (CASE   WHEN id_guisado == 1 THEN 'MOLE VERDE'   WHEN id_guisado == 2 THEN 'CARNE'  WHEN id_guisado == 3 THEN 'PICADILLO'   WHEN id_guisado == 4 THEN   'SESOS'   WHEN id_guisado == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado == 6 THEN 'QUESO'   WHEN id_guisado == 7 THEN 'POLLO'   WHEN id_guisado == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado == 9 THEN 'PATA'   WHEN id_guisado == 10 THEN 'TINGA DE POLLO' WHEN id_guisado == 11 THEN 'TINGA DE RES'   ELSE 'error' END ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2,     cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord where id_estatus = 1
    union all
    select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS' WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS'  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu,   (CASE   WHEN id_guisado == 1 THEN 'FLAN'   WHEN id_guisado == 2 THEN 'QUESO'  WHEN id_guisado == 3 THEN 'CHOCOLATE'   WHEN id_guisado == 4 THEN   'OTRA' ELSE 'error' END ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2, cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord2 where id_estatus = 1
    union all
    select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS / COMBINADO' WHEN id_menu == 2 THEN 'TOSTADAS / COMBINADO'   WHEN id_menu == 3 THEN 'PAMBAZOS / COMBINADO'  WHEN id_menu == 4 THEN 'SOPES / COMBINADO'   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu , (CASE   WHEN id_guisado == 1 THEN 'MOLE VERDE'   WHEN id_guisado == 2 THEN 'CARNE'  WHEN id_guisado == 3 THEN 'PICADILLO'   WHEN id_guisado == 4 THEN   'SESOS'   WHEN id_guisado == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado == 6 THEN 'QUESO'   WHEN id_guisado == 7 THEN 'POLLO'   WHEN id_guisado == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado == 9 THEN 'PATA'   WHEN id_guisado == 10 THEN 'TINGA DE POLLO' WHEN id_guisado == 11 THEN 'TINGA DE RES'   ELSE 'error' END ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2,     cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord3 where id_estatus = 1
        * */

        String query = "select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS' WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS'  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu,   (CASE   WHEN id_guisado == 1 THEN 'MOLE VERDE'   WHEN id_guisado == 2 THEN 'CARNE'  WHEN id_guisado == 3 THEN 'PICADILLO'   WHEN id_guisado == 4 THEN   'SESOS'   WHEN id_guisado == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado == 6 THEN 'QUESO'   WHEN id_guisado == 7 THEN 'POLLO'   WHEN id_guisado == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado == 9 THEN 'PATA'   WHEN id_guisado == 10 THEN 'TINGA DE POLLO' WHEN id_guisado == 11 THEN 'TINGA DE RES' WHEN id_guisado = 31 THEN 'FLAN' WHEN id_guisado = 32 THEN 'QUESO' WHEN id_guisado = 33 THEN 'CHOCOLATE' WHEN id_guisado = 34 THEN 'ROMPOPE' WHEN id_guisado = 35 THEN 'MOSAICO' WHEN id_guisado = 36 THEN 'OTRO SABOR'   ELSE 'error' END ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2,     cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord where id_user = '"+clvuser+"' and id_estatus = 1\n" +
                "    union all\n" +
                "    select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS' WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS'  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu,   (CASE   WHEN id_guisado = 31 THEN 'FLAN' WHEN id_guisado = 32 THEN 'QUESO' WHEN id_guisado = 33 THEN 'CHOCOLATE' WHEN id_guisado = 34 THEN 'ROMPOPE' WHEN id_guisado = 35 THEN 'MOSAICO' WHEN id_guisado = 36 THEN 'OTRO SABOR' ELSE 'error' END ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2, cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord2 where id_user = '"+clvuser+"' and id_estatus = 1\n" +
                "    union all\n" +
                "    select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS' WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS'  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu , (CASE   WHEN id_guisado == 1 THEN 'MOLE VERDE'   WHEN id_guisado == 2 THEN 'CARNE'  WHEN id_guisado == 3 THEN 'PICADILLO'   WHEN id_guisado == 4 THEN   'SESOS'   WHEN id_guisado == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado == 6 THEN 'QUESO'   WHEN id_guisado == 7 THEN 'POLLO'   WHEN id_guisado == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado == 9 THEN 'PATA'   WHEN id_guisado == 10 THEN 'TINGA DE POLLO' WHEN id_guisado == 11 THEN 'TINGA DE RES' WHEN id_guisado = 31 THEN 'FLAN' WHEN id_guisado = 32 THEN 'QUESO' WHEN id_guisado = 33 THEN 'CHOCOLATE' WHEN id_guisado = 34 THEN 'ROMPOPE' WHEN id_guisado = 35 THEN 'MOSAICO' WHEN id_guisado = 36 THEN 'OTRO SABOR'   ELSE 'error' END  ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2,     cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord3 where id_user = '"+clvuser+"' and id_estatus = 1";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    // eliminar todos los registros de la tabla
    void deleteall(String j){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " where id_user = '" + j + "'" );
        db.execSQL("DELETE FROM " + TABLE_NAME2 + " where id_user = '" + j + "'"  );
        db.execSQL("DELETE FROM " + TABLE_NAME3 + " where id_user = '" + j + "'" );
    }

    //consulta para ordenar final
    Cursor readallpedidosendd( String clvuser ){

        String query = "select   id_menu, id_guisado,id_guisado2, cantidad, precio, id_estatus, id_user, id from tmp_ord where  id_user = '"+ clvuser +"' and id_estatus = 1 \n" +
                "union all\n" +
                "select   id_menu, id_guisado,id_guisado2, cantidad, precio, id_estatus, id_user, id from tmp_ord2 where id_user = '"+ clvuser +"' and id_estatus = 1 \n" +
                "union all\n" +
                "select   id_menu, id_guisado,id_guisado2, cantidad, precio, id_estatus, id_user, id from tmp_ord3 where id_user = '"+ clvuser +"' and  id_estatus = 1 ";
        SQLiteDatabase db = this.getReadableDatabase();
        //100937601090888193374
        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;

    }

    //actualiazamos el estatus debido a que se ordeno ya en la base de datos central.
    public String updata_tbl_tmp_ord_estatus(String a){
        SQLiteDatabase db = this.getWritableDatabase();
        //actualiazamos el estatus debido a que se ordeno ya en la base de datos central.
        String updatequery = "UPDATE tmp_ord set id_estatus = 2 where  id = " + a;
        db.execSQL(updatequery);
        String updatequery2 = "UPDATE tmp_ord2 set id_estatus = 2 where  id = " + a;
        db.execSQL(updatequery2);
        String updatequery3 = "UPDATE tmp_ord3 set id_estatus = 2 where  id = " + a;
        db.execSQL(updatequery3);
        return a;
    }

    //consulta para ordenar EN CURSO
    Cursor readallPEDNEXXT(String clvuser){

// SELECT sum(cantidad) as cantidadx, SUM(precio) as preciovv, id_estatus , folio_ord,fechax FROM `entradas` GROUP by folio_ord
        String query = "select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS'" +
                "   WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS' " +
                "  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' " +
                "  WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu, " +
                " (CASE   WHEN id_guisado == 1 THEN 'MOLE VERDE'   WHEN id_guisado == 2 THEN 'CARNE'" +
                "   WHEN id_guisado == 3 THEN 'PICADILLO'   WHEN id_guisado == 4 THEN 'SESOS' " +
                "  WHEN id_guisado == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado == 6 THEN 'QUESO' " +
                "  WHEN id_guisado == 7 THEN 'POLLO'   WHEN id_guisado == 8 THEN 'PAPAS CON CHORIZO'" +
                "   WHEN id_guisado == 9 THEN 'PATA'   WHEN id_guisado == 10 THEN 'TINGA DE POLLO' WHEN id_guisado == 11 THEN 'TINGA DE RES' WHEN id_guisado = 31 THEN 'FLAN' WHEN id_guisado = 32 THEN 'QUESO' WHEN id_guisado = 33 THEN 'CHOCOLATE' WHEN id_guisado = 34 THEN 'ROMPOPE' WHEN id_guisado = 35 THEN 'MOSAICO' WHEN id_guisado = 36 THEN 'OTRO SABOR'  ELSE 'error' END ) as  id_guisado," +
                " cantidad, precio, " +
                " (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord where id_user = '"+clvuser+"' and id_estatus = 2;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    //actualiazamos el estatus debido a que se ordeno ya en la base de datos central.
    Cursor notifitycountyanli(String clvuser){
        String query = "select sum(cantidad) as gex from  (select cantidad from tmp_ord where id_user = '"+clvuser+"' and id_estatus = 1 " +
                "union all " +
                "select cantidad from tmp_ord2 where id_user = '"+clvuser+"' and id_estatus = 1 " +
                "union all " +
                "select cantidad from tmp_ord3 where id_user = '"+clvuser+"' and id_estatus = 1) " +
                "as aaaa; ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    //comfirmar compra en efectivo
    Cursor Countefect(String clvuser){
        String query = "select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS' WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS'  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu,   (CASE   WHEN id_guisado == 1 THEN 'MOLE VERDE'   WHEN id_guisado == 2 THEN 'CARNE'  WHEN id_guisado == 3 THEN 'PICADILLO'   WHEN id_guisado == 4 THEN   'SESOS'   WHEN id_guisado == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado == 6 THEN 'QUESO'   WHEN id_guisado == 7 THEN 'POLLO'   WHEN id_guisado == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado == 9 THEN 'PATA'   WHEN id_guisado == 10 THEN 'TINGA DE POLLO' WHEN id_guisado == 11 THEN 'TINGA DE RES' WHEN id_guisado = 31 THEN 'FLAN' WHEN id_guisado = 32 THEN 'QUESO' WHEN id_guisado = 33 THEN 'CHOCOLATE' WHEN id_guisado = 34 THEN 'ROMPOPE' WHEN id_guisado = 35 THEN 'MOSAICO' WHEN id_guisado = 36 THEN 'OTRO SABOR'   ELSE 'error' END ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2,     cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord where id_user = '"+clvuser+"' and id_estatus = 1\n" +
                "    union all\n" +
                "    select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS' WHEN id_menu == 2 THEN 'TOSTADAS'   WHEN id_menu == 3 THEN 'PAMBAZOS'  WHEN id_menu == 4 THEN 'SOPES'   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu,   (CASE WHEN id_guisado = 31 THEN 'FLAN' WHEN id_guisado = 32 THEN 'QUESO' WHEN id_guisado = 33 THEN 'CHOCOLATE' WHEN id_guisado = 34 THEN 'ROMPOPE' WHEN id_guisado = 35 THEN 'MOSAICO' WHEN id_guisado = 36 THEN 'OTRO SABOR' ELSE 'error' END ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2, cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord2 where id_user = '"+clvuser+"' and id_estatus = 1\n" +
                "    union all\n" +
                "    select  (CASE   WHEN id_menu == 1 THEN 'DOBLADITAS ' WHEN id_menu == 2 THEN 'TOSTADAS '   WHEN id_menu == 3 THEN 'PAMBAZOS '  WHEN id_menu == 4 THEN 'SOPES '   WHEN id_menu == 5 THEN 'GELATINAS' WHEN id_menu == 6 THEN 'COMBINADAS'   ELSE 'error' END ) as  id_menu , (CASE   WHEN id_guisado == 1 THEN 'MOLE VERDE'   WHEN id_guisado == 2 THEN 'CARNE'  WHEN id_guisado == 3 THEN 'PICADILLO'   WHEN id_guisado == 4 THEN   'SESOS'   WHEN id_guisado == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado == 6 THEN 'QUESO'   WHEN id_guisado == 7 THEN 'POLLO'   WHEN id_guisado == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado == 9 THEN 'PATA'   WHEN id_guisado == 10 THEN 'TINGA DE POLLO' WHEN id_guisado == 11 THEN 'TINGA DE RES' WHEN id_guisado = 31 THEN 'FLAN' WHEN id_guisado = 32 THEN 'QUESO' WHEN id_guisado = 33 THEN 'CHOCOLATE' WHEN id_guisado = 34 THEN 'ROMPOPE' WHEN id_guisado = 35 THEN 'MOSAICO' WHEN id_guisado = 36 THEN 'OTRO SABOR'  ELSE 'error' END ) as  id_guisado,(CASE   WHEN id_guisado2 == 1 THEN 'MOLE VERDE'   WHEN id_guisado2 == 2 THEN 'CARNE'  WHEN id_guisado2 == 3 THEN 'PICADILLO'   WHEN id_guisado2 == 4 THEN   'SESOS'   WHEN id_guisado2 == 5 THEN 'CHAMPIÑONES'   WHEN id_guisado2 == 6 THEN 'QUESO'   WHEN id_guisado2 == 7 THEN 'POLLO'   WHEN id_guisado2 == 8 THEN    'PAPAS CON CHORIZO'  WHEN id_guisado2 == 9 THEN 'PATA'   WHEN id_guisado2 == 10 THEN 'TINGA DE POLLO' WHEN id_guisado2 == 11 THEN 'TINGA DE RES'   ELSE 'na' END ) as  id_guisado2,     cantidad, precio, (CASE WHEN id_estatus == 1 THEN 'ESTADO: PREORDEN' ELSE 'ESTADO: PREPARACIÓN' END ) as  id_estatus from tmp_ord3 where id_user = '"+clvuser+"' and id_estatus = 1";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

}



/*
*
*
*
*
* id_menu.add(cursor.getString(2));
                id_guisado.add(cursor.getString(3));
                cantidad.add(cursor.getString(4));
                precio.add(cursor.getString(5));
                id_estatus.add(cursor.getString(6));
                *
select
 (CASE
  WHEN id_menu == 1 THEN 'QUESADILLAS'
  WHEN id_menu == 2 THEN 'PAMBAZOS'
  WHEN id_menu == 3 THEN 'TOSTADAS'
  WHEN id_menu == 4 THEN 'SOPES'
  WHEN id_menu == 5 THEN 'COMBINADAS'
  WHEN id_menu == 6 THEN 'GELATINAS'
  ELSE 'error' END ) as  id_menu,
 (CASE
  WHEN id_guisado == 1 THEN 'MOLE VERDE'
  WHEN id_guisado == 2 THEN 'CARNE'
  WHEN id_guisado == 3 THEN 'PICADILLO'
  WHEN id_guisado == 4 THEN 'SESOS'
  WHEN id_guisado == 5 THEN 'CHAMPIÑONES'
  WHEN id_guisado == 6 THEN 'QUESO'
  WHEN id_guisado == 7 THEN 'POLLO'
  WHEN id_guisado == 8 THEN 'PAPAS CON CHORIZO'
  WHEN id_guisado == 9 THEN 'PATA'
  WHEN id_guisado == 10 THEN 'TINGA'
  ELSE 'error' END ) as  id_guisado, cantidad, precio,
 (CASE WHEN id_estatus == 1 THEN 'preregistro'ELSE 'ya ordenado'END ) as  estatus from tmp_ord;

*
*
*
*
*
*
*
*
*
*
* */
