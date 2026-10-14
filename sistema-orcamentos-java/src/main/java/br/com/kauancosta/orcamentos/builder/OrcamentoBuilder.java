package br.com.kauancosta.orcamentos.builder;

import br.com.kauancosta.orcamentos.model.Orcamento;
import br.com.kauancosta.orcamentos.model.Servico;

import java.util.ArrayList;
import java.util.List;

/**
 * Construtor fluente (Builder) para facilitar a montagem e validação de instâncias de Orcamento.
 */
public class OrcamentoBuilder {
    private String cliente;
    private final List<Servico> servicos = new ArrayList<>();
    private String observacoes = "Sem observações.";
    private String status = "EM ANÁLISE";

    public OrcamentoBuilder cliente(String cliente) {
        this.cliente = cliente;
        return this;
    }

    public OrcamentoBuilder adicionarServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("O serviço não pode ser nulo.");
        }
        servicos.add(servico);
        return this;
    }

    public OrcamentoBuilder observacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }

    public OrcamentoBuilder status(String status) {
        this.status = status;
        return this;
    }

    public Orcamento build() {
        return new Orcamento(cliente, servicos, observacoes, status);
    }
}
