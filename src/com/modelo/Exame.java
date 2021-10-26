package com.modelo;

import java.io.Serializable;
import java.util.List;

public abstract class Exame implements Serializable {

    private static final long serialVersionUID = 2L;

    private String nome;
    private String exclusivoFemino;
    private List<Preparacao> preparacoes;
    private String status;

    public Exame(String nome, String exclusivoFemino, List<Preparacao> preparacoes, String status) {
        this.nome = nome;
        this.exclusivoFemino = exclusivoFemino;
        this.preparacoes = preparacoes;
        this.status = status;
    }

    public abstract void realizarPreparacao();

    public String getNome() {
        return nome;
    }

    public String getExclusivoFemino() {
        return exclusivoFemino;
    }

    public List<Preparacao> getPresparacoes() {
        return preparacoes;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        String retorno = "";

        retorno += "exame: " + this.nome + "\n";

        return retorno;
    }
}
