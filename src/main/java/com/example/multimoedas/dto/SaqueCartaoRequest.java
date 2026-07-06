package com.example.multimoedas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SaqueCartaoRequest {
    
    @NotBlank(message = "Número do cartão é obrigatório")
    private String numeroCartao;
    
    @NotBlank(message = "Moeda do saque é obrigatória")
    private String moedaSaque;
    
    @NotNull(message = "Valor do saque é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor do saque deve ser maior que zero")
    private BigDecimal valorSaqueMoedaEstrangeiraOrigem;
    
    public SaqueCartaoRequest() {}

    public String getNumeroCartao() {
        return numeroCartao;
    }
    
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    
    public String getMoedaSaque() {
        return moedaSaque;
    }
    
    public void setMoedaSaque(String moedaSaque) {
        this.moedaSaque = moedaSaque;
    }
    
    public BigDecimal getValorSaqueMoedaEstrangeiraOrigem() {
        return valorSaqueMoedaEstrangeiraOrigem;
    }
    
    public void setValorSaqueMoedaEstrangeiraOrigem(BigDecimal valorSaqueMoedaEstrangeiraOrigem) {
        this.valorSaqueMoedaEstrangeiraOrigem = valorSaqueMoedaEstrangeiraOrigem;
    }
}
