package com.modelo;

import java.util.List;

public class ExameSangue extends Exame {
    private Double quantidadeSangueEmMiliLitros;
    private String estaEmJejum;
    private String tomouRemedioNasUltimas24h;
    private String qualRemedioTomou;
    private String ingeriuBebidasAlcoolicasNasUltimas24h;

    public ExameSangue(String nome, String exclusivoFemino, List<Preparacao> preparacoes, String status, Double quantidadeSangueEmMiliLitros, String estaEmJejum, String tomouRemedioNasUltimas24h, String qualRemedioTomou, String ingeriuBebidasAlcoolicasNasUltimas24h) {
        super(nome, exclusivoFemino, preparacoes, status);
        this.quantidadeSangueEmMiliLitros = quantidadeSangueEmMiliLitros;
        this.estaEmJejum = estaEmJejum;
        this.tomouRemedioNasUltimas24h = tomouRemedioNasUltimas24h;
        this.qualRemedioTomou = qualRemedioTomou;
        this.ingeriuBebidasAlcoolicasNasUltimas24h = ingeriuBebidasAlcoolicasNasUltimas24h;
    }

    @Override
    public void realizarPreparacao(){
        System.out.println("Realizar preparação para o exame de sangue");

        for (Preparacao preparacao : this.getPresparacoes()){
            System.out.println(preparacao.getInstrucao() + "\n");
        }
    }
}
