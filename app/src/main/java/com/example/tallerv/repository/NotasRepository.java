package com.example.tallerv.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tallerv.Entidades.Nota;

import java.util.ArrayList;

public class NotasRepository extends BaseHelper {

    private static final String TABLE_NOTAS = "t_notas";
    Context context;

    public NotasRepository(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long insertarNotas(String tituloNotaTxt, String localizacionNotaTxt, String descripcionNotaTxt, String fechaNotaTxt){

        long id = 0;
        try{
            BaseHelper baseHelper = new BaseHelper(context);
            SQLiteDatabase db = baseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("tituloNotaTxt",tituloNotaTxt);
            values.put("localizacionNotaTxt",localizacionNotaTxt);
            values.put("descripcionNotaTxt",descripcionNotaTxt);
            values.put("fechaNotaTxt",fechaNotaTxt);

            id = db.insert( TABLE_NOTAS, null, values);

        } catch(Exception ex){
            ex.toString();
        }
        return id;
    }

    //----

    public ArrayList<Nota> mostrarNotas(){
        BaseHelper baseHelper = new BaseHelper(context);
        SQLiteDatabase db = baseHelper.getWritableDatabase();

        ArrayList<Nota> listaNotas = new ArrayList<>();

        Nota crearNotasE = null;
        Cursor cursorNotas =null;

        cursorNotas = db.rawQuery("SELECT * FROM " + TABLE_NOTAS, null);

        if(cursorNotas.moveToFirst()){

            do{
                crearNotasE = new Nota();

                crearNotasE.setId(cursorNotas.getInt(0));
                crearNotasE.setTituloNotaTxt(cursorNotas.getString(1));
                crearNotasE.setLocalizacionNotaTxt(cursorNotas.getString(2));
                crearNotasE.setDescripcionNotaTxt(cursorNotas.getString(3));
                crearNotasE.setFechaNotaTxt(cursorNotas.getString(4));
                listaNotas.add(crearNotasE);

            }while(cursorNotas.moveToNext());


        }
        cursorNotas.close();
        return listaNotas;
    }
    //---

}