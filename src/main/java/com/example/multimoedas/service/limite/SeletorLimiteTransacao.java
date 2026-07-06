package com.example.multimoedas.service.limite;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

@Component
public class SeletorLimiteTransacao {

    private static final LocalTime INICIO_DIURNO = LocalTime.of(6, 0);
    private static final LocalTime FIM_DIURNO = LocalTime.of(22, 0);

    private final LimiteTransacaoStrategy limiteDiurno;
    private final LimiteTransacaoStrategy limiteNoturno;

    public SeletorLimiteTransacao(LimiteDiurnoStrategy limiteDiurno,
                                  LimiteNoturnoStrategy limiteNoturno) {
        this.limiteDiurno = limiteDiurno;
        this.limiteNoturno = limiteNoturno;
    }

    public BigDecimal limiteVigente() {
        return estrategiaVigente(LocalTime.now()).valorMaximoPermitido();
    }

    LimiteTransacaoStrategy estrategiaVigente(LocalTime hora) {
        boolean horarioDiurno = !hora.isBefore(INICIO_DIURNO) && hora.isBefore(FIM_DIURNO);
        return horarioDiurno ? limiteDiurno : limiteNoturno;
    }
}
