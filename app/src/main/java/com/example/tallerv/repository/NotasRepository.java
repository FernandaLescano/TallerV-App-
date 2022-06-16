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


    public Nota save(Nota nota, Context context){
        nota.setId(new BaseHelper(context).getWritableDatabase().insert( TABLE_NOTAS, null, notaToContentValue(nota)));
        return nota;
    }

    @NonNull
    private ContentValues notaToContentValue(Nota nota) {
        ContentValues values = new ContentValues();

        values.put("tituloNotaTxt", nota.getTituloNotaTxt());
        values.put("localizacionNotaTxt", nota.getLocalizacionNotaTxt());
        values.put("descripcionNotaTxt", nota.getDescripcionNotaTxt());
        values.put("fechaNotaTxt", nota.getFechaNotaTxt());
        return values;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Nota> buscarNotas(Context context, Optional<String> titulo, Optional<Long> id, Optional<Long> userId){
        StringBuilder query = new StringBuilder("select * from " + TABLE_NOTAS + " where 1=1 ");
        List<String> parameters = new ArrayList<>();

        titulo.ifPresent(e->{
            query.append(" and titulo = ? ");
            parameters.add(e);
        });

        id.ifPresent(i->{
            query.append(" and id = ? ");
            parameters.add(i.toString());
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
        Nota usuario = new Nota();
        usuario.setTituloNotaTxt(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        //TODO: completar
        return usuario;
    }
    //---
    private static SQLiteDatabase getDatabase(Context context) {
        return new BaseHelper(context).getWritableDatabase();
    }

}