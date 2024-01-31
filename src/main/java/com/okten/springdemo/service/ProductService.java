package com.okten.springdemo.service;

import com.okten.springdemo.dto.ProductDto;
import com.okten.springdemo.entity.Product;
import com.okten.springdemo.mapper.ProductMapper;
import com.okten.springdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final ProductMapper productMapper;

    public List<ProductDto> getProducts(String name) {
        return Optional
                .ofNullable(name)
                .map(repository::findByName)
                .orElseGet(repository::findAll)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.fromDto(productDto);
        Product createdProduct = repository.save(product);
        return productMapper.toDto(createdProduct);
    }

    public Optional<ProductDto> getProductById(Long id) {
        return repository.findById(id).map(productMapper::toDto);
    }
}
