package br.com.kauancosta.orcamentos.strategy;

public class CalculoInstalacao implements CalculadoraPreco {
    private static final double PRECO_POR_UNIDADE = 80.00;

    @Override
    public double calcular(double quantidade) {
        return quantidade * PRECO_POR_UNIDADE;
    }
}
