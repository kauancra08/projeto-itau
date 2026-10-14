package br.com.kauancosta.orcamentos;

import br.com.kauancosta.orcamentos.builder.OrcamentoBuilder;
import br.com.kauancosta.orcamentos.config.ConfiguracaoSistema;
import br.com.kauancosta.orcamentos.factory.ServicoFactory;
import br.com.kauancosta.orcamentos.model.Orcamento;
import br.com.kauancosta.orcamentos.model.Servico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);
    static final ServicoFactory fabrica = new ServicoFactory();

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("pt-BR"));
        ConfiguracaoSistema configuracao = ConfiguracaoSistema.getInstancia();

        System.out.println("========================================");
        System.out.println("       SISTEMA DE ORÇAMENTOS");
        System.out.println("       " + configuracao.getNomeEmpresa());
        System.out.println("========================================");

        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine().trim();

        OrcamentoBuilder builder = new OrcamentoBuilder().cliente(nomeCliente);

        adicionarServicos(builder);

        System.out.print("\nObservações (Enter para pular): ");
        String obs = scanner.nextLine().trim();
        if (!obs.isEmpty()) {
            builder.observacoes(obs);
        }

        Orcamento orcamento = builder.build();
        imprimirOrcamento(orcamento, configuracao);

        scanner.close();
    }

    private static void adicionarServicos(OrcamentoBuilder builder) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nTipos de serviço disponíveis:");
            System.out.println("  1 - Pintura          (R$ 35,00 por m²)");
            System.out.println("  2 - Instalação        (R$ 80,00 por unidade)");
            System.out.println("  3 - Drywall           (R$ 110,00 por m²)");
            System.out.print("Escolha o tipo de serviço (1, 2 ou 3): ");

            String opcao = scanner.nextLine().trim();
            String tipo = resolverTipo(opcao);

            if (tipo == null) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            System.out.print("Descrição do serviço (ex: Pintura sala principal): ");
            String descricao = scanner.nextLine().trim();

            double quantidade = lerQuantidade(tipo);

            try {
                Servico servico = fabrica.criarServico(tipo, descricao, quantidade);
                builder.adicionarServico(servico);
                System.out.printf("Serviço adicionado: %s | Subtotal: %s%n",
                        descricao, formatarMoeda(servico.calcularPreco()));
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao adicionar serviço: " + e.getMessage());
                continue;
            }

            System.out.print("\nDeseja adicionar outro serviço? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            continuar = resposta.equals("s");
        }
    }

    private static String resolverTipo(String opcao) {
        return switch (opcao) {
            case "1" -> "pintura";
            case "2" -> "instalacao";
            case "3" -> "drywall";
            default -> null;
        };
    }

    private static double lerQuantidade(String tipo) {
        String unidade = tipo.equals("instalacao") ? "unidades" : "metros quadrados (m²)";

        while (true) {
            System.out.printf("Informe a quantidade em %s: ", unidade);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(entrada);
                if (valor <= 0) {
                    System.out.println("A quantidade deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número (ex: 15 ou 15.5).");
            }
        }
    }

    private static void imprimirOrcamento(Orcamento orcamento, ConfiguracaoSistema configuracao) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\n========================================");
        System.out.println("           ORÇAMENTO GERADO");
        System.out.println("========================================");
        System.out.println("Empresa : " + configuracao.getNomeEmpresa());
        System.out.println("Cliente : " + orcamento.getCliente());
        System.out.println("Data    : " + LocalDate.now().format(formatoData));
        System.out.println("----------------------------------------");
        System.out.printf("%-4s %-30s %10s %15s%n", "Nº", "Serviço", "Qtd/Un", "Subtotal");
        System.out.println("----------------------------------------");

        int numero = 1;
        for (Servico servico : orcamento.getServicos()) {
            System.out.printf("%-4d %-30s %6.2f %-4s %12s%n",
                    numero++,
                    servico.getNome(),
                    servico.getQuantidade(),
                    servico.getUnidade(),
                    formatarMoeda(servico.calcularPreco()));
        }

        System.out.println("----------------------------------------");
        System.out.printf("%-35s %s%n", "TOTAL DO ORÇAMENTO:", formatarMoeda(orcamento.calcularTotal()));
        System.out.println("----------------------------------------");
        System.out.println("Observações : " + orcamento.getObservacoes());
        System.out.println("Status      : " + orcamento.getStatus());
        System.out.println("========================================");
    }

    private static String formatarMoeda(double valor) {
        return String.format(Locale.forLanguageTag("pt-BR"), "R$ %,.2f", valor);
    }
}
