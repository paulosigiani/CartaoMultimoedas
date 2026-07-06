package com.example.multimoedas.service.validation;

import org.springframework.stereotype.Component;

import com.example.multimoedas.dto.CarregaCartaoRequest;
import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.service.limite.SeletorLimiteTransacao;

@Component
public class ValidadorCarga {

    private final RegrasBasicasValidacao regras;
    private final SeletorLimiteTransacao seletorLimite;

    public ValidadorCarga(RegrasBasicasValidacao regras, SeletorLimiteTransacao seletorLimite) {
        this.regras = regras;
        this.seletorLimite = seletorLimite;
    }

    public void validarCarga(CarregaCartaoRequest request, Cartao cartao) {
        regras.validarCarga(request, cartao);
        regras.validarLimite(request.getValorCargaRealBrasileiro(), seletorLimite.limiteVigente());
    }
}
