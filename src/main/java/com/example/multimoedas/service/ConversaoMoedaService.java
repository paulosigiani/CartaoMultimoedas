package com.example.multimoedas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.multimoedas.model.DadosConversao;
import com.example.multimoedas.service.interfaces.ConversaoMoedaInterface;
import com.example.multimoedas.service.interfaces.ProvedorCotacao;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ConversaoMoedaService implements ConversaoMoedaInterface {

    private static final Logger logger = LoggerFactory.getLogger(ConversaoMoedaService.class);

    private final ProvedorCotacao provedorCotacao;

    public ConversaoMoedaService(ProvedorCotacao provedorCotacao) {
        this.provedorCotacao = provedorCotacao;
    }

    @Override
    public BigDecimal converterComDados(DadosConversao dados) {
        if (dados.isMesmaMoeda()) {
            return dados.getValor();
        }
        BigDecimal taxa = provedorCotacao.obterTaxa(dados.getMoedaOrigem(), dados.getMoedaDestino());
        BigDecimal valorConvertido = dados.getValor().multiply(taxa).setScale(2, RoundingMode.HALF_UP);
        logger.debug("Conversão: {} {} = {} {} (taxa: {})",
                dados.getValor(), dados.getMoedaOrigem(), valorConvertido, dados.getMoedaDestino(), taxa);
        return valorConvertido;
    }

    @Override
    public BigDecimal obterTaxaConversao(String moedaOrigem, String moedaDestino) {
        return provedorCotacao.obterTaxa(moedaOrigem, moedaDestino);
    }
}
