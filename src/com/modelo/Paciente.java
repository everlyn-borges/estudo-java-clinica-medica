package com.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String sobrenome;
    private String sexo;
    private String dataNascimento;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;
    private String convenio;

    private List<Exame> exames;

    public Paciente(String nome, String sobrenome, String sexo, String dataNascimento, String cpf, String rg, String email, String telefone, String convenio) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.telefone = telefone;
        this.convenio = convenio;
    }

    public void examesSolicitados(List<Exame> examesSolicitados){
        this.exames = examesSolicitados;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        String retorno = "";

        retorno += "Nome: " + this.nome + "\n";
        retorno += "Sobrenome: " + this.sobrenome + "\n";
        retorno += "Sexo: " + this.sexo + "\n";
        retorno += "Data de nascimento: " + this.dataNascimento + "\n";
        retorno += "CPF: " + this.cpf + "\n";
        retorno += "RG: " + this.rg + "\n";
        retorno += "E-mail: " + this.email + "\n";
        retorno += "Telefone: " + this.telefone + "\n";
        retorno += "Convenio: " + this.convenio + "\n";
        retorno += "Exames solicitados: " + this.exames.toString() + "\n";

        return retorno;
    }
}