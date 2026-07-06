package com.example.multimoedas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.multimoedas.dto.SaqueCartaoRequest;
import com.example.multimoedas.dto.SaqueCartaoResponse;
import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.model.DadosConversao;
import com.example.multimoedas.model.Operacao;
import com.example.multimoedas.model.ValorEmMoeda;
import com.example.multimoedas.model.ResultadoConversao;
import com.example.multimoedas.repository.CartaoRepository;
import com.example.multimoedas.service.interfaces.ConversaoMoedaInterface;
import com.example.multimoedas.service.validation.ValidadorSaque;

import java.math.BigDecimal;

@Service
@Transactional
public class SaqueService {

    private static final String MOEDA_DESTINO_SAQUE = "BRL";

    private final CartaoService cartaoService;
    private final ConversaoMoedaInterface conversaoMoeda;
    private final ValidadorSaque validadorSaque;
    private final TransacaoService transacaoService;
    private final CartaoRepository cartaoRepository;

    public SaqueService(CartaoService cartaoService,
            ConversaoMoedaInterface conversaoMoeda,
            ValidadorSaque validadorSaque,
            TransacaoService transacaoService,
            CartaoRepository cartaoRepository) {
        this.cartaoService = cartaoService;
        this.conversaoMoeda = conversaoMoeda;
        this.validadorSaque = validadorSaque;
        this.transacaoService = transacaoService;
        this.cartaoRepository = cartaoRepository;
    }

    public SaqueCartaoResponse simularSaque(SaqueCartaoRequest request) {
        Cartao cartao = cartaoService.buscarCartao(request.getNumeroCartao());
        ValorEmMoeda valorSaque = extrairValorSaque(request);
        validadorSaque.validarSaque(cartao, valorSaque);

        ResultadoConversao resultado = calcularConversao(request);
        BigDecimal saldoRestante = calcularSaldoRestante(cartao, request);

        SaqueCartaoResponse response = construirResponse(request, resultado);
        response.setSaldoRestante(saldoRestante);
        response.setMensagem("Simulação de saque realizada com sucesso");
        return response;
    }

    public SaqueCartaoResponse confirmarSaque(SaqueCartaoRequest request) {
        Cartao cartao = cartaoService.buscarCartao(request.getNumeroCartao());
        ValorEmMoeda valorSaque = extrairValorSaque(request);
        validadorSaque.validarSaque(cartao, valorSaque);

        ResultadoConversao resultado = calcularConversao(request);
        BigDecimal saldoRestante = calcularSaldoRestante(cartao, request);

        cartao.debitar(valorSaque.getMoeda(), valorSaque.getValor());
        cartaoRepository.save(cartao);

        Operacao operacao = Operacao.saque(valorSaque, resultado);
        transacaoService.registrar(cartao, operacao);

        SaqueCartaoResponse response = construirResponse(request, resultado);
        response.setSaldoRestante(saldoRestante);
        response.setMensagem("Saque realizado com sucesso");
        return response;
    }

    private ValorEmMoeda extrairValorSaque(SaqueCartaoRequest request) {
        return new ValorEmMoeda(request.getMoedaSaque(), request.getValorSaqueMoedaEstrangeiraOrigem());
    }

    private ResultadoConversao calcularConversao(SaqueCartaoRequest request) {
        DadosConversao dados = new DadosConversao(
                request.getMoedaSaque(),
                MOEDA_DESTINO_SAQUE,
                request.getValorSaqueMoedaEstrangeiraOrigem());

        BigDecimal valorConvertido = conversaoMoeda.converterComDados(dados);
        BigDecimal taxa = conversaoMoeda.obterTaxaConversao(
                request.getMoedaSaque(), MOEDA_DESTINO_SAQUE);

        return new ResultadoConversao(valorConvertido, taxa);
    }

    private BigDecimal calcularSaldoRestante(Cartao cartao, SaqueCartaoRequest request) {
        return cartao.saldoDe(request.getMoedaSaque())
                .subtract(request.getValorSaqueMoedaEstrangeiraOrigem());
    }

    private SaqueCartaoResponse construirResponse(SaqueCartaoRequest request,
            ResultadoConversao resultado) {
        SaqueCartaoResponse response = new SaqueCartaoResponse();
        response.setNumeroCartao(request.getNumeroCartao());
        response.setMoeda(request.getMoedaSaque());
        response.setValorSaqueMoedaEstrangeiraOrigem(request.getValorSaqueMoedaEstrangeiraOrigem());
        response.setTaxaConversao(resultado.getTaxaConversao());
        response.setValorSaqueRealBrasileiro(resultado.getValorConvertido());
        return response;
    }
}
