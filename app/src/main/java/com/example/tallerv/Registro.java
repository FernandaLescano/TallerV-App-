package com.example.tallerv;

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

import com.example.tallerv.Usuarios.BaseUsuarios;

public class Registro extends AppCompatActivity {

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1000;
    Button cancelabtn, registrabtn, abrirCamaraBtn;
    EditText email, contrasena, usuario, repcontrasena;
    ImageView imagenRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        BaseHelper db = new BaseHelper(this);

        abrirCamaraBtn = (Button) findViewById(R.id.abrirCamaraBtn);
        imagenRegistro = (ImageView) findViewById(R.id.imagenRegistro);

        cancelabtn = (Button) findViewById(R.id.cancelabtn);
        registrabtn = (Button) findViewById(R.id.registrabtn);

        usuario = (EditText) findViewById(R.id.usuario) ;
        email = (EditText) findViewById(R.id.email);
        contrasena = (EditText) findViewById(R.id.contrasena);
        repcontrasena = (EditText) findViewById(R.id.repcontrasena);


        cancelabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        registrabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseUsuarios baseUsuarios = new BaseUsuarios(Registro.this);

                long id = baseUsuarios.insertarUsuario(usuario.getText().toString(),email.getText().toString(),contrasena.getText().toString(), repcontrasena.getText().toString());

                if(!usuario.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !contrasena.getText().toString().isEmpty() && !repcontrasena.getText().toString().isEmpty()){
                    Toast.makeText(Registro.this, "Usuario Guardado", Toast.LENGTH_SHORT).show();
                }
               else{
                    Toast.makeText(Registro.this,"Error, falta completar campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        abrirCamaraBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /* LIMPIAR REGISTRO UNA VEZ GUARDADO
    private void limpiar (){

        etNombreClientes.setText("");
        etCuitClientes.setText("");
        etDireccionClientes.setText("");
        etTelefonoClientes.setText("");

    }*/

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