package com.example.ac2_mobile_finanas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class CadastrodeGastoActivity extends AppCompatActivity {
    private EditText editDescricao, editValor, editData;
    private Spinner spinnerCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gasto);

        editDescricao = findViewById(R.id.editDescricao);
        editValor = findViewById(R.id.editValor);
        editData = findViewById(R.id.editData);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        Button btnSalvar = findViewById(R.id.btnSalvar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        editData.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(CadastrodeGastoActivity.this, (view, year, month, dayOfMonth) ->
                    editData.setText(String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)),
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnSalvar.setOnClickListener(v -> {
            String descricao = editDescricao.getText().toString();
            double valor = Double.parseDouble(editValor.getText().toString());
            String categoria = spinnerCategoria.getSelectedItem().toString();
            String data = editData.getText().toString();

            Gasto novoGasto = new Gasto(descricao, valor, categoria, data);
            Database.getInstance(this).inserirGasto(novoGasto);
            Toast.makeText(this, "Gasto salvo!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

