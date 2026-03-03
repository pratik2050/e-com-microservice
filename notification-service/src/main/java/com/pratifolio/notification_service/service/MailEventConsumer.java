package com.pratifolio.notification_service.service;

import com.pratifolio.notification_service.feign.ProductInterface;
import com.pratifolio.notification_service.feign.UserInterface;
import com.pratifolio.notification_service.model.dto.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailEventConsumer {

    @Autowired
    private ProductInterface productInterface;

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private MailService mailService;

    @RabbitListener(queues = "order-placed")
    public void handleOrderPlacedEvent(OrderResponse orderResponse) {
        try {
            String to = null; String subject = null; String body = "The following orders were placed from your account: ";

            ResponseEntity<UserDTO> userDTOResponseEntity = userInterface.getUserDetails(orderResponse.customerId());

            if (userDTOResponseEntity.getStatusCode().is2xxSuccessful()) {
                UserDTO userDTO = userDTOResponseEntity.getBody();
                to = userDTO.email();

                subject = "Order Details || Status: " + orderResponse.status() + " - " + userDTO.username();
            }

            List<Integer> productIds = new ArrayList<>();

            for (OrderItemResponse itemResponse : orderResponse.items()) {
                productIds.add(itemResponse.productId());
            }

            ResponseEntity<List<ProductDTO>> productDTOResponseEntity = productInterface.getProductDTO(productIds);

            if (productDTOResponseEntity.getStatusCode().is2xxSuccessful()) {
                List<ProductDTO> productDTOList = productDTOResponseEntity.getBody();
                body = body + productDTOList;
            }

            MailTemplate mailTemplate = new MailTemplate(
                    to,
                    subject,
                    body
            );

            mailService.sendMail(mailTemplate);
        } catch (Exception e) {
            System.out.println("Exception Occur in " + e.getMessage());
        }
    }

}
