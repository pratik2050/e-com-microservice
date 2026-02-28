package com.pratifolio.order_service.model.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        int productId,
        int quantity,
        BigDecimal totalPrice
) {
}
