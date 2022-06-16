package com.example.tallerv.Notas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerv.R;

import java.util.Calendar;

public class CrearNotas extends AppCompatActivity {

    Button agregarNota;
    EditText tituloNotaTxt, localizacionNotaTxt, descripcionNotaTxt;
    EditText fechaNotaTxt;

    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notas);

        tituloNotaTxt = findViewById(R.id.tituloNotaTxt);
        localizacionNotaTxt = findViewById(R.id.localizacionNotaTxt);
        descripcionNotaTxt = findViewById(R.id.descripcionNotaTxt);
        agregarNota = findViewById(R.id.crearNotasBtn);
        fechaNotaTxt = findViewById(R.id.fechaNotaTxt);

        agregarNota.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                BaseNotas baseNotas = new BaseNotas(CrearNotas.this);

                if(!tituloNotaTxt.getText().toString().isEmpty() && !localizacionNotaTxt.getText().toString().isEmpty() && !descripcionNotaTxt.getText().toString().isEmpty() ){
                long id = baseNotas.insertarNotas(tituloNotaTxt.getText().toString(), localizacionNotaTxt.getText().toString(), descripcionNotaTxt.getText().toString(), fechaNotaTxt.getText().toString());
                    Toast.makeText(CrearNotas.this, "Nota guardada", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CrearNotas.this, Notas.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(CrearNotas.this,"No se guardo la nota", Toast.LENGTH_SHORT).show();
                }
                //ABRIMOS GOOGLE CALENDAR
                /*if(!tituloNotaTxt.getText().toString().isEmpty() && !localizacionNotaTxt.getText().toString().isEmpty() && !descripcionNotaTxt.getText().toString().isEmpty() ){

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
                }*/

                    }
                });


        fechaNotaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int anio = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(CrearNotas.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int dia, int mes, int anio) {
                        fechaNotaTxt.setText(dia + "/" + (mes+1) + "/" + anio);
                    }
                },dia,mes,anio);
                dpd.show();
            }
        });



        //APRETAMOS VER NOTAS Y NOS LLEVA A LA PAGINA DE NOTAS CREADAS
        /*verNotasBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent i = new Intent(CrearNotas.this, Notas.class);
                startActivity(i);
            }
        });*/



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
