package com.okten.springdemo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okten.springdemo.dto.ProductDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUploadService {

    private final ProductService productService;

    private final ObjectMapper objectMapper;

    @Transactional
    @SneakyThrows
    public List<ProductDto> uploadProducts(MultipartFile file) {
        List<ProductDto> productsToCreate = objectMapper.readValue(file.getBytes(), new TypeReference<>() {
        });

//
//        return productsToCreate
//                .stream()
//                .map(productService::createProduct)
//                .toList();
        return productService.createAll(productsToCreate);
    }
}
