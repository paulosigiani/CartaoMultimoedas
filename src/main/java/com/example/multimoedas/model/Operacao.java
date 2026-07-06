package com.example.multimoedas.model;

import java.math.BigDecimal;

public class Operacao {

    private final TipoTransacao tipo;
    private final String moeda;
    private final BigDecimal valorRealBrasileiro;
    private final BigDecimal valorMoedaEstrangeira;
    private final BigDecimal taxaConversao;
    private final String descricao;

    private Operacao(TipoTransacao tipo, String moeda,
                     BigDecimal valorRealBrasileiro, BigDecimal valorMoedaEstrangeira,
                     BigDecimal taxaConversao, String descricao) {
        this.tipo = tipo;
        this.moeda = moeda;
        this.valorRealBrasileiro = valorRealBrasileiro;
        this.valorMoedaEstrangeira = valorMoedaEstrangeira;
        this.taxaConversao = taxaConversao;
        this.descricao = descricao;
    }

    public static Operacao carga(ValorEmMoeda valorCarga, ResultadoConversao resultado) {
        BigDecimal valorMoedaEstrangeira = resultado.getValorConvertido();
        return new Operacao(TipoTransacao.CARGA, valorCarga.getMoeda(),
                valorCarga.getValor(), valorMoedaEstrangeira, resultado.getTaxaConversao(),
                "Carga de " + valorMoedaEstrangeira + " " + valorCarga.getMoeda());
    }

    public static Operacao saque(ValorEmMoeda valorSaque, ResultadoConversao resultado) {
        BigDecimal valorMoedaEstrangeira = valorSaque.getValor();
        return new Operacao(TipoTransacao.SAQUE, valorSaque.getMoeda(),
                resultado.getValorConvertido(), valorMoedaEstrangeira, resultado.getTaxaConversao(),
                "Saque de " + valorMoedaEstrangeira + " " + valorSaque.getMoeda());
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public String getMoeda() {
        return moeda;
    }

    public BigDecimal getValorRealBrasileiro() {
        return valorRealBrasileiro;
    }

    public BigDecimal getValorMoedaEstrangeira() {
        return valorMoedaEstrangeira;
    }

    public BigDecimal getTaxaConversao() {
        return taxaConversao;
    }

    public String getDescricao() {
        return descricao;
    }
}
