package com.example.multimoedas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CarregaCartaoRequest {
    
    @NotBlank(message = "Número do cartão é obrigatório")
    private String numeroCartao;
    
    @NotBlank(message = "Moeda é obrigatória")
    private String moedaCarga;
    
    @NotNull(message = "Valor da carga é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor da carga deve ser maior que zero")
    private BigDecimal valorCargaRealBrasileiro;
    
    public CarregaCartaoRequest() {}

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getMoedaCarga() {
        return moedaCarga;
    }

    public void setMoedaCarga(String moedaCarga) {
        this.moedaCarga = moedaCarga;
    }

    public BigDecimal getValorCargaRealBrasileiro() {
        return valorCargaRealBrasileiro;
    }

    public void setValorCargaRealBrasileiro(BigDecimal valorCargaRealBrasileiro) {
        this.valorCargaRealBrasileiro = valorCargaRealBrasileiro;
    }
}
