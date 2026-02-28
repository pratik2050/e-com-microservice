package com.pratifolio.order_service.model.dto;

public record ProductDTO(
        int id,
        String productName,
        int stock,
        int price
) {
}
