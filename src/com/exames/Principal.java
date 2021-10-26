package com.exames;

import com.modelo.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    // CAMINHO DO ARQUIVO QUE SER� CRIADO NO DISCO RIGIDO
    private static final String CAMINHO_ARQUIVO = "D:\\pacientes.dat";

    private List<Paciente> pacientes;

    public Principal() {
        this.pacientes = new ArrayList<>();
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        String menu;
        String opcaoSelecionadaMenuPrincipal;
        int codigoOpcaoSelecionadaMenuPrincipal;

        do {

            menu = "Menu principal\n" +
                    "Opções:\n" +
                    "1. Cadastrar paciente \n" +
                    "2. Relatório \n" +
                    "3. Realizar exames \n" +
                    "9. Sair";

            opcaoSelecionadaMenuPrincipal = JOptionPane.showInputDialog(menu + "\n\n");

            codigoOpcaoSelecionadaMenuPrincipal = principal.converterDeStringParaInteiro(opcaoSelecionadaMenuPrincipal);

            switch (codigoOpcaoSelecionadaMenuPrincipal) {
                case 1:
                    Paciente paciente = principal.preencherInformacoesCadastrais();
                    List<Exame> examesSolicitados = principal.solicitarExames();
                    paciente.examesSolicitados(examesSolicitados);

                    principal.pacientes.add(paciente);
                    principal.gravarArquivoEmDisco();

                    break;

                case 2:
                    List<Paciente> pacientesRecuperadosDoDisco = principal.recuperarArquivoDoDisco();

                    if (pacientesRecuperadosDoDisco.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Nenhum paciente cadastrado.");
                        break;
                    }

                    String dados = "";

                    for (int i=0; i < pacientesRecuperadosDoDisco.size(); i++)	{
                        dados += pacientesRecuperadosDoDisco.get(i).toString() + "---------------\n";
                    }

                    JOptionPane.showMessageDialog(null,dados);

                    break;

                case 3:

                    List<Paciente> pacientesRecuperadosDoDiscParaRealizarExames = principal.recuperarArquivoDoDisco();
                    String dadosRealizarExames = "";

                    for (Paciente pacienteParaRealiarExames : pacientesRecuperadosDoDiscParaRealizarExames){

                        List<Exame> examesDoPaciente = pacienteParaRealiarExames.getExames();

                        for(Exame exame : examesDoPaciente){
                            dadosRealizarExames += "Exames do paciente: " + pacienteParaRealiarExames.getNome() + "\n";
                            dadosRealizarExames += "Preparações para o exame: " + exame.getNome() + "\n";

                            for (Preparacao preparacao : exame.getPresparacoes()) {
                                dadosRealizarExames += preparacao.getInstrucao() + "\n";
                                exame.realizarPreparacao();
                            }

                            dadosRealizarExames += "---------------\n";

                        }

                    }

                    JOptionPane.showMessageDialog(null,dadosRealizarExames);

                    break;

                case 9:
                    JOptionPane.showMessageDialog(null, "Fim do aplicativo");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção selecionada não encontrada");
            }

        } while (codigoOpcaoSelecionadaMenuPrincipal != 9);
    }

    private Paciente preencherInformacoesCadastrais() {

        String[] valores = new String[8];
        String[] nomeVal = { "Nome", "Sobrenome", "Sexo", "Data de nascimento", "CPF", "R.G.", "E-mail", "Telefone", "Convênio" };
        valores = leValores(nomeVal);

        String nome = valores[0];
        String sobrenome = valores[1];
        String sexo = valores[2];
        String  dataNascimento = valores[3];
        String cpf = valores[4];
        String rg = valores[5];
        String email = valores[6];
        String telefone = valores[7];
        String convenio = valores[8];

        Paciente paciente = new Paciente(nome, sobrenome, sexo, dataNascimento, cpf, rg, email, telefone, convenio);

        return paciente;

    }

    public String[] leValores(String[] dadosIn) {
        String[] dadosOut = new String[dadosIn.length];

        for (int i = 0; i < dadosIn.length; i++) {
            dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");
        }

        return dadosOut;
    }

    private List<Exame> solicitarExames() {

        List<Exame> examesSolicitados = new ArrayList<>();

        String opcaoSelecionadaSubMenuOpcoesExames;
        int codigoOpcaoSelecionadaSubMenuOpcoesExames;

        do {

            String subMenuOpcoesExames = "Selecione o exame\n" +
                    "Opções:\n" +
                    "1. Exame de sangue \n" +
                    "2. Raio X \n" +
                    "3. Ecografia \n" +
                    "4. Voltar para o menu principal";

            opcaoSelecionadaSubMenuOpcoesExames = JOptionPane.showInputDialog(subMenuOpcoesExames + "\n\n");
            codigoOpcaoSelecionadaSubMenuOpcoesExames = this.converterDeStringParaInteiro(opcaoSelecionadaSubMenuOpcoesExames);

            switch (codigoOpcaoSelecionadaSubMenuOpcoesExames){
                case 1:
                    String[] valores = new String[3];
                    String[] nomeVal = { "Está em jejum de 8 horas?", "Tomou algum remédio nas últimas 24h?", "Qual remédio tomou?", "Ingeriu bebida álcoolica nas últimas 24h?"};
                    valores = leValores(nomeVal);

                    String nome = "Exame de sangue";
                    String exclusivoFemino = "Não";
                    String status = "Pendente";
                    Double quantidadeSangueEmMiliLitros = 20.0;

                    String estaEmJejum = valores[0];
                    String tomouRemedioNasUltimas24h = valores[1];
                    String qualRemedioTomou = valores[2];
                    String ingeriuBebidasAlcoolicasNasUltimas24h = valores[3];

                    List<Preparacao> preparacoes = new ArrayList<>();

                    preparacoes.add(new Preparacao("Estar em jejum de 8h."));
                    preparacoes.add(new Preparacao("Não ter tomado remédio tarja preta nas últimas 24h."));
                    preparacoes.add(new Preparacao("Não ter ingerido bebida alcoólica nas últimas 24h."));

                    ExameSangue exameSangue = new ExameSangue(nome, exclusivoFemino, preparacoes, status, quantidadeSangueEmMiliLitros, estaEmJejum, tomouRemedioNasUltimas24h, qualRemedioTomou, ingeriuBebidasAlcoolicasNasUltimas24h);

                    examesSolicitados.add(exameSangue);

                    break;
                case 2:
                    String[] valoresRaioX = new String[1];
                    String[] nomeValRaioX = { "Está grávida?", "Possui prótese metálica?"};
                    valoresRaioX = leValores(nomeValRaioX);

                    String nomeRaioX = "Exame de Raio X";
                    String exclusivoFeminoRaioX = "Não";
                    String statusRaioX = "Pendente";

                    String estaGravida = valoresRaioX[0];
                    String possuiProteseMetalica = valoresRaioX[1];

                    List<Preparacao> preparacoesRaioX = new ArrayList<>();

                    preparacoesRaioX.add(new Preparacao("Está grávida?"));
                    preparacoesRaioX.add(new Preparacao("Possui prótese metálica?"));

                    ExameRaioX exameRaioX = new ExameRaioX(nomeRaioX, exclusivoFeminoRaioX, preparacoesRaioX, statusRaioX, estaGravida);

                    examesSolicitados.add(exameRaioX);

                    break;
                case 3:
                    String[] valoresEcografia = new String[0];
                    String[] nomeValEcografia = { "Está com a bexiga cheia?"};
                    valoresEcografia = leValores(nomeValEcografia);

                    String nomeEcografia = "Ecografia";
                    String exclusivoFeminoEcografia = "Não";
                    String statusEcografia = "Pendente";

                    String bexigaCheia = valoresEcografia[0];

                    List<Preparacao> preparacoesEcografia = new ArrayList<>();

                    preparacoesEcografia.add(new Preparacao("Está com a bexiga cheia?"));

                    Ecografia exameEcografia = new Ecografia(nomeEcografia, exclusivoFeminoEcografia, preparacoesEcografia, statusEcografia, bexigaCheia);

                    examesSolicitados.add(exameEcografia);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Voltar ao menu principal");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção selecionada não encontrada");
            }

        } while(codigoOpcaoSelecionadaSubMenuOpcoesExames != 4);

        return examesSolicitados;
    }

    public int converterDeStringParaInteiro(String entrada) {

        while (this.conversaoInvalida(entrada)) {
            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
        }

        return Integer.parseInt(entrada);
    }

    private boolean conversaoInvalida(String numeroComoString) {
        try {
            Integer.parseInt(numeroComoString);
            return false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void gravarArquivoEmDisco() {

        /**
         * UTILIZAÇÃO DO TRY-WITH-RESOURCES
         *
         * NA DECLARAÇÃO DO TRY O OBJETO ObjectOutputStream É INSTANCIADO PARA GRAVAÇÃO
         * DO ARQUIVO
         *
         * QUANDO TERMINAR A EXECUÇÃO (COM SUCESSO OU FALHA) O OBJETO ObjectOutputStream
         * É FINALIZADO E OS RECURSOS LIBERADOS.
         *
         * COM ISSO NÃO É NECESSÁRIO UTILIZAR OS COMANDOS ABAIXO, POIS O PRÓPRIO
         * TRY-WITH-RESOURCES SE ENCARREGA DISSO.
         *
         * outputStream.flush(); outputStream.close();
         *
         **/
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CAMINHO_ARQUIVO))) {

            for (Paciente paciente : pacientes) {
                outputStream.writeObject(paciente);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Paciente> recuperarArquivoDoDisco() {
        List<Paciente> pacientesRecuperadosDoDisco = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO))) {
            Object obj = null;

            while ((obj = inputStream.readObject()) != null) { // ENQUANDO TIVER LINHAS NO ARQUIVO
                if (obj instanceof Paciente) {
                    pacientesRecuperadosDoDisco.add((Paciente) obj);
                }
            }

        } catch (EOFException ex) { // QUANDO O FINAL DO ARQUIVO É ATINGIDO
            System.out.println("Leitura atingiu final do arquivo \n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pacientesRecuperadosDoDisco;
    }
}
