package com.example.ac2_mobile_finanas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdaptadorDeGasto adapter;
    private ArrayList<Gasto> listaGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerGastos);
        Button btnNovoGasto = findViewById(R.id.btnNovoGasto);
        Button btnResumo = findViewById(R.id.btnResumo);

        listaGastos = Database.getInstance(this).listarGastos();
        adapter = new AdaptadorDeGasto(listaGastos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnNovoGasto.setOnClickListener(v -> startActivity(new Intent(this, CadastrodeGastoActivity.class)));
        btnResumo.setOnClickListener(v -> startActivity(new Intent(this, ResumoActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaGastos.clear();
        listaGastos.addAll(Database.getInstance(this).listarGastos());
        adapter.notifyDataSetChanged();
    }
}
