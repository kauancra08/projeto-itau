package br.com.kauancosta.orcamentos.config;

/**
 * Gerenciador de configurações gerais do sistema (Singleton).
 */
public final class ConfiguracaoSistema {
    private static final ConfiguracaoSistema INSTANCIA = new ConfiguracaoSistema();

    private final String nomeEmpresa = "Kauan Costa Dev";

    private ConfiguracaoSistema() {
    }

    public static ConfiguracaoSistema getInstancia() {
        return INSTANCIA;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }
}
