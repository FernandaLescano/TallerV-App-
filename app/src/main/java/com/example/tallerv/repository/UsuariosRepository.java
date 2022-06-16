package com.example.tallerv.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.tallerv.Entidades.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UsuariosRepository {

    private static final String TABLE_USUARIOS = "t_usuarios";

    public Usuario save(Usuario usuario, Context context){
        usuario.setId(getDatabase(context).insert( TABLE_USUARIOS, null, userToContentValues(usuario)));
        return usuario;
    }

    private static SQLiteDatabase getDatabase(Context context) {
        return new BaseHelper(context).getWritableDatabase();
    }

    @NonNull
    private static ContentValues userToContentValues(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("usuario", usuario.getUsuario());
        values.put("email", usuario.getEmail());
        values.put("contrasena", usuario.getContrasena());
        return values;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Collection<Usuario> buscarUsuario(Context context, Optional<String> email, Optional<String> contrasena, Optional<Long> id){
        StringBuilder query = new StringBuilder("select * from " + TABLE_USUARIOS + " where 1=1 ");
        List<String> parameters = new ArrayList<>();

        email.ifPresent(e->{
            query.append(" and email = ? ");
            parameters.add(e);
        });

        contrasena.ifPresent(c->{
            query.append(" and contrasena = ? ");
            parameters.add(c);
        });

        id.ifPresent(i->{
            query.append(" and id = ?");
            parameters.add(i.toString());
        });

        return getUsersFromCursor(getDatabase(context).rawQuery(query.toString(), parameters.toArray(new String[parameters.size()])));
    }

    private Collection<Usuario> getUsersFromCursor(Cursor cursor) {
        Collection<Usuario> usuarios = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                usuarios.add(cursorToUser(cursor));
            }while(cursor.moveToNext());
        }
        return usuarios;
    }

    private Usuario cursorToUser(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setUsuario(cursor.getString(cursor.getColumnIndexOrThrow("usuario")));
        usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
        usuario.setContrasena(cursor.getString(cursor.getColumnIndexOrThrow("contrasena")));
        usuario.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
        return usuario;
    }
}
