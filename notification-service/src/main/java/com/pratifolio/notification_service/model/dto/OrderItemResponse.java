package com.pratifolio.notification_service.model.dto;

public record OrderItemResponse(
        int productId,
        int quantity,
        int totalPrice
) {
}
