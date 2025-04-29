package com.example.ac2_mobile_finanas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "financas.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_GASTOS = "gastos";

    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context.getApplicationContext());
        }
        return instance;
    }

    private Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_GASTOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT, valor REAL, categoria TEXT, data TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GASTOS);
        onCreate(db);
    }

    public void inserirGasto(Gasto gasto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descricao", gasto.getDescricao());
        values.put("valor", gasto.getValor());
        values.put("categoria", gasto.getCategoria());
        values.put("data", gasto.getData());
        db.insert(TABLE_GASTOS, null, values);
    }

    public ArrayList<Gasto> listarGastos() {
        ArrayList<Gasto> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_GASTOS, null, null, null, null, null, "data DESC");
        while (cursor.moveToNext()) {
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
            double valor = cursor.getDouble(cursor.getColumnIndexOrThrow("valor"));
            String categoria = cursor.getString(cursor.getColumnIndexOrThrow("categoria"));
            String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
            lista.add(new Gasto(descricao, valor, categoria, data));
        }
        cursor.close();
        return lista;
    }
}
