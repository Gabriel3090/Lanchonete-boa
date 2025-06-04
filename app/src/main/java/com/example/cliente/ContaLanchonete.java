package com.example.cliente;

public class ContaLanchonete {
    private int idConta;  // Pode ser opcional
    private int idCli;    // FK para Cliente
    private String dataAbertura;  // yyyy-MM-dd HH:mm:ss
    private double valorTotal;

    public ContaLanchonete(int idCli, String dataAbertura, double valorTotal) {
        this.idCli = idCli;
        this.dataAbertura = dataAbertura;
        this.valorTotal = valorTotal;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdCli() {
        return idCli;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
