package com.example.tallerv.Usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tallerv.BaseHelper;

public class BaseUsuarios extends BaseHelper {

    private static final String TABLE_USUARIOS = "t_usuarios";
    Context context;

    public BaseUsuarios(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String usuario, String email, String contrasena, String repcontrasena){

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
}
