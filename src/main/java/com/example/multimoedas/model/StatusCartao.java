package com.example.multimoedas.model;

public enum StatusCartao {
    ATIVO("Ativo"),
    BLOQUEADO("Bloqueado"),
    CANCELADO("Cancelado");
    
    private final String descricao;
    
    StatusCartao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
