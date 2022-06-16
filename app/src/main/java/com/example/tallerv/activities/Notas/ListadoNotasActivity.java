package com.example.tallerv.activities.Notas;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerv.Entidades.Usuario;
import com.example.tallerv.R;
import com.example.tallerv.activities.RegistroActivity;
import com.example.tallerv.adaptadores.ListaNotasAdapter;
import com.example.tallerv.repository.NotasRepository;
import com.example.tallerv.repository.UsuariosRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Optional;

public class ListadoNotasActivity extends AppCompatActivity {

    Usuario userLogged = null;
    RecyclerView listadoNotas;
    ArrayList<ListadoNotasActivity> listadoArrayNotas;
    UsuariosRepository userRepository = new UsuariosRepository();
    NotasRepository notasRepository = new NotasRepository();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        userLogged = userRepository.buscarUsuario(ListadoNotasActivity.this,
                Optional.empty(), Optional.empty(),
                Optional.of(getIntent().getLongExtra("user_id", 0))).stream().findFirst().get();
        //--

        listadoNotas = findViewById(R.id.listadoNotas);
        listadoNotas.setLayoutManager(new LinearLayoutManager(this));


        ListaNotasAdapter adapter = new ListaNotasAdapter(notasRepository.buscarNotas(ListadoNotasActivity.this,
                Optional.empty(), Optional.empty(),
                Optional.of(userLogged.getId())));

        listadoNotas.setAdapter(adapter);
        //--

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListadoNotasActivity.this, NuevaNotaActivity.class);
                i.putExtra("user_id", userLogged.getId());
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