package com.pratifolio.order_service.service;

import com.pratifolio.order_service.model.Order;
import com.pratifolio.order_service.model.OrderItem;
import com.pratifolio.order_service.model.dto.OrderItemRequest;
import com.pratifolio.order_service.model.dto.OrderItemResponse;
import com.pratifolio.order_service.model.dto.OrderRequest;
import com.pratifolio.order_service.model.dto.OrderResponse;
import com.pratifolio.order_service.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public ResponseEntity<?> placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : orderRequest.items()) {
//            Product product = productRepo.findById(itemRequest.productId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
//
//            product.setStockQuantity(product.getStockQuantity() - itemRequest.quantity());
//            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .quantity(itemRequest.quantity())
                    .totalPrice(BigDecimal.valueOf(itemRequest.price()*itemRequest.quantity()))
                    .order(order)
                    .build();

            orderItems.add(orderItem);

        }

        order.setOrderItems(orderItems);

        Order savedOrder = orderRepo.save(order);

        List<OrderItemResponse> orderItemResponses = new ArrayList<>();

        for (OrderItem itemResponse : order.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    itemResponse.getProductId(),
                    itemResponse.getQuantity(),
                    itemResponse.getTotalPrice()
            );

            orderItemResponses.add(orderItemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getCustomerName(),
                savedOrder.getEmail(),
                savedOrder.getStatus(),
                orderItemResponses
        );

        return null;
    }
}
