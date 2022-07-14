package com.example.navegacaoentretelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObjetoAdapter extends RecyclerView.Adapter<ObjetoAdapter.ViewHolder> {
    private ArrayList<Objeto> objetos;

    public ObjetoAdapter(Context context, ArrayList<Objeto> lista){
        objetos = lista;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNome, tvDescricao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
        }
    }


    @NonNull
    @Override
    public ObjetoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_itens, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjetoAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(objetos.get(position));

        holder.tvNome.setText(objetos.get(position).getNome()+" (id "+objetos.get(position).getId()+")");
        holder.tvDescricao.setText(objetos.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return objetos.size();
    }
}
