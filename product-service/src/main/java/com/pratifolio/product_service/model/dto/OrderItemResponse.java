package com.pratifolio.product_service.model.dto;

public record OrderItemResponse(
        int productId,
        int quantity,
        int totalPrice
) {
}
