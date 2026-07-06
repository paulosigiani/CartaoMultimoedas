package com.example.multimoedas.model;

import java.math.BigDecimal;

public class DadosConversao {
    
    private final String moedaOrigem;
    private final String moedaDestino;
    private final BigDecimal valor;
    
    public DadosConversao(String moedaOrigem, String moedaDestino, BigDecimal valor) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valor = valor;
    }
    
    public String getMoedaOrigem() {
        return moedaOrigem;
    }
    
    public String getMoedaDestino() {
        return moedaDestino;
    }
    
    public BigDecimal getValor() {
        return valor;
    }
    
    public boolean isMesmaMoeda() {
        return moedaOrigem.equalsIgnoreCase(moedaDestino);
    }
}
