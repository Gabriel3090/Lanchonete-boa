package com.example.cliente;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private RadioGroup rgSanduiches, rgRefrigerantes;
    private CheckBox cbBatataFrita, cbOnionRings, cbSalada;
    private EditText etQuantidade;
    private Button btnAdicionar, btnFinalizar;
    private TextView tvTotal;

    private ArrayList<ItensConta> listaItens = new ArrayList<>();
    private double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        rgSanduiches = findViewById(R.id.rgSanduiches);
        rgRefrigerantes = findViewById(R.id.rgRefrigerantes);
        cbBatataFrita = findViewById(R.id.cbBatataFrita);
        cbOnionRings = findViewById(R.id.cbOnionRings);
        cbSalada = findViewById(R.id.cbSalada);
        etQuantidade = findViewById(R.id.etQuantidade);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        tvTotal = findViewById(R.id.tvTotal);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedIdSanduiche = rgSanduiches.getCheckedRadioButtonId();
                if (selectedIdSanduiche == -1) {
                    Toast.makeText(MenuActivity.this, "Selecione um sanduíche!", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton rbSanduicheSelecionado = findViewById(selectedIdSanduiche);
                String sanduiche = rbSanduicheSelecionado.getText().toString();

                int selectedIdRefri = rgRefrigerantes.getCheckedRadioButtonId();
                if (selectedIdRefri == -1) {
                    Toast.makeText(MenuActivity.this, "Selecione um refrigerante!", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton rbRefriSelecionado = findViewById(selectedIdRefri);
                String refrigerante = rbRefriSelecionado.getText().toString();

                ArrayList<String> acompanhamentos = new ArrayList<>();
                if (cbBatataFrita.isChecked()) acompanhamentos.add("Batata Frita");
                if (cbOnionRings.isChecked()) acompanhamentos.add("Onion Rings");
                if (cbSalada.isChecked()) acompanhamentos.add("Salada Extra");

                String qtdStr = etQuantidade.getText().toString();
                int quantidade;
                try {
                    quantidade = Integer.parseInt(qtdStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MenuActivity.this, "Digite uma quantidade válida!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (quantidade <= 0) {
                    Toast.makeText(MenuActivity.this, "Quantidade deve ser maior que zero!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double precoSanduiche = 20.0;
                double precoRefrigerante = 5.0;
                double precoAcompanhamento = 5.0;

                double precoUni = precoSanduiche + precoRefrigerante + (acompanhamentos.size() * precoAcompanhamento);
                double precoTotalItem = precoUni * quantidade;


                String descricao = sanduiche + " + " + refrigerante;
                if (!acompanhamentos.isEmpty()) {
                    descricao += " (" + String.join(", ", acompanhamentos) + ")";
                }


                ItensConta item = new ItensConta();
                item.setDescricao(descricao);
                item.setQuantidade(quantidade);
                item.setPrecoUni(precoUni);
                item.setIdConta(0);

                listaItens.add(item);

                total += precoTotalItem;
                tvTotal.setText(String.format("R$ %.2f", total));


                rgSanduiches.clearCheck();
                rgRefrigerantes.clearCheck();
                cbBatataFrita.setChecked(false);
                cbOnionRings.setChecked(false);
                cbSalada.setChecked(false);
                etQuantidade.setText("");
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaItens.isEmpty()) {
                    Toast.makeText(MenuActivity.this, "Nenhum item adicionado!", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContaLanchonete conta = new ContaLanchonete();
                conta.setValorTotal(total);

                BancodeDados banco = BancodeDados.getInstance(MenuActivity.this);
                long idConta = banco.insertContaLanchonete(conta);

                if (idConta == -1) {
                    Toast.makeText(MenuActivity.this, "Erro ao salvar a conta!", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean erroAoSalvarItem = false;

                for (ItensConta item : listaItens) {
                    item.setIdConta((int) idConta);
                    long resultado = banco.insertItensConta(item);
                    if (resultado == -1) {
                        erroAoSalvarItem = true;
                        break;
                    }
                }

                if (erroAoSalvarItem) {
                    Toast.makeText(MenuActivity.this, "Erro ao salvar um item!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MenuActivity.this, "Conta finalizada e salva com sucesso!", Toast.LENGTH_SHORT).show();
                }

                listaItens.clear();
                total = 0.0;
                tvTotal.setText(String.format("R$ %.2f", total));

                rgSanduiches.clearCheck();
                rgRefrigerantes.clearCheck();
                cbBatataFrita.setChecked(false);
                cbOnionRings.setChecked(false);
                cbSalada.setChecked(false);
                etQuantidade.setText("");
            }
        });
    }
}
