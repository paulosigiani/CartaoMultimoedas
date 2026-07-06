package com.example.multimoedas.dto;

import java.math.BigDecimal;

public class CarregaCartaoResponse {
    
    private String numeroCartao;
    private BigDecimal valorCargaRealBrasileiro;
    private String moeda;
    private BigDecimal taxaConversao;
    private BigDecimal valorCargaMoedaDestino;
    private String mensagem;
    
    public CarregaCartaoResponse() {}

    public String getNumeroCartao() {
        return numeroCartao;
    }
    
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    
    public BigDecimal getValorCargaRealBrasileiro() {
        return valorCargaRealBrasileiro;
    }
    
    public void setValorCargaRealBrasileiro(BigDecimal valorCargaRealBrasileiro) {
        this.valorCargaRealBrasileiro = valorCargaRealBrasileiro;
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
    
    public BigDecimal getValorCargaMoedaDestino() {
        return valorCargaMoedaDestino;
    }
    
    public void setValorCargaMoedaDestino(BigDecimal valorCargaMoedaDestino) {
        this.valorCargaMoedaDestino = valorCargaMoedaDestino;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
