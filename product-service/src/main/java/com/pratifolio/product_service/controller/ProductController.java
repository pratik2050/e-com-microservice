package com.pratifolio.product_service.controller;

import com.pratifolio.product_service.model.Product;
import com.pratifolio.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("allProducts")
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("getProduct/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
