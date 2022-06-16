package com.example.tallerv.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerv.Entidades.Nota;
import com.example.tallerv.R;

import java.util.ArrayList;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotaViewHolder> {

    ArrayList<Nota> listadoNotas;

    public ListaNotasAdapter(ArrayList<Nota> listadoNotas){

        this.listadoNotas = listadoNotas;
    }

    @NonNull
    @Override

    //CREAMOS CADA ELEMENTO DE LA LISTA
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_nota, null, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaNotasAdapter.NotaViewHolder holder, int position) {

        holder.viewTituloNota.setText(listadoNotas.get(position).getTituloNotaTxt());
        holder.viewLocalizacionNota.setText(listadoNotas.get(position).getLocalizacionNotaTxt());
        holder.viewDescripcionNota.setText(listadoNotas.get(position).getDescripcionNotaTxt());
        holder.viewFechaNota.setText(listadoNotas.get(position).getFechaNotaTxt());



    }

    @Override
    public int getItemCount() {
        return listadoNotas.size();

    }

    public class NotaViewHolder extends RecyclerView.ViewHolder {

        TextView viewTituloNota;
        TextView viewLocalizacionNota;
        TextView viewDescripcionNota;
        TextView viewFechaNota;



        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewTituloNota = itemView.findViewById(R.id.viewTituloNota);
            viewLocalizacionNota = itemView.findViewById(R.id.viewLocalizacionNota);
            viewDescripcionNota = itemView.findViewById(R.id.viewDescripcionNota);
            viewFechaNota = itemView.findViewById(R.id.viewFechaNota);


            //MODIFICAR TODO:


          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetalleNotas.class);

                    intent.putExtra("ID",listadoNotas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            }); */

        }
    }
}
