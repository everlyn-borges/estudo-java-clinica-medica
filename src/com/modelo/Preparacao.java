package com.modelo;

import java.io.Serializable;

public class Preparacao implements Serializable {

    private static final long serialVersionUID = 3L;

    private String instrucao;

    public Preparacao(String instrucao) {
        this.instrucao = instrucao;
    }

    public String getInstrucao() {
        return instrucao;
    }
}
