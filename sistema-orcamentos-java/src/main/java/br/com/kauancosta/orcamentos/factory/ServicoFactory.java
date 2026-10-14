package br.com.kauancosta.orcamentos.factory;

import br.com.kauancosta.orcamentos.model.Servico;
import br.com.kauancosta.orcamentos.strategy.CalculoDrywall;
import br.com.kauancosta.orcamentos.strategy.CalculoInstalacao;
import br.com.kauancosta.orcamentos.strategy.CalculoPintura;

public class ServicoFactory {
    public Servico criarServico(String tipo, String nome, double quantidade) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo do serviço é obrigatório.");
        }

        return switch (tipo.trim().toLowerCase()) {
            case "pintura" -> new Servico(nome, quantidade, "m²", new CalculoPintura());
            case "instalacao" -> new Servico(nome, quantidade, "unidade(s)", new CalculoInstalacao());
            case "drywall" -> new Servico(nome, quantidade, "m²", new CalculoDrywall());
            default -> throw new IllegalArgumentException("Tipo de serviço desconhecido: " + tipo);
        };
    }
}
