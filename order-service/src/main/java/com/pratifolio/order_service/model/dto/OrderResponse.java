package com.pratifolio.order_service.model.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        String orderId,
        String customerName,
        String email,
        String status,
        List<OrderItemResponse> items
) {
}
