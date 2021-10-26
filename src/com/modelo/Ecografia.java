package com.modelo;

import java.util.List;

public class Ecografia extends Exame {
    private String bexigaCheia;

    public Ecografia(String nome, String exclusivoFemino, List<Preparacao> preparacoes, String status, String bexigaCheia) {
        super(nome, exclusivoFemino, preparacoes, status);
        this.bexigaCheia = bexigaCheia;
    }

    @Override
    public void realizarPreparacao(){
        System.out.println("Realizar preparação para o exame de ecografia");

        for (Preparacao preparacao : this.getPresparacoes()){
            System.out.println(preparacao.getInstrucao() + "\n");
        }
    }
}
