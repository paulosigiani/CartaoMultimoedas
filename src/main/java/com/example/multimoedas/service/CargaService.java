package com.example.multimoedas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.multimoedas.dto.CarregaCartaoRequest;
import com.example.multimoedas.dto.CarregaCartaoResponse;
import com.example.multimoedas.model.*;
import com.example.multimoedas.repository.CartaoRepository;
import com.example.multimoedas.service.interfaces.ConversaoMoedaInterface;
import com.example.multimoedas.service.validation.ValidadorCarga;

import java.math.BigDecimal;

@Service
@Transactional
public class CargaService {

    private static final String MOEDA_ORIGEM_CARGA = "BRL";

    private final CartaoService cartaoService;
    private final ConversaoMoedaInterface conversaoMoeda;
    private final ValidadorCarga validadorCarga;
    private final CartaoRepository cartaoRepository;
    private final TransacaoService transacaoService;

    public CargaService(CartaoService cartaoService,
                        ConversaoMoedaInterface conversaoMoeda,
                        ValidadorCarga validadorCarga,
                        CartaoRepository cartaoRepository,
                        TransacaoService transacaoService) {
        this.cartaoService = cartaoService;
        this.conversaoMoeda = conversaoMoeda;
        this.validadorCarga = validadorCarga;
        this.cartaoRepository = cartaoRepository;
        this.transacaoService = transacaoService;
    }

    public CarregaCartaoResponse simularCarga(CarregaCartaoRequest request) {
        Cartao cartao = cartaoService.buscarCartao(request.getNumeroCartao());
        validadorCarga.validarCarga(request, cartao);
        ResultadoConversao resultado = calcularConversao(request);

        CarregaCartaoResponse response = construirResponse(request, resultado);
        response.setMensagem("Simulacao de carga realizada com sucesso");
        return response;
    }

    public CarregaCartaoResponse confirmarCarga(CarregaCartaoRequest request) {
        Cartao cartao = cartaoService.buscarCartao(request.getNumeroCartao());
        validadorCarga.validarCarga(request, cartao);
        ResultadoConversao resultado = calcularConversao(request);

        cartao.creditar(request.getMoedaCarga(), resultado.getValorConvertido());
        cartaoRepository.save(cartao);

        ValorEmMoeda valorCarga = new ValorEmMoeda(request.getMoedaCarga(),
                request.getValorCargaRealBrasileiro());
        Operacao operacao = Operacao.carga(valorCarga, resultado);
        transacaoService.registrar(cartao, operacao);

        CarregaCartaoResponse response = construirResponse(request, resultado);
        response.setMensagem("Carga realizada com sucesso");
        return response;
    }

    private ResultadoConversao calcularConversao(CarregaCartaoRequest request) {
        BigDecimal taxa = conversaoMoeda.obterTaxaConversao(MOEDA_ORIGEM_CARGA, request.getMoedaCarga());
        DadosConversao dados = new DadosConversao(MOEDA_ORIGEM_CARGA, request.getMoedaCarga(),
                request.getValorCargaRealBrasileiro());
        BigDecimal valorConvertido = conversaoMoeda.converterComDados(dados);
        return new ResultadoConversao(valorConvertido, taxa);
    }

    private CarregaCartaoResponse construirResponse(CarregaCartaoRequest request,
                                                    ResultadoConversao resultado) {
        CarregaCartaoResponse response = new CarregaCartaoResponse();
        response.setNumeroCartao(request.getNumeroCartao());
        response.setMoeda(request.getMoedaCarga());
        response.setValorCargaRealBrasileiro(request.getValorCargaRealBrasileiro());
        response.setTaxaConversao(resultado.getTaxaConversao());
        response.setValorCargaMoedaDestino(resultado.getValorConvertido());
        return response;
    }
}
