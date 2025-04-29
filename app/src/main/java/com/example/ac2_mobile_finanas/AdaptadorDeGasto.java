package com.example.ac2_mobile_finanas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdaptadorDeGasto extends RecyclerView.Adapter<AdaptadorDeGasto.GastoViewHolder> {
    private final ArrayList<Gasto> lista;

    public AdaptadorDeGasto(ArrayList<Gasto> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public GastoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gasto, parent, false);
        return new GastoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoViewHolder holder, int position) {
        Gasto gasto = lista.get(position);
        holder.txtDescricao.setText(gasto.getDescricao());
        holder.txtValor.setText(String.format("R$ %.2f", gasto.getValor()));
        holder.txtCategoria.setText(gasto.getCategoria());
        holder.txtData.setText(gasto.getData());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class GastoViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescricao, txtValor, txtCategoria, txtData;

        public GastoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtValor = itemView.findViewById(R.id.txtValor);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtData = itemView.findViewById(R.id.txtData);
        }
    }
}
