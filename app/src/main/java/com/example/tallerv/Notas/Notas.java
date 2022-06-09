package com.example.tallerv.Notas;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerv.R;
import com.example.tallerv.adaptadores.ListaNotasAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Notas extends AppCompatActivity {

    RecyclerView listadoNotas;
    ArrayList<Notas> listadoArrayNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        //--

        listadoNotas = findViewById(R.id.listadoNotas);
        listadoNotas.setLayoutManager(new LinearLayoutManager(this));

        BaseNotas baseNotas = new BaseNotas ( Notas.this);

        listadoArrayNotas = new ArrayList<>();

        ListaNotasAdapter adapter = new ListaNotasAdapter( baseNotas.mostrarNotas());
        listadoNotas.setAdapter(adapter);
        //--

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notas.this, CrearNotas.class));
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