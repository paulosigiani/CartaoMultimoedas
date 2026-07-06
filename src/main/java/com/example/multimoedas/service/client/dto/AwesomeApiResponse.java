package com.example.multimoedas.service.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AwesomeApiResponse {
    
    @JsonProperty("bid")
    private String bid;
    
    public AwesomeApiResponse() {}
    
    public void setBid(String bid) { this.bid = bid; }
    
    public BigDecimal getBidAsBigDecimal() {
        if (bid == null || bid.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(bid);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }
}
