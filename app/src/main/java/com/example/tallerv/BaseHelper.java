package com.example.tallerv;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "tallerV.db";
    private static final String TABLE_USUARIOS = "t_usuarios";
    private static final String TABLE_NOTAS = "t_notas";


    public BaseHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "usuario TEXT NOT NULL,"+
                "email TEXT NOT NULL," +
                "contrasena TEXT NOT NULL," +
                "repcontrasena TEXT NOT NULL"+")"
        );

        db.execSQL(" CREATE TABLE " + TABLE_NOTAS+ "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "tituloNotaTxt TEXT NOT NULL," +
                "localizacionNotaTxt TEXT NOT NULL," +
                "descripcionNotaTxt TEXT NOT NULL"+")"
        );

    }
        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
            db.execSQL(" DROP TABLE " + TABLE_USUARIOS);
            onCreate(db);

            db.execSQL(" DROP TABLE " + TABLE_NOTAS);
            onCreate(db);
        }

    public boolean validarEmailContra(String email, String contrasena){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" Select * from " + TABLE_USUARIOS + " where email=? and contrasena=? ", new String[]{email,contrasena});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean validarEmail(String email){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" Select * from " + TABLE_USUARIOS + " where email=? ", new String[]{email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
