package br.com.kauancosta.orcamentos;

import br.com.kauancosta.orcamentos.builder.OrcamentoBuilder;
import br.com.kauancosta.orcamentos.config.ConfiguracaoSistema;
import br.com.kauancosta.orcamentos.factory.ServicoFactory;
import br.com.kauancosta.orcamentos.model.Orcamento;
import br.com.kauancosta.orcamentos.model.Servico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("pt-BR"));

        ConfiguracaoSistema configuracao = ConfiguracaoSistema.getInstancia();
        ServicoFactory fabrica = new ServicoFactory();

        Servico pintura = fabrica.criarServico("pintura", "Pintura de parede", 35.0);
        Servico instalacao = fabrica.criarServico("instalacao", "Instalação de luminárias", 4.0);
        Servico drywall = fabrica.criarServico("drywall", "Serviço de drywall", 12.5);

        Orcamento orcamento = new OrcamentoBuilder()
                .cliente("Mariana Souza")
                .adicionarServico(pintura)
                .adicionarServico(instalacao)
                .adicionarServico(drywall)
                .observacoes("Orçamento para reforma residencial - serviços gerais.")
                .build();

        imprimirOrcamento(orcamento, configuracao);
    }

    private static void imprimirOrcamento(Orcamento orcamento, ConfiguracaoSistema configuracao) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("========================================");
        System.out.println("       SISTEMA DE ORÇAMENTOS");
        System.out.println("========================================");
        System.out.println("Empresa: " + configuracao.getNomeEmpresa());
        System.out.println("Cliente: " + orcamento.getCliente());
        System.out.println("Data: " + LocalDate.now().format(formatoData));
        System.out.println("----------------------------------------");

        int numero = 1;
        for (Servico servico : orcamento.getServicos()) {
            System.out.printf("%d. %s | %.2f %s | %s%n",
                    numero++,
                    servico.getNome(),
                    servico.getQuantidade(),
                    servico.getUnidade(),
                    formatarMoeda(servico.calcularPreco()));
        }

        System.out.println("----------------------------------------");
        System.out.println("TOTAL DO ORÇAMENTO: " + formatarMoeda(orcamento.calcularTotal()));
        System.out.println("Observações: " + orcamento.getObservacoes());
        System.out.println("Status: " + orcamento.getStatus());
        System.out.println("========================================");
    }

    private static String formatarMoeda(double valor) {
        return String.format(Locale.forLanguageTag("pt-BR"), "R$ %,.2f", valor);
    }
}
