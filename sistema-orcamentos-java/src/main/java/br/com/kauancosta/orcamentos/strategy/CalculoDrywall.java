package br.com.kauancosta.orcamentos.strategy;

public class CalculoDrywall implements CalculadoraPreco {
    private static final double PRECO_POR_METRO_QUADRADO = 110.00;

    @Override
    public double calcular(double quantidade) {
        return quantidade * PRECO_POR_METRO_QUADRADO;
    }
}
