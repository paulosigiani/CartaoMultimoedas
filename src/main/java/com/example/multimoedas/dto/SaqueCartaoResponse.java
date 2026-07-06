package com.example.multimoedas.dto;

import java.math.BigDecimal;

public class SaqueCartaoResponse {
    
    private String numeroCartao;
    private BigDecimal valorSaqueMoedaEstrangeiraOrigem;
    private String moeda;
    private BigDecimal taxaConversao;
    private BigDecimal valorSaqueRealBrasileiro;
    private BigDecimal saldoRestante;
    private String mensagem;
    
    public SaqueCartaoResponse() {}

    public String getNumeroCartao() {
        return numeroCartao;
    }
    
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    
    public BigDecimal getValorSaqueMoedaEstrangeiraOrigem() {
        return valorSaqueMoedaEstrangeiraOrigem;
    }
    
    public void setValorSaqueMoedaEstrangeiraOrigem(BigDecimal valorSaqueMoedaEstrangeiraOrigem) {
        this.valorSaqueMoedaEstrangeiraOrigem = valorSaqueMoedaEstrangeiraOrigem;
    }
    
    public String getMoeda() {
        return moeda;
    }
    
    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }
    
    public BigDecimal getTaxaConversao() {
        return taxaConversao;
    }
    
    public void setTaxaConversao(BigDecimal taxaConversao) {
        this.taxaConversao = taxaConversao;
    }
    
    public BigDecimal getValorSaqueRealBrasileiro() {
        return valorSaqueRealBrasileiro;
    }
    
    public void setValorSaqueRealBrasileiro(BigDecimal valorSaqueRealBrasileiro) {
        this.valorSaqueRealBrasileiro = valorSaqueRealBrasileiro;
    }
    
    public BigDecimal getSaldoRestante() {
        return saldoRestante;
    }
    
    public void setSaldoRestante(BigDecimal saldoRestante) {
        this.saldoRestante = saldoRestante;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
