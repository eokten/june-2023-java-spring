package com.okten.springdemo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.okten.springdemo.dto.ProductDto;
import com.okten.springdemo.service.ProductService;
import com.okten.springdemo.service.ProductUploadService;
import com.okten.springdemo.util.View;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductUploadService productUploadService;

    @JsonView(View.Internal.class)
    @GetMapping("/internal/products")
    public ResponseEntity<List<ProductDto>> getProductsInternal(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(productService.getProducts(name));
    }

    @JsonView(View.External.class)
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(productService.getProducts(name));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProducts(@PathVariable Long id) {
        return ResponseEntity.of(productService.getProductById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok().body(productService.createProduct(productDto));
    }

    @PostMapping("/products/upload")
    public ResponseEntity<List<ProductDto>> uploadProducts(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(productUploadService.uploadProducts(file));
    }
}
