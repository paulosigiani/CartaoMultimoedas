package com.example.multimoedas.service.interfaces;

import java.math.BigDecimal;

import com.example.multimoedas.model.DadosConversao;

public interface ConversaoMoedaInterface {

    BigDecimal converterComDados(DadosConversao dados);

    BigDecimal obterTaxaConversao(String moedaOrigem, String moedaDestino);
}
