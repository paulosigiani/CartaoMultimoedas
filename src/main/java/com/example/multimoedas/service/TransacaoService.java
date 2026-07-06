package com.example.multimoedas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.model.Operacao;
import com.example.multimoedas.model.StatusTransacao;
import com.example.multimoedas.model.Transacao;
import com.example.multimoedas.repository.TransacaoRepository;
import com.example.multimoedas.service.builder.TransacaoBuilder;

import java.util.List;

@Service
@Transactional
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CartaoService cartaoService;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            CartaoService cartaoService) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoService = cartaoService;
    }

    public Transacao registrar(Cartao cartao, Operacao operacao) {
        Transacao transacao = new TransacaoBuilder()
                .cartao(cartao)
                .tipo(operacao.getTipo())
                .moeda(operacao.getMoeda())
                .valorRealBrasileiro(operacao.getValorRealBrasileiro())
                .valorConvertidoMoedaDestino(operacao.getValorMoedaEstrangeira())
                .taxaConversao(operacao.getTaxaConversao())
                .status(StatusTransacao.APROVADA)
                .descricao(operacao.getDescricao())
                .build();
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> obterHistorico(String numeroCartao) {
        Cartao cartao = cartaoService.buscarCartao(numeroCartao);
        return transacaoRepository.findByCartaoOrderByDataTransacaoDesc(cartao);
    }
}
