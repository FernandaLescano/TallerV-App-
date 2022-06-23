package com.example.tallerv.activities.Notas;

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

import com.example.tallerv.Entidades.Nota;
import com.example.tallerv.Entidades.Usuario;
import com.example.tallerv.R;
import com.example.tallerv.repository.NotasRepository;
import com.example.tallerv.repository.UsuariosRepository;

import java.util.Calendar;
import java.util.Optional;

public class NuevaNotaActivity extends AppCompatActivity {

    Button agregarNota;
    static EditText tituloNotaTxt, localizacionNotaTxt, descripcionNotaTxt;
    static EditText fechaNotaTxt;
    UsuariosRepository usuariosRepository = new UsuariosRepository();
    NotasRepository notasRepository = new NotasRepository();
    Calendar c;
    DatePickerDialog dpd;
    private Usuario userLogged;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notas);
        userLogged = usuariosRepository.buscarUsuario(NuevaNotaActivity.this,
                Optional.empty(), Optional.empty(),
                Optional.of(getIntent().getLongExtra("user_id", 0))).stream().findFirst().get();

        tituloNotaTxt = findViewById(R.id.tituloNotaTxt);
        localizacionNotaTxt = findViewById(R.id.localizacionNotaTxt);
        descripcionNotaTxt = findViewById(R.id.descripcionNotaTxt);
        agregarNota = findViewById(R.id.crearNotasBtn);
        fechaNotaTxt = findViewById(R.id.fechaNotaTxt);

        agregarNota.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(!tituloNotaTxt.getText().toString().isEmpty() && !localizacionNotaTxt.getText().toString().isEmpty() && !descripcionNotaTxt.getText().toString().isEmpty() ){
                    notasRepository.save(getNota(), NuevaNotaActivity.this);
                    Toast.makeText(NuevaNotaActivity.this, "Nota guardada", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NuevaNotaActivity.this, NotaCreadaActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(NuevaNotaActivity.this,"No se guardo la nota", Toast.LENGTH_SHORT).show();
                }
                    }
                });

        fechaNotaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int anio = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(NuevaNotaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int dia, int mes, int anio) {
                        fechaNotaTxt.setText(dia + "/" + (mes+1) + "/" + anio);
                    }
                },anio,mes,dia);
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

    private Nota getNota() {
        Nota nota = new Nota();
        //tituloNotaTxt.getText().toString(), localizacionNotaTxt.getText().toString(), descripcionNotaTxt.getText().toString(), fechaNotaTxt.getText().toString()

        //nota.setUser_id(userLogged.getId());
        nota.setUser_id(userLogged.getId());
        nota.setTituloNotaTxt(tituloNotaTxt.getText().toString());
        nota.setLocalizacionNotaTxt(localizacionNotaTxt.getText().toString());
        nota.setDescripcionNotaTxt(descripcionNotaTxt.getText().toString());
        nota.setFechaNotaTxt(fechaNotaTxt.getText().toString());
        return  nota;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
