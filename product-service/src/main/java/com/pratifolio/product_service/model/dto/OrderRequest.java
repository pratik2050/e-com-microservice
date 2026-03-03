package com.pratifolio.product_service.model.dto;

import java.util.List;

public record OrderRequest(
        int customerId,
        List<OrderItemRequest> items
) {
}
