package com.example.tallerv.repository;

import android.content.Context;
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
                "contrasena TEXT NOT NULL "+")"
        );

        db.execSQL(" CREATE TABLE " + TABLE_NOTAS+ "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "tituloNotaTxt TEXT NOT NULL," +
                "localizacionNotaTxt TEXT NOT NULL," +
                "descripcionNotaTxt TEXT NOT NULL,"+
                "fechaNotaTxt TEXT NOT NULL,"+
                "user_id INTEGER NOT NULL "+")"
        );

    }
        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
            db.execSQL(" DROP TABLE " + TABLE_USUARIOS);
            db.execSQL(" DROP TABLE " + TABLE_NOTAS);
            onCreate(db);
        }
}
