package com.example.cliente;

public class ItensConta {
    private int idItem;   // Pode ser opcional
    private int idConta;  // FK para ContaLanchonete
    private String descricao;
    private int quantidade;
    private double precoUni;

    public ItensConta(int idConta, String descricao, int quantidade, double precoUni) {
        this.idConta = idConta;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUni = precoUni;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdConta() {
        return idConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUni() {
        return precoUni;
    }
}
