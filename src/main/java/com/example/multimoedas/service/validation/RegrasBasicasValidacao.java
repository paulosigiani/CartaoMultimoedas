package com.example.multimoedas.service.validation;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.multimoedas.dto.CarregaCartaoRequest;
import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.model.StatusCartao;
import com.example.multimoedas.model.ValorEmMoeda;

@Component
public class RegrasBasicasValidacao {

    public void validarCarga(CarregaCartaoRequest request, Cartao cartao) {
        validarCartaoAtivo(cartao);
        validarMoeda(request.getMoedaCarga());
        validarValor(request.getValorCargaRealBrasileiro());
    }

    public void validarSaque(Cartao cartao, ValorEmMoeda entrada) {
        validarCartaoAtivo(cartao);
        validarMoeda(entrada.getMoeda());
        validarValor(entrada.getValor());
        validarSaldoSuficiente(cartao, entrada);
    }

    public void validarLimite(BigDecimal valor, BigDecimal limiteVigente) {
        if (valor.compareTo(limiteVigente) > 0) {
            throw new RuntimeException("Valor " + valor
                    + " excede o limite permitido no horario atual: " + limiteVigente);
        }
    }

    private void validarCartaoAtivo(Cartao cartao) {
        if (cartao.getStatus() != StatusCartao.ATIVO) {
            throw new RuntimeException("Cartao nao esta ativo: " + cartao.getStatus().getDescricao());
        }
    }

    private void validarMoeda(String moeda) {
        if (moeda == null || moeda.trim().isEmpty()) {
            throw new IllegalArgumentException("Moeda nao pode ser vazia");
        }
    }

    private void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
    }

    private void validarSaldoSuficiente(Cartao cartao, ValorEmMoeda entrada) {
        BigDecimal saldoAtual = cartao.saldoDe(entrada.getMoeda());
        if (saldoAtual.compareTo(entrada.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente. Saldo atual: " + saldoAtual + " " + entrada.getMoeda());
        }
    }
}
