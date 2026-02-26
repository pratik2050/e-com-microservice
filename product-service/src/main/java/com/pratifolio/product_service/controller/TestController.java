package com.pratifolio.product_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("test-product")
    public String testProduct() {
        return "Product Testing Successful";
    }
}
