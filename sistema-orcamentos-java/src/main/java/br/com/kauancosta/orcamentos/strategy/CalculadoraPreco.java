package br.com.kauancosta.orcamentos.strategy;

/**
 * Contrato de estratégia (Strategy) para o cálculo de preço de serviços.
 */
@FunctionalInterface
public interface CalculadoraPreco {
    double calcular(double quantidade);
}
