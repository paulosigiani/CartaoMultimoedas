package com.example.multimoedas.model;

import java.math.BigDecimal;

public class ValorEmMoeda {

    private final String moeda;
    private final BigDecimal valor;

    public ValorEmMoeda(String moeda, BigDecimal valor) {
        this.moeda = moeda;
        this.valor = valor;
    }

    public String getMoeda() {
        return moeda;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
