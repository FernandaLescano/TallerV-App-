package com.example.tallerv.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.tallerv.Entidades.Nota;
import com.example.tallerv.Entidades.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class NotasRepository{

    private static final String TABLE_NOTAS = "t_notas";
    Context context;

    /*public Nota save(Nota nota, Context context){
        nota.setId(new BaseHelper(context).getWritableDatabase().insert( TABLE_NOTAS, null, notaToContentValue(nota)));
        return nota;
    }*/

    public Nota save(Nota nota, Context context){
        nota.setId(getDatabase(context).insert( TABLE_NOTAS, null, notaToContentValue(nota)));
        return nota;
    }

    private static SQLiteDatabase getDatabase(Context context) {
        return new BaseHelper(context).getWritableDatabase();
    }

    @NonNull
    private ContentValues notaToContentValue(Nota nota) {
        ContentValues values = new ContentValues();
        values.put("tituloNotaTxt", nota.getTituloNotaTxt());
        values.put("localizacionNotaTxt", nota.getLocalizacionNotaTxt());
        values.put("descripcionNotaTxt", nota.getDescripcionNotaTxt());
        values.put("fechaNotaTxt", nota.getFechaNotaTxt());
        values.put("user_id",nota.getUser_id());
        return values;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Nota> buscarNotas(Context context, Optional<String> tituloNotaTxt, Optional<Long> id, Optional<Long> userId){
        StringBuilder query = new StringBuilder("select * from " + TABLE_NOTAS + " where 1=1 ");
        List<String> parameters = new ArrayList<>();


        id.ifPresent(i->{
            query.append(" and id = ? ");
            parameters.add(i.toString());
        });

        tituloNotaTxt.ifPresent(e->{
            query.append(" and tituloNotaTxt = ? ");
            parameters.add(e);
        });

        userId.ifPresent(i->{
            query.append(" and user_id = ? ");
            parameters.add(i.toString());
        });

        return getNotasFromCursor(getDatabase(context).rawQuery(query.toString(), parameters.toArray(new String[parameters.size()])));
    }

    private List<Nota> getNotasFromCursor(Cursor cursor) {
        List<Nota> notas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                notas.add(cursorToNota(cursor));
            }while(cursor.moveToNext());
        }
        return notas;
    }
    private Nota cursorToNota(Cursor cursor) {
        Nota nota = new Nota();

        nota.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
        nota.setTituloNotaTxt(cursor.getString(cursor.getColumnIndexOrThrow("tituloNotaTxt")));
        nota.setLocalizacionNotaTxt(cursor.getString(cursor.getColumnIndexOrThrow("localizacionNotaTxt")));
        nota.setDescripcionNotaTxt(cursor.getString(cursor.getColumnIndexOrThrow("descripcionNotaTxt")));
        nota.setFechaNotaTxt(cursor.getString(cursor.getColumnIndexOrThrow("fechaNotaTxt")));
        nota.setUser_id(cursor.getLong(cursor.getColumnIndexOrThrow("user_id")));

        return nota;
    }
}