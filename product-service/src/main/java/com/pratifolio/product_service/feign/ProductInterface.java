package com.pratifolio.product_service.feign;

import com.pratifolio.product_service.model.dto.OrderRequest;
import com.pratifolio.product_service.model.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ORDER-SERVICE")
public interface ProductInterface {

    @PostMapping("order/place-order")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest);

}
