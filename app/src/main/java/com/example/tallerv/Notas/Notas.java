package com.example.tallerv.Notas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tallerv.R;
import com.example.tallerv.adaptadores.ListaNotasAdapter;

import java.util.ArrayList;

public class Notas extends AppCompatActivity {

    RecyclerView listadoNotas;
    ArrayList<Notas> listadoArrayNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        //--TODO:

        listadoNotas = findViewById(R.id.listadoNotas);
        listadoNotas.setLayoutManager(new LinearLayoutManager(this));

        BaseNotas baseNotas = new BaseNotas ( Notas.this);

        listadoArrayNotas = new ArrayList<>();

        ListaNotasAdapter adapter = new ListaNotasAdapter( baseNotas.mostrarNotas());
        listadoNotas.setAdapter(adapter);
        //--


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