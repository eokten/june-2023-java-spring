package com.okten.springdemo.service;

import com.okten.springdemo.dto.ProductDto;
import com.okten.springdemo.entity.Product;
import com.okten.springdemo.mapper.ProductMapper;
import com.okten.springdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final ProductMapper productMapper;

    private final MailService mailService;

    @Value("${spring.mail.username}")
    private String mailFrom;

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
        sendProductCreatedMail(createdProduct);
        return productMapper.toDto(createdProduct);
    }

    public List<ProductDto> createAll(List<ProductDto> productsDto) {
        List<Product> products = productsDto.stream().map(productMapper::fromDto).toList();
        List<Product> createdProducts = repository.saveAll(products);
        createdProducts.forEach(this::sendProductCreatedMail);
        return createdProducts.stream().map(productMapper::toDto).toList();
    }

    private void sendProductCreatedMail(Product product) {
        mailService.sendEmail(mailFrom, "New product created", "Product %s was created with price %s".formatted(product.getName(), product.getPrice()));
    }

    public Optional<ProductDto> getProductById(Long id) {
        return repository.findById(id).map(productMapper::toDto);
    }
}
