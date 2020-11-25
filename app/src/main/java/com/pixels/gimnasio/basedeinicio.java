package com.pixels.gimnasio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class basedeinicio extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="basededatosI";
    private static final int VERSION_BD=1;
    private static final String TABLA_INICIO="CREATE TABLE INICIO(ID TEXT PRIMARY KEY,CODIGO TEXT,USUARIO TEXT, CONTRASEÑA TEXT,TIPO TEXT)";

    public basedeinicio(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);


    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TABLA_INICIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_INICIO);
        sqLiteDatabase.execSQL(TABLA_INICIO);
    }


    public void agregarE(String id,String codigo,String usuario,String contraseña,String tipo){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            bd.execSQL("INSERT INTO INICIO VALUES('"+id+"','"+codigo+"','"+usuario+"','"+contraseña+"','"+tipo+"')");
            bd.close();
        }
    }


    public void buscu(usa datos,String id){
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM INICIO WHERE ID='"+id+"'",null);

        if(cursor.moveToFirst()){
            do{
                datos.setContraseña(cursor.getString(1));
                datos.setNombre(cursor.getString(2));
                datos.setNombre(cursor.getString(3));




            }while(cursor.moveToNext());
        }


    }
    public List<usa> obtusur(){
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM INICIO",null);
        List<usa> usurr=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                usurr.add(new usa(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));


            }while(cursor.moveToNext());
        }
        return usurr;

    }
    public void inic(String id,String codigo,String usuario,String contraseña,String tipo){

        SQLiteDatabase bd=getWritableDatabase();

        if(bd!=null)

        {

            bd.execSQL("UPDATE INICIO SET CODIGO='"+codigo+"',USUARIO='"+usuario+"',CONTRASEÑA='"+contraseña+"',TIPO='"+tipo+"' WHERE ID='"+id+"'");

            bd.close();

        }

    }

}
