package com.pratifolio.product_service.model.dto;

public record ProductDTO(
        int id,
        String productName,
        int stock,
        int price
) {
}
