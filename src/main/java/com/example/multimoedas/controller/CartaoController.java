package com.example.multimoedas.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.multimoedas.dto.*;
import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.model.Transacao;
import com.example.multimoedas.service.CargaService;
import com.example.multimoedas.service.CartaoService;
import com.example.multimoedas.service.SaqueService;
import com.example.multimoedas.service.TransacaoService;

import java.util.List;

@RestController
@RequestMapping("/cartao")
@CrossOrigin(origins = "*")
public class CartaoController {

    private final CartaoService cartaoService;

    private final CargaService cargaService;

    private final SaqueService saqueService;

    private final TransacaoService transacaoService;

    CartaoController(CartaoService cartaoService, SaqueService saqueService,
                     CargaService cargaService, TransacaoService transacaoService) {
        this.cartaoService = cartaoService;
        this.saqueService = saqueService;
        this.cargaService = cargaService;
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<CriarCartaoResponse> criarCartao(@Valid @RequestBody CriarCartaoRequest request) {
        CriarCartaoResponse response = cartaoService.criarCartao(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<Cartao> consultarCartao(@PathVariable String numeroCartao) {
        Cartao cartao = cartaoService.buscarCartao(numeroCartao);
        return ResponseEntity.ok(cartao);
    }

    @GetMapping("/{numeroCartao}/transacoes")
    public ResponseEntity<List<Transacao>> historicoTransacoes(@PathVariable String numeroCartao) {
        List<Transacao> transacoes = transacaoService.obterHistorico(numeroCartao);
        return ResponseEntity.ok(transacoes);
    }

    @PostMapping("/carga/simular")
    public ResponseEntity<CarregaCartaoResponse> simularCarga(@Valid @RequestBody CarregaCartaoRequest request) {
        CarregaCartaoResponse response = cargaService.simularCarga(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/carga/confirmar")
    public ResponseEntity<CarregaCartaoResponse> confirmarCarga(@Valid @RequestBody CarregaCartaoRequest request) {
        CarregaCartaoResponse response = cargaService.confirmarCarga(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saque/simular")
    public ResponseEntity<SaqueCartaoResponse> simularSaque(@Valid @RequestBody SaqueCartaoRequest request) {
        SaqueCartaoResponse response = saqueService.simularSaque(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saque/confirmar")
    public ResponseEntity<SaqueCartaoResponse> confirmarSaque(@Valid @RequestBody SaqueCartaoRequest request) {
        SaqueCartaoResponse response = saqueService.confirmarSaque(request);
        return ResponseEntity.ok(response);
    }
}
