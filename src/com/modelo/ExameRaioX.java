package com.modelo;

import java.util.List;

public class ExameRaioX extends Exame {
    private String estaGravida;
    private String possuiProteseMetalica;

    public ExameRaioX(String nome, String exclusivoFemino, List<Preparacao> preparacoes, String status, String estaGravida) {
        super(nome, exclusivoFemino, preparacoes, status);
        this.estaGravida = estaGravida;
    }

    @Override
    public void realizarPreparacao(){
        System.out.println("Realizar preparação para o exame de Raio X");

        for (Preparacao preparacao : this.getPresparacoes()){
            System.out.println(preparacao.getInstrucao() + "\n");
        }
    }
}
