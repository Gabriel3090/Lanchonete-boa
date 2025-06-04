package com.example.cliente;

public class ContaLanchonete {
    private int idConta;
    private String dataAbertura;
    private double valorTotal;

    public ContaLanchonete(String dataAbertura, double valorTotal) {
        this.dataAbertura = dataAbertura;
        this.valorTotal = valorTotal;
    }

    public ContaLanchonete() {
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
