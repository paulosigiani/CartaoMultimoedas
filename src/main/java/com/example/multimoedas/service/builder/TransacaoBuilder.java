package com.example.multimoedas.service.builder;

import java.math.BigDecimal;

import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.model.StatusTransacao;
import com.example.multimoedas.model.TipoTransacao;
import com.example.multimoedas.model.Transacao;

public class TransacaoBuilder {

    private final Transacao transacao = new Transacao();

    public TransacaoBuilder cartao(Cartao cartao) {
        transacao.setCartao(cartao);
        return this;
    }

    public TransacaoBuilder tipo(TipoTransacao tipo) {
        transacao.setTipo(tipo);
        return this;
    }

    public TransacaoBuilder moeda(String moeda) {
        transacao.setMoeda(moeda);
        return this;
    }

    public TransacaoBuilder valorRealBrasileiro(BigDecimal valor) {
        transacao.setValorRealBrasileiro(valor);
        return this;
    }

    public TransacaoBuilder valorConvertidoMoedaDestino(BigDecimal valor) {
        transacao.setValorConvertidoMoedaDestino(valor);
        return this;
    }

    public TransacaoBuilder taxaConversao(BigDecimal taxa) {
        transacao.setTaxaConversao(taxa);
        return this;
    }

    public TransacaoBuilder status(StatusTransacao status) {
        transacao.setStatus(status);
        return this;
    }

    public TransacaoBuilder descricao(String descricao) {
        transacao.setDescricao(descricao);
        return this;
    }

    public Transacao build() {
        return transacao;
    }
}
