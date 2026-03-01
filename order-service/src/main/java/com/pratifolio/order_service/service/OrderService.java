package com.pratifolio.order_service.service;

import com.pratifolio.order_service.model.Order;
import com.pratifolio.order_service.model.OrderItem;
import com.pratifolio.order_service.model.dto.OrderItemRequest;
import com.pratifolio.order_service.model.dto.OrderItemResponse;
import com.pratifolio.order_service.model.dto.OrderRequest;
import com.pratifolio.order_service.model.dto.OrderResponse;
import com.pratifolio.order_service.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public ResponseEntity<?> placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerId(orderRequest.customerId());
        order.setStatus("NEW");

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : orderRequest.items()) {
            OrderItem orderItem = OrderItem.builder()
                    .productId(itemRequest.productId())
                    .quantity(itemRequest.quantity())
                    .totalPrice(itemRequest.price()*itemRequest.quantity())
                    .order(order)
                    .build();

            orderItems.add(orderItem);

        }

        order.setOrderItems(orderItems);

        Random random = new Random();
//        int paymentSuccess = random.nextInt(1, 4);
        int paymentSuccess = 2;

        if (paymentSuccess == 2) {
            order.setStatus("PLACED");
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
                    savedOrder.getCustomerId(),
                    savedOrder.getStatus(),
                    orderItemResponses
            );

            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Error Please try again", HttpStatus.BAD_REQUEST);
    }
}
