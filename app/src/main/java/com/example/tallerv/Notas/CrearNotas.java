package com.example.tallerv.Notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tallerv.R;

public class CrearNotas extends AppCompatActivity {

    Button agregarNota, verNotasBtn;
    EditText tituloNotaTxt, localizacionNotaTxt, descripcionNotaTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notas);

        tituloNotaTxt = findViewById(R.id.tituloNotaTxt);
        localizacionNotaTxt = findViewById(R.id.localizacionNotaTxt);
        descripcionNotaTxt = findViewById(R.id.descripcionNotaTxt);
        agregarNota = findViewById(R.id.crearNotasBtn);
        verNotasBtn = findViewById(R.id.verNotasBtn);

        agregarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseNotas baseNotas = new BaseNotas(CrearNotas.this);

                long id = baseNotas.insertarNotas(tituloNotaTxt.getText().toString(), localizacionNotaTxt.getText().toString(), descripcionNotaTxt.getText().toString());

                if(!tituloNotaTxt.getText().toString().isEmpty() && !localizacionNotaTxt.getText().toString().isEmpty() && !descripcionNotaTxt.getText().toString().isEmpty()){

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, tituloNotaTxt.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, localizacionNotaTxt.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, descripcionNotaTxt.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY,true);
                    intent.putExtra(Intent.EXTRA_EMAIL,"");

                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(CrearNotas.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(CrearNotas.this,"No se guardo la nota", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //APRETAMOS VER NOTAS Y NOS LLEVA A LA PAGINA DE NOTAS CREADAS
        verNotasBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent i = new Intent(CrearNotas.this, Notas.class);
                startActivity(i);
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
