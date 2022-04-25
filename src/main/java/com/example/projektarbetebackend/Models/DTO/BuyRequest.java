package com.example.projektarbetebackend.Models.DTO;

import lombok.Data;

@Data
public class BuyRequest {

    private Long customerId;
    private Long itemId;

    public BuyRequest(Long customerId, Long itemId) {
        this.customerId = customerId;
        this.itemId = itemId;
    }

    public BuyRequest(){}
}
