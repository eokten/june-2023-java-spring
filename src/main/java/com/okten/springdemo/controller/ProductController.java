package com.okten.springdemo.controller;

import com.okten.springdemo.dao.ProductDao;
import com.okten.springdemo.entity.Product;
import com.okten.springdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

//    private final ProductRepository productDao;
    private final ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam String name) {
        if (name != null) {
            return ResponseEntity.ok(productRepository.findByNameLike(name));
        } else {
//        return ResponseEntity.ok(productDao.getProducts());
            return ResponseEntity.ok(productRepository.findAll());
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
//        Product createdProduct = productDao.saveProduct(product);
        Product createdProduct = productRepository.save(product);
        return ResponseEntity.ok().body(createdProduct);
    }
}
