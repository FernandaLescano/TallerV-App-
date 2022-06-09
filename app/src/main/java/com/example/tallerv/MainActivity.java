package com.example.tallerv;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerv.Notas.Notas;

public class MainActivity extends AppCompatActivity {

    Button btnIngresar;
    TextView compartir;
    TextView registrarse;
    BaseHelper db;
    EditText Email, Contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new BaseHelper(this);

       btnIngresar = (Button) findViewById(R.id.btnIngresar);
       registrarse = (TextView) findViewById(R.id.registrarse);
       Email = (EditText) findViewById(R.id.email);
       Contrasena = (EditText) findViewById(R.id.contrasena);
       compartir = (TextView) findViewById(R.id.compartir);

       //NOS LOGUEAMOS Y NOS LLEVA A LA SIGUIENTE PANTALLA DE NOTAS
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String contrasena = Contrasena.getText().toString();
                Boolean validarEmailContrasena = db.validarEmailContra(email,contrasena);

                if(validarEmailContrasena == true){
                    Toast.makeText(getApplicationContext(),"Usuario Correcto", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Notas.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Email o Contraseña NO Valido",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //APRETAMOS REGISTRARSE Y NOS LLEVA A LA PAGINA DE REGISTRO
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Registro.class);
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

        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}