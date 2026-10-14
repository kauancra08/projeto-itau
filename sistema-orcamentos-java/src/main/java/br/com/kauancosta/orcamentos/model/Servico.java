package br.com.kauancosta.orcamentos.model;

import br.com.kauancosta.orcamentos.strategy.CalculadoraPreco;

import java.util.Objects;

public class Servico {
    private final String nome;
    private final double quantidade;
    private final String unidade;
    private final CalculadoraPreco calculadora;

    public Servico(String nome, double quantidade, String unidade, CalculadoraPreco calculadora) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do serviço é obrigatório.");
        }
        if (!Double.isFinite(quantidade) || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = Objects.requireNonNull(unidade, "A unidade é obrigatória.");
        this.calculadora = Objects.requireNonNull(calculadora, "A estratégia de cálculo é obrigatória.");
    }

    public String getNome() {
        return nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public double calcularPreco() {
        return calculadora.calcular(quantidade);
    }
}
