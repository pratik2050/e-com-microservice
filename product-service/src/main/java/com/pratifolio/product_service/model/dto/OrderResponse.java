package com.pratifolio.product_service.model.dto;

import java.util.List;

public record OrderResponse(
        String orderId,
        int customerId,
        String status,
        List<OrderItemResponse> items
) {
}
