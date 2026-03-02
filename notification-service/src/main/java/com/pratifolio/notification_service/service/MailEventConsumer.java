package com.pratifolio.notification_service.service;

import com.pratifolio.notification_service.model.dto.OrderResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MailEventConsumer {

    @RabbitListener(queues = "placed-order")
    public void handleOrderPlacedEvent(OrderResponse orderResponse) {

    }

}
