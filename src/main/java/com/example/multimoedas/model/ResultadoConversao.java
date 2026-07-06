package com.example.multimoedas.model;

import java.math.BigDecimal;

public class ResultadoConversao {
    
    private final BigDecimal valorConvertido;
    private final BigDecimal taxaConversao;
    
    public ResultadoConversao(BigDecimal valorConvertido, BigDecimal taxaConversao) {
        this.valorConvertido = valorConvertido;
        this.taxaConversao = taxaConversao;
    }
    
    public BigDecimal getValorConvertido() {
        return valorConvertido;
    }
    
    public BigDecimal getTaxaConversao() {
        return taxaConversao;
    }
}
