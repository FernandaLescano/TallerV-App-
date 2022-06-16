package com.example.tallerv.activities;

import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerv.Entidades.Usuario;
import com.example.tallerv.activities.Notas.ListadoNotasActivity;
import com.example.tallerv.R;
import com.example.tallerv.repository.BaseHelper;
import com.example.tallerv.repository.UsuariosRepository;

import java.util.Collection;
import java.util.Optional;

public class LoginActivity extends AppCompatActivity {

    Button btnIngresar;
    TextView compartir;
    Button registrarse;
    UsuariosRepository repository = new UsuariosRepository();
    EditText Email, Contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btnIngresar = (Button) findViewById(R.id.btnIngresar);
       registrarse = (Button) findViewById(R.id.registrarse);
       Email = (EditText) findViewById(R.id.email);
       Contrasena = (EditText) findViewById(R.id.contrasena);
       compartir = (TextView) findViewById(R.id.compartir);


       //NOS LOGUEAMOS Y NOS LLEVA A LA SIGUIENTE PANTALLA DE NOTAS
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String contrasena = Contrasena.getText().toString();
                Collection<Usuario> usuarios = repository.buscarUsuario(LoginActivity.this, Optional.of(email), Optional.of(contrasena), Optional.empty());

                if(!usuarios.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Usuario Correcto", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(LoginActivity.this, ListadoNotasActivity.class);
                    i.putExtra("user_id", usuarios.stream().findFirst().get().getId());
                    startActivity(i);
                } else{
                    Toast.makeText(getApplicationContext(),"Email o Contraseña NO Valido",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //APRETAMOS EL BOTON REGISTRARSE Y NOS LLEVA A LA PAGINA DE REGISTRO
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });

        //BOTON PARA COMPARTIR
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent (Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Te invito a usar la App de Taller V";
                String shareSub = "Taller V App";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent,"Taller V App"));
            }
        });

        /*if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }*/
}