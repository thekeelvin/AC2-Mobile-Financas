package com.example.ac2_mobile_finanas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;


public class ResumoActivity extends AppCompatActivity {
    private TextView txtResumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        txtResumo = findViewById(R.id.txtResumo);

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            StringBuilder resultado = new StringBuilder();
            HashMap<String, Double> totais = new HashMap<>();
            double totalMes = 0;
            String maiorCategoria = "";
            double maiorValor = 0;

            for (Gasto gasto : Database.getInstance(this).listarGastos()) {
                double acumulado = totais.getOrDefault(gasto.getCategoria(), 0.0) + gasto.getValor();
                totais.put(gasto.getCategoria(), acumulado);
                totalMes += gasto.getValor();
                if (acumulado > maiorValor) {
                    maiorValor = acumulado;
                    maiorCategoria = gasto.getCategoria();
                }
            }

            for (String categoria : totais.keySet()) {
                resultado.append(categoria).append(": R$ ").append(totais.get(categoria)).append("\n");
            }
            resultado.append("\nTotal do mês: R$ ").append(totalMes);
            resultado.append("\nCategoria com maior gasto: ").append(maiorCategoria);

            runOnUiThread(() -> txtResumo.setText(resultado.toString()));
        }).start();
    }
}