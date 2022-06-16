package com.example.tallerv.activities.Notas;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerv.R;

public class NotaCreadaActivity extends AppCompatActivity {

    Button recordatorio, irNotas;
    EditText tituloNotaTxt, localizacionNotaTxt, descripcionNotaTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_creada);

        recordatorio = findViewById(R.id.recordatorio);
        irNotas = findViewById(R.id.irNotas);

        tituloNotaTxt = findViewById(R.id.tituloNotaTxt);
        localizacionNotaTxt = findViewById(R.id.localizacionNotaTxt);
        descripcionNotaTxt = findViewById(R.id.descripcionNotaTxt);


        recordatorio.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                //ABRIMOS GOOGLE CALENDAR
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, NuevaNotaActivity.tituloNotaTxt.getText().toString());
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, NuevaNotaActivity.localizacionNotaTxt.getText().toString());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, NuevaNotaActivity.descripcionNotaTxt.getText().toString());
                intent.putExtra(CalendarContract.Events.ALL_DAY,true);
                startActivity(intent);

                //ABRIMOS GOOGLE CALENDAR
                /*if(!CrearNotas.tituloNotaTxt.getText().toString().isEmpty() && !CrearNotas.localizacionNotaTxt.getText().toString().isEmpty() && !CrearNotas.descripcionNotaTxt.getText().toString().isEmpty() ){

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
                        Toast.makeText(NotaCreada.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(NotaCreada.this,"No se guardo la nota", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        //APRETAMOS EL BOTON Y NOS LLEVA A LA PAGINA DE MIS NOTAS
        irNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotaCreadaActivity.this, ListadoNotasActivity.class);
                startActivity(i);
            }
        });
    }
}