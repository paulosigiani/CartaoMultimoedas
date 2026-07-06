package com.example.multimoedas.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.multimoedas.service.client.dto.AwesomeApiResponse;

import java.util.Map;

@FeignClient(name = "awesomeApiClient", url = "https://economia.awesomeapi.com.br/last/")
public interface AwesomeApiClient {
    
    @GetMapping("/{parMoedas}")
    Map<String, AwesomeApiResponse> getTaxaConversao(@PathVariable("parMoedas") String parMoedas);
}
