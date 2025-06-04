package com.example.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etTelefone;
    private Button btnCadastrar;

    private BancodeDados bd;  // Banco de dados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        bd = BancodeDados.Sharedinstance(this);  // Inicializa o banco de dados

        btnCadastrar.setOnClickListener(v -> {
            adicionarCliente();
        });
    }

    // ✅ Método fora do onCreate
    public void adicionarCliente() {
        String nome = etNome.getText().toString();
        String telefone = etTelefone.getText().toString();

        if (nome.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente cliente = new Cliente(nome, telefone);

        long aux = bd.insertCliente(cliente);

        if (aux > 0) {
            Toast.makeText(getApplicationContext(), "CLIENTE! " + nome + " ADICIONADO(a) COM SUCESSO", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "CLIENTE! " + nome + " JÁ FOI CADASTRADO!", Toast.LENGTH_LONG).show();
        }

        Intent in = new Intent(this, MenuActivity.class);
        startActivity(in);
        finish();
    }
}
