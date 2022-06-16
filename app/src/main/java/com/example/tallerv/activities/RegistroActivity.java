package com.example.tallerv.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerv.Entidades.Usuario;
import com.example.tallerv.activities.Notas.ListadoNotasActivity;
import com.example.tallerv.R;
import com.example.tallerv.repository.BaseHelper;
import com.example.tallerv.repository.UsuariosRepository;

import java.util.Collection;
import java.util.Optional;

public class RegistroActivity extends AppCompatActivity {

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1000;

    private UsuariosRepository repository = new UsuariosRepository();

    private Button cancelabtn, registrabtn;
    private EditText email;
    private EditText contrasena;
    private EditText usuario;
    private EditText repcontrasena;
    private ImageView imagenRegistro;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializarCampos();
        inicializarEventos();

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void inicializarEventos() {
        cancelabtn.setOnClickListener(e -> finish());
        registrabtn.setOnClickListener(e-> registrarUsuario());
        imagenRegistro.setOnClickListener(e-> abrirCamara());
    }

    private void inicializarCampos() {
        imagenRegistro = (ImageView) findViewById(R.id.imagenRegistro);
        cancelabtn = (Button) findViewById(R.id.cancelabtn);
        registrabtn = (Button) findViewById(R.id.registrabtn);
        usuario = (EditText) findViewById(R.id.usuario) ;
        email = (EditText) findViewById(R.id.email);
        contrasena = (EditText) findViewById(R.id.contrasena);
        repcontrasena = (EditText) findViewById(R.id.repcontrasena);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void registrarUsuario() {
        String emailReg = email.getText().toString();
        if(isFormComplete()) {
            if (!repository.buscarUsuario(RegistroActivity.this, Optional.of(emailReg), Optional.empty(), Optional.empty()).isEmpty()) {
                guardarUsuario();
            } else {
                Toast.makeText(RegistroActivity.this, "El email ya existe", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void guardarUsuario() {
        Usuario usuario = repository.save(getUserToForm(), RegistroActivity.this);
        Toast.makeText(RegistroActivity.this, "Usuario Guardado", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RegistroActivity.this, ListadoNotasActivity.class);
        i.putExtra("user_id", usuario.getId());
        startActivity(i);
    }

    private Usuario getUserToForm() {
        Usuario user = new Usuario();
        user.setEmail(email.getText().toString());
        user.setUsuario(usuario.getText().toString());
        user.setContrasena(contrasena.getText().toString());
        return  user;
    }

    private boolean isFormComplete() {
        if(!repcontrasena.getText().toString().equals(contrasena.getText().toString())) {
            Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean complete = usuario.getText().toString().isEmpty()
                || email.getText().toString().isEmpty()
                || contrasena.getText().toString().isEmpty()
                || repcontrasena.getText().toString().isEmpty();

        if(!complete){
            Toast.makeText(RegistroActivity.this,"Error, falta completar campos", Toast.LENGTH_SHORT).show();
        }
        return complete;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //LIMPIAR REGISTRO
    private void limpiar (){
        usuario.setText("");
        email.setText("");
        contrasena.setText("");
        repcontrasena.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void abrirCamara(){
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }
        else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imagenRegistro.setImageBitmap(bitmap);
        }
    }
}