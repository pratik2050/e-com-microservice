package com.pratifolio.product_service.controller;

import com.pratifolio.product_service.model.Product;
import com.pratifolio.product_service.model.dto.OrderRequest;
import com.pratifolio.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();

        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("getFullProduct/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Product Not Found - Try Again", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);

        if (addedProduct != null) {
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>("Failed to add product", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @PostMapping("order-product")
    public ResponseEntity<?> orderProduct(@RequestBody OrderRequest orderRequest) {
        return productService.placeOrder(orderRequest);
    }

    @PostMapping("getProduct")
    public ResponseEntity<?> getProductDTO(@RequestBody List<Integer> ids) {
        return productService.getProductDTO(ids);
    }

}
