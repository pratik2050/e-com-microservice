package com.pratifolio.product_service.model.dto;

import java.util.List;

public record OrderRequest(
        int customerId,
        String email,
        List<OrderItemRequest> items
) {
}
