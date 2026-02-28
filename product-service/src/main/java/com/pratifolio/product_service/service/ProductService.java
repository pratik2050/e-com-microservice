package com.pratifolio.product_service.service;

import com.pratifolio.product_service.model.Product;
import com.pratifolio.product_service.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();

        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    public ResponseEntity<?> getProductById(int id) {
        if (productRepo.findById(id).isEmpty()) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<Product>(productRepo.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<?> addProduct(Product product) {
        try {
            productRepo.save(product);
            return new ResponseEntity<>("Product Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error - Please try again | " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateProduct(Product product) {
        try {
            Product productToUpdate = productRepo.findById(product.getId()).get();

            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setProductDescription(product.getProductDescription());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setStock(product.getStock());

            productRepo.save(productToUpdate);

            return new ResponseEntity<>("Product Updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error - Please try again | " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteProduct(int id) {
        try {
            productRepo.deleteById(id);
            return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error - Please try again | " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
