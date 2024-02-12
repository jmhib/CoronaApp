package com.example.coronaapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronaapp.R;
import com.example.coronaapp.modelo.Tabla;

import java.util.List;

public class TablaAdapter extends RecyclerView.Adapter<TablaAdapter.TablaHolder>{
    List<Tabla> lista;
    int layout;
    Activity activity;

    public TablaAdapter(List<Tabla> lista, int layout, Activity activity) {
        this.lista = lista;
        this.layout = layout;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TablaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).inflate(layout, parent, false);

        return new TablaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TablaHolder holder, int position) {
        Tabla tabla = lista.get(position);
        holder.txtid.setText(tabla.getId());
        holder.txtincidencia14.setText(String.valueOf(tabla.getIncidencia14()));
        holder.txtincidencia7.setText(String.valueOf(tabla.getIncidencia7()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class TablaHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtincidencia14, txtincidencia7;

        public TablaHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.item_id);
            txtincidencia14 = itemView.findViewById(R.id.item_incidencia14);
            txtincidencia7 = itemView.findViewById(R.id.item_incidencia7);
        }
    }

}
