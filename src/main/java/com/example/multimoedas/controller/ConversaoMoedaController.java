package com.example.multimoedas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.multimoedas.model.DadosConversao;
import com.example.multimoedas.service.ConversaoMoedaService;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/conversao")
@CrossOrigin(origins = "*")
public class ConversaoMoedaController {

    private final ConversaoMoedaService conversaoMoedaService;

    ConversaoMoedaController(ConversaoMoedaService conversaoMoedaService) {
        this.conversaoMoedaService = conversaoMoedaService;
    }

    @GetMapping("/taxa/{moedaOrigem}/{moedaDestino}")
    public ResponseEntity<?> obterTaxa(
            @PathVariable String moedaOrigem,
            @PathVariable String moedaDestino) {
        try {
            BigDecimal taxa = conversaoMoedaService.obterTaxaConversao(moedaOrigem, moedaDestino);
            String origem = moedaOrigem.toUpperCase();
            String destino = moedaDestino.toUpperCase();
            return ResponseEntity.ok(Map.of(
                "moedaOrigem", moedaOrigem,
                "moedaDestino", moedaDestino,
                "taxa", taxa,
                "descricao", "1 " + origem + " = " + taxa + " " + destino
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping("/converter/{valor}/{moedaOrigem}/{moedaDestino}")
    public ResponseEntity<?> converterMoeda(
            @PathVariable BigDecimal valor,
            @PathVariable String moedaOrigem,
            @PathVariable String moedaDestino) {
        try {
            BigDecimal valorConvertido = conversaoMoedaService.converterComDados(
                    new DadosConversao(moedaOrigem, moedaDestino, valor));
            String origem = moedaOrigem.toUpperCase();
            String destino = moedaDestino.toUpperCase();

            return ResponseEntity.ok(Map.of(
                "valorOriginal", valor,
                "moedaOrigem", moedaOrigem,
                "valorConvertido", valorConvertido,
                "moedaDestino", moedaDestino,
                "descricao", valor + " " + origem + " = " + valorConvertido + " " + destino
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}
