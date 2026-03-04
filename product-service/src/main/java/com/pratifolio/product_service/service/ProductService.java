package com.pratifolio.product_service.service;

import com.pratifolio.product_service.feign.OrderInterface;
import com.pratifolio.product_service.model.Product;
import com.pratifolio.product_service.model.dto.OrderRequest;
import com.pratifolio.product_service.model.dto.OrderResponse;
import com.pratifolio.product_service.model.dto.ProductDTO;
import com.pratifolio.product_service.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderInterface productInterface;

    @Cacheable(value = "allProductCache", key = "allProducts")
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();

        return allProducts;
    }

    @Cacheable(value = "productCache", key = "#id")
    public ResponseEntity<?> getProductById(int id) {
        if (productRepo.findById(id).isEmpty()) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<Product>(productRepo.findById(id).get(), HttpStatus.OK);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "allProductCache", key = "allProducts")
            },
            put = {
                    @CachePut(value = "productCache", key = "#product.id")
            }
    )
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

    @CacheEvict(value = "allProductCache", key = "allProducts")
    public ResponseEntity<?> deleteProduct(int id) {
        try {
            productRepo.deleteById(id);
            return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error - Please try again | " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<OrderResponse> placeOrder(OrderRequest orderRequest) {
        ResponseEntity<OrderResponse> response = productInterface.placeOrder(orderRequest);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return ResponseEntity.ok(response.getBody());
        }

        return ResponseEntity.status(response.getStatusCode())
                .body(null);

    }

    public ResponseEntity<List<ProductDTO>> getProductDTO(List<Integer> ids) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {
            Product product = productRepo.findById(ids.get(i)).get();

            ProductDTO productDTO = new ProductDTO(
                    product.getProductName(),
                    product.getPrice()
            );

            productDTOList.add(productDTO);
        }

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }
}
