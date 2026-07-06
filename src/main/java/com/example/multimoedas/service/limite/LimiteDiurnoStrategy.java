package com.example.multimoedas.service.limite;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class LimiteDiurnoStrategy implements LimiteTransacaoStrategy {

    private static final BigDecimal LIMITE_DIURNO = new BigDecimal("10000");

    @Override
    public BigDecimal valorMaximoPermitido() {
        return LIMITE_DIURNO;
    }
}
