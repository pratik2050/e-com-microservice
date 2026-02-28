package com.pratifolio.order_service.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity,
        int price
) {
}
