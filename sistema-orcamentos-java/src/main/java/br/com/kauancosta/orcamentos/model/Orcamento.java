package br.com.kauancosta.orcamentos.model;

import java.util.List;
import java.util.Objects;

public class Orcamento {
    private final String cliente;
    private final List<Servico> servicos;
    private final String observacoes;
    private final String status;

    public Orcamento(String cliente, List<Servico> servicos, String observacoes, String status) {
        if (cliente == null || cliente.isBlank()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (servicos == null || servicos.isEmpty()) {
            throw new IllegalArgumentException("O orçamento precisa ter pelo menos um serviço.");
        }
        this.cliente = cliente;
        this.servicos = List.copyOf(servicos);
        this.observacoes = Objects.requireNonNullElse(observacoes, "Sem observações.");
        this.status = Objects.requireNonNullElse(status, "EM ANÁLISE");
    }

    public String getCliente() {
        return cliente;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getStatus() {
        return status;
    }

    public double calcularTotal() {
        return servicos.stream().mapToDouble(Servico::calcularPreco).sum();
    }
}
