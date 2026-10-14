# Sistema de Orçamentos de Serviços

Projeto desenvolvido em Java puro focado na aplicação prática de padrões de projeto (Design Patterns / GoF) para modelagem e cálculo de orçamentos de serviços.

## Visão Geral

A aplicação simula o fluxo de geração de orçamentos para prestação de serviços (como pintura, instalações elétricas e drywall), calculando valores com base em métricas específicas (m² ou unidades) e consolidando as informações em um relatório formatado no console.

## Padrões de Projeto Aplicados

- **Strategy** (`strategy/`): Define uma família de algoritmos para o cálculo de preços (`CalculoPintura`, `CalculoInstalacao`, `CalculoDrywall`), permitindo que a regra de negócio de cada serviço evolua independentemente sem condicionais excessivas (`if`/`else` ou `switch`).
- **Simple Factory** (`factory/`): Centraliza a criação de instâncias de serviços (`ServicoFactory`), associando automaticamente o nome, unidade de medida e a respectiva estratégia de precificação.
- **Builder** (`builder/`): Facilita a construção progressiva e fluente do objeto `Orcamento`, desacoplando sua montagem complexa e garantindo validações de integridade.
- **Singleton** (`config/`): Garante uma única instância compartilhada de `ConfiguracaoSistema` para gerenciar informações globais durante a execução da aplicação.

## Tecnologias e Requisitos

- **Java JDK**: 17 ou superior
- **IDE**: IntelliJ IDEA, VS Code ou Eclipse (ou execução via terminal)
- Não requer dependências externas (Java Standard Edition puro).

## Como Executar

### Via Terminal

Na raiz do repositório, compile e execute:

```bash
# Compilando os arquivos
javac -d out -sourcepath sistema-orcamentos-java/src/main/java sistema-orcamentos-java/src/main/java/br/com/kauancosta/orcamentos/Main.java

# Executando a classe principal
java -cp out br.com.kauancosta.orcamentos.Main
```

Ou, acessando o diretório `sistema-orcamentos-java`:

```bash
cd sistema-orcamentos-java
javac -d out -sourcepath src/main/java src/main/java/br/com/kauancosta/orcamentos/Main.java
java -cp out br.com.kauancosta.orcamentos.Main
```

### Via IDE (IntelliJ / Eclipse / VS Code)

1. Abra a pasta do projeto na sua IDE favorita.
2. Certifique-se de que a pasta `sistema-orcamentos-java/src/main/java` está marcada como pasta de fontes (Sources Root).
3. Execute o método `main` na classe `br.com.kauancosta.orcamentos.Main`.

## Exemplo de Saída

```text
========================================
       SISTEMA DE ORÇAMENTOS
========================================
Empresa: Kauan Costa Dev
Cliente: Mariana Souza
Data: 14/10/2026
----------------------------------------
1. Pintura de parede | 35,00 m² | R$ 1.225,00
2. Instalação de luminárias | 4,00 unidade(s) | R$ 320,00
3. Serviço de drywall | 12,50 m² | R$ 1.375,00
----------------------------------------
TOTAL DO ORÇAMENTO: R$ 2.920,00
Observações: Orçamento para reforma residencial - serviços gerais.
Status: EM ANÁLISE
========================================
```

## Estrutura do Projeto

```text
sistema-orcamentos-java/
└── src/main/java/br/com/kauancosta/orcamentos/
    ├── Main.java
    ├── builder/
    │   └── OrcamentoBuilder.java
    ├── config/
    │   └── ConfiguracaoSistema.java
    ├── factory/
    │   └── ServicoFactory.java
    ├── model/
    │   ├── Orcamento.java
    │   └── Servico.java
    └── strategy/
        ├── CalculadoraPreco.java
        ├── CalculoDrywall.java
        ├── CalculoInstalacao.java
        └── CalculoPintura.java
```

Desenvolvido por [Kauan Gonçalves](https://github.com/kauancra08)
