package com.example.multimoedas.dto;

import java.time.LocalDateTime;

public class CriarCartaoResponse {
    
    private String numeroCartao;
    private LocalDateTime dataEmissao;
    private String status;
    
    public CriarCartaoResponse() {}
    
    public CriarCartaoResponse(String numeroCartao, LocalDateTime dataEmissao, String status) {
        this.numeroCartao = numeroCartao;
        this.dataEmissao = dataEmissao;
        this.status = status;
    }
    
    public String getNumeroCartao() {
        return numeroCartao;
    }
    
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    
    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }
    
    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
