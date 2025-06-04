package com.example.cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancodeDados extends SQLiteOpenHelper {
    private static BancodeDados instance = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BancoInteracao.sqlite";

    private Context context;

    public BancodeDados(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context.getApplicationContext();
    }

    public static synchronized BancodeDados getInstance(Context context) {
        if (instance == null) {
            instance = new BancodeDados(context);
        }
        return instance;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "IdCli INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NomeCli TEXT NOT NULL, " +
                "Telefone TEXT)";
        db.execSQL(query1);

        String query2 = "CREATE TABLE IF NOT EXISTS ContaLanchonete (" +
                "IdConta INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "IdCli INTEGER, " +
                "DataAbertura TEXT DEFAULT (datetime('now')), " +
                "ValorTotal REAL NOT NULL, " +
                "FOREIGN KEY (IdCli) REFERENCES Cliente(IdCli))";
        db.execSQL(query2);

        String query3 = "CREATE TABLE IF NOT EXISTS ItensConta (" +
                "IdItem INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "IdConta INTEGER, " +
                "Descricao TEXT NOT NULL, " +
                "Quantidade INTEGER NOT NULL, " +
                "PrecoUni REAL NOT NULL, " +
                "FOREIGN KEY (IdConta) REFERENCES ContaLanchonete(IdConta))";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ItensConta");
        db.execSQL("DROP TABLE IF EXISTS ContaLanchonete");
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        onCreate(db);
    }

    public long insertCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NomeCli", cliente.getNomeCli());
        values.put("Telefone", cliente.getTelefone());

        long result = db.insert("Cliente", null, values);
        db.close();
        return result;
    }

    public long insertContaLanchonete(ContaLanchonete contaLanchonete) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DataAbertura", contaLanchonete.getDataAbertura());
        values.put("ValorTotal", contaLanchonete.getValorTotal());

        long result = db.insert("ContaLanchonete", null, values);
        db.close();
        return result;
    }

    public long insertItensConta(ItensConta itensConta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IdConta", itensConta.getIdConta());
        values.put("Descricao", itensConta.getDescricao());
        values.put("Quantidade", itensConta.getQuantidade());
        values.put("PrecoUni", itensConta.getPrecoUni());

        long result = db.insert("ItensConta", null, values);
        db.close();
        return result;
    }
}
