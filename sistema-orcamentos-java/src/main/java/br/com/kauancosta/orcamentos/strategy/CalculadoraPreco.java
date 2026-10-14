package br.com.kauancosta.orcamentos.strategy;

@FunctionalInterface
public interface CalculadoraPreco {
    double calcular(double quantidade);
}
