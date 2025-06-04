package com.example.cliente;

public class Cliente {
    private int idCli;
    private String nomeCli;
    private String telefone;

    public Cliente(String nomeCli, String telefone) {
        this.nomeCli = nomeCli;
        this.telefone = telefone;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getNomeCli() {
        return nomeCli;
    }

    public String getTelefone() {
        return telefone;
    }
}
