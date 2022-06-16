package com.example.tallerv.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UsuariosRepository extends BaseHelper {

    private static final String TABLE_USUARIOS = "t_usuarios";
    private static Context context;


    public UsuariosRepository(@Nullable Context context){
        super(context);
        this.context = context;
    }

        public static long insertarUsuario(String usuario, String email, String contrasena, String repcontrasena){

        long id = 0;
        try{
            BaseHelper baseHelper = new BaseHelper(context);
            SQLiteDatabase db = baseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("usuario",usuario);
            values.put("email",email);
            values.put("contrasena",contrasena);
            values.put("repcontrasena",repcontrasena);

            id = db.insert( TABLE_USUARIOS, null, values);

        } catch(Exception ex){
                ex.toString();
        }
        return id;
    }

    public boolean validarEmailContra(String email, String contrasena){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" Select * from " + TABLE_USUARIOS + " where email=? and contrasena=? ", new String[]{email,contrasena});
        return cursor.getCount()>0;
    }



    public boolean validarEmail(String emailReg){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" Select * from " + TABLE_USUARIOS +" where email=? ", new String[]{emailReg});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //--

    public ArrayList<String> Listado(String email){

        ArrayList<String> datos = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        //String sql = "select id from "+ TABLE_USUARIOS +" where email=? ", new String[]{email};
        Cursor c = db.rawQuery("select id from "+ TABLE_USUARIOS +" where email=? ", new String[]{email});
        if(c.moveToFirst()){
            do{
                String linea = c.getInt(0)+"";
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
    }
}
