package com.pratifolio.notification_service.feign;

import com.pratifolio.notification_service.model.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("PRODUCT-SERVICE")
public interface ProductInterface {
    @PostMapping("product/getProduct")
    public ResponseEntity<List<ProductDTO>> getProductDTO(@RequestBody List<Integer> ids);
}
