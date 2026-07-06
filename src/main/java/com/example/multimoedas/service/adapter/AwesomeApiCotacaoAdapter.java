package com.example.multimoedas.service.adapter;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.multimoedas.service.client.AwesomeApiClient;
import com.example.multimoedas.service.client.dto.AwesomeApiResponse;
import com.example.multimoedas.service.interfaces.ProvedorCotacao;

@Component
public class AwesomeApiCotacaoAdapter implements ProvedorCotacao {

    private static final Logger logger = LoggerFactory.getLogger(AwesomeApiCotacaoAdapter.class);

    private final AwesomeApiClient awesomeApiClient;

    public AwesomeApiCotacaoAdapter(AwesomeApiClient awesomeApiClient) {
        this.awesomeApiClient = awesomeApiClient;
    }

    @Override
    public BigDecimal obterTaxa(String moedaOrigem, String moedaDestino) {
        try {
            String parMoedas = moedaOrigem.toUpperCase() + "-" + moedaDestino.toUpperCase();
            logger.debug("Consultando taxa para: {}", parMoedas);

            Map<String, AwesomeApiResponse> response = awesomeApiClient.getTaxaConversao(parMoedas);
            BigDecimal taxa = extrairTaxa(response, parMoedas);

            logger.debug("Taxa obtida: {} = {} {}", moedaOrigem, taxa, moedaDestino);
            return taxa;

        } catch (Exception e) {
            logger.error("Erro ao consultar AwesomeAPI para {}->{}: {}", moedaOrigem, moedaDestino, e.getMessage());
            throw new RuntimeException("Erro ao consultar API de conversão: " + e.getMessage(), e);
        }
    }

    private BigDecimal extrairTaxa(Map<String, AwesomeApiResponse> response, String parMoedas) {
        if (response == null || response.isEmpty()) {
            throw new RuntimeException("Resposta vazia da API para " + parMoedas);
        }

        String chave = parMoedas.replace("-", "");
        AwesomeApiResponse dadosResposta = response.get(chave);

        if (dadosResposta == null) {
            throw new RuntimeException("Taxa não encontrada para " + parMoedas);
        }

        BigDecimal taxa = dadosResposta.getBidAsBigDecimal();

        if (taxa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Taxa inválida para " + parMoedas);
        }

        return taxa;
    }
}
