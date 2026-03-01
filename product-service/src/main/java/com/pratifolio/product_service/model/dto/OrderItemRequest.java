package com.pratifolio.product_service.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity,
        int price
) {
}
