package com.example.cliente;

public class ItensConta {
    private long idConta;
    private String descricao;
    private int quantidade;
    private double precoUni;

    // getters e setters
    public long getIdConta() { return idConta; }
    public void setIdConta(long idConta) { this.idConta = idConta; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPrecoUni() { return precoUni; }
    public void setPrecoUni(double precoUni) { this.precoUni = precoUni; }
}
